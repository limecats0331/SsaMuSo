package com.ssafy.ssamuso.security.provider;

import java.util.Map;

public interface OAuthUserInfo {
    String getProviderId();

    String getProvider();

    String getName();

    String getEmail();

    Map<String, Object> getAttributes();
}
