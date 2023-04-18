package com.example.projecteasybuy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Order {
    private int order_ID;
    private int customer_ID;
    private double total_amount;
    String Store;

    public int generate_orderID()
    {
        int cnt = 0;
        try {
            DBMSConnection d = new DBMSConnection("jdbc:mysql://localhost:3306/mydb", "root","");
            Connection myConnection = d.getConnection();

            String Query = "SELECT count(*) from orders";

            Statement stmt = myConnection.createStatement();

            stmt.execute("use mydb");
            ResultSet rs = stmt.executeQuery(Query);

            while(rs.next()) {
                cnt = rs.getInt(1);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            String errorMsg = e.getMessage();
            System.out.println(errorMsg);

        }
        return cnt + 1;
    }

    Order(String store, int id) {
        this.customer_ID = id;
        this.Store = store;
        this.order_ID = generate_orderID();
        if (store.equals("CDS")) {
            total_amount = CDSShoppingCart.getInstance().calculateTotal();
        } else if (store.equals("ROCKET")) {
            total_amount = RocketShoppingCart.getInstance().calculateTotal();
        }
        else {
            total_amount = PharmacyShoppingCart.getInstance().calculateTotal();
        }
    }

    public int getOrder_ID() {
        return order_ID;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public String getStore() {
        return Store;
    }
}


