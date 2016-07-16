package servlets;

import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/Register")
public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("UserRole");

        UserDAO dao = new UserDAO(true);
        dao.getUsers();

        System.out.println("x0a0xa0xa0");

        response.getWriter().print("Success! Name: " + fullname +
                "\nUsername: " + username + "\nPassword: " + password + "\nRole: " + role);
    }

}
