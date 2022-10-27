package com.ridiculands.grocery.server;

import com.ridiculands.grocery.service.UserServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServer {

    private static final Logger LOGGER = Logger.getLogger(UserServer.class.getName());
    private Server server;

    public void startServer() {
        int port = 5001;
        try {
            server = ServerBuilder.forPort(port)
                    .addService(new UserServiceImpl())
                    .build()
                    .start();

            LOGGER.log(Level.INFO, "server started");

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    LOGGER.log(Level.INFO, "Shutting down server after JVM is shut down...");
                    try {
                        UserServer.this.stopServer();
                    } catch (InterruptedException e) {
                        LOGGER.log(Level.SEVERE, "UserServer is not stopped properly", e);
                    }
                }
            });
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "cannot start UserServer", e);
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
        UserServer userServer = new UserServer();
        userServer.startServer();
        userServer.blockUntilShutdown();
    }
}
