package com.eslamshawky.hp.makeupartist.ModelsServiceProvider;

public class MyServiceModel {
    private  String name,type,price,description;

    public MyServiceModel(String name, String type, String price, String description) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public MyServiceModel() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
