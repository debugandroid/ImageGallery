package com.debugandroid.imagegallery;

/**
 * Created by Pawan.
 */
public class ImageItem {

    String DATA;
    String DISPLAY_NAME;
    String CREATED;
    public ImageItem(String DATA, String DISPLAY_NAME,String CREATED){
        this.DATA=DATA;
        this.DISPLAY_NAME=DISPLAY_NAME;
        this.CREATED=CREATED;
    };
    public ImageItem(){

    }


    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String getDISPLAY_NAME() {
        return DISPLAY_NAME;
    }

    public void setDISPLAY_NAME(String DISPLAY_NAME) {
        this.DISPLAY_NAME = DISPLAY_NAME;
    }

    public String getCREATED() {
        return CREATED;
    }

    public void setCREATED(String CREATED) {
        this.CREATED = CREATED;
    }


}
