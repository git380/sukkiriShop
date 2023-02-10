package servlet;

import exception.ChumonException;
import exception.ZaikoException;
import model.Cart;
import model.ChumonLogic;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChumonServlet")
public class ChumonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // パラメータを取得
        String cmd = request.getParameter("cmd");
        // フォワード先の決定
        String url;
        if (cmd != null && cmd.equals("result")) {
            url = "/WEB-INF/jsp/chumonresult.jsp";
        } else if (cmd != null && cmd.equals("zaiko")) {
            url = "/WEB-INF/jsp/chumonzaiko.jsp";
        } else {
            url = "/WEB-INF/jsp/chumonerror.jsp";
        }
        // フォワード
        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //セッションからカートリストとログインユーザを得ます
        HttpSession session = request.getSession();
        List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
        String userId = (String) session.getAttribute("userId");

        //ログイン判定
        if (userId == null || userId.equals("")) {
            //ログイン画面にリダイレクトする
            response.sendRedirect("/プロジェクト名/LoginServlet");
            return;
        }

        ChumonLogic logic = new ChumonLogic();
        try {
            //注文処理を実行する
            logic.execute(userId, cartList);
            //カートを"resultList"に登録する
            session.setAttribute("resultList", cartList);
            //カートをクリアする
            session.removeAttribute("cartList");
            //注文結果画面にリダイレクト
            response.sendRedirect("/sukkiriShop/ChumonServlet?cmd=result");
        } catch (ZaikoException e) {
            //例外クラスをセッションスコープにセット
            session.setAttribute("e", e);
            //在庫メッセージ画面にリダイレクト
            response.sendRedirect("/sukkiriShop/ChumonServlet?cmd=zaiko");
        } catch (ChumonException e) {
            e.printStackTrace(); //エラーの詳細をコンソールにも出力
            //例外クラスをセッションスコープにセット
            session.setAttribute("e", e);
            //エラー画面にリダイレクト
            response.sendRedirect("/sukkiriShop/ChumonServlet?cmd=error");
        }
    }
}

