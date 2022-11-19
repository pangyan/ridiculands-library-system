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
        Borrower borrower = borrowerDao.getDetails(request.getBorrowerName());

        BorrowerResponse.Builder borrowerResponseBuilder = BorrowerResponse.newBuilder()
                .setId(borrower.getId())
                .setBorrowerName(borrower.getUserName())
                .setName(borrower.getName())
                .setBorrowerType(BorrowerType.valueOf(borrower.getMemberType()));

        BorrowerResponse borrowerResponse = borrowerResponseBuilder.build();

        responseObserver.onNext(borrowerResponse);
        responseObserver.onCompleted();
    }
}
