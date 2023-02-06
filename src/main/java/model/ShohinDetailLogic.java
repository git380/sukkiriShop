package model;

import dao.ShohinDAO;

public class ShohinDetailLogic {
    public Shohin execute(String code) {
        ShohinDAO dao = new ShohinDAO();
        return dao.findByCode(code);
    }
}
