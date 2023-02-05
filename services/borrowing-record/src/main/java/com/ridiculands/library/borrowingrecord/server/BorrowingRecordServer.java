package com.ridiculands.library.borrowingrecord.server;

import com.ridiculands.library.borrowingrecord.service.BorrowingRecordServiceImpl;
import com.ridiculands.library.date.DateUtil;
import com.ridiculands.library.db.H2DatabaseConnectionPool;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BorrowingRecordServer {
    private static final Logger LOGGER = Logger.getLogger(BorrowingRecordServer.class.getName());
    private final DateUtil dateUtil;
    private Server server;

    public BorrowingRecordServer(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    public void startServer() {
        int port = 5002;
        try {
            H2DatabaseConnectionPool.initializeDatabase();

            server = ServerBuilder.forPort(port)
                    .addService(new BorrowingRecordServiceImpl(dateUtil))
                    .build()
                    .start();

            LOGGER.log(Level.INFO, "BorrowingRecord Server started");

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                LOGGER.log(Level.INFO, "Shutting down borrowing record server after JVM is shut down...");
                try {
                    BorrowingRecordServer.this.stopServer();
                } catch (InterruptedException e) {
                    LOGGER.log(Level.SEVERE, "BorrowingRecordServer is not stopped properly", e);
                }
            }));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "cannot start BorrowingRecordServer", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "cannot initialize BorrowingRecord Service Database", e);
        }
    }

    public void stopServer() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DateUtil dateUtil = new DateUtil();
        BorrowingRecordServer borrowingRecordServer = new BorrowingRecordServer(dateUtil);
        borrowingRecordServer.startServer();
        borrowingRecordServer.blockUntilShutdown();
    }
}
