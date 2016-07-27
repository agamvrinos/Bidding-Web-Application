package dao;

import entities.Item;

import java.sql.*;
import java.util.*;

public class ItemDAO {

    private static final String SQL_GET_CATEGORIES = "SELECT category FROM item_categories WHERE id = 0 ";
    private static final String SQL_ADD_NEW_ITEM = "INSERT INTO items (name, currently, buy_price, first_bid, country, location, latitude, longitude, creation, ends, seller, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_MATCH_ITEM_CATEGORY = "INSERT INTO item_categories (id, category) VALUES (?, ?)";

    private ConnectionFactory factory;


    public ItemDAO(boolean pool)
    {
        factory = ConnectionFactory.getInstance(pool);
    }

    public List<String> getCategories(){

        List<String> list = new ArrayList<String>();

        try{
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_GET_CATEGORIES, false);
            ResultSet results = statement.executeQuery();

            while(results.next()){
                list.add(results.getString("category"));
            }

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return list;
    }

    public void insertItem(Item item){

        try {

            Connection connection = factory.getConnection();

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_ADD_NEW_ITEM, true, item.getName(),
                    item.getCurrently(), item.getBuy_price(), item.getFirst_bid(), item.getCountry(), item.getLocation(),
                    item.getLatitude(), item.getLongitude(), item.getCreation(), item.getEnds(), item.getSeller(),
                    item.getDesc());

            if (statement.executeUpdate()==0)
                System.err.println("Creating item failed, no rows affected.");


            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);

            for(int i=0; i<item.getCategories().size(); i++) {
                statement = DAOUtil.prepareStatement(connection, SQL_MATCH_ITEM_CATEGORY, false, id, item.getCategories().get(i));
                statement.executeUpdate();
            }

        }
        catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
            return;
        }
    }

}
