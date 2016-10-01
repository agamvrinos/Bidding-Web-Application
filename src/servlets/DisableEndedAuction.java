package servlets;

import dao.ItemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/DisableEndedAuction")
public class DisableEndedAuction extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get username from ajax
        String auction_id = request.getParameter("id");

        // Disable ended auction
        ItemDAO dao = new ItemDAO(true);
        dao.disableAuction(auction_id);
    }
}
