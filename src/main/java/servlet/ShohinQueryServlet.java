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
        // Get the parameter
        String cmd = request.getParameter("cmd");
        String keyword = request.getParameter("keyword");

        // Define variables
        ShohinQueryLogic logic = new ShohinQueryLogic();
        ArrayList<Shohin> shohinList = null;

        // Determine the action based on the cmd parameter
        if (cmd == null || cmd.equals("")) { // First time: no cmd specified
            shohinList = new ArrayList<Shohin>(); // Generate an empty result list
        } else if (cmd.equals("query")) { // Form call: cmd = "query"
            shohinList = logic.execute(keyword); // Search by product name
        } else { // Form call: cmd = "price"
            shohinList = logic.execute(Integer.parseInt(keyword)); // Search by price * There is a possibility of exception depending on the input value
        }

        // Set the result list to the request scope
        request.setAttribute("shohinList", shohinList);

        // Forward
        request.getRequestDispatcher("/WEB-INF/jsp/shohinquery.jsp").forward(request, response);
    }
}
