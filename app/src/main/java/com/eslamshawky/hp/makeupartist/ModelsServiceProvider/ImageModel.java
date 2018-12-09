package com.eslamshawky.hp.makeupartist.ModelsServiceProvider;

public class ImageModel {

    private int id ;
    private String Url;

    public ImageModel(int id, String url) {
        this.id = id;
        Url = url;
    }

    public ImageModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
