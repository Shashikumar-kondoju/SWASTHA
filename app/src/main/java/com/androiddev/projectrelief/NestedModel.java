package com.androiddev.projectrelief;

public class NestedModel {
    private String img,name,tag;

    public NestedModel(){

    }

    public NestedModel(String img, String name, String tag) {
        this.img = img;
        this.name = name;
        this.tag = tag;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
}
