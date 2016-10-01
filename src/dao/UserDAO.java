package dao;

import entities.AppEntities.User;
import java.sql.*;
import java.util.*;

public class UserDAO {

    private int numOfResults;
    private int numOfPages;
    private static final int USERS_PER_PAGE = 15;

    private static final Integer USERNAME_EXISTS_ERROR = -2;
    private static final String SQL_ADD_NEW_USER = "INSERT INTO users (fullname, username, password, email, phone, country, city, address, afm, role, validated) VALUES (?, ?, MD5(?), ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SEARCH_USER = "SELECT * FROM users WHERE username = ? AND password = MD5(?) ";
    private static final String SQL_SEARCH_USER_BY_ID = "SELECT * FROM users WHERE id = ? ";
    private static final String SQL_SEARCH_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ? ";
    private static final String SQL_EXISTS_USER = "SELECT 1 FROM users WHERE username = ?";
    private static final String SQL_GET_USER_LIST = "SELECT SQL_CALC_FOUND_ROWS * FROM users ORDER BY id DESC LIMIT ?, " + USERS_PER_PAGE;
    private static final String SQL_VALIDATE_USERID = "UPDATE users SET validated=1 WHERE id= ?";
    private static final String SQL_VALIDATE_USERNAME = "UPDATE users SET validated=1 WHERE username= ?";

    private ConnectionFactory factory;


    public UserDAO(boolean pool)
    {
        factory = ConnectionFactory.getInstance(pool);
    }

    public int getNumOfResults() {
        return numOfResults;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public Integer insertUser(User userInfo, Integer is_imported){
        //is_imported = 0 -> User created from UI
        //is_imported = 1 -> User created from xml files

        int ret = -1;
        int validated = 0;
        if (is_imported == 1){
            validated = 1;
        }

        Object[] values = { userInfo.getFullname(), userInfo.getUsername(), userInfo.getPassword(), userInfo.getEmail()
                , userInfo.getPhone(), userInfo.getCountry(), userInfo.getCity(), userInfo.getAddress(), userInfo.getAfm(),
                userInfo.getRole(), validated};

        try {
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_ADD_NEW_USER, false, values);

            int affectedRows = statement.executeUpdate();
            ret = affectedRows;

            connection.close();

            if (ret == 0) {
                System.err.println("Creating user failed, no rows affected.");
                return ret;
            }
            // if User was created from the UI then set initial rating to 0
            else if (ret != 0 && is_imported == 0){
                new BidDAO(true).insertRating(userInfo.getUsername(), 0);
            }

        } catch (SQLIntegrityConstraintViolationException e1){
            return  USERNAME_EXISTS_ERROR;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return ret;
        }

        ret = 1;

        return ret;
    }

    public User getUser(String username, String password){

        try{
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_SEARCH_USER, false, username, password);
            ResultSet results = statement.executeQuery();

            if(!results.next()) {
                connection.close();
                return null;
            }
            else{
                User user = new User(results.getString("fullname"), results.getString("username"), null,
                        results.getString("email"), results.getString("phone"), results.getString("country"), results.getString("city"),
                        results.getString("address"), results.getString("afm"), results.getInt("role"));

                user.setId(results.getInt("id"));
                user.setValidated(results.getInt("validated"));
                connection.close();
                return user;

            }

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<User> getUserList(Integer page){

        List<User> list = new ArrayList<User>();

        if(page < 1)
            return list;

        Integer offset = (page-1) * USERS_PER_PAGE;

        try{
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_GET_USER_LIST, false, offset);
            ResultSet results = statement.executeQuery();

            while(results.next()){
                User user = new User(results.getString("fullname"), results.getString("username"), null,
                        results.getString("email"), results.getString("phone"), results.getString("country"), results.getString("city"),
                        results.getString("address"), results.getString("afm"), results.getInt("role"));

                user.setId(results.getInt("id"));
                user.setValidated(results.getInt("validated"));

                list.add(user);
            }

            //get number of rows
            results = statement.executeQuery("SELECT FOUND_ROWS()");
            results.next();
            numOfResults = results.getInt(1);

            if(numOfResults%USERS_PER_PAGE==0)
                numOfPages = numOfResults/USERS_PER_PAGE;
            else
                numOfPages = (numOfResults/USERS_PER_PAGE)+1;

            connection.close();
            return list;
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return list;
    }

    public User getUserbyID(Integer id){

        try{

            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_SEARCH_USER_BY_ID, false, id);
            ResultSet results = statement.executeQuery();

            if(!results.next()) {
                connection.close();
                return null;
            }
            else{
                User user = new User(results.getString("fullname"), results.getString("username"), null,
                        results.getString("email"), results.getString("phone"), results.getString("country"), results.getString("city"),
                        results.getString("address"), results.getString("afm"), results.getInt("role"));

                user.setId(results.getInt("id"));
                user.setValidated(results.getInt("validated"));

                connection.close();
                return user;
            }

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public User getUserbyName(String username){

        try{

            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_SEARCH_USER_BY_USERNAME, false, username);
            ResultSet results = statement.executeQuery();

            if(!results.next()) {
                connection.close();
                throw new RuntimeException("No results at getUserByName");
            }
            else{
                User user = new User(results.getString("fullname"), results.getString("username"), null,
                        results.getString("email"), results.getString("phone"), results.getString("country"), results.getString("city"),
                        results.getString("address"), results.getString("afm"), results.getInt("role"));

                user.setId(results.getInt("id"));
                user.setValidated(results.getInt("validated"));

                connection.close();
                return user;
            }

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            throw new RuntimeException("ERROR at getUserByName");
        }
    }

    public boolean existsUsername(String username){

        try{
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_EXISTS_USER, false, username);
            ResultSet results = statement.executeQuery();

            // Username does not exist
            if (!results.isBeforeFirst()){
                connection.close();
                return false;
            }
            // Username exists
            else {
                connection.close();
                return true;
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public void approveUser(String id, Integer validateBy){
        //validateBy = 0 -> validate by ID
        //validateBy = 1 -> validate by username
        try{
            Connection connection = factory.getConnection();
            PreparedStatement statement;

            if (validateBy == 0)
                statement = DAOUtil.prepareStatement(connection,SQL_VALIDATE_USERID, false, id);
            else
                statement = DAOUtil.prepareStatement(connection,SQL_VALIDATE_USERNAME, false, id);

            statement.executeUpdate();
            connection.close();
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
