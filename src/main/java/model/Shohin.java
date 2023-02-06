package model;

import java.io.Serializable;

public class Shohin implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String name;
    private int price;

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
