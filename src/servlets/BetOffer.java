package servlets;

import dao.ItemDAO;
import dao.UserDAO;
import entities.AppEntities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/BetOffer")
public class BetOffer extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Set request encoding to UTF-8
        request.setCharacterEncoding("UTF-8");

        // Get auction parameters
        Integer auction_id = Integer.valueOf(request.getParameter("id"));
        String chosen_category = request.getParameter("category");
        String b = request.getParameter("bid");

        Double bid = isNumeric(b);

        // Set response encoding to UTF-8
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        RequestDispatcher dispatcher = request.getRequestDispatcher("auctionsBrowse.jsp?category=" + chosen_category);
        RequestDispatcher dispatcher2 = request.getRequestDispatcher("singleproduct.jsp?id=" + auction_id + "&" + chosen_category);
        RequestDispatcher dispatcher3 = request.getRequestDispatcher("login.jsp");

        ItemDAO dao = new ItemDAO(true);

        // Check if auction exists in DB (if exists)
        boolean exists = dao.existsItem(auction_id);

        // This means the item has been bought
        if (!exists){
            request.setAttribute("error-message","Το αντικείμενο έχει πουληθεί σε κάποιον άλλον χρήστη.");
            dispatcher.forward(request, response);
            return;
        }

        // Check if in the meantime the auction has ended
        Date now = new Date();
        Date ends = dao.getItemByID(auction_id).getEnds();

        if (now.compareTo(ends) > 0){
            request.setAttribute("error-message","Η δημοπρασία έληξε!");
            dispatcher.forward(request, response);
            return;
        }

        // Check if bet is not numeric
        if (bid == null){
            request.setAttribute("error-message","Το ποσό που προσφέρετε δεν είναι έγκυρο.");
            dispatcher2.forward(request, response);
            return;
        }

        UserDAO udao = new UserDAO(true);
        User bidder = (User) request.getSession().getAttribute("user");
        if (bidder == null){
            request.setAttribute("error-message","Πρέπει να είστε συνδεδεμένος για να υποβάλετε προσφορά.");
            dispatcher3.forward(request, response);
            return;
        }

        //place bid
        if (dao.BetAuction(auction_id, bid, bidder.getUsername()) == -1){
            request.setAttribute("error-message","Το ποσό που προσφέρετε είναι μικρότερο από το ήδη υπάρχον.");
            dispatcher2.forward(request, response);
            return;
        }

        //return to the same item page
        dispatcher2.forward(request, response);
        return;
    }

    private Double isNumeric(String str)
    {
        Double d;
        try {
            d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe){
            return null;
        }
        return d;
    }
}
