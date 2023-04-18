package com.example.projecteasybuy;


public class CartEntry {
    private int product_ID;
    private String product_name;
    private String Img_loc;
    private int quantity;
    private float price;
    public CartEntry(int id, String product_name, String Img_loc, int quantity, float price ){

        this.product_name = product_name;
        this.Img_loc = Img_loc;
        this.quantity=quantity;
        this.product_ID = id;
        this.price = price;
    }


    public String getProduct_name() {
        return product_name;
    }

    public int getProduct_ID() {
        return product_ID;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getImg_loc() {
        return Img_loc;
    }

    public void increaseQuantity(Products pr){
        if(this.quantity<pr.getStock()) this.quantity++;
    }

    public void decreaseQuantity(){
        if(this.quantity>0) {
            this.quantity--;
        }
    }

}
