package com.example.camflex.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("title")
    private String title;

    @SerializedName("date")
    private String date; // 서버의 LocalDate를 String으로 받습니다.

    @SerializedName("productCategory")
    private String productCategory;

    @SerializedName("price")
    private int price;

    @SerializedName("image")
    private String image; // 이미지 URL

    // 각 필드의 Getter 메소드
    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getProductCategory() { return productCategory; }
    public int getPrice() { return price; }
    public String getImage() { return image; }
}