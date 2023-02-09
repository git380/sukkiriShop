package test;

import dao.ChumonDAO;
import exception.ChumonException;
import exception.ZaikoException;
import model.Cart;

import java.util.ArrayList;
import java.util.List;

public class ChumonDAOTest {
    public static void main(String[] args) {
        //テスト条件
        String userId = "minato";
        List<Cart> cartList = new ArrayList<Cart>();
        cartList.add(new Cart("bis001", "商品名1", 100, 5));
        cartList.add(new Cart("bis002", "商品名2", 100, 5));
        cartList.add(new Cart("bis003", "商品名3", 100, 5));
        //DAOクラスのインスタンスを生成
        ChumonDAO dao = new ChumonDAO();
        try {
            dao.execChumon(userId, cartList); //テスト実行
        } catch (ChumonException e) { //わざとエラーを起こさないと発生しません
            e.printStackTrace();
        } catch (ZaikoException e) { //在庫が足らなくなればすぐに発生します
            System.out.println(e.getMessage());
            for (String msg : e.getMsgList()) {
                System.out.println(msg);
            }
        }
    }
}
