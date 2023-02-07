package com.ridiculands.library.borrower.serviceadapter;

import com.ridiculands.library.stubs.book.BookServiceGrpc;
import com.ridiculands.library.stubs.book.GetBookRequest;
import com.ridiculands.library.stubs.book.GetBookResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class BookServiceAdapter {

    private static final String SERVICE_URL = "localhost:5003";

    public GetBookResponse getBook(int bookId) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget(SERVICE_URL).usePlaintext().build();
        BookServiceGrpc.BookServiceBlockingStub bookServiceBlockingStub = BookServiceGrpc.newBlockingStub(channel);
        GetBookRequest request = GetBookRequest.newBuilder().setBookId(bookId).build();
        GetBookResponse getBookResponse = bookServiceBlockingStub.getBook(request);
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // TODO ISRAELW review exception
            throw new RuntimeException(e);
        }
        return getBookResponse;
    }

}
