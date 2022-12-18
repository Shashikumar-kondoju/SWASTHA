package com.androiddev.projectrelief.Models;

public class NestedModel {
    private String img,name,tag,gif,info,yt;

    public NestedModel(){

    }

    public NestedModel(String gif, String img, String info,String name,String tag,String yt) {
        this.img = img;
        this.name = name;
        this.tag = tag;
        this.gif = gif;
        this.info = info;
        this.yt = yt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getYt() {
        return yt;
    }

    public void setYt(String yt) {
        this.yt = yt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getGif() {
        return gif;
    }

    public void setGif(String gif) {
        this.gif = gif;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
