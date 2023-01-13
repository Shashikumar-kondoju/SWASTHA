package com.androiddev.projectrelief.Models;

public class songs {
    private String description, imgLink, songName, songDuration, songLink;

    public songs(){

    }

    public songs(String imgLink, String songName, String description,String songDuration,String songLink) {

        this.songDuration = songDuration;
        this.imgLink = imgLink;
        this.description = description;
        this.songName = songName;
        this.songLink = songLink;
    }

    public String getSongName() {
        return songName;
    }
    public void setSongName(String songName) {this.songName = songName;}

    public String getSongDuration() {
        return songDuration;
    }
    public void setSongDuration(String songDuration) {this.songDuration = songDuration;}


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {this.description = description;}

    public String getImgLink() {
        return imgLink;
    }
    public void setImgLink(String imgLink) {this.imgLink = imgLink;}

    public String getSongLink() {
        return songLink;
    }
    public void setSongLink(String songLink) {this.songLink = songLink;}
}
