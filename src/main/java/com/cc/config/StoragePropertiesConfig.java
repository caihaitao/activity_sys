package com.cc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StoragePropertiesConfig {
    @Value("${image_savePath}")
    private String location;

    /**
     * Folder location for storing files
     */
    private String default_location = "/static/upload/";

    public String getLocation() {
        return location == null ? default_location : location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
