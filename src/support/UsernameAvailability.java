package support;

import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UsernameAvailability")
public class UsernameAvailability extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get username from ajax
        String username = request.getParameter("username");

        // Check username availability
        UserDAO dao = new UserDAO(true);
        boolean exists = dao.existsUsername(username);

        response.setContentType("text/plain");

        if (exists)
            response.getWriter().print("Username Exists!");
    }
}
