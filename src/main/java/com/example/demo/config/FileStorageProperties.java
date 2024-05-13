package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;
    private String location = System.getProperty("user.home");
    public String getUploadDir() {
        return location; 
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}