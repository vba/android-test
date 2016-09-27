package com.waracle.androidtest.dto;

import java.io.InputStream;

public class Response {
    private final InputStream content;
    private final String contentType;

    public Response(InputStream content, String contentType) {
        this.content = content;
        this.contentType = contentType;
    }

    public InputStream getContent() {
        return content;
    }

    public String getContentType() {
        return contentType;
    }
}
