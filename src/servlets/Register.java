package servlets;

import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import entities.User;

@WebServlet("/Register")
public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String afm = request.getParameter("afm");
        String role = request.getParameter("UserRole");

        System.out.println("COUNYTY: " + country);

        // Create UserDTO
        User userInfo = new User(fullname, username, password, email, phone, country, city, address, afm, Integer.parseInt(role));

        // Create UserDAO and Add new user to database
        UserDAO dao = new UserDAO(true);
        dao.insertUser(userInfo);

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        response.getWriter().print("Success! Name: " + fullname +
                "\nUsername: " + username + "\nPassword: " + password + "\nRole: " + role);
    }

}
