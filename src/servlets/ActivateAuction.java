package servlets;

import dao.DAOUtil;
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
import java.util.Date;

@WebServlet("/ActivateAuction")
public class ActivateAuction extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if(user==null || user.getRole() == 2) {
            response.sendRedirect("error_page.jsp");
            return;
        }

        String id = request.getParameter("id");
        Integer int_id = DAOUtil.IntConvert(id);

        if(int_id == null){
            response.sendRedirect("error_page.jsp");
            return;
        }

        // Activate Auction (state = 1)
        ItemDAO dao = new ItemDAO(true);
        Item item = dao.getItemByID(int_id);

        if(!dao.belongsToUser(user.getUsername(), item.getId())) {
            response.sendRedirect("error_page.jsp");
            return;
        }

        if(item.getState() != 0) {
            response.sendRedirect("error_page.jsp");
            return;
        }

        if(!new Date().before(item.getEnds())){
            RequestDispatcher dispatcher = request.getRequestDispatcher("my_auctions.jsp");
            request.setAttribute("error-message","Η χρονική στιγμή λήξης της δημοπρασίας προηγείται του παρόντος!");
            dispatcher.forward(request, response);
            return;
        }

        boolean result = dao.activateAuction(id);

        if (result)
            response.sendRedirect("my_auctions.jsp");
        else
            response.sendRedirect("error_page.jsp");
    }
}
