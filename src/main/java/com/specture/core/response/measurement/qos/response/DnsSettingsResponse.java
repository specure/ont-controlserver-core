package com.specture.core.response.measurement.qos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DnsSettingsResponse {
    private String concurrencyGroup;
    private String resolver;
    private String record;
    private String host;
    private String serverPort;
    private String qosTestUid;
    private String serverAddr;
    private String timeout;
}
