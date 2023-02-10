package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountDAO extends DAOParam {

    public Account findByLogin(Login login) {
        Account account = null;

        // データベースへ接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // SELECT文を準備
            String sql = "SELECT USER_ID, PASS, MAIL, NAME, AGE FROM ACCOUNT WHERE USER_ID = ? AND PASS = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, login.getUserId());
            pStmt.setString(2, login.getPass());

            // SELECTを実行し、結果表を取得
            ResultSet rs = pStmt.executeQuery();

            // 一致したユーザーが存在した場合
            // そのユーザーを表すAccountインスタンスを生成
            if (rs.next()) {
                // 結果表からデータを取得
                String userId = rs.getString("USER_ID");
                String pass = rs.getString("PASS");
                String mail = rs.getString("MAIL");
                String name = rs.getString("NAME");
                int age = rs.getInt("AGE");
                account = new Account(userId, pass, mail, name, age);

                // 取得したデータを出力
                System.out.println("id:" + userId + " pw:" + pass + " main:" + mail + " name:" + name + " age:" + age);
                /*System.out.println("名前:" + pw);
                System.out.println("dbID:" + dbId);
                System.out.println("db名前:" + dbPw);
                System.out.println("ans:" + check);*/
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        // 見つかったユーザーまたはnullを返す
        return account;
    }

    public void insertAccount(Account account) {
        // データベースへ接続
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            // SQL文の準備
            String sql = "INSERT INTO ACCOUNT (USER_ID, PASS, MAIL, NAME, AGE) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // パラメータの設定
            preparedStatement.setString(1, account.getUserId());
            preparedStatement.setString(2, account.getPass());
            preparedStatement.setString(3, account.getMail());
            preparedStatement.setString(4, account.getName());
            preparedStatement.setInt(5, account.getAge());

            // SQL実行
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(String userId) {
        // データベースへ接続
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "DELETE FROM ACCOUNT WHERE USER_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}