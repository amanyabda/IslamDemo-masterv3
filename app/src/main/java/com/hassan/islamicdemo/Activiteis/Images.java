package com.hassan.islamicdemo.Activiteis;

public class Images {
    private  int image;
    private String share,wall;

    public Images(int image, String share, String wall) {
        this.image = image;
        this.share = share;
        this.wall = wall;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }



    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getWall() {
        return wall;
    }

    public void setWall(String wall) {
        this.wall = wall;
    }
}
