package com.example.greenlifeproject.config.auth.oath.provider;

public interface OAuth2UserInfo {
    String getProviderId();

    String getProvider();

    String getEmail();

    String getName();
}
