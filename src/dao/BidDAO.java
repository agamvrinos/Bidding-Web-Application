package dao;

import entities.Bid;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class BidDAO {

    private ConnectionFactory factory;

    private static final String SQL_INSERT_BID = "INSERT INTO bids (item_id, username, bid_time, bid_amount) VALUES (?, ?, ?, ?)";
    private static final String SQL_INSERT_RATING = "INSERT INTO ratings (username, rating) " +
            "SELECT col1, col2 " +
            "FROM (select ? as col1, ? as col2) t " +
            "WHERE NOT EXISTS (SELECT * FROM ratings WHERE username = ?);";

    public BidDAO(boolean pool) {
        factory = ConnectionFactory.getInstance(pool);
    }

    public void insertBid(Bid bid, Integer item_id) {

        String bidder_name = bid.getUsername();
        Integer bidder_rating = bid.getRating();
        Date bid_time = bid.getTime();
        Double bid_amount = bid.getAmount();

        try {
            Connection connection = factory.getConnection();
            PreparedStatement statement;

            // Check if Bidder exists and if not create the User-Bidder
            UserDAO udao = new UserDAO(true);

            boolean exists = udao.existsUsername(bidder_name);

            if (exists)
                System.out.println("Bidder UserName exists. Dont add it");
            else {
                System.out.println("Bidder Username does not exist. Need to create new user");
                User user = new User("Nikolaos Korompos", bidder_name, "root", "root@email.com", "6934999656",
                        "Ελλάδα", "Αθήνα", "Κορόμπου 13", "1543", 1);

                // Insert new user
                udao.insertUser(user);

                // Validate User
                udao.approveUser(bidder_name, 1);
            }

            // Insert the Bid
            statement = DAOUtil.prepareStatement(connection, SQL_INSERT_BID, true, item_id, bidder_name, bid_time, bid_amount);

            if (statement.executeUpdate() == 0)
                throw new RuntimeException("Creating Bid failed, no rows affected.");

            // Insert User-Bidder rating
            insertRating(bidder_name, bidder_rating);

            connection.close();

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            throw new RuntimeException("Errori at InsertBid");
        }
    }

    public void insertRating(String username, Integer rating) {
        try {
            Connection connection = factory.getConnection();
            PreparedStatement statement;

            // Insert User-Bidder rating
            statement = DAOUtil.prepareStatement(connection, SQL_INSERT_RATING, true, username, rating, username);

            if (statement.executeUpdate() == 0)
                System.out.println("Rated user already exists");

            connection.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            throw new RuntimeException("Errori at InsertRating");
        }
    }
}