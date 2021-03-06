package com.specure.core.response.settings;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MapServerSettingsResponse {
    private String host;
    private Long port;
    private boolean ssl;
}
