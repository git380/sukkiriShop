package servlet;

import model.Shohin;
import model.ShohinQueryLogic;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShohinQueryServlet")
public class ShohinQueryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // パラメータを取得する
        String cmd = request.getParameter("cmd");
        String keyword = request.getParameter("keyword");

        System.out.println("cmd:" + cmd);
        System.out.println("keyword:" + keyword);

        // 変数の定義
        ShohinQueryLogic logic = new ShohinQueryLogic();
        ArrayList<Shohin> shohinList = null;

        // cmdパラメータに基づいたアクションを決定する
        if (cmd == null || cmd.equals("")) { // 初回：cmdを指定しない
            shohinList = new ArrayList<Shohin>(); // 空の結果リストを生成する
        } else if (cmd.equals("query")) { // フォームの呼び出し: cmd = "query"
            shohinList = logic.execute(keyword); // 製品名で探す
        } else { // フォーム呼び出し: cmd = "price"
            shohinList = logic.execute(Integer.parseInt(keyword)); // 価格から探す ※入力値により例外が発生する可能性があります。
        }

        // リクエストスコープに結果リストを設定する
        request.setAttribute("shohinList", shohinList);

        // Forward(フォワード)
        request.getRequestDispatcher("/WEB-INF/jsp/shohinquery.jsp").forward(request, response);
    }
}
