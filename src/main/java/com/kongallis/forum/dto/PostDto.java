package com.kongallis.forum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class PostDto {

    private String title;
    @JsonProperty("creation date")
    private Instant createdDate;
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
