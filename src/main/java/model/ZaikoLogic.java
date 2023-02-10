package model;

import dao.ZaikoDAO;

public class ZaikoLogic {
    public void zaiko(int stock) {
        ZaikoDAO dao = new ZaikoDAO();
        dao.updateStock(stock);
    }
}