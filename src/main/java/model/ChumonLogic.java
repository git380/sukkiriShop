package model;

import dao.ChumonDAO;
import exception.ChumonException;
import exception.ZaikoException;

import java.util.List;

public class ChumonLogic {
    public void execute(String userId, List<Cart> cartList)
            throws ChumonException, ZaikoException {
        ChumonDAO dao = new ChumonDAO();
        dao.execChumon(userId, cartList);
    }
}