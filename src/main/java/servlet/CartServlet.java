package servlet;

import model.Cart;
import model.Shohin;
import model.ShohinDetailLogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //「/WEB-INF/jsp/cart.jsp」にフォワードする
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //パラメータを取得する。
        String cmd = request.getParameter("cmd");
        String idxStr = request.getParameter("idx");
        String code = request.getParameter("code");
        String quantityStr = request.getParameter("quantity");

        /*//数値型に変換
        int idx = 0;
        if (idxStr != null && !idxStr.isEmpty()) {
            idx = Integer.parseInt(idxStr);
        }
        int quantity = 0;
        if (quantityStr != null && !quantityStr.isEmpty()) {
            quantity = Integer.parseInt(quantityStr);
        }*/

        //セッションを取得し、セッションから「cartList」を取得する。
        HttpSession session = request.getSession();
        List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");

        //もしもcartList がnull(未設定)ならば、新規作成してセッションに設定する
        if (cartList == null) {
            cartList = new ArrayList<Cart>();
            session.setAttribute("cartList", cartList);
        }

        //パラメータ「cmd」によって処理を分岐。
        int idx, quantity; //変数定義
        switch (cmd) {
            case "add": // add=商品追加
                quantity = Integer.parseInt(quantityStr); //数量数値化
                Shohin s = new ShohinDetailLogic().execute(code); // 商品コードをもとにDBを検索
                if (s == null) { // もし検索が失敗したらエラー
                    request.setAttribute("error", "不正な商品コードです"); // エラーメッセージを設定
                    //「/WEB-INF/jsp/error.jsp」にフォワードする。
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
                // カートに商品を追加
                cartList.add(new Cart(code, s.getName(), s.getPrice(), quantity));
                break;

            case "mod": // mod=数量変更
                idx = Integer.parseInt(idxStr); //idx数値化
                quantity = Integer.parseInt(quantityStr); //数量数値化
                // カート位置の商品コードとパラメータのコードを比較して、もし不一致ならば
                if (!cartList.get(idx).getCode().equals(code)) {
                    request.setAttribute("error", "不正な商品コードです"); // エラーメッセージを設定
                    //「/WEB-INF/jsp/error.jsp」にフォワードする。
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
                // 数量を変更
                cartList.get(idx).setQuantity(quantity);
                break;

            case "del": // del=商品削除
                idx = Integer.parseInt(idxStr); //idx数値化
                // カート位置の商品コードとパラメータのコードを比較して、もし不一致ならば
                if (!cartList.get(idx).getCode().equals(code)) {
                    request.setAttribute("error", "不正な商品コードです"); // エラーメッセージを設定
                    //「/WEB-INF/jsp/error.jsp」にフォワードする。
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
                //カートの該当商品を削除する
                cartList.remove(idx);
                break;

            default: //cmdが誤っているとき
                request.setAttribute("error", "不正な操作です"); // エラーメッセージを設定
                //「/WEB-INF/jsp/error.jsp」にフォワードする。
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
                dispatcher.forward(request, response);
                return;
        }
        //カート画面表示:自分自身"CartServlet"にリダイレクト (doGet()が実行される)
        response.sendRedirect("/sukkiriShop/CartServlet");
    }
}