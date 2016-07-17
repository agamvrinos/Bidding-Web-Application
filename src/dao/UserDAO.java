package dao;

import entities.User;
import java.sql.*;

public class UserDAO {

    private static final String SQL_ADD_NEW_USER = "INSERT INTO users (fullname, username, password, email, phone, country, city, address, afm, role) VALUES (?, ?, MD5(?), ?, ?, ?, ?, ?, ?, ?)";

    private ConnectionFactory factory;


    public UserDAO(boolean pool)
    {
        factory = ConnectionFactory.getInstance(pool);
    }

    public boolean insertUser(User userInfo){

        Object[] values = { userInfo.getFullname(), userInfo.getUsername(), userInfo.getPassword(), userInfo.getEmail()
                , userInfo.getPhone(), userInfo.getCountry(), userInfo.getCity(), userInfo.getAddress(), userInfo.getAfm(),
                userInfo.getRole()};

        System.out.println("COUNYTY: " + userInfo.getCountry());


        try (
                Connection connection = factory.getConnection();
                PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_ADD_NEW_USER, false, values);)
        {
            int affectedRows = statement.executeUpdate();
            System.out.println("Affected Rows: " + affectedRows);

        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return true;
    }

}
