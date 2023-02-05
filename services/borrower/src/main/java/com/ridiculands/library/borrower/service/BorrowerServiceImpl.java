package com.ridiculands.library.borrower.service;

import com.ridiculands.library.borrower.db.Borrower;
import com.ridiculands.library.borrower.db.BorrowerDao;
import com.ridiculands.library.stubs.borrower.BorrowerType;
import com.ridiculands.library.stubs.borrower.BorrowingRecord;
import com.ridiculands.library.stubs.borrower.GetBorrowerDetailsRequest;
import com.ridiculands.library.stubs.borrower.GetBorrowerDetailsResponse;
import com.ridiculands.library.stubs.borrower.BorrowerServiceGrpc;
import com.ridiculands.library.stubs.borrower.GetBorrowingRecordRequest;
import com.ridiculands.library.stubs.borrower.GetBorrowingRecordResponse;
import com.ridiculands.library.stubs.borrowing_record.BorrowingRecordServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
        // TODO ISRAELW 1. get channel
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:5002").usePlaintext().build();

        // TODO ISRAELW 2. get a stub object
        BorrowingRecordServiceGrpc.BorrowingRecordServiceBlockingStub borrowingRecordServiceBlockingStub = BorrowingRecordServiceGrpc.newBlockingStub(channel);

        // TODO ISRAELW 3. call service method
        com.ridiculands.library.stubs.borrowing_record.GetBorrowingRecordRequest getBorrowingRecordRequest = com.ridiculands.library.stubs.borrowing_record.GetBorrowingRecordRequest.newBuilder()
                .setBorrowerId(borrower.getId())
                .build();
        com.ridiculands.library.stubs.borrowing_record.GetBorrowingRecordResponse getBorrowingRecordResponse = borrowingRecordServiceBlockingStub.getBorrowingRecord(getBorrowingRecordRequest);

        // TODO ISRAELW 4. close channel
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // TODO ISRAELW review exception
            throw new RuntimeException(e);
        }

        // TODO ISRAELW 5. map to response borrowing records
        List<BorrowingRecord> borrowingRecordInResponse = getBorrowingRecordResponse.getBorrowingRecordList().stream().map(borrowingRecord -> BorrowingRecord.newBuilder()
                .setAuthor("tbi")
                .setCallNumber("tbi")
                .setName("tbi")
                .setDueDate(borrowingRecord.getDueDate())
                .build())
                .collect(Collectors.toList());

        GetBorrowingRecordResponse.Builder responseBuilder = GetBorrowingRecordResponse.newBuilder().addAllBorrowingRecord(borrowingRecordInResponse);
        GetBorrowingRecordResponse response = responseBuilder.build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
