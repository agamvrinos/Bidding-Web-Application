package servlets.Messages;

import dao.BidDAO;
import dao.MessageDAO;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DisableMessageRating")
public class DisableMessageRating extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get sender id in order to update his rating
        String seller_uname = request.getParameter("seller_uname");

        // Get rating value (-1 for negative, +1 for positive)
        Integer rating = Integer.valueOf(request.getParameter("rate_val"));

        // Get message id in order to update is_auto value
        Integer message_id = Integer.valueOf(request.getParameter("msg_id"));

        System.out.println("Seller to be rated: " + seller_uname);
        System.out.println("Rating to be added: " + rating);
        System.out.println("Message to be updated: " + message_id);

        MessageDAO mdao = new MessageDAO(true);
        BidDAO bdao = new BidDAO(true);
        UserDAO udao = new UserDAO(true);

        // Update msg is_auto so the user wont be able to rate anymore
        mdao.updateIsAuto(message_id);

        // Update the rating of the user
        udao.updateRating(seller_uname, rating);

    }
}
