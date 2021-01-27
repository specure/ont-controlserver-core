package com.specure.core.service.impl;

import com.specure.core.enums.MeasurementStatus;
import com.specure.core.mapper.OpenDataMapper;
import com.specure.core.model.OpenData;
import com.specure.core.repository.OpenDataRepository;
import com.specure.core.service.OpenDataService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OpenDataServiceImplTest {

    @MockBean private OpenDataRepository openDataRepository;
    @MockBean private OpenDataMapper openDataMapper;

    @Captor private ArgumentCaptor<Timestamp> fromCaptor;
    @Captor private ArgumentCaptor<Timestamp> toCaptor;

    private OpenDataService openDataService;

    @Before
    public void setUp(){
        Resource licenseResource = new ClassPathResource("license/LICENSE-CC-BY-4.0.txt");
        openDataService = new OpenDataServiceImpl(openDataRepository, openDataMapper);
        ReflectionTestUtils.setField(openDataService, "licenseResource", licenseResource);
    }
    @Test
    public void getOpenDataMonthlyExport_whenInvokeWithLastMonth_expectFirstMonthNextYear() {

        Timestamp from = Timestamp.valueOf("2020-12-01 00:00:00.0");
        Timestamp to = Timestamp.valueOf("2021-01-01 00:00:00.0");


        when(openDataRepository.findAllByTimeBetweenAndStatus(eq(from), eq(to), eq(MeasurementStatus.FINISHED)))
                .thenReturn(List.of(OpenData.builder().build()));

        openDataService.getOpenDataMonthlyExport(2020, 12, "csv");

        verify(openDataRepository).findAllByTimeBetweenAndStatus(fromCaptor.capture(), toCaptor.capture(), eq(MeasurementStatus.FINISHED));

        Assert.assertEquals(from, fromCaptor.getValue());
        Assert.assertEquals(to, toCaptor.getValue());
    }

    @Test
    public void getOpenDataMonthlyExport_whenInvokeWithNotLastMonth_expectNextMonthThisYear() {

        Timestamp from = Timestamp.valueOf("2020-10-01 00:00:00.0");
        Timestamp to = Timestamp.valueOf("2020-11-01 00:00:00.0");


        when(openDataRepository.findAllByTimeBetweenAndStatus(eq(from), eq(to), eq(MeasurementStatus.FINISHED)))
                .thenReturn(List.of(OpenData.builder().build()));

        openDataService.getOpenDataMonthlyExport(2020, 10, "csv");

        verify(openDataRepository).findAllByTimeBetweenAndStatus(fromCaptor.capture(), toCaptor.capture(), eq(MeasurementStatus.FINISHED));

        Assert.assertEquals(from, fromCaptor.getValue());
        Assert.assertEquals(to, toCaptor.getValue());
    }
}