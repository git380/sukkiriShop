package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Shohin;

public class ShohinDAO extends DAOParam {
    public ArrayList<Shohin> findByName(String query) {// 製品名で探す
        ArrayList<Shohin> resultList = new ArrayList<Shohin>();

        // データベース接続
        try (Connection conn = DriverManager.getConnection(
                JDBC_URL, DB_USER, DB_PASS)) {
            // SELECT文の準備
            String sql = "SELECT * FROM Shohin WHERE name LIKE ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, "%" + query + "%");
            // SELECTを実行
            ResultSet rs = pStmt.executeQuery();
            // SELECT文の結果をArrayListに格納
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String vol = rs.getString("vol");
                int dbPrice = rs.getInt("price");
                String comment = rs.getString("comment");
                String image = rs.getString("image");
                Shohin shohin = new Shohin(code, name, vol, dbPrice, comment, image);
                resultList.add(shohin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public ArrayList<Shohin> findByPrice(int price) {//価格で探す
        ArrayList<Shohin> resultList = new ArrayList<Shohin>();
        // データベース接続
        try (Connection conn = DriverManager.getConnection(
                JDBC_URL, DB_USER, DB_PASS)) {
            // SELECT文の準備
            String sql = "SELECT * FROM Shohin WHERE price = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, price);
            // SELECTを実行
            ResultSet rs = pStmt.executeQuery();
            // SELECT文の結果をArrayListに格納
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String vol = rs.getString("vol");
                int dbPrice = rs.getInt("price");
                String comment = rs.getString("comment");
                String image = rs.getString("image");
                Shohin shohin = new Shohin(code, name, vol, dbPrice, comment, image);
                resultList.add(shohin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }


    /*ダメだった
    public Shohin findByCode(String code) throws SQLException {
        Shohin shohin = new Shohin();
        // データベース接続
        try (Connection conn = DriverManager.getConnection(
                JDBC_URL, DB_USER, DB_PASS)) {
            // SELECT文の準備
            String sql = "SELECT * FROM shohin WHERE code = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, code);
            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                shohin.setCode(rs.getString("code"));
                shohin.setName(rs.getString("name"));
                shohin.setPrice(rs.getInt("price"));
            } else {
                return null;
            }
        }
        return shohin;
    }*/

    public Shohin findByCode(String code) {
        // データベース接続
        try (Connection conn = DriverManager.getConnection(
                JDBC_URL, DB_USER, DB_PASS)) {
            // SELECT文の準備
            String sql = "SELECT * FROM Shohin WHERE code = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, code);
            // SELECTを実行
            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                Shohin shohin = new Shohin();
                shohin.setCode(rs.getString("code"));
                shohin.setName(rs.getString("name"));
                shohin.setPrice(rs.getInt("price"));
                return shohin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
