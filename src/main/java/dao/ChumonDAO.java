package dao;

import exception.ChumonException;
import exception.ZaikoException;
import model.Cart;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChumonDAO extends DAOParam {
    //注文処理本体
    //業務例外:ChumonException = 継続不能な業務エラーや内部エラー等
    //業務例外:ZaikoException = 在庫不足に起因する処理中断。顧客にカートの修正を促す
    public void execChumon(String userId, List<Cart> cartList)
            throws ChumonException, ZaikoException {
        //使用するSQL
        // sql1 = 注文テーブルにインサートします
        // sql2 = 在庫テーブルから商品の在庫数を取得します
        // sql3 = 在庫テーブルの商品の在庫数を更新します
        // sql4 = 注文明細テーブルにインサートします
        // sql1Param = AUTO_INCREMENTの値がプログラム側でも得られるようになる設定値
        String sql1 = "INSERT INTO Chumon(user_id) VALUES(?)";
        String sql2 = "SELECT stock FROM Zaiko WHERE code = ? FOR UPDATE";
        String sql3 = "UPDATE Zaiko SET stock = ? WHERE code = ?";
        String sql4 = "INSERT INTO Meisai(chumon_no, sub_no, code, price, quantity) VALUES(?, ?, ?, ?, ?)";
        int sql1Param = java.sql.Statement.RETURN_GENERATED_KEYS;
        //デッドロック対策:カートリストを商品番号順にソート(カート順は変ってしまう)
        Collections.sort(cartList, new Comparator<Cart>() {
            @Override
            public int compare(Cart c1, Cart c2) {
                return c1.getCode().compareTo(c2.getCode());
            }
        });
        //DBに接続/自動切断
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            //トランザクション開始
            conn.setAutoCommit(false);
            //内側のtryブロック(ここで例外が発生してもロールバックできます)
            try (PreparedStatement pstmt1 = conn.prepareStatement(sql1, sql1Param);
                 PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                 PreparedStatement pstmt3 = conn.prepareStatement(sql3);
                 PreparedStatement pstmt4 = conn.prepareStatement(sql4);
            ) {

                //在庫メッセージ
                List<String> msgList = new ArrayList<String>();
                //注文テーブルにレコード追加
                int chumonNo;
                pstmt1.setString(1, userId); //注文テーブルのレコードの値(他は初期値)
                pstmt1.executeUpdate(); // INSERT文は基本失敗はないはず
                ResultSet rs1 = pstmt1.getGeneratedKeys(); //AUTO_INCREMENTした値を得る
                if (rs1.next()) {
                    chumonNo = rs1.getInt(1); //注文番号取得
                } else {
                    throw new ChumonException("注文番号の取得に失敗しました");
                }
                //カートリストでループ
                int count = 0;
                for (Cart cart : cartList) {
                    //在庫検索(FOR UPDATE:レコードロック発生)
                    pstmt2.setString(1, cart.getCode());
                    ResultSet rs2 = pstmt2.executeQuery();
                    int stock = 0;
                    if (rs2.next()) {
                        stock = rs2.getInt("stock"); //在庫数取得
                    } else {
                        throw new ChumonException(
                                "商品コード(" + cart.getCode() + ")"
                                        + "商品名(" + cart.getName() + ")がありません");

                    }
                    //在庫は足りるのか判定
                    if (cart.getQuantity() > stock) {
                        //在庫メッセージを追加
                        msgList.add("商品コード(" + cart.getCode() + ")"
                                + "商品名(" + cart.getName() + ")の在庫不足"
                                + "/注文数=" + cart.getQuantity()
                                + "/在庫数=" + stock);

                    } else {
                        //在庫が足りるならば在庫を更新する
                        pstmt3.setInt(1, stock - cart.getQuantity());
                        pstmt3.setString(2, cart.getCode());
                        int result3 = pstmt3.executeUpdate();
                        if (result3 != 1) {
                            throw new ChumonException("商品コード(" + cart.getCode() +
                                    ") の在庫更新失敗しました");
                        }
                    }

                    //注文明細テーブルにレコードを追加する
                    pstmt4.setInt(1, chumonNo); //ChumonテーブルのAUTO_INCREMENTの値
                    pstmt4.setInt(2, ++count);
                    pstmt4.setString(3, cart.getCode()); //商品コード
                    pstmt4.setInt(4, cart.getPrice()); //販売時価格
                    pstmt4.setInt(5, cart.getQuantity()); //商品数量
                    pstmt4.executeUpdate(); // INSERT文は基本失敗はないはず
                }
                //在庫メッセージがある場合は在庫不足
                if (msgList.size() > 0) {
                    //在庫不足の商品があったので例外をスローする
                    throw new ZaikoException("商品を用意できませんでした", msgList);
                } else {
                    //処理が問題なく完遂できたのでコミット(変更を適用)する
                    conn.commit();
                }
            } catch (SQLException | ChumonException | ZaikoException e) {
                //途中でエラーになったのでロールバック(巻き戻し)する
                conn.rollback();
                throw e; //上位の例外処理にそのままスローする
            }
        } catch (ChumonException | ZaikoException e) {
            throw e; //業務例外は呼び出し元の例外処理にそのままスローする
        } catch (Exception e) {
            //内部エラーはChumonExceptionでくるんでスローする
            throw new ChumonException("注文処理でエラーが発生しました", e);
        }
    }
}