package com.ridiculands.library.book.db;

import com.ridiculands.library.db.H2DatabaseConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDao {
    private static final Logger logger = Logger.getLogger(BookDao.class.getName());

    public Book getBook(int id) {
        Book book = new Book();

        try{
            Connection connection = H2DatabaseConnectionPool.getConnectionToDatabase();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from book, book_status where book.id=? and book.status_id=book_status.id");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                book.setId(resultSet.getInt("id"));
                book.setCallNumber(resultSet.getString("call_number"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setStatus(resultSet.getString("status"));
            }
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, "Could not execute query", exception);
        }

        return book;
    }
}
