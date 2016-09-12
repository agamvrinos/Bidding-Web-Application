package servlets.Messages;

import dao.MessageDAO;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteMessage")
public class DeleteMessage extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer message_id = Integer.valueOf(request.getParameter("id"));
        Integer type = Integer.valueOf(request.getParameter("type"));

        MessageDAO dao = new MessageDAO(true);
        dao.deleteMessageOfUser(message_id, type);

        response.sendRedirect("my_messages.jsp");
    }
}
