package com.example.siot;

public class Item {
    String bname;
    int image;

    public Item(String bname) {
        this.bname = bname;
        this.image = image;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
