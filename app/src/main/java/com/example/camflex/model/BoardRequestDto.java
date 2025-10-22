package com.example.camflex.model;

public class BoardRequestDto {
    private String title;
    private String content;
    private String category;
    private String writer; // 사용자 이메일 또는 이름

    public BoardRequestDto(String title, String content, String category, String writer) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.writer = writer;
    }

    // Getter/Setter 필요 시 추가
}
