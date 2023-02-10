package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ZaikoDAO extends DAOParam {
    public void updateStock(int stock) {
        // データベースへ接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            // SELECT文を準備
            String sql = "UPDATE Zaiko SET stock=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
