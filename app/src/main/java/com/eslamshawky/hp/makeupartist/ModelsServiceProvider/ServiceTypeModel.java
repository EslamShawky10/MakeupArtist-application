package com.eslamshawky.hp.makeupartist.ModelsServiceProvider;

public class ServiceTypeModel {

    private int id;
    private String title;

    public ServiceTypeModel(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public ServiceTypeModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
