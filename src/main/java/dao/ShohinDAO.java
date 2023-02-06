package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Shohin;

public class ShohinDAO extends DAOParam {
    public ArrayList<Shohin> findByName(String query) {
        ArrayList<Shohin> resultList = new ArrayList<Shohin>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            String sql = "SELECT * FROM Shohin WHERE name LIKE ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, "%" + query + "%");
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                Shohin shohin = new Shohin();
                shohin.setName(rs.getString("name"));
                shohin.setPrice(rs.getInt("price"));
                resultList.add(shohin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultList;
    }

    public ArrayList<Shohin> findByPrice(int price) {
        ArrayList<Shohin> resultList = new ArrayList<Shohin>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            String sql = "SELECT * FROM Shohin WHERE price = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, price);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                Shohin shohin = new Shohin();
                shohin.setName(rs.getString("name"));
                shohin.setPrice(rs.getInt("price"));
                resultList.add(shohin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultList;
    }
}
