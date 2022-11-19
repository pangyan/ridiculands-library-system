package com.ridiculands.library.service;

import com.ridiculands.library.db.Borrower;
import com.ridiculands.library.db.BorrowerDao;
import com.ridiculands.library.stubs.borrower.BorrowerType;
import com.ridiculands.library.stubs.borrower.BorrowerRequest;
import com.ridiculands.library.stubs.borrower.BorrowerResponse;
import com.ridiculands.library.stubs.borrower.BorrowerServiceGrpc;
import io.grpc.stub.StreamObserver;

public class BorrowerServiceImpl extends BorrowerServiceGrpc.BorrowerServiceImplBase {
    @Override
    public void getBorrowerDetails(BorrowerRequest request, StreamObserver<BorrowerResponse> responseObserver) {
        BorrowerDao borrowerDao = new BorrowerDao();
        Borrower borrower = borrowerDao.getDetails(request.getUserName());

        BorrowerResponse.Builder borrowerResponseBuilder = BorrowerResponse.newBuilder()
                .setId(borrower.getId())
                .setUserName(borrower.getUserName())
                .setBorrowerFullName(borrower.getBorrowerFullName())
                .setBorrowerType(BorrowerType.valueOf(borrower.getBorrowerType()));

        BorrowerResponse borrowerResponse = borrowerResponseBuilder.build();

        responseObserver.onNext(borrowerResponse);
        responseObserver.onCompleted();
    }
}
