package dao;

import entities.Bid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class BidDAO {

    private ConnectionFactory factory;

    private final String SQL_INSERT_BID = "INSERT INTO bids (item_id, username, bid_time, bid_amount) VALUES (?, ?, ?, ?)";

    public BidDAO(boolean pool)
    {
        factory = ConnectionFactory.getInstance(pool);
    }

    public void insertBid(Bid bid, Integer item_id, String username){

        Date bid_time = bid.getTime();
        Double bid_amount = bid.getAmount();

        try {
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_INSERT_BID, true, item_id, username, bid_time, bid_amount);

            if (statement.executeUpdate() == 0)
                throw new RuntimeException("Creating Bid failed, no rows affected.");

            connection.close();

        } catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
            throw new RuntimeException("Errori at InsertBid");
        }
    }
}
