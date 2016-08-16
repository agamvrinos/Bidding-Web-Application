package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.UserDAO;
import entities.User;

@WebServlet("/Login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO(true);
        HttpSession session = request.getSession();

        // in case you are already logged in
        if (session.getAttribute("user")!=null) {
            response.sendRedirect("index.jsp");
            return;
        }

        User user = dao.getUser(username,password);

        // success
        if (user != null){
            session.setAttribute("user", user);

            if(user.getRole()==0)
                response.sendRedirect("adminpanel.jsp");
            else
                response.sendRedirect("index.jsp");

            return;
        }
        else{ // error
            request.setAttribute("login-error","yes");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            return;
        }

    }

}
