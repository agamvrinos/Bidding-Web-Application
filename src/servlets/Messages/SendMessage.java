package servlets.Messages;

import dao.MessageDAO;
import entities.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the id of the message that I am replying to
        Integer message_id = Integer.valueOf(request.getParameter("id"));
        String msg_type = request.getParameter("type");

        MessageDAO dao = new MessageDAO(true);

        // Retrieve the message that I am answering
        Message message = dao.getMessageById(message_id);

        Integer my_id = null;
        Integer receiver_id = null;

        // Create message to send
        if (msg_type.equals("rec")) {
            my_id = message.getReceiver_id();
            receiver_id = message.getSender_id();
        }
        else if (msg_type.equals("send")){
            my_id = message.getSender_id();
            receiver_id = message.getReceiver_id();
        }
        String message_title = request.getParameter("title");
        String message_content = request.getParameter("message");

        Message message_to_send = new Message(null, my_id, receiver_id, message_title, message_content, 0, new Date(), 0);

        // Finally send the message
        dao.sendReplyMessage(message_to_send);

        request.setAttribute("send-message-success","yes");
        RequestDispatcher dispatcher = request.getRequestDispatcher("my_messages.jsp");
        dispatcher.forward(request, response);
    }
}
