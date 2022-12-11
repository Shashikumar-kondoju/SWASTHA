package com.androiddev.projectrelief.Models;

public class songs {
    private String description, imagem, song;

    public songs(){

    }

    public songs(String imagem, String song, String description) {

        this.imagem = imagem;
        this.description = description;
        this.song = song;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {this.description = description;}

    public String getImg() {
        return imagem;
    }
    public void setImg(String imagem) {this.imagem = imagem;}

    public String getSong() {
        return song;
    }
    public void setSong(String song) {this.song = song;}
}
