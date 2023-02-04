package com.ridiculands.library.service;

import com.ridiculands.library.db.Borrower;
import com.ridiculands.library.db.BorrowerDao;
import com.ridiculands.library.stubs.borrower.BorrowerType;
import com.ridiculands.library.stubs.borrower.GetBorrowerDetailsRequest;
import com.ridiculands.library.stubs.borrower.GetBorrowerDetailsResponse;
import com.ridiculands.library.stubs.borrower.BorrowerServiceGrpc;
import com.ridiculands.library.stubs.borrower.GetBorrowingRecordRequest;
import com.ridiculands.library.stubs.borrower.GetBorrowingRecordResponse;
import io.grpc.stub.StreamObserver;

public class BorrowerServiceImpl extends BorrowerServiceGrpc.BorrowerServiceImplBase {
    @Override
    public void getBorrowerDetails(GetBorrowerDetailsRequest request, StreamObserver<GetBorrowerDetailsResponse> responseObserver) {
        BorrowerDao borrowerDao = new BorrowerDao();
        Borrower borrower = borrowerDao.getDetails(request.getBorrowerCardNumber());

        GetBorrowerDetailsResponse.Builder borrowerResponseBuilder = GetBorrowerDetailsResponse.newBuilder()
                .setBorrowerCardNumber(borrower.getBorrowerCardNumber())
                .setUserName(borrower.getUserName())
                .setBorrowerFullName(borrower.getBorrowerFullName())
                .setBorrowerType(BorrowerType.valueOf(borrower.getBorrowerType()));

        GetBorrowerDetailsResponse borrowerResponse = borrowerResponseBuilder.build();

        responseObserver.onNext(borrowerResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getBorrowingRecord(GetBorrowingRecordRequest request, StreamObserver<GetBorrowingRecordResponse> responseObserver) {
        BorrowerDao borrowerDao = new BorrowerDao();
        Borrower borrower = borrowerDao.getDetails(request.getBorrowerCardNumber());

        // TODO ISRAELW call borrowing record service with the borrower id
        borrower.getId();


    }
}
