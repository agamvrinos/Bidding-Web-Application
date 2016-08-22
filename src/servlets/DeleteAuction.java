package servlets;

import dao.ItemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteAuction")
public class DeleteAuction extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer id = null;
        String auction_id = request.getParameter("id");

        try {
            id = Integer.parseInt(auction_id);
        } catch(NumberFormatException e) {
            response.getWriter().println("Error at DeleteAuction.jsp");
        } catch(NullPointerException e) {
            response.getWriter().println("Error at DeleteAuction.jsp 2");
        }

        ItemDAO dao = new ItemDAO(true);
        boolean result = dao.deleteItem(id);

        if (!result)
            response.getWriter().println("Error at dao.deleteItem");
        else
            response.sendRedirect("myauctions.jsp");

    }
}
