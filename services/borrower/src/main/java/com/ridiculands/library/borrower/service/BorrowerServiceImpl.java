package com.ridiculands.library.borrower.service;

import com.ridiculands.library.borrower.db.Borrower;
import com.ridiculands.library.borrower.db.BorrowerDao;
import com.ridiculands.library.borrower.serviceadapter.factory.ServiceAdapterFactory;
import com.ridiculands.library.stubs.borrower.BorrowerType;
import com.ridiculands.library.stubs.borrower.BorrowingRecord;
import com.ridiculands.library.stubs.borrower.GetBorrowerDetailsRequest;
import com.ridiculands.library.stubs.borrower.GetBorrowerDetailsResponse;
import com.ridiculands.library.stubs.borrower.BorrowerServiceGrpc;
import com.ridiculands.library.stubs.borrower.GetBorrowingRecordRequest;
import com.ridiculands.library.stubs.borrower.GetBorrowingRecordResponse;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class BorrowerServiceImpl extends BorrowerServiceGrpc.BorrowerServiceImplBase {

    private final ServiceAdapterFactory adapterFactory;

    public BorrowerServiceImpl(ServiceAdapterFactory adapterFactory) {
        this.adapterFactory = adapterFactory;
    }

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

        List<BorrowingRecord> borrowingRecordInResponse = adapterFactory.getBorrowingRecordServiceAdapter().getBorrowingRecord(borrower.getId());

        GetBorrowingRecordResponse.Builder responseBuilder = GetBorrowingRecordResponse.newBuilder().addAllBorrowingRecord(borrowingRecordInResponse);
        GetBorrowingRecordResponse response = responseBuilder.build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
