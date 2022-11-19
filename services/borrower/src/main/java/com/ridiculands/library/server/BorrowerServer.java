package com.ridiculands.library.server;

import com.ridiculands.library.db.H2DatabaseConnectionPool;
import com.ridiculands.library.service.BorrowerServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BorrowerServer {

    private static final Logger LOGGER = Logger.getLogger(BorrowerServer.class.getName());
    private Server server;

    public void startServer() {
        int port = 5001;
        try {
            H2DatabaseConnectionPool.initializeBorrowerServiceDatabase();

            server = ServerBuilder.forPort(port)
                    .addService(new BorrowerServiceImpl())
                    .build()
                    .start();

            LOGGER.log(Level.INFO, "User Server started");

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    LOGGER.log(Level.INFO, "Shutting down user server after JVM is shut down...");
                    try {
                        BorrowerServer.this.stopServer();
                    } catch (InterruptedException e) {
                        LOGGER.log(Level.SEVERE, "UserServer is not stopped properly", e);
                    }
                }
            });
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "cannot start UserServer", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "cannot initialize User Service Database", e);
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
        BorrowerServer borrowerServer = new BorrowerServer();
        borrowerServer.startServer();
        borrowerServer.blockUntilShutdown();
    }
}
