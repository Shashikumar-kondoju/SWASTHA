package com.androiddev.projectrelief;

class model {
    String name,yurl;
    model(){

    }

    public model(String name, String yurl) {
        this.name = name;
        this.yurl = yurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYurl() {
        return yurl;
    }

    public void setYurl(String yurl) {
        this.yurl = yurl;
    }
}
