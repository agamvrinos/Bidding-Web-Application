package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import dao.UserDAO;


@WebServlet("/Login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO(true);

        if (dao.findUser(username, password)){
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.getWriter().println("Success! You have succesfully logged in with username: " + username);
        }
        else{
            response.getWriter().println("ERROR! Username or Password incorrect!");
        }

    }

}
