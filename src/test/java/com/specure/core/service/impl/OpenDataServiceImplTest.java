package com.specure.core.service.impl;

import com.specure.core.enums.DigitalSeparator;
import com.specure.core.model.OpenDataExport;
import com.specure.core.model.OpenDataExportList;
import com.specure.core.service.OpenDataInputStreamService;
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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;
import java.util.List;

import static com.specure.core.TestConstants.DEFAULT_OPEN_DATA_FILE_EXTENSION;
import static com.specure.core.service.impl.OpenDataInputStreamServiceImpl.LABEL_DATA_SOURCE;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class OpenDataServiceImplTest {

    @MockBean private OpenDataInputStreamService openDataRepository;

    @Captor private ArgumentCaptor<Timestamp> fromCaptor;
    @Captor private ArgumentCaptor<Timestamp> toCaptor;

    private OpenDataService openDataService;

    @Before
    public void setUp(){
        when(openDataRepository.getSourceLabel()).thenReturn(LABEL_DATA_SOURCE);
        doReturn(OpenDataExport.class).when(openDataRepository).getOpenDataClass();
        Resource licenseResource = new ClassPathResource("license/LICENSE-CC-BY-4.0.txt");
        openDataService = new OpenDataServiceImpl(List.of(openDataRepository));
        ReflectionTestUtils.setField(openDataService, "licenseResource", licenseResource);
    }

    @Test
    public void getOpenDataMonthlyExport_whenInvokeWithNotLastMonth_expectLastDayOfMonth() {

        Timestamp from = Timestamp.valueOf("2020-10-01 00:00:00.0");
        Timestamp to = Timestamp.valueOf("2020-10-31 00:00:00.0");

        List<OpenDataExport> data = List.of(OpenDataExport.builder().build());
        OpenDataExportList<?> openData = new OpenDataExportList<>(data);
        doReturn(openData).when(openDataRepository).getAllByTimeBetweenWithSeparator(eq(from), eq(to), eq(DigitalSeparator.COMMA), any());


        openDataService.getOpenDataMonthlyExport(2020, 10, DEFAULT_OPEN_DATA_FILE_EXTENSION, DigitalSeparator.COMMA, ';', null, LABEL_DATA_SOURCE);

        verify(openDataRepository)
                .getAllByTimeBetweenWithSeparator(fromCaptor.capture(), toCaptor.capture(), eq(DigitalSeparator.COMMA), any());

        Assert.assertEquals(from, fromCaptor.getValue());
        Assert.assertEquals(to, toCaptor.getValue());
    }
    @Test
    public void getOpenDataMonthlyExport_whenInvokeWithLastMonth_expectLastDayOfYear() {

        Timestamp from = Timestamp.valueOf("2020-12-01 00:00:00.0");
        Timestamp to = Timestamp.valueOf("2020-12-31 00:00:00.0");

        List<OpenDataExport> data = List.of(OpenDataExport.builder().build());
        OpenDataExportList<?> openData = new OpenDataExportList<>(data);
        doReturn(openData).when(openDataRepository).getAllByTimeBetweenWithSeparator(eq(from), eq(to), eq(DigitalSeparator.COMMA), any());

        openDataService.getOpenDataMonthlyExport(2020, 12, DEFAULT_OPEN_DATA_FILE_EXTENSION, DigitalSeparator.COMMA, ';', null, LABEL_DATA_SOURCE);

        verify(openDataRepository)
                .getAllByTimeBetweenWithSeparator(fromCaptor.capture(), toCaptor.capture(), eq(DigitalSeparator.COMMA), any());

        Assert.assertEquals(from, fromCaptor.getValue());
        Assert.assertEquals(to, toCaptor.getValue());
    }
    @Test
    public void getOpenDataFullExport_whenInvokeWithXML_expectCorrectAnswer() {
        List<OpenDataExport> data = List.of(OpenDataExport.builder().build());
        var listData = new OpenDataExportList<>(data);
        doReturn(listData)
                .when(openDataRepository)
                .getAllOpenDataWithSeparator(eq(DigitalSeparator.COMMA), any());

        var result = openDataService.getOpenDataFullExport("xml", DigitalSeparator.COMMA, ';', null, LABEL_DATA_SOURCE);

        Assert.assertNotNull(result);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    @Test
    public void getOpenDataFullExport_whenInvokeWithJson_expectCorrectAnswer() {
        List<OpenDataExport> data = List.of(OpenDataExport.builder().build());
        var listData = new OpenDataExportList<>(data);
        doReturn(listData).when(openDataRepository).getAllOpenDataWithSeparator(eq(DigitalSeparator.COMMA), any());

        var result = openDataService.getOpenDataFullExport("json", DigitalSeparator.COMMA, ';', null, LABEL_DATA_SOURCE);

        Assert.assertNotNull(result);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}
