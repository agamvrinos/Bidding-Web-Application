package dao;

import entities.User;
import java.sql.*;

public class UserDAO {

    private static final Integer USERNAME_EXISTS_ERROR = -2;
    private static final String SQL_ADD_NEW_USER = "INSERT INTO users (fullname, username, password, email, phone, country, city, address, afm, role) VALUES (?, ?, MD5(?), ?, ?, ?, ?, ?, ?, ?)";

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

}
