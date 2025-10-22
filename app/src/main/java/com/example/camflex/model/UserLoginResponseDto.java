package com.example.camflex.model;

public class UserLoginResponseDto {
    private boolean success;
    private String message;
    private String uid;
    private String name;   // ✅ 추가

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getUid() { return uid; }
    public String getName() { return name; }
}