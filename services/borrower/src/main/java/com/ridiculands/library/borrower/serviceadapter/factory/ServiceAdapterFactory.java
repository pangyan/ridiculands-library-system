package com.ridiculands.library.borrower.serviceadapter.factory;

import com.ridiculands.library.borrower.serviceadapter.BookServiceAdapter;
import com.ridiculands.library.borrower.serviceadapter.BorrowingRecordServiceAdapter;

public class ServiceAdapterFactory {

    private final BookServiceAdapter bookServiceAdapter = new BookServiceAdapter();
    private final BorrowingRecordServiceAdapter borrowingRecordServiceAdapter = new BorrowingRecordServiceAdapter(bookServiceAdapter);

    public BookServiceAdapter getBookServiceAdapter() {
        return bookServiceAdapter;
    }
    public BorrowingRecordServiceAdapter getBorrowingRecordServiceAdapter() {
        return borrowingRecordServiceAdapter;
    }
}
