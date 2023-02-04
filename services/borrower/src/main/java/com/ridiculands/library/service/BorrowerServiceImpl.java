package com.ridiculands.library.service;

import com.ridiculands.library.db.Borrower;
import com.ridiculands.library.db.BorrowerDao;
import com.ridiculands.library.stubs.borrower.BorrowerType;
import com.ridiculands.library.stubs.borrower.GetBorrowerDetailsRequest;
import com.ridiculands.library.stubs.borrower.GetBorrowerDetailsResponse;
import com.ridiculands.library.stubs.borrower.BorrowerServiceGrpc;
import io.grpc.stub.StreamObserver;

public class BorrowerServiceImpl extends BorrowerServiceGrpc.BorrowerServiceImplBase {
    @Override
    public void getBorrowerDetails(GetBorrowerDetailsRequest request, StreamObserver<GetBorrowerDetailsResponse> responseObserver) {
        BorrowerDao borrowerDao = new BorrowerDao();
        Borrower borrower = borrowerDao.getDetails(request.getBorrowerCardNumber());

        GetBorrowerDetailsResponse.Builder borrowerResponseBuilder = GetBorrowerDetailsResponse.newBuilder()
                .setId(borrower.getId())
                .setBorrowerCardNumber(borrower.getBorrowerCardNumber())
                .setUserName(borrower.getUserName())
                .setBorrowerFullName(borrower.getBorrowerFullName())
                .setBorrowerType(BorrowerType.valueOf(borrower.getBorrowerType()));

        GetBorrowerDetailsResponse borrowerResponse = borrowerResponseBuilder.build();

        responseObserver.onNext(borrowerResponse);
        responseObserver.onCompleted();
    }
}
