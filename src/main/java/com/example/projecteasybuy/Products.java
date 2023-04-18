package com.example.projecteasybuy;

public class Products {


    //price and image name;
    private int Product_ID;
    private String Product_name;
    private String imageFile;
    private float price;
    private int stock;

    Products(int P_ID, String Product_name, String imageFile, int stock,float price){
        this.Product_name = Product_name;
        this.imageFile=imageFile;
        this.price=price;
        this.stock = stock;
        this.Product_ID = P_ID;
    }
    Products()
    {

    }

    public void setProduct_ID(int Product_ID) {
        this.Product_ID=Product_ID;
    }

    public void setPrice(float price) {
        this.price=price;
    }

    public void setStock(int stock) {
        this.stock=stock;
    }

    public void setImageFile(String ImageFile) {
        this.imageFile=imageFile;
    }

    public void setProduct_name(String Product_name) {
        this.Product_name=Product_name;
    }


    public int getProduct_ID() {
        return Product_ID;
    }

    public float getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getImageFile() {
        return imageFile;
    }

    public String getProduct_name() {
        return Product_name;
    }

}
