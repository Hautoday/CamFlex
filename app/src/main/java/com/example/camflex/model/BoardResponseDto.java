package com.example.camflex.model;

public class BoardResponseDto {
    private Long id;
    private String uid;
    private String title;
    private String productCategory;
    private String content;
    private int price;
    private String boardImage;
    private String date;

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getProductCategory() { return productCategory; }
    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getBoardImage() { return boardImage; }
    public void setBoardImage(String boardImage) { this.boardImage = boardImage; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
