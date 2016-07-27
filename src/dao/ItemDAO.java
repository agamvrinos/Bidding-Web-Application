package dao;

import java.sql.*;
import java.util.*;

public class ItemDAO {

    private static final String SQL_GET_CATEGORIES = "SELECT category FROM item_categories WHERE id = 0 ";

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

}
