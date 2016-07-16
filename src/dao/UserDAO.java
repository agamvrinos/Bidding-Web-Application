package dao;

import java.sql.*;
import java.io.*;

public class UserDAO {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM users";

    private ConnectionFactory factory;


    public UserDAO(boolean pool)
    {
        factory = ConnectionFactory.getInstance(pool);
    }

    boolean insertUser(){
        return true;
    }

    public void getUsers() {
        try (
            Connection connection = factory.getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection,SQL_FIND_BY_ID, false);
            ResultSet resultSet = statement.executeQuery();)
        {
            System.out.println("x0a0xa0xa022");

            while (resultSet.next()){
                System.out.println(resultSet.getString("fullname"));
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
