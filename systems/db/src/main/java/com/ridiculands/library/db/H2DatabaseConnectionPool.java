package com.ridiculands.library.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.h2.tools.RunScript;
import org.h2.tools.Server;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Gets a connection to the database
 * Loads a database driver class and gets connection using url, username and password
 * */
public class H2DatabaseConnectionPool {
    private static final Logger logger = Logger.getLogger(H2DatabaseConnectionPool.class.getName());
    private static ComboPooledDataSource cpds = new ComboPooledDataSource();

    static {
        try {
            cpds.setDriverClass("org.h2.Driver");
            cpds.setJdbcUrl("jdbc:h2:mem:shoppingDb");
            cpds.setUser("");
            cpds.setPassword("");
        } catch (PropertyVetoException e) {
            logger.log(Level.SEVERE, "Cannot initialize database connection pool", e);
        }
    }

    static Server server;

    private H2DatabaseConnectionPool() {

    }

    public static Connection getConnectionToDatabase() throws SQLException {
        return cpds.getConnection();
    }

    /* Loads the initialize.sql file from the classpath folder "resources".
    Runs all the queries from the file to create tables, insert records and make it ready to use
    **/
    public static void initializeDatabase() throws SQLException {
        logger.log(Level.INFO, "initialize service database...");
        InputStream resource = H2DatabaseConnectionPool.class.getClassLoader().getResourceAsStream("initialize.sql");
        RunScript.execute(getConnectionToDatabase(), new InputStreamReader(resource));
    }

    /*
     * Starts the database TCP server in case one needs to access it using a 3rd party(external) client
     *
     * */
    public static void startDatabase() throws SQLException {
        server = Server.createTcpServer().start();
    }

    /*
     * Stops the database server
     *
     * */
    public static void stopDatabase() {
        server.stop();
    }
}
