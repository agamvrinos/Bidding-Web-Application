package servlets;

import dao.ItemDAO;
import entities.AppEntities.Item;
import entities.AppEntities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteAuction")
public class DeleteAuction extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        if(user == null || user.getRole() == 2){
            response.sendRedirect("error_page.jsp");
            return;
        }

        ItemDAO dao = new ItemDAO(true);
        Integer id;
        String auction_id = request.getParameter("id");

        try {
            id = Integer.parseInt(auction_id);
        }
        catch(NumberFormatException | NullPointerException e) {
            response.sendRedirect("error_page.jsp");
            return;
        }

        Item item = dao.getItemByID(id);
        if (item == null) {
            response.sendRedirect("error_page.jsp");
            return;
        }

        if(!dao.belongsToUser(user.getUsername(), item.getId())) {
            response.sendRedirect("error_page.jsp");
            return;
        }

        if (!(item.getState() == 0 || (item.getState() == 1 && item.getTotal_offers() == 0))){
            RequestDispatcher dispatcher = request.getRequestDispatcher("my_auctions.jsp");
            request.setAttribute("error-message","Είναι αδύνατη η διαγραφή του προϊόντος εφόσον υπάρχει τουλάχιστον μια προσφορά!");
            dispatcher.forward(request, response);
            return;
        }


        boolean result = dao.deleteItem(id);

        if (!result)
            response.sendRedirect("error_page.jsp");
        else
            response.sendRedirect("my_auctions.jsp");

    }
}
