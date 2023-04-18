package com.example.projecteasybuy;

public class ProductsRocket {
    private String imageFile;
    private float price;

    ProductsRocket(String imageFile, float price){
        this.imageFile=imageFile;
        this.price=price;
    }

    public String getImageFile(){
        return imageFile;
    }
    public float getPrice(){
        return this.price;
    }
}
