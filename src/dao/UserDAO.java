package dao;


public class UserDAO {

    private ConnectionFactory factory;

    public UserDAO(boolean pool)
    {
        factory = ConnectionFactory.getInstance(pool);
    }

    boolean insertUser(){
        return true;
    }
}
