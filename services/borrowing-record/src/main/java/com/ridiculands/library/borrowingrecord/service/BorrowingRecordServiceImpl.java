package com.ridiculands.library.borrowingrecord.service;

import com.ridiculands.library.borrowingrecord.db.BorrowingRecord;
import com.ridiculands.library.borrowingrecord.db.BorrowingRecordDao;
import com.ridiculands.library.date.DateUtil;
import com.ridiculands.library.stubs.borrowing_record.BorrowingRecordServiceGrpc;
import com.ridiculands.library.stubs.borrowing_record.GetBorrowingRecordRequest;
import com.ridiculands.library.stubs.borrowing_record.GetBorrowingRecordResponse;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.stream.Collectors;

public class BorrowingRecordServiceImpl extends BorrowingRecordServiceGrpc.BorrowingRecordServiceImplBase {

    private final DateUtil dateUtil;

    public BorrowingRecordServiceImpl(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @Override
    public void getBorrowingRecord(GetBorrowingRecordRequest request, StreamObserver<GetBorrowingRecordResponse> responseStreamObserver) {
        BorrowingRecordDao borrowingRecordDao = new BorrowingRecordDao(dateUtil);
        List<BorrowingRecord> borrowingRecords = borrowingRecordDao.getBorrowingRecord(request.getBorrowerId());

        List<com.ridiculands.library.stubs.borrowing_record.BorrowingRecord> borrowingRecordsInResponse = borrowingRecords.stream().map(borrowingRecord -> com.ridiculands.library.stubs.borrowing_record.BorrowingRecord.newBuilder()
                .setBorrowerId(borrowingRecord.getBorrowerId())
                .setBookId(borrowingRecord.getBookId())
                .setDueDate(borrowingRecord.getDueDate())
                        .build())
                .collect(Collectors.toList());

        GetBorrowingRecordResponse.Builder borrowingRecordResponseBuilder = GetBorrowingRecordResponse.newBuilder()
                .addAllBorrowingRecord(borrowingRecordsInResponse);

        GetBorrowingRecordResponse borrowingRecordResponse = borrowingRecordResponseBuilder.build();

        responseStreamObserver.onNext(borrowingRecordResponse);
        responseStreamObserver.onCompleted();
    }
}
