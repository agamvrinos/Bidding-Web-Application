package dao;

import entities.AppEntities.Bid;
import entities.AppEntities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BidDAO {

    private ConnectionFactory factory;

    private static final String SQL_INSERT_BID = "INSERT INTO bids (item_id, username, bid_time, bid_amount) VALUES (?, ?, ?, ?)";
    private static final String SQL_INSERT_RATING = "INSERT INTO ratings (username, rating) " +
            "SELECT col1, col2 " +
            "FROM (select ? as col1, ? as col2) t " +
            "WHERE NOT EXISTS (SELECT * FROM ratings WHERE username = ?);";
    private static final String SQL_UPDATE_RATING = "UPDATE ratings SET rating = rating + ? WHERE username = ?";
    private static final String SQL_GET_ITEM_BIDS = "SELECT bids.bid_id, bids.username, bids.bid_time, bids.bid_amount," +
            " users.country, users.city, ratings.rating FROM ted.bids, ted.users, ted.ratings WHERE bids.item_id = ? " +
            "AND users.username = bids.username AND users.username = ratings.username ORDER BY bids.bid_amount DESC";

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

            // If user does not exist
            if (!exists){
                User user = new User("Nikolaos Korompos", bidder_name, "root", "root@email.com", "6934999656",
                        bid.getCountry(), bid.getLocation(), "Κορόμπου 13", "1543", 1);

                // Insert new user
                udao.insertUser(user, 1);
            }

            // Insert the Bid
            statement = DAOUtil.prepareStatement(connection, SQL_INSERT_BID, true, item_id, bidder_name, bid_time, bid_amount);

            if (statement.executeUpdate() == 0)
                throw new RuntimeException("Creating Bid failed, no rows affected.");

            // Insert User-Bidder rating
            new UserDAO(true).insertRating(bidder_name, bidder_rating);

            connection.close();

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            throw new RuntimeException("Errori at InsertBid");
        }
    }

//    public void insertRating(String username, Integer rating) {
//        try {
//            Connection connection = factory.getConnection();
//            PreparedStatement statement;
//
//            // Insert User-Bidder rating
//            statement = DAOUtil.prepareStatement(connection, SQL_INSERT_RATING, true, username, rating, username);
//            statement.executeUpdate();
//
//            connection.close();
//        } catch (SQLException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//            throw new RuntimeException("Errori at InsertRating");
//        }
//    }
//
//    public void updateRating(String username, Integer rating_val) {
//        try {
//            Connection connection = factory.getConnection();
//            PreparedStatement statement;
//
//            // Update User rating
//            statement = DAOUtil.prepareStatement(connection, SQL_UPDATE_RATING, true, rating_val, username);
//            statement.executeUpdate();
//
//            connection.close();
//        } catch (SQLException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//            throw new RuntimeException("Errori at UpdateRating");
//        }
//    }

    public List<Bid> getItemBids(Integer id){

        List<Bid> bids = new ArrayList<Bid>();

        try {
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_GET_ITEM_BIDS, false, id);
            ResultSet result = statement.executeQuery();

            while (result.next()){
                bids.add(new Bid(Integer.parseInt(result.getString(1)),result.getString(2), result.getString(5),
                        result.getString(6), Integer.parseInt(result.getString(7)), result.getTimestamp(3),
                        Double.parseDouble(result.getString(4))));
            }

            connection.close();
            return bids;
        }
        catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
            throw new RuntimeException("Error at getItemBids");
        }

    }
}