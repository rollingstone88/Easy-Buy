package com.example.projecteasybuy;

import java.util.*;

public class CDSShoppingCart {
    private static CDSShoppingCart INSTANCE;
    Products product;
    public static CDSShoppingCart getInstance(){
        if(INSTANCE==null){
            INSTANCE= new CDSShoppingCart();
        }

        return INSTANCE;
    }
    private Map<String,CartEntry> entries;



    private CDSShoppingCart(){
        this.entries=new HashMap<>();
    }

    public void addProduct(Products pr){
        CartEntry productEntry= entries.get(pr.getProduct_name().toUpperCase());
        if(productEntry!=null){
            productEntry.increaseQuantity(pr);
        }
        else{
            CartEntry entry=new CartEntry(pr.getProduct_ID(),pr.getProduct_name(),pr.getImageFile(),1,pr.getPrice());
            entries.put(pr.getProduct_name().toUpperCase(),entry);
        }
    }

    public void removeProduct(String productName){
        CartEntry productEntry = entries.get(productName.toUpperCase());
        if(productEntry!=null){
            productEntry.decreaseQuantity();
        }
    }

    public int getQuantity(String productName){
        CartEntry entry=entries.get(productName.toUpperCase());
        if(entry!=null){
            return entry.getQuantity();
        }
        return 0;
    }

    public double calculateTotal(){
        double total= 0;
        for(CartEntry entry:entries.values()){
            double entryCost= entry.getPrice()*entry.getQuantity();
            total=total+entryCost;
        }
        return total;
    }

    public List<CartEntry> cdsgetEntries(){
        return new ArrayList<>(entries.values());
    }


}