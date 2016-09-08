package dao;

import entities.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageDAO {

    private static final String SQL_GET_RECEIVED_MSGS = "SELECT * FROM messages WHERE receiver_id = (?)";
    private static final String SQL_GET_SENT_MSGS = "SELECT * FROM messages WHERE sender_id = (?)";

    private ConnectionFactory factory;

    public MessageDAO(boolean pool)
    {
        factory = ConnectionFactory.getInstance(pool);
    }

    public List<Message> getReceivedMessages(Integer user_id){

        List<Message> sentMessages = new ArrayList<>();

        try {
            Connection connection = factory.getConnection();

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_GET_RECEIVED_MSGS, false, user_id);
            ResultSet results = statement.executeQuery();

            while(results.next()){
                Integer id = results.getInt("id");
                Integer sender_id = results.getInt("sender_id");
                Integer receiver_id = results.getInt("receiver_id");
                String message_title = results.getString("message_title");
                String message_content = results.getString("message_content");
                Integer is_read = results.getInt("is_read");
                Date date = results.getTimestamp("date_sent");

                Message message = new Message(id, sender_id, receiver_id, message_title, message_content, is_read, date);

                sentMessages.add(message);
            }
            connection.close();

        }
        catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }

        return sentMessages;
    }

    public List<Message> getSentMessages(){
        return null;
    }
}
