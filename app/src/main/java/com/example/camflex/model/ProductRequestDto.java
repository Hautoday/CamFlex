package com.example.camflex.model;

public class ProductRequestDto {
    private String title;
    private String price;
    private String description;
    private String category;
    private String userEmail;

    public ProductRequestDto(String title, String price, String description, String category, String userEmail) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.userEmail = userEmail;
    }

    // Getter/Setter (필요 시 추가)
}
