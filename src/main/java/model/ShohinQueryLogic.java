package model;

import java.util.ArrayList;
import dao.ShohinDAO;

public class ShohinQueryLogic {
    public ArrayList<Shohin> execute(String query) {
        ShohinDAO dao = new ShohinDAO();
        ArrayList<Shohin> result = dao.findByName(query);
        return result;
    }

    public ArrayList<Shohin> execute(int price) {
        ShohinDAO dao = new ShohinDAO();
        ArrayList<Shohin> result = dao.findByPrice(price);
        return result;
    }
}
