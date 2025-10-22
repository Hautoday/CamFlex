package com.example.camflex.model;

public class UserLoginRequestDto {
    private String uid;
    private String password;

    public UserLoginRequestDto(String uid, String password) {
        this.uid = uid;
        this.password = password;
    }
    // Getter/Setter
}
