package com.ridiculands.library.book.server;

import com.ridiculands.library.book.service.BookServiceImpl;
import com.ridiculands.library.db.H2DatabaseConnectionPool;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookServer {

    private static final Logger LOGGER = Logger.getLogger(BookServer.class.getName());
    private Server server;

    public void startServer() {
        int port = 5003;
        try {
            H2DatabaseConnectionPool.initializeDatabase();

            server = ServerBuilder.forPort(port)
                    .addService(new BookServiceImpl())
                    .build()
                    .start();

            LOGGER.log(Level.INFO, "Book Server started");

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                LOGGER.log(Level.INFO, "Shutting down book server after JVM is shut down...");
                try {
                    BookServer.this.stopServer();
                } catch (InterruptedException e) {
                    LOGGER.log(Level.SEVERE, "BookServer is not stopped properly", e);
                }
            }));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "cannot start BookServer", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "cannot initialize Book Service Database", e);
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
        BookServer bookServer = new BookServer();
        bookServer.startServer();
        bookServer.blockUntilShutdown();
    }
}
