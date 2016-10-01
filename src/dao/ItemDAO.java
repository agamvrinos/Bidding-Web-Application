package dao;

import entities.*;

import java.sql.*;
import java.util.Date;
import java.util.*;

public class ItemDAO {

    private final Integer LOW_BET = -1;
    private int numOfResults;
    private int numOfPages;
    private static final int RESULTS_PER_PAGE = 15;
    private static final int KNN = 30;
    private static final int MAX_REC_ITEMS_PER_USER = 10;

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
    private static final String SQL_GET_EXPIRED_ITEMS = "SELECT id FROM items WHERE state=1 AND ends<=NOW()";

    private static final String SQL_GET_AUCTIONS_BY_CAT = "SELECT * FROM items,item_categories WHERE items.id = item_categories.id AND item_categories.category = (?)";
    private static final String SQL_UPDATE_ITEM = "UPDATE items SET name = (?), currently = (?), buy_price = (?), first_bid = (?)," +
            " country = (?), location = (?), latitude = (?), longitude = (?), creation = (?)," +
            " ends = (?), seller = (?), description = (?), Image = (?), total_offers = (?) WHERE id = (?)";
    private static final String SQL_GET_TOTAL_BIDS = "SELECT total_offers FROM items WHERE id = (?)";
    private static final String SQL_UPDATE_ITEM_BIDS = "UPDATE items SET total_offers = (?), currently = (?) WHERE id = (?)";
    private static final String SQL_INSERT_NEW_BID = "INSERT INTO bids (item_id, username, bid_time, bid_amount) VALUES (?, ?, ?, ?)";

    private static final String SQL_SEARCH_ITEMS_BY_NAME = "SELECT SQL_CALC_FOUND_ROWS DISTINCT id FROM items WHERE name LIKE ? LIMIT ? , 15";
    private static final String SQL_SEARCH_ITEMS_BY_CATEGORY = "SELECT SQL_CALC_FOUND_ROWS DISTINCT id FROM item_categories WHERE category LIKE ? LIMIT ? , 15";
    private static final String SQL_SEARCH_ITEMS_BY_DESCRIPTION = "SELECT SQL_CALC_FOUND_ROWS DISTINCT id FROM items WHERE description LIKE ? LIMIT ? , 15";
    private static final String SQL_SEARCH_ITEMS_BY_PRICE = "SELECT SQL_CALC_FOUND_ROWS DISTINCT id FROM items WHERE currently < ? LIMIT ? , 15";
    private static final String SQL_SEARCH_ITEMS_BY_LOCATION = "SELECT SQL_CALC_FOUND_ROWS DISTINCT id FROM items WHERE country LIKE ? OR location LIKE ? LIMIT ? , 15";

    private static final String SQL_SEARCH_ITEMS = "SELECT SQL_CALC_FOUND_ROWS DISTINCT items.id FROM items, item_categories WHERE (items.country LIKE ? OR items.location LIKE ?) " +
            "AND (items.description LIKE ? OR items.name LIKE ?) AND (items.id = item_categories.id AND item_categories.category LIKE ?) AND (items.currently < ?) ORDER BY items.currently ASC LIMIT ? , " + RESULTS_PER_PAGE;

    private static final String SQL_GET_USER_RATING = "SELECT rating FROM ratings WHERE username = ?";

    private static final String SQL_GET_ALL_USERNAMES = "SELECT username FROM users";
    private static final String SQL_GET_NEIGHBORS = "SELECT DISTINCT a2.username, COUNT(DISTINCT a2.username, a2.item_id) AS c " +
            "FROM ted.bids AS a1, ted.bids AS a2 WHERE a1.username = ? AND a2.item_id = a1.item_id " +
            "AND a2.username != a1.username GROUP BY a2.username ORDER BY c DESC LIMIT 0 , " + KNN;
    private static final String SQL_GET_NEIGHBORHOOD_ITEMS = "SELECT a1.item_id FROM ted.bids AS a1, ted.items AS items " +
            "WHERE a1.username = ? AND a1.item_id = items.id AND items.state = 1 " +
            "AND NOT EXISTS( SELECT * FROM ted.bids AS a2 WHERE a2.item_id = a1.item_id AND a2.username = ?)";
    private static final String SQL_UPDATE_RECOMMENDATIONS = "INSERT INTO recommendations (username, rank, item_id) VALUES(?, ?, ?) ON DUPLICATE KEY UPDATE item_id = ?";
    private static final String SQL_DELETE_EXTRA_REC_ITEMS = "DELETE FROM recommendations WHERE username = ? AND rank >= ?";
    private static final String SQL_GET_REC_ITEMS = "SELECT item_id FROM recommendations WHERE username = ? ORDER BY rank ASC";


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

            for(String category : categories) {
                statement = DAOUtil.prepareStatement(connection, SQL_DEL_OLD_CATEGORY, false, id, category);
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

    public Item getItemByID(Integer id){
        try {
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_GET_ITEM, false, id);
            ResultSet result = statement.executeQuery();
            BidDAO bdao = new BidDAO(true);

            if(result.next()){

                Item item = new Item(id, result.getString("name"), result.getDouble("currently"), result.getDouble("first_bid"),
                        result.getDouble("buy_price"), result.getString("country"), result.getString("location"),
                        result.getDouble("latitude"), result.getDouble("longitude"), result.getTimestamp("creation"),
                        result.getTimestamp("starts"), result.getTimestamp("ends"), result.getString("seller"), result.getString("description"),
                        null, bdao.getItemBids(id), result.getInt("state"), result.getString("image"), result.getInt("total_offers"));

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

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_GET_EXPIRED_ITEMS, false);
            ResultSet rs = statement.executeQuery();

            statement = DAOUtil.prepareStatement(connection, SQL_CHANGE_ITEM_STATE, false);
            statement.executeUpdate();

            while (rs.next()){
                UserDAO udao = new UserDAO(true);
                Item item = getItemByID(rs.getInt(1));
                if(item.getBids().size()>0)
                    new MessageDAO(true).autoSuccessMessage(item.getId(), item.getName(), udao.getUserbyName(item.getSeller()).getId(),
                            udao.getUserbyName(item.getBids().get(0).getUsername()).getId(), new Date());
            }

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

    public List<Item> getSearchResults(String text, String category, String price, String location, Integer page){
        List<Item> items = new ArrayList<Item>();

        if(page < 1)
            return items;

        Integer offset = (page-1) * RESULTS_PER_PAGE;

        try{
            Connection connection = factory.getConnection();
            PreparedStatement statement;

//            if(type.equals("name")) {
//                statement = DAOUtil.prepareStatement(connection, SQL_SEARCH_ITEMS_BY_NAME, false);
//                statement.setString(1, "%" + value + "%");
//                statement.setInt(2, offset);
//            }
//            else if (type.equals("category")) {
//                statement = DAOUtil.prepareStatement(connection, SQL_SEARCH_ITEMS_BY_CATEGORY, false);
//                statement.setString(1, "%" + value + "%");
//                statement.setInt(2, offset);
//            }
//            else if(type.equals("description")) {
//                statement = DAOUtil.prepareStatement(connection, SQL_SEARCH_ITEMS_BY_DESCRIPTION, false);
//                statement.setString(1, "%" + value + "%");
//                statement.setInt(2, offset);
//            }
//            else if (type.equals("price")) {
//                statement = DAOUtil.prepareStatement(connection, SQL_SEARCH_ITEMS_BY_PRICE, false, value);
//                statement.setInt(2, offset);
//            }
//            else if (type.equals("location")) {
//                statement = DAOUtil.prepareStatement(connection, SQL_SEARCH_ITEMS_BY_LOCATION, false);
//                statement.setString(1, "%" + value + "%");
//                statement.setString(2, "%" + value + "%");
//                statement.setInt(3, offset);
//            }
//            else
//                return items;

            statement = DAOUtil.prepareStatement(connection, SQL_SEARCH_ITEMS,false);

            if(location == null || location.equals("")){
                statement.setString(1, "%");
                statement.setString(2, "%");
            }
            else {
                statement.setString(1, "%" + location + "%");
                statement.setString(2, "%" + location + "%");
            }
            if(text == null || text.equals("")){
                statement.setString(3, "%");
                statement.setString(4, "%");
            }
            else {
                statement.setString(3, "%" + text + "%");
                statement.setString(4, "%" + text + "%");
            }
            if(category == null || category.equals("")){
                statement.setString(5, "%");
            }
            else {
                statement.setString(5, category);
            }
            if(price == null || price.equals("")) {
                statement.setString(6, "9999999999");
            }
            else{
                statement.setString(6, price);
            }

            statement.setInt(7, offset);



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

            //get number of rows
            result = statement.executeQuery("SELECT FOUND_ROWS()");
            result.next();
            numOfResults = result.getInt(1);

            if(numOfResults%RESULTS_PER_PAGE==0)
                numOfPages = numOfResults/RESULTS_PER_PAGE;
            else
                numOfPages = (numOfResults/RESULTS_PER_PAGE)+1;

            connection.close();
            return items;

        }
        catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
            throw new RuntimeException("Error at getSearchResults");
        }
    }

    public int getNumOfResults() {
        return numOfResults;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void loadXmlEntities(Item item){

        String seller = item.getSeller();
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
                udao.insertUser(user, 1);
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

            //DB-Create the bids for the specific item
            if (bids != null) {
                for (Bid bid : bids)
                    bdao.insertBid(bid, item_id);
            }
            connection.close();
        }
        catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
            throw new RuntimeException("Error at LoadXmlEntities");
        }

    }

    public Integer getUserRating(String username){

        try {
            Connection connection = factory.getConnection();

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_GET_USER_RATING, false, username);
            ResultSet result = statement.executeQuery();

            result.next();
            Integer rating = result.getInt(1);

            connection.close();
            return rating;
        }
        catch (SQLException ex){
            System.err.println("ERROR: " + ex.getMessage());
            throw new RuntimeException("Error at getUserRating");
        }
    }

    public void updateRecommendedItems(){

        try {

            Connection connection = factory.getConnection();

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_GET_ALL_USERNAMES, false);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {

                List<UserCorrelation> neighbors = new ArrayList<UserCorrelation>();
                Map<Integer, Integer> items_unranked = new HashMap<Integer, Integer>();
                List<RecommendedItem> items_ranked = new ArrayList<RecommendedItem>();


                //get usernames of neighbors ordered by correlation
                statement = DAOUtil.prepareStatement(connection, SQL_GET_NEIGHBORS, false, rs.getString(1));
                ResultSet results = statement.executeQuery();


                //get them on the list
                while (results.next())
                    neighbors.add(new UserCorrelation(results.getString(1), results.getInt(2)));

                //get the items they have bid for, but user have not
                for (int i = 0; i < neighbors.size(); i++) {
                    statement = DAOUtil.prepareStatement(connection, SQL_GET_NEIGHBORHOOD_ITEMS, false, neighbors.get(i).getUsername(), rs.getString(1));
                    results = statement.executeQuery();

                    //add all the items in a list
                    while (results.next()) {
                        if (!items_unranked.containsKey(results.getInt(1)))
                            items_unranked.put(results.getInt(1), neighbors.get(i).getCorrelation());
                        else
                            items_unranked.put(results.getInt(1), neighbors.get(i).getCorrelation() + items_unranked.get(results.getInt(1)));
                    }
                }

                for (Map.Entry<Integer, Integer> entry : items_unranked.entrySet()) {
                    items_ranked.add(new RecommendedItem(entry.getKey(), entry.getValue()));
                }

                //sort list based on rank
                Collections.sort(items_ranked, RecommendedItem.DESC_COMPARATOR);

                //recommended items per user should be 10 or less
                int max_rec = MAX_REC_ITEMS_PER_USER;
                if(items_ranked.size() < MAX_REC_ITEMS_PER_USER)
                    max_rec = items_ranked.size();

                //insert or update rec table
                for(int i=0; i < max_rec; i++) {
                    statement = DAOUtil.prepareStatement(connection, SQL_UPDATE_RECOMMENDATIONS, false, rs.getString(1), i, items_ranked.get(i).getId(), items_ranked.get(i).getId());
                    statement.executeUpdate();
                }

                statement = DAOUtil.prepareStatement(connection, SQL_DELETE_EXTRA_REC_ITEMS, false, rs.getString(1), max_rec);
                statement.executeUpdate();

            }

        }
        catch (SQLException ex){
            System.err.println("ERROR: " + ex.getMessage());
            throw new RuntimeException("Error at updateRecommendedItems");
        }

    }

    public List<Item> getRecItems(String username){

        if(username == null)
            return new ArrayList<Item>();

        try {

            Connection connection = factory.getConnection();

            PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_GET_REC_ITEMS, false, username);
            ResultSet rs = statement.executeQuery();

            List<Item> rec_items = new ArrayList<Item>();

            while (rs.next())
                rec_items.add(getItemByID(rs.getInt(1)));

            return rec_items;

        }
        catch (SQLException ex){
            System.err.println("ERROR: " + ex.getMessage());
            throw new RuntimeException("Error at getRecItems");
        }
    }

}
