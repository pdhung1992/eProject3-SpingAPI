package com.springapi.payload.request;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class CityRequest {
    private String name;
    private MultipartFile thumbnail;

    public CityRequest(String name, MultipartFile thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MultipartFile thumbnail) {
        this.thumbnail = thumbnail;
    }
}
