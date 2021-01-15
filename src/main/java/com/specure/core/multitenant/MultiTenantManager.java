package com.specure.core.multitenant;

import com.specure.core.config.ClientTenantConfig;
import com.specure.core.config.ElasticIndexTenantConfig;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Service
public class MultiTenantManager {

    private final ThreadLocal<String> currentTenant = new ThreadLocal<>();
    private final Map<Object, Object> tenantDataSources = new ConcurrentHashMap<>();

    private ConnectionManager connectionManager;
    private AbstractRoutingDataSource multiTenantDataSource;
    private ElasticIndexTenantConfig elasticIndexTenantConfig;
    private ClientTenantConfig clientTenantConfig;

    public MultiTenantManager(ElasticIndexTenantConfig elasticIndexTenantConfig, ConnectionManager connectionManager, ClientTenantConfig clientTenantConfig) {
        this.elasticIndexTenantConfig = elasticIndexTenantConfig;
        this.connectionManager = connectionManager;
        this.clientTenantConfig = clientTenantConfig;
    }

    @Bean
    public DataSource dataSource() {
        multiTenantDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                return currentTenant.get();
            }
        };
        multiTenantDataSource.setTargetDataSources(tenantDataSources);
        multiTenantDataSource.setDefaultTargetDataSource(connectionManager.buildDefaultDataSource());
        multiTenantDataSource.afterPropertiesSet();
        return multiTenantDataSource;
    }

    public String addTenant(String tenantId) {
        if (!StringUtils.isBlank(tenantId) && !isTenantExist(tenantId)) {
            DataSource dataSource = connectionManager.buildDataSource(tenantId);
            try (Connection selectOne = connectionManager.checkConnection(dataSource)) {
                tenantDataSources.put(tenantId, dataSource);
                multiTenantDataSource.afterPropertiesSet();
            } catch (SQLException message) {
                log.error(String.format("Unable to connect ot db %s: %s", tenantId, message));
            }
        }
        return tenantId;
    }

    public void setCurrentTenant(String client) {
        String tenant = clientTenantConfig.getClientTenantMapping().get(client);

        if (Objects.isNull(tenant) || !tenantDataSources.containsKey(client)) {
            client = clientTenantConfig.getDefaultTenant();
        }
        currentTenant.set(client);
    }

    public String getCurrentTenant() {
        String tenant = Optional.ofNullable(currentTenant.get())
                .orElse(clientTenantConfig.getDefaultTenant());
        return tenant;
    }

    public String getCurrentTenantBasicIndex() {
        return elasticIndexTenantConfig.getBasicTenantIndexes().get(getCurrentTenant());
    }

    public String getCurrentTenantQosIndex() {
        return elasticIndexTenantConfig.getBasicQosTenantIndexes().get(getCurrentTenant());
    }

    public boolean isTenantExist(String tenantId) {
        return tenantDataSources.containsKey(tenantId);
    }
}