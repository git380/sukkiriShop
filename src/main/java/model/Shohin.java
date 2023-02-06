package model;

import java.io.Serializable;

public class Shohin implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String name;
    private String vol;
    private int price;
    private String comment;
    private String image;

    public Shohin() {
    }

    public Shohin(
            String code,
            String name,
            String vol,
            int price,
            String comment,
            String image
    ) {
        this.code = code;
        this.name = name;
        this.vol = vol;
        this.price = price;
        this.comment = comment;
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(int String) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
