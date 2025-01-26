package com.naeayedea.simplewebserver.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix="simple-web-server")
public class AppProperties {

    private Map<String, AppProperty> apps = new HashMap<>();

    public Map<String, AppProperty> getApps() {
        return apps;
    }

    @SuppressWarnings("unused")
    private void setApps(Map<String, AppProperty> apps) {
        this.apps = apps;
    }



}
