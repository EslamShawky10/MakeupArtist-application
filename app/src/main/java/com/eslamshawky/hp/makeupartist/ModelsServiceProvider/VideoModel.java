package com.eslamshawky.hp.makeupartist.ModelsServiceProvider;

public class VideoModel {
    private int idVideo ;
    private String urlVideo;

    public VideoModel(int idVideo, String urlVideo) {
        this.idVideo = idVideo;
        this.urlVideo = urlVideo;
    }

    public VideoModel() {
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }
}
