package com.kongallis.forum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String avatar;
    @JsonProperty("username")
    private String usernName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return usernName;
    }

    public void setUsername(String usernName) {
        this.usernName = usernName;
    }
}
