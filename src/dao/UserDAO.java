package dao;

import entities.User;
import java.sql.*;
import java.util.*;

public class UserDAO {

    private static final Integer USERNAME_EXISTS_ERROR = -2;
    private static final String SQL_ADD_NEW_USER = "INSERT INTO users (fullname, username, password, email, phone, country, city, address, afm, role) VALUES (?, ?, MD5(?), ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SEARCH_USER = "SELECT * FROM users WHERE username = ? AND password = MD5(?) ";
    private static final String SQL_SEARCH_USER_BY_ID = "SELECT * FROM users WHERE id = ? ";
    private static final String SQL_EXISTS_USER = "SELECT 1 FROM users WHERE username = ?";
    private static final String SQL_GET_USER_LIST = "SELECT * FROM users";

    private ConnectionFactory factory;


    public UserDAO(boolean pool)
    {
        factory = ConnectionFactory.getInstance(pool);
    }

    public Integer insertUser(User userInfo){

        int ret = -1;

        Object[] values = { userInfo.getFullname(), userInfo.getUsername(), userInfo.getPassword(), userInfo.getEmail()
                , userInfo.getPhone(), userInfo.getCountry(), userInfo.getCity(), userInfo.getAddress(), userInfo.getAfm(),
                userInfo.getRole()};

        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_ADD_NEW_USER, false, values);)
        {
            int affectedRows = statement.executeUpdate();
            System.out.println("Affected Rows: " + affectedRows);
            ret = affectedRows;

            if (ret == 0) {
                System.err.println("Creating user failed, no rows affected.");
                return ret;
            }

        } catch (SQLIntegrityConstraintViolationException e1){
            return  USERNAME_EXISTS_ERROR;

        }
         catch (SQLException e) {
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

            if(!results.next())
                return null;
            else{
                User user = new User(results.getString("fullname"), results.getString("username"), null,
                        results.getString("email"), results.getString("phone"), results.getString("country"), results.getString("city"),
                        results.getString("address"), results.getString("afm"), results.getInt("role"));

                user.setId(results.getInt("id"));
                user.setValidated(results.getInt("validated"));

                return user;

            }

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }

    }

    public List<User> getUserList(){

        List<User> userlist = new ArrayList<User>();

        try{
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_GET_USER_LIST, false);
            ResultSet results = statement.executeQuery();

            while(results.next()){
                User user = new User(results.getString("fullname"), results.getString("username"), null,
                        results.getString("email"), results.getString("phone"), results.getString("country"), results.getString("city"),
                        results.getString("address"), results.getString("afm"), results.getInt("role"));

                user.setId(results.getInt("id"));
                user.setValidated(results.getInt("validated"));

                userlist.add(user);
            }

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return userlist;
    }

    public User getUserbyID(Integer id){

        try{

            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_SEARCH_USER_BY_ID, false, id);
            ResultSet results = statement.executeQuery();

            if(!results.next())
                return null;
            else{
                User user = new User(results.getString("fullname"), results.getString("username"), null,
                        results.getString("email"), results.getString("phone"), results.getString("country"), results.getString("city"),
                        results.getString("address"), results.getString("afm"), results.getInt("role"));

                user.setId(results.getInt("id"));
                user.setValidated(results.getInt("validated"));

                return user;
            }

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public boolean existsUsername(String username){

        try{
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_EXISTS_USER, false, username);
            ResultSet results = statement.executeQuery();

            if (!results.isBeforeFirst())
                // Username does not exist
                return false;
            else
                // Username exists
                return true;

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            return false;

        }
    }
}
