package com.ridiculands.library.borrowingrecord.db;

import com.ridiculands.library.date.DateUtil;
import com.ridiculands.library.db.H2DatabaseConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BorrowingRecordDao {

    private static final Logger logger = Logger.getLogger(BorrowingRecordDao.class.getName());
    private final DateUtil dateUtil;

    public BorrowingRecordDao(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    public List<BorrowingRecord> getBorrowingRecord(int borrowerId) {
        List<BorrowingRecord> borrowingRecords = new ArrayList<>();
        BorrowingRecord borrowingRecord = null;

        try{
            Connection connection = H2DatabaseConnectionPool.getConnectionToDatabase();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from borrowing_record, borrowing_record_status where borrowing_record.borrower_id=? and borrowing_record.borrowing_record_status_id=borrowing_record_status.id");
            preparedStatement.setInt(1, borrowerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                borrowingRecord = new BorrowingRecord();
                borrowingRecord.setId(resultSet.getInt("id"));
                borrowingRecord.setBorrowerId(resultSet.getInt("borrower_id"));
                borrowingRecord.setBookId(resultSet.getInt("book_id"));
                borrowingRecord.setDueDate(dateUtil.formatDate(resultSet.getDate("due_date")));
                borrowingRecords.add(borrowingRecord);
            }
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, "Could not execute query", exception);
        }

        return borrowingRecords;
    }
}
