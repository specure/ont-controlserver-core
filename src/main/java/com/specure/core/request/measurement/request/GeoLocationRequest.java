package com.specure.core.request.measurement.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
public class GeoLocationRequest {
    private Double geoLat;
    private Double geoLong;
    private Double accuracy;
    private Double altitude;
    private Double bearing;
    private Double speed;
    @JsonProperty("tstamp")
    private Date timestamp;
    private String provider;
}
