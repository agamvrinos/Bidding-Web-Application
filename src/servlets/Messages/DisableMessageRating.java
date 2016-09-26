package servlets.Messages;

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
        Integer seller_id = Integer.valueOf(request.getParameter("seller_id"));

        // Get rating value (-1 for negative, +1 for positive)
        Integer rating = Integer.valueOf(request.getParameter("rate_val"));

        // Get message id in order to update is_auto value
        Integer message_id = Integer.valueOf(request.getParameter("msg_id"));

        System.out.println("Seller to be rated: " + seller_id);
        System.out.println("Rating to be added: " + rating);
        System.out.println("Message to be updated: " + message_id);
    }
}
