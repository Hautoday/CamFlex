package com.example.camflex;

public class PurchaseItem {
    public String title;
    public String locationTime;
    public String status;
    public String price;
    public int imageResId;

    public PurchaseItem(String title, String locationTime, String status, String price, int imageResId) {
        this.title = title;
        this.locationTime = locationTime;
        this.status = status;
        this.price = price;
        this.imageResId = imageResId;
    }
}
