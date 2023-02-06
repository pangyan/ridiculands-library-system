package com.ridiculands.library.borrower.serviceadapter.factory;

import com.ridiculands.library.borrower.serviceadapter.BorrowingRecordServiceAdapter;

public class BorrowingRecordServiceAdapterFactory {
    private static final BorrowingRecordServiceAdapter adapter = new BorrowingRecordServiceAdapter();

    public BorrowingRecordServiceAdapter getAdapter() {
        return adapter;
    }
}
