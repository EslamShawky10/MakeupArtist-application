package com.eslamshawky.hp.makeupartist.ModelsServiceProvider;

public class OrdersModel {
    private String name,date,totalPrice;

    public OrdersModel(String name, String date, String totalPrice) {
        this.name = name;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public OrdersModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
