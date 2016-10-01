package dao;

import entities.AppEntities.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageDAO {

    private static final String SQL_GET_RECEIVED_MSGS = "SELECT * FROM messages WHERE receiver_id = (?) AND receiver_deleted=0 ORDER BY date_sent DESC";
    private static final String SQL_GET_SENT_MSGS = "SELECT * FROM messages WHERE sender_id = (?) AND sender_deleted=0 ORDER BY date_sent DESC";
    private static final String SQL_GET_MSG_BY_ID = "SELECT * FROM messages WHERE id = (?)";
    private static final String SQL_SEND_REPLY = "INSERT INTO messages (sender_id, receiver_id, message_title, message_content, date_sent) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_AUTO_MSG = "INSERT INTO messages (sender_id, receiver_id, message_title, message_content, date_sent, is_auto) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_ISREAD = "UPDATE messages SET is_read=1 WHERE id= ?";
    private static final String SQL_UPDATE_ISAUTO = "UPDATE messages SET is_auto=0 WHERE id= ?";
    private static final String SQL_GET_USER_NEW_MSGS = "SELECT COUNT(*) AS count FROM messages WHERE receiver_id = (?) AND is_read=0 AND receiver_deleted=0";
    private static final String SQL_DELETE_SEND_MSG = "UPDATE messages SET sender_deleted=1 where id = (?)";
    private static final String SQL_DELETE_REC_MSG = "UPDATE messages SET receiver_deleted=1 where id = (?)";
    private static final String SQL_DELETE_MSG = "DELETE FROM messages WHERE id = (?) AND (sender_deleted=1 AND receiver_deleted=1)";

    private ConnectionFactory factory;

    public MessageDAO(boolean pool) {
        factory = ConnectionFactory.getInstance(pool);
    }

    public List<Message> getMessages(Integer user_id, Integer operation) {
        // operation = 0 -> Get Received Messages
        // operation = 1 -> Get Send Messages

        List<Message> messages = new ArrayList<>();

        try {
            Connection connection = factory.getConnection();

            PreparedStatement statement = null;
            if (operation == 0)
                statement = DAOUtil.prepareStatement(connection, SQL_GET_RECEIVED_MSGS, false, user_id);
            else if (operation == 1)
                statement = DAOUtil.prepareStatement(connection, SQL_GET_SENT_MSGS, false, user_id);
            else
                throw new RuntimeException("Wrong Operation Given");

            ResultSet results = statement.executeQuery();

            while (results.next()) {
                Integer id = results.getInt("id");
                Integer sender_id = results.getInt("sender_id");
                Integer receiver_id = results.getInt("receiver_id");
                String message_title = results.getString("message_title");
                String message_content = results.getString("message_content");
                Integer is_read = results.getInt("is_read");
                Date date = results.getTimestamp("date_sent");
                Integer is_auto = results.getInt("is_auto");

                Message message = new Message(id, sender_id, receiver_id, message_title, message_content, is_read, date, is_auto);

                messages.add(message);
            }
            connection.close();

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }

        return messages;
    }

    public Message getMessageById(Integer message_id){

        try {
            Connection connection = factory.getConnection();

            PreparedStatement statement = null;
            statement = DAOUtil.prepareStatement(connection, SQL_GET_MSG_BY_ID, false, message_id);

            ResultSet results = statement.executeQuery();

            if(results.next()) {

                Integer id = results.getInt("id");
                Integer sender_id = results.getInt("sender_id");
                Integer receiver_id = results.getInt("receiver_id");
                String message_title = results.getString("message_title");
                String message_content = results.getString("message_content");
                Integer is_read = results.getInt("is_read");
                Date date = results.getTimestamp("date_sent");
                Integer is_auto = results.getInt("is_auto");

                Message message = new Message(id, sender_id, receiver_id, message_title, message_content, is_read, date, is_auto);

                connection.close();

                return message;
            }
            else {
                connection.close();
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("ERRORI: " + ex.getMessage());
            return null;
        }
    }

    public void sendReplyMessage(Message message) {
        Integer sender_id = message.getSender_id();
        Integer receiver_id = message.getReceiver_id();
        String message_title = message.getTitle();
        String message_content = message.getMessage();
        Date date_sent = message.getDate();

        try {
            Connection connection = factory.getConnection();

            PreparedStatement statement = null;
            statement = DAOUtil.prepareStatement(connection, SQL_SEND_REPLY, true, sender_id, receiver_id, message_title, message_content, date_sent);

            if (statement.executeUpdate() == 0)
                throw new RuntimeException("Creating item failed, no rows affected.");

            connection.close();

        } catch (SQLException ex) {
            System.out.println("ERRORI: " + ex.getMessage());
            throw new RuntimeException("Error at message reply");
        }
    }

    public void updateIsRead(Integer message_id){
        try {
            Connection connection = factory.getConnection();

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_UPDATE_ISREAD, false, message_id);
            statement.executeUpdate();

            connection.close();

        } catch (SQLException ex) {
            System.out.println("ERRORI: " + ex.getMessage());
            throw new RuntimeException("Error at update is_read");
        }
    }

    public void updateIsAuto(Integer message_id){
        try {
            Connection connection = factory.getConnection();

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_UPDATE_ISAUTO, false, message_id);
            statement.executeUpdate();

            connection.close();

        } catch (SQLException ex) {
            System.out.println("ERRORI: " + ex.getMessage());
            throw new RuntimeException("Error at update is_auto");
        }
    }

    public Integer getUserNewMessages(Integer user_id){
        Integer counter = 0;

        try {
            Connection connection = factory.getConnection();

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_GET_USER_NEW_MSGS, false, user_id);
            ResultSet results = statement.executeQuery();

            if(results.next())
                counter = results.getInt("count");
            else {
                connection.close();
                return null;
            }

            connection.close();

        } catch (SQLException ex) {
            System.out.println("ERRORI: " + ex.getMessage());
            throw new RuntimeException("Error at getUserNewMessages");
        }

        return counter;
    }

    public void deleteMessageOfUser(Integer message_id, Integer type){
        // type = 0 -> Received
        // type = 1 -> Sended

        try {
            Connection connection = factory.getConnection();
            PreparedStatement statement;

            if (type == 0)  // Receiver case
                statement = DAOUtil.prepareStatement(connection, SQL_DELETE_REC_MSG, false, message_id);
            else if (type == 1) // Sender case
                statement = DAOUtil.prepareStatement(connection, SQL_DELETE_SEND_MSG, false, message_id);
            else
                throw new RuntimeException("Wrong type value");

            statement.executeUpdate();

            // Clear message if deleted from both sides
            statement = DAOUtil.prepareStatement(connection, SQL_DELETE_MSG, false, message_id);

            statement.executeUpdate();

            connection.close();

        } catch (SQLException ex) {
            System.out.println("ERRORI: " + ex.getMessage());
            throw new RuntimeException("Error at message delete");
        }

    }

    // This function is used to send an auto message from the Seller to
    // the user that bought the item or made the biggest bid
    public void autoSuccessMessage (Integer item_id, String item_title, Integer sender_id, Integer receiver_id, Date date_sent){

        String message_title = "Συγχαρητήρια για την αγορά";
        String message_content = "Έχει δρομολογηθεί η διαδικασία αποστολής του προϊόντος: <a href=\"singleproduct.jsp?id=" + item_id + "\" >" + item_title + "</a>. Για " +
                "οποιαδήποτε απορία επικοινωνήστε μαζί μας";
        try {
            Connection connection = factory.getConnection();

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_AUTO_MSG, true, sender_id, receiver_id, message_title, message_content, date_sent, 1);

            if (statement.executeUpdate() == 0)
                throw new RuntimeException("Creating Auto Message Failed");

            connection.close();

        } catch (SQLException ex) {
            System.out.println("ERRORI: " + ex.getMessage());
            throw new RuntimeException("Error at auto message");
        }
    }


}
