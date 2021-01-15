package com.specure.core;

import com.specure.core.enums.ClientType;
import com.specure.core.model.Provider;

import java.util.HashMap;
import java.util.Map;

public interface TestConstants {
    String DEFAULT_OPEN_TEST_UUID = "O6136c67b-c1ad-4c2f-9c86-39328c5dbf35";
    Float DEFAULT_PING = 1.2f;
    Integer DEFAULT_SIGNAL_STRENGTH = -16;
    Integer DEFAULT_PAGE = 1;
    Integer DEFAULT_SIZE = 10;
    String DEFAULT_SORT = "measurementDate,desc";
    String DEFAULT_USER_AGENT = "agent";
    String DEFAULT_X_REAL_IP = "real_ip";
    String DEFAULT_X_FORWARDED = "real_forwrded_ip";
    String DEFAULT_SORT_PROPERTY = "measurementDate";
    Long DEFAULT_ID = 2L;
    Long DEFAULT_PROVIDER_ID = 6L;
    Provider DEFAULT_PROVIDER = Provider.builder()
            .name("BATELCO")
            .build();
    String DEFAULT_PROBE_ID = "DEFAULT_PROBE_ID";
    String DEFAULT_ADVERTISED_NAME = "DEFAULT_ADVERTISED_NAME";
    String DEFAULT_PORT = "DEFAULT_PORT";
    int DEFAULT_REMOTE_PORT = 1;
    String DEFAULT_SITE_ADDRESS = "DEFAULT_SITE_ADDRESS";
    String DEFAULT_IP_FOR_PROVIDER = "176.37.47.123";
    String DEFAULT_PROBE_PORT_INT = "sim2";
    String DEFAULT_LANGUAGE = "en";
    String DEFAULT_TIME_ZONE = "Europe/Prague";
    String DEFAULT_UUID = "540607f2-2a43-4019-af89-fa6d42b9a14f";
    Long DEFAULT_MEASUREMENT_SERVER_ID = 1L;
    String DEFAULT_TEST_RESULT_URL = "http://localhost:8080/testRequest";
    String DEFAULT_QOS_TEST_RESULT_URL = "http://localhost:8080/QosTestRequest";
    Integer DEFAULT_TEST_DURATION = 5;
    String DEFAULT_MEASUREMENT_SERVER_NAME = "EKIP Server (Podgorica)";
    Integer DEFAULT_TEST_WEIGHT = 0;
    String DEFAULT_MEASUREMENT_SERVER_ADDRESS = "ekip-m01.customers.nettest.org";
    Integer DEFAULT_NUM_TEST_THREADS = 20;
    Integer DEFAULT_MEASUREMENT_SERVER_PORT = 5232;
    Integer DEFAULT_MEASUREMENT_SERVER_SSL_PORT = 443;
    Boolean DEFAULT_IS_MEASUREMENT_SERVER_ENCRYPTED = true;
    String DEFAULT_MEASUREMENT_SERVER_TOKEN = "22c4b536-ab0f-4fcc-b5eb-9b8052335438_1589282586_2/jcdrb5OeSeaS9n2tXWOXBZSM0=";
    String DEFAULT_MEASUREMENT_SERVER_SECRET_KEY = "22c4b536-ab0f-4fcc-b5eb-9b8052335438_1589282586_2/jcdrb5OeSeaS9n2tXWOXBZSM0=";
    Integer DEFAULT_TEST_NUM_PINGS = 10;
    Long DEFAULT_TEST_ID = 12615919L;
    String DEFAULT_TOKEN = "f934d7b3-ce36-44c0-9a80-1d3fecb674a0_1589301534_8ar2pr6yrs2pdeH3c0Xjp357rRw=";
    Integer DEFAULT_SPEED_DOWNLOAD = 7858;
    Integer DEFAULT_SPEED_UPLOAD = 1660;
    Integer DEFAULT_LTE_RSRP = -103;
    Integer DEFAULT_LTE_RSRQ = -16;
    String DEFAULT_VOIP_RESULT_PACKET_LOSS = "1.0";
    String DEFAULT_VOIP_RESULT_JITTER = "11.82";
    String DEFAULT_DEVICE = "0009";
    Integer DEFAULT_NETWORK_TYPE = 13;
    String DEFAULT_TAG = "sim2";
    Long DEFAULT_PIN_MEDIAN = 32457927L;
    String DEFAULT_HOST = "localhost";
    Long DEFAULT_VOIP_OBJECTIVE_CALL_DURATION = 2000000000L;
    Long DEFAULT_VOIP_OBJECTIVE_DELAY = 20000000L;
    Long DEFAULT_VOIP_RESULT_IN_MEAN_JITTER = 5483404L;
    Long DEFAULT_VOIP_RESULT_OUT_MEAN_JITTER = 15239686L;
    Long DEFAULT_VOIP_RESULT_OUT_NUM_PACKETS = 99L;
    Long DEFAULT_VOIP_RESULT_IN_NUM_PACKETS = 100L;
    Long DEFAULT_BYTES_DOWNLOAD = 3014656L;
    Long DEFAULT_N_SEC_DOWNLOAD = 5003543224L;
    Long DEFAULT_BYTES_UPLOAD = 1040384L;
    Long DEFAULT_N_SEC_UPLOAD = 5314435780L;
    Long DEFAULT_SLOT_WINDOW = 5L;
    String DEFAULT_CITY = "London";
    String DEFAULT_EMAIL = "test@email.com";
    String DEFAULT_PROVIDER_NAME = "BATELCO";
    String DEFAULT_GEO_PROVIDER_NAME = "LANETUA-AS, UA";
    ClientType DEFAULT_CLIENT = ClientType.RMBTws;
    Map<String, String> DEFAULT_HEADER = new HashMap<>();
    Long DEFAULT_ASN = 39608L;
    long DEFAULT_TODAY_AMOUNT_OF_MEASUREMENTS = 123L;
    long DEFAULT_THIS_WEEK_AMOUNT_OF_MEASUREMENTS = 1234L;
    long DEFAULT_THIS_MONTH_AMOUNT_OF_MEASUREMENTS = 12345L;
    long DEFAULT_THIS_YEAR_AMOUNT_OF_MEASUREMENTS = 123456L;
    String DEFAULT_PACKAGE_TYPE_STRING = "Mobile prepaid";
    String DEFAULT_AD_HOC_CAMPAIGN = "Default AdHoc Campaign";
    String DEFAULT_STRING = "some string";
}