package dao;

import entities.Item;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ItemDAO {

    private static final String SQL_GET_CATEGORIES = "SELECT category FROM item_categories WHERE id = 0 ";
    private static final String SQL_ADD_NEW_ITEM = "INSERT INTO items (name, currently, buy_price, first_bid, country, location, latitude, longitude, creation, ends, seller, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_MATCH_ITEM_CATEGORY = "INSERT INTO item_categories (id, category) VALUES (?, ?)";
    private static final String SQL_GET_USER_AUCTIONS = "SELECT * FROM items WHERE items.seller = (?)";
    private static final String SQL_GET_ITEM_CATEGORIES = "SELECT * FROM item_categories WHERE item_categories.id = (?)";
    private static final String SQL_ACTIVATE_AUCTION = "UPDATE items SET state=1 WHERE id= ?";

    private ConnectionFactory factory;


    public ItemDAO(boolean pool)
    {
        factory = ConnectionFactory.getInstance(pool);
    }

    public List<Item> getUserAuctions (String username){

        List<Item> userAuctions = new ArrayList<>();

        try {

            Connection connection = factory.getConnection();

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_GET_USER_AUCTIONS, false, username);
            ResultSet results = statement.executeQuery();

            while(results.next()){

                Integer id = results.getInt("id");
                String title = results.getString("name");       //done
                Double current = results.getDouble("currently");
                Double buy_price = DAOUtil.getDouble(results, "buy_price");
                Double first_bid = results.getDouble("first_bid");
                String country = results.getString("country");  //done
                String location = results.getString("location");//done
                Double latitude = DAOUtil.getDouble(results, "latitude");
                Double longitude = DAOUtil.getDouble(results, "longitude");
                Date creation = results.getDate("creation");
                Date starts = results.getDate("starts");
                Date ends = results.getDate("ends");
                String seller = results.getString("seller");            //done
                String description = results.getString("description");  //done
                Integer state = results.getInt("state");

                System.out.println("id = " + id);
                System.out.println("TITLE = " + title);
                System.out.println("current = " + current);
                System.out.println("buy_price = " + buy_price);
                System.out.println("first_bid = " + first_bid);
                System.out.println("country = " + country);
                System.out.println("location = " + location);
                System.out.println("latitude = " + latitude);
                System.out.println("longitude = " + longitude);
                System.out.println("creation = " + creation);
                System.out.println("starts = " + starts);
                System.out.println("ends = " + ends);
                System.out.println("seller = " + seller);
                System.out.println("description = " + description);
                System.out.println("state = " + state);
                System.out.println("=============================================");

                PreparedStatement statement2 = DAOUtil.prepareStatement(connection, SQL_GET_ITEM_CATEGORIES, false, id);
                ResultSet results2 = statement2.executeQuery();

                List<String> categories = new ArrayList<>();

                while(results2.next()) {
                    System.out.print("Category: " + results2.getString("category"));
                    categories.add(results2.getString("category"));
                }

                Item item = new Item(id, title, current, first_bid, buy_price, country, location, latitude, longitude, creation, starts, ends, seller, description, categories, null, state);

                userAuctions.add(item);
            }

        }
        catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }

        return userAuctions;
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

    public boolean activateAuction(String id){

        try{
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_ACTIVATE_AUCTION, false, id);

            statement.executeUpdate();

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
}
