package com.example.textilemart;

public class model {
    String Price, ProductName, Quantity,Userid;

    public model() {

    }

    public model(String price, String productName, String quantity,String userid) {
        Price = price;
        ProductName = productName;
        Quantity = quantity;
        Userid=userid;
    }

    public String getPrice() {
        return Price;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
