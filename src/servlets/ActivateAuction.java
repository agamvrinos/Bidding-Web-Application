package servlets;

import dao.ItemDAO;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ActivateAuction")
public class ActivateAuction extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        // Activate Auction (state = 1)
        ItemDAO dao = new ItemDAO(true);
        boolean result = dao.activateAuction(id);

        if (result)
            response.sendRedirect("my_auctions.jsp");
        else
            response.sendRedirect("FAILED");
    }
}
