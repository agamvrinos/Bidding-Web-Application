package servlets;

import dao.ItemDAO;
import dao.MessageDAO;
import dao.UserDAO;
import entities.AppEntities.Item;
import entities.AppEntities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/BuyAuction")
public class BuyAuction extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Set request encoding to UTF-8
        request.setCharacterEncoding("UTF-8");

        // Get auction parameters
        String id = request.getParameter("id");
        Integer auction_id = Integer.valueOf(id);

        ItemDAO dao = new ItemDAO(true);

        // Check if auction exists in DB (if exists)
        boolean exists = dao.existsItem(auction_id);

        // Set response encoding to UTF-8
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        RequestDispatcher dispatcher = request.getRequestDispatcher("my_messages.jsp");

        // This means the item has been bought
        if (!exists){
            request.setAttribute("bought","yes");
            dispatcher.forward(request, response);
            return;
        }

        // Check if in the meantime the auction has ended
        Date now = new Date();
        Item item = dao.getItemByID(auction_id);
        Date ends = item.getEnds();

        if(item.getState() != 1){
            response.sendRedirect("singleproduct.jsp?id=" + auction_id);
            return;
        }

        if (now.compareTo(ends) > 0){
            request.setAttribute("ended","yes");
            dispatcher.forward(request, response);
            return;
        }

        User buyer = (User) request.getSession().getAttribute("user");
        if (buyer == null || buyer.getRole() == 1) {
            response.sendRedirect("error_page.jsp");
            return;
        }

        // If I get here then the item CAN be bought

        // 1) Send a message to the user that bought it
        UserDAO udao = new UserDAO(true);
        User seller = udao.getUserbyName(item.getSeller());

        Integer seller_id = seller.getId();
        Integer buyer_id = buyer.getId();
        MessageDAO mdao = new MessageDAO(true);

        mdao.autoSuccessMessage(item.getId(), item.getName(), seller_id, buyer_id, now);

        // 2) Buy Item
        dao.disableAuction(id);

        response.sendRedirect("my_messages.jsp");
    }
}
