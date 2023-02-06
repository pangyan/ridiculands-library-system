package com.ridiculands.library.borrower.serviceadapter;

import com.ridiculands.library.stubs.borrower.BorrowingRecord;
import com.ridiculands.library.stubs.borrowing_record.BorrowingRecordServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BorrowingRecordServiceAdapter {

    private static final String SERVICE_URL = "localhost:5002";

    public List<BorrowingRecord> getBorrowingRecord(int borrowerId) {
        // TODO ISRAELW call borrowing record service with the borrower id
        // TODO ISRAELW 1. get channel
        ManagedChannel channel = ManagedChannelBuilder.forTarget(SERVICE_URL).usePlaintext().build();

        // TODO ISRAELW 2. get a stub object
        BorrowingRecordServiceGrpc.BorrowingRecordServiceBlockingStub borrowingRecordServiceBlockingStub = BorrowingRecordServiceGrpc.newBlockingStub(channel);

        // TODO ISRAELW 3. call service method
        com.ridiculands.library.stubs.borrowing_record.GetBorrowingRecordRequest getBorrowingRecordRequest = com.ridiculands.library.stubs.borrowing_record.GetBorrowingRecordRequest.newBuilder()
                .setBorrowerId(borrowerId)
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

        return borrowingRecordInResponse;
    }
}
