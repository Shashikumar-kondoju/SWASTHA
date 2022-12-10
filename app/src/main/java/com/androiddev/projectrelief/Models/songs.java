package com.androiddev.projectrelief.Models;

public class songs {
    private String description, imag, song;

    public songs(){

    }

    public songs(String imag, String song, String description) {

        this.imag = imag;
        this.description = description;
        this.song = song;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {this.description = description;}

    public String getImg() {
        return imag;
    }
    public void setImag(String imag) {this.imag = imag;}

    public String getSong() {
        return song;
    }
    public void setSong(String song) {this.song = song;}
}
