package servlet;

import model.Account;
import model.LoginLogic;
import model.ZaikoLogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateZaikoServlet")
public class UpdateZaikoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // フォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/zaiko.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String itemId = request.getParameter("itemId");
        String stock = request.getParameter("stock");

        // 登録処理の実行
        ZaikoLogic bo = new ZaikoLogic();
        bo.zaiko(Integer.parseInt(stock));

        response.sendRedirect("/sukkiriShop/");

        /*try {
            connection = DBConnector.getConnection();
            ZaikoDAO zaikoDAO = new ZaikoDAO(connection);

            int updateCount = zaikoDAO.updateZaiko();

            if (updateCount == 0) {
                request.setAttribute("errorMessage", "更新に失敗しました");
            } else {
                request.setAttribute("message", "更新に成功しました");
            }

            request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }
}