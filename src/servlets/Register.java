package servlets;

import dao.UserDAO;

import javax.servlet.RequestDispatcher;
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

        // Set request encoding to UTF-8
        request.setCharacterEncoding("UTF-8");

        // Get register form fields
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

        // Create UserDTO
        User userInfo = new User(fullname, username, password, email, phone, country, city, address, afm, Integer.parseInt(role));

        // Create UserDAO and Add new user to database
        UserDAO dao = new UserDAO(true);
        Integer register_result = dao.insertUser(userInfo);

        // Set response encoding to UTF-8
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        //TODO: NA VALW ME ALERT AN PAR OLA AUTA DWSEI DUPLICATE USERNAME
        if (register_result == -2){     // Duplicate Username check
            response.getWriter().print("UserName Already Exists");
            response.getWriter().println("<script type=\"text/javascript\">");
            response.getWriter().println("alert('User or password incorrect');");
            response.getWriter().println("location='register.jsp';");
            response.getWriter().println("</script>");

        }
        else if (register_result == -1) {
            response.getWriter().print("Error! Please try again.");
        }
        else if (register_result == 1){ // No Errors. Register is successful
            response.getWriter().print("Success! Name: " + fullname +
                "\nUsername: " + username + "\nPassword: " + password + "\nRole: " + role);
        }
    }

}
