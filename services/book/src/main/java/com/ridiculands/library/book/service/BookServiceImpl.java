package com.ridiculands.library.book.service;


import com.ridiculands.library.book.db.Book;
import com.ridiculands.library.book.db.BookDao;
import com.ridiculands.library.stubs.book.BookServiceGrpc;
import com.ridiculands.library.stubs.book.GetBookRequest;
import com.ridiculands.library.stubs.book.GetBookResponse;
import com.ridiculands.library.stubs.book.Status;
import io.grpc.stub.StreamObserver;

public class BookServiceImpl extends BookServiceGrpc.BookServiceImplBase {

    @Override
    public void getBook(GetBookRequest request, StreamObserver<GetBookResponse> responseObserver) {
        BookDao bookDao = new BookDao();
        Book book = bookDao.getBook(request.getBookId());

        GetBookResponse.Builder bookResponseBuilder = GetBookResponse.newBuilder()
                .setName(book.getName())
                .setAuthor(book.getAuthor())
                .setCallNumber(book.getCallNumber())
                .setStatus(Status.valueOf(book.getStatus()));

        GetBookResponse bookResponse = bookResponseBuilder.build();

        responseObserver.onNext(bookResponse);
        responseObserver.onCompleted();
    }
}
