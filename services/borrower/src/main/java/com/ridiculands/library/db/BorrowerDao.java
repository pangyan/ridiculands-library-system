package com.ridiculands.library.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BorrowerDao {
    private static final Logger logger = Logger.getLogger(BorrowerDao.class.getName());

    public Borrower getDetails(String borrowerCardNumber){
        Borrower borrower = new Borrower();

        try{
            Connection connection = H2DatabaseConnectionPool.getConnectionToDatabase();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from borrower where borrower_card_number=?");
            preparedStatement.setString(1, borrowerCardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                borrower.setId(resultSet.getInt("id"));
                borrower.setBorrowerCardNumber(resultSet.getString("borrower_card_number"));
                borrower.setUserName(resultSet.getString("user_name"));
                borrower.setBorrowerFullName(resultSet.getString("borrower_full_name"));
                borrower.setBorrowerType(resultSet.getString("borrower_type"));
            }
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, "Could not execute query", exception);
        }
        return borrower;
    }
}
