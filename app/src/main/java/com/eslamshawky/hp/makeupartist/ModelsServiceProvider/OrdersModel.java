package com.eslamshawky.hp.makeupartist.ModelsServiceProvider;

public class OrdersModel {
    private String name,date,totalPrice,address;
    String listOfServices;
    public OrdersModel(String name, String date, String totalPrice,String address ,String listOfServices) {
        this.name = name;
        this.date = date;
        this.totalPrice = totalPrice;
        this.address = address;
        this.listOfServices = listOfServices;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getListOfServices() {
        return listOfServices;
    }

    public void setListOfServices(String listOfServices) {
        this.listOfServices = listOfServices;
    }
}
