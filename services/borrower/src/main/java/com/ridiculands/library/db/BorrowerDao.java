package com.ridiculands.library.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BorrowerDao {
    private static final Logger logger = Logger.getLogger(BorrowerDao.class.getName());

    public Borrower getDetails(String username){
        Borrower borrower = new Borrower();

        try{
            Connection connection = H2DatabaseConnectionPool.getConnectionToDatabase();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from customer where username=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                borrower.setId(resultSet.getInt("id"));
                borrower.setUserName(resultSet.getString("username"));
                borrower.setName(resultSet.getString("name"));
                borrower.setMemberType(resultSet.getString("membertype"));
            }
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, "Could not execute query", exception);
        }
        return borrower;
    }
}
