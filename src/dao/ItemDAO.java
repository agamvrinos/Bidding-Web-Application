package dao;

import entities.Bid;
import entities.Item;
import entities.Message;
import entities.User;

import java.sql.*;
import java.util.Date;
import java.util.*;

public class ItemDAO {

    private final Integer LOW_BET = -1;

    private static final String SQL_GET_CATEGORIES = "SELECT category FROM item_categories WHERE id = 0 ";
    private static final String SQL_ADD_NEW_ITEM = "INSERT INTO items (name, currently, buy_price, first_bid, country, location, latitude, longitude, creation, ends, seller, description, Image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_IMPORT_ITEM = "INSERT INTO items (name, currently, buy_price, first_bid, country, location, latitude, longitude, creation, starts, ends, seller, description, state, Image, total_offers) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_ITEM = "DELETE FROM items WHERE id = (?)";
    private static final String SQL_MATCH_ITEM_CATEGORY = "INSERT INTO item_categories (id, category) VALUES (?, ?)";
    private static final String SQL_DEL_OLD_CATEGORY = "DELETE FROM item_categories WHERE id = (?) AND category = (?)";
    private static final String SQL_GET_USER_AUCTIONS = "SELECT * FROM items WHERE items.seller = (?)";
    private static final String SQL_GET_ITEM_CATEGORIES = "SELECT * FROM item_categories WHERE item_categories.id = (?)";
    private static final String SQL_ACTIVATE_AUCTION = "UPDATE items SET state=1 WHERE id= ?";
    private static final String SQL_DISABLE_AUCTION = "UPDATE items SET state=-1 WHERE id= ?";
    private static final String SQL_GET_ITEM = "SELECT * FROM items WHERE items.id = (?)";
    private static final String SQL_GET_CATEGORY = "SELECT * FROM item_categories WHERE category = (?) AND id = 0";
    private static final String SQL_INSERT_CATEGORY = "INSERT INTO item_categories (id, category) VALUES (0, ?)";
    private static final String SQL_CHANGE_ITEM_STATE = "UPDATE items SET state=-1 WHERE state=1 AND ends<=NOW()";
    private static final String SQL_GET_AUCTIONS_BY_CAT = "SELECT * FROM items,item_categories WHERE items.id = item_categories.id AND item_categories.category = (?)";
    private static final String SQL_UPDATE_ITEM = "UPDATE items SET name = (?), currently = (?), buy_price = (?), first_bid = (?)," +
            " country = (?), location = (?), latitude = (?), longitude = (?), creation = (?)," +
            " ends = (?), seller = (?), description = (?), Image = (?), total_offers = (?) WHERE id = (?)";
    private static final String SQL_GET_TOTAL_BIDS = "SELECT total_offers FROM items WHERE id = (?)";
    private static final String SQL_UPDATE_ITEM_BIDS = "UPDATE items SET total_offers = (?), currently = (?) WHERE id = (?)";
    private static final String SQL_INSERT_NEW_BID = "INSERT INTO bids (item_id, username, bid_time, bid_amount) VALUES (?, ?, ?, ?)";
    private static final String SQL_SEARCH_ITEMS_BY_NAME = "SELECT DISTINCT id FROM items WHERE name LIKE ?";
    private static final String SQL_SEARCH_ITEMS_BY_CATEGORY = "SELECT DISTINCT id FROM item_categories WHERE category LIKE ?";
    private static final String SQL_SEARCH_ITEMS_BY_DESCRIPTION = "SELECT DISTINCT id FROM items WHERE description LIKE ?";
    private static final String SQL_SEARCH_ITEMS_BY_PRICE = "SELECT DISTINCT id FROM items WHERE currently < ?";
    private static final String SQL_SEARCH_ITEMS_BY_LOCATION = "SELECT DISTINCT id FROM items WHERE country LIKE ? OR location LIKE ?";

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
                Integer total_offers = results.getInt("total_offers");

                PreparedStatement statement2 = DAOUtil.prepareStatement(connection, SQL_GET_ITEM_CATEGORIES, false, id);
                ResultSet results2 = statement2.executeQuery();

                List<String> categories = new ArrayList<>();

                while(results2.next()) {
                    categories.add(results2.getString("category"));
                }

                Item item = new Item(id, title, current, first_bid, buy_price, country, location, latitude, longitude, creation, starts, ends, seller, description, categories, null, state, image, total_offers);

                userAuctions.add(item);
            }
            connection.close();

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
                Integer total_offers = results.getInt("total_offers");

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

                Item item = new Item(id, title, current, first_bid, buy_price, country, location, latitude, longitude, creation, starts, ends, seller, description, categories, null, state, image, total_offers);

                auctions.add(item);
            }
            connection.close();
        }
        catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }

        return auctions;
    }

    public List<String> getCategories(){

        List<String> list = new ArrayList<>();

        try{
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_GET_CATEGORIES, false);
            ResultSet results = statement.executeQuery();

            while(results.next()){
                list.add(results.getString("category"));
            }
            connection.close();
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return list;
    }

    public Integer insertItem(Item item, Integer imported){

        try {

            Connection connection = factory.getConnection();

            if (item.getImage().equals(""))
                item.setImage(null);

            PreparedStatement statement;

            if (imported == 0)
                statement= DAOUtil.prepareStatement(connection, SQL_ADD_NEW_ITEM, true, item.getName(),
                        item.getCurrently(), item.getBuy_price(), item.getFirst_bid(), item.getCountry(), item.getLocation(),
                        item.getLatitude(), item.getLongitude(), item.getCreation(), item.getEnds(), item.getSeller(),
                        item.getDesc(), item.getImage());
            else
                statement= DAOUtil.prepareStatement(connection, SQL_IMPORT_ITEM, true, item.getName(),
                        item.getCurrently(), item.getBuy_price(), item.getFirst_bid(), item.getCountry(), item.getLocation(),
                        item.getLatitude(), item.getLongitude(), item.getCreation(), item.getStarts(), item.getEnds(), item.getSeller(),
                        item.getDesc(), item.getState(), item.getImage(), item.getTotal_offers());

            if (statement.executeUpdate() == 0)
                throw new RuntimeException("Creating item failed, no rows affected.");


            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);

            for(int i=0; i<item.getCategories().size(); i++) {
                statement = DAOUtil.prepareStatement(connection, SQL_MATCH_ITEM_CATEGORY, false, id, item.getCategories().get(i));
                statement.executeUpdate();
            }

            connection.close();
            return id;
        }
        catch (SQLException ex){
            throw new RuntimeException("ERROR: " + ex.getMessage());
        }
    }

    public boolean updateItem(Item item, Item old){
        try {
            Connection connection = factory.getConnection();

            if (item.getImage().equals(""))
                item.setImage(null);

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_UPDATE_ITEM, true, item.getName(),
                    item.getCurrently(), item.getBuy_price(), item.getFirst_bid(), item.getCountry(), item.getLocation(),
                    item.getLatitude(), item.getLongitude(), item.getCreation(), item.getEnds(), item.getSeller(),
                    item.getDesc(), item.getImage(), item.getTotal_offers(), item.getId());

            Integer id = item.getId();
            statement.executeUpdate();

            for(int i = 0; i < old.getCategories().size(); i++) {
                statement = DAOUtil.prepareStatement(connection, SQL_DEL_OLD_CATEGORY, false, id, old.getCategories().get(i));
                statement.executeUpdate();
            }
            for(int i = 0; i < item.getCategories().size(); i++) {
                statement = DAOUtil.prepareStatement(connection, SQL_MATCH_ITEM_CATEGORY, false, id, item.getCategories().get(i));
                statement.executeUpdate();
            }

            connection.close();
        }
        catch (SQLException ex){
            System.out.println("ERROR2: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteItem(Integer id){

        ItemDAO dao = new ItemDAO(true);
        Item item_to_del = dao.getItemByID(id);

        List<String> categories = item_to_del.getCategories();

        try{
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_DELETE_ITEM, false, id);
            statement.executeUpdate();

            for(int i = 0; i < categories.size(); i++) {
                statement = DAOUtil.prepareStatement(connection, SQL_DEL_OLD_CATEGORY, false, id, categories.get(i));
                statement.executeUpdate();
            }
            connection.close();

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean activateAuction(String id){

        try{
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_ACTIVATE_AUCTION, false, id);

            statement.executeUpdate();
            connection.close();

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void disableAuction(String id){
        try{
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_DISABLE_AUCTION, false, id);

            statement.executeUpdate();

            connection.close();
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
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
                        result.getDouble("latitude"), result.getDouble("longitude"), result.getTimestamp("creation"),
                        result.getTimestamp("starts"), result.getTimestamp("ends"), result.getString("seller"), result.getString("description"),
                        null, getItemBids(id), result.getInt("state"), result.getString("image"), result.getInt("total_offers"));

                statement = DAOUtil.prepareStatement(connection, SQL_GET_ITEM_CATEGORIES, false, id);
                ResultSet results = statement.executeQuery();

                List<String> categories = new ArrayList<>();

                while(results.next()) {
                    categories.add(results.getString("category"));
                }

                item.setCategories(categories);

                connection.close();
                return item;

            }
            else{
                connection.close();
                return null;
            }

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }


    }

    public void updateExpiredItems(){

        try {

            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_CHANGE_ITEM_STATE, true);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();

            int i=0;

            while (rs.next()){
                i++;
            }

//            System.out.println(i);

            connection.close();

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean existsItem(Integer id){

        try {

            Connection connection = factory.getConnection();

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_GET_ITEM, false, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next() ) {   // No auction found
                System.out.println("no data");
                connection.close();
                return false;
            }
            connection.close();
        }
        catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
            throw new RuntimeException("Error at existsItem");
        }
        return true;
    }

    public boolean existsCategory(String category_name){

        try {
            Connection connection = factory.getConnection();

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_GET_CATEGORY, false, category_name);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next() ) {   // No auction found
                connection.close();
                return false;
            }
            connection.close();
        }
        catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
            throw new RuntimeException("Error at existsCategory");
        }
        return true;
    }

    public Integer BetAuction(Integer id, Double bid_value, String username){

        Item item = getItemByID(id);

        Integer current_offers = item.getTotal_offers();
        Double current_bid = item.getCurrently();

        // User should bid more than the current bid
        if (bid_value < current_bid){
            return LOW_BET;
        }

        Bid bid = new Bid(null, username, null, null, null, new Date(), bid_value);

        try{

            Connection connection = factory.getConnection();

            //insert new bid
            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_INSERT_NEW_BID, false, id, bid.getUsername(), bid.getTime(), bid.getAmount());

            if(statement.executeUpdate()==0)
                throw new SQLException();

            //get total offers
            statement = DAOUtil.prepareStatement(connection, SQL_GET_TOTAL_BIDS, false, id);
            ResultSet result = statement.executeQuery();

            result.next();
            Integer total = result.getInt("total_offers");
            total++;

            //update total_offers and current bid
            statement = DAOUtil.prepareStatement(connection, SQL_UPDATE_ITEM_BIDS, false, total, bid_value, id);

            if(statement.executeUpdate()==0)
                throw new SQLException();


            connection.close();
            return 0;

        }
        catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
            throw new RuntimeException("Error at BetAuction");
        }
    }

    public List<Item> getSearchResults(String value, String type){
        List<Item> items = new ArrayList<Item>();

        try{
            Connection connection = factory.getConnection();
            PreparedStatement statement;

            if(type.equals("name")) {
                statement = DAOUtil.prepareStatement(connection, SQL_SEARCH_ITEMS_BY_NAME, false);
                statement.setString(1, "%" + value + "%");
            }
            else if (type.equals("category")) {
                statement = DAOUtil.prepareStatement(connection, SQL_SEARCH_ITEMS_BY_CATEGORY, false);
                statement.setString(1, "%" + value + "%");
            }
            else if(type.equals("description")) {
                statement = DAOUtil.prepareStatement(connection, SQL_SEARCH_ITEMS_BY_DESCRIPTION, false);
                statement.setString(1, "%" + value + "%");
            }
            else if (type.equals("price")) {
                statement = DAOUtil.prepareStatement(connection, SQL_SEARCH_ITEMS_BY_PRICE, false, value);
            }
            else if (type.equals("location")) {
                statement = DAOUtil.prepareStatement(connection, SQL_SEARCH_ITEMS_BY_LOCATION, false);
                statement.setString(1, "%" + value + "%");
                statement.setString(2, "%" + value + "%");
            }
            else
                return items;



            //execute query
            ResultSet result = statement.executeQuery();

            //get a list of items
            while (result.next()) {
                Integer id = Integer.parseInt(result.getString("id"));
                if (!id.equals(0)) {
                    Item item = getItemByID(id);
                    items.add(item);
                }
            }

            return items;

        }
        catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
            throw new RuntimeException("Error at getSearchResults");
        }
    }

    public void loadXmlEntities(Item item){

        Integer id = item.getId();
        String title = item.getName();
        Double current = item.getCurrently();
        Double buy_price = item.getBuy_price();
        Double first_bid = item.getFirst_bid();
        String country = item.getCountry();
        String location = item.getLocation();
        Double latitude = item.getLatitude();
        Double longitude = item.getLongitude();
        Date creation = item.getCreation();
        Date starts = item.getStarts();
        Date ends = item.getEnds();
        String seller = item.getSeller();
        String description = item.getDesc();
        Integer state = item.getState();
        String image = item.getImage();
        Integer total_offers = item.getTotal_offers();
        List<String> categories = item.getCategories();
        List<Bid> bids = item.getBids();

        PreparedStatement statement;

        try{
            // Get connection
            Connection connection = factory.getConnection();

            //DB-Create the user that sells the item if he doesn't exist
            UserDAO udao = new UserDAO(true);
            ItemDAO idao = new ItemDAO(true);
            BidDAO bdao = new BidDAO(true);

            boolean exists = udao.existsUsername(seller);

            if (exists)
                System.out.println("UserName exists. Dont add it");
            else {
                System.out.println("Username does not exist. Need to create new user");
                User user = new User("Nikolaos Korompos", seller, "root", "root@email.com", "6934999656",
                                    "Ελλάδα", "Αθήνα", "Κορόμπου 13", "1543", 1);

                // Insert new user
                udao.insertUser(user);

                // Validate User
                udao.approveUser(seller, 1);
            }
            //Create new categories if not exist
            for (String category : categories){
                boolean exists_cat = existsCategory(category);

                if (exists_cat)
                    System.out.println("Category \"" + category + "\" already exists. Skip it." );
                else {
                    System.out.println("Category \"" + category + "\" does not exist. Add it." );

                    statement = DAOUtil.prepareStatement(connection, SQL_INSERT_CATEGORY, true, category);

                    if (statement.executeUpdate() == 0)
                        System.err.println("Creating Category failed, no rows affected.");
                }
            }

            //DB-Create The item. Don't use the ID provided.
            Integer item_id = idao.insertItem(item, 1);

            //TODO: DB-Create the bids for the specific item
            for (Bid bid : bids)
                bdao.insertBid(bid, item_id, seller);

            //TODO: DB-Create the rating for this user

            connection.close();
        }
        catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
            throw new RuntimeException("Error at LoadXmlEntities");
        }



    }
}
