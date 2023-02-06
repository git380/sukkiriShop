package model;

import java.util.ArrayList;
import dao.ShohinDAO;

public class ShohinQueryLogic {
    public ArrayList<Shohin> execute(String query) {// 製品名で探す
        ShohinDAO dao = new ShohinDAO();
        System.out.println("qu");
        return dao.findByName(query);
    }

    public ArrayList<Shohin> execute(int price) {//価格で探す
        ShohinDAO dao = new ShohinDAO();
        System.out.println("pr");
        return dao.findByPrice(price);
    }
}
