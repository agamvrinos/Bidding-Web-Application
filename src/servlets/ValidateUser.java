package servlets;

import dao.UserDAO;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/ValidateUser")
public class ValidateUser extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        if (user.getRole() != 0){
            response.sendRedirect("index.jsp");
            return;
        }

        UserDAO dao = new UserDAO(true);
        dao.approveUser(request.getParameter("id"), 0);
        response.sendRedirect("userprofile.jsp?id=" + request.getParameter("id"));
    }

}
