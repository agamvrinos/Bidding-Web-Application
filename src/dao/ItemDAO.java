package dao;

import entities.Bid;
import entities.Item;

import javax.xml.transform.Result;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class ItemDAO {

    private static final String SQL_GET_CATEGORIES = "SELECT category FROM item_categories WHERE id = 0 ";
    private static final String SQL_ADD_NEW_ITEM = "INSERT INTO items (name, currently, buy_price, first_bid, country, location, latitude, longitude, creation, ends, seller, description, Image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_MATCH_ITEM_CATEGORY = "INSERT INTO item_categories (id, category) VALUES (?, ?)";
    private static final String SQL_GET_USER_AUCTIONS = "SELECT * FROM items WHERE items.seller = (?)";
    private static final String SQL_GET_ITEM_CATEGORIES = "SELECT * FROM item_categories WHERE item_categories.id = (?)";
    private static final String SQL_ACTIVATE_AUCTION = "UPDATE items SET state=1 WHERE id= ?";
    private static final String SQL_GET_ITEM = "SELECT * FROM items WHERE items.id = (?)";
    private static final String SQL_GET_AUCTIONS_BY_CAT = "SELECT * FROM items,item_categories WHERE items.id = item_categories.id AND items.state=1 AND item_categories.category = (?)";

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
                Date creation = results.getTimestamp("creation");
                Date starts = results.getTimestamp("starts");
                Date ends = results.getTimestamp("ends");
                String seller = results.getString("seller");            //done
                String description = results.getString("description");  //done
                Integer state = results.getInt("state");
                String image = results.getString("image");            //done

                PreparedStatement statement2 = DAOUtil.prepareStatement(connection, SQL_GET_ITEM_CATEGORIES, false, id);
                ResultSet results2 = statement2.executeQuery();

                List<String> categories = new ArrayList<>();

                while(results2.next()) {
                    categories.add(results2.getString("category"));
                }

                Item item = new Item(id, title, current, first_bid, buy_price, country, location, latitude, longitude, creation, starts, ends, seller, description, categories, null, state, image);

                userAuctions.add(item);
            }

        }
        catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }

        return userAuctions;
    }

    public List<Item> getAuctionsByCategory(String category){

        List<Item> auctions = new ArrayList<>();

        try {

            Connection connection = factory.getConnection();

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_GET_AUCTIONS_BY_CAT, false, category);
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
                Date creation = results.getTimestamp("creation");
                Date starts = results.getTimestamp("starts");
                Date ends = results.getTimestamp("ends");
                String seller = results.getString("seller");            //done
                String description = results.getString("description");  //done
                Integer state = results.getInt("state");
                String image = results.getString("image");

//                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//                String ends2 = formatter.format(ends);
//                System.out.println("ENDS " + ends);

                PreparedStatement statement2 = DAOUtil.prepareStatement(connection, SQL_GET_ITEM_CATEGORIES, false, id);
                ResultSet results2 = statement2.executeQuery();

                List<String> categories = new ArrayList<>();

                while(results2.next()) {
//                    System.out.print("Category: " + results2.getString("category"));
                    categories.add(results2.getString("category"));
                }

                Item item = new Item(id, title, current, first_bid, buy_price, country, location, latitude, longitude, creation, starts, ends, seller, description, categories, null, state, image);

                auctions.add(item);
            }

        }
        catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }

        return auctions;
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

            if (item.getImage().equals(""))
                item.setImage(null);

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_ADD_NEW_ITEM, true, item.getName(),
                    item.getCurrently(), item.getBuy_price(), item.getFirst_bid(), item.getCountry(), item.getLocation(),
                    item.getLatitude(), item.getLongitude(), item.getCreation(), item.getEnds(), item.getSeller(),
                    item.getDesc(), item.getImage());

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

    public List<Bid> getItemBids(Integer id){
        //TODO: PREPEI NA PAIKSW BALITSA ME TH VASOULA KAI TA BIDS
        return new ArrayList<Bid>();
    }

    public Item getItemByID(Integer id){
        try {
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_GET_ITEM, false, id);
            ResultSet result = statement.executeQuery();

            if(result.next()){

                Item item = new Item(id, result.getString("name"), result.getDouble("currently"), result.getDouble("first_bid"),
                        result.getDouble("buy_price"), result.getString("country"), result.getString("location"),
                        result.getDouble("latitude"), result.getDouble("longitude"), result.getDate("creation"),
                        result.getDate("starts"), result.getDate("ends"), result.getString("seller"), result.getString("description"),
                        null, getItemBids(id), result.getInt("state"), result.getString("image"));

                statement = DAOUtil.prepareStatement(connection, SQL_GET_ITEM_CATEGORIES, false, id);
                ResultSet results = statement.executeQuery();

                List<String> categories = new ArrayList<>();

                while(results.next()) {
                    categories.add(results.getString("category"));
                }

                item.setCategories(categories);

                return item;

            }
            else{
                return null;
            }

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }


    }
}
