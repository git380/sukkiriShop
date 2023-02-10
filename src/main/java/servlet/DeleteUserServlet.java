package servlet;

import model.Account;
import model.LoginLogic;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // フォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/delete.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 削除するユーザのIDを取得
        String userId = request.getParameter("userId");

        // 削除処理の実行
        LoginLogic bo = new LoginLogic();
        bo.delAccount(userId);

        /*// 削除結果に応じてメッセージを設定
        if (result) {
            request.setAttribute("message", "ユーザの削除が完了しました");
        } else {
            request.setAttribute("message", "ユーザの削除に失敗しました");
        }*/

        response.sendRedirect("/sukkiriShop/");
    }
}

