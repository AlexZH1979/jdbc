package com.epam.zhmyd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class JDBCUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "root";


    private static Connection connection;

    public static void executeStatement(String sql) {
        Connection connection = getConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private static void fillFriendships(String sql, int userId1, int userId2, Date date) {
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId1);
                statement.setInt(2, userId2);
                statement.setDate(3, date);
                statement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private static void fillPosts(String sql, int userId1, String text, Date date) {
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId1);
                statement.setString(2, text);
                statement.setDate(3, date);
                statement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void fillUser(String sql, String name, String surname, Date date) {
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, surname);
                statement.setDate(3, date);
                statement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Connection getConnection() {
        if (connection == null) {
            try {
                connection = createConnection();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }

    private static synchronized Connection createConnection() throws SQLException {
        Properties properties = new Properties();
        properties.put("user", USER);
        properties.put("password", PASSWORD);
        Driver driver = new com.mysql.jdbc.Driver();
        return driver.connect(URL, properties);
    }

    public static void create() {
        executeStatement(SQLStatements.DROP_SHEMA);
        executeStatement(SQLStatements.CREATE_SHEMA);
        executeStatement("use `simple`;");
        executeStatement(SQLStatements.TABLE_USER);
        executeStatement(SQLStatements.TABLE_FRIENSHIPS);
        executeStatement(SQLStatements.TABLE_POSTS);

        for(int i =0; i< 1001;i++){
            fillUser(SQLStatements.INSERT_USER, "user"+i, "user"+i,new Date(System.currentTimeMillis()));
        }

        for(int i =1; i< 500;i++){
            int count = new Random().nextInt(200);
            List<Integer> friends = new ArrayList<>();
            for (int j = 0; j <count ; j++) {
                int friend = new Random().nextInt(500)+500;
                if(!friends.contains(friend)) {
                    fillFriendships(SQLStatements.INSERT_FRIENSHIPS, i, friend, new Date(System.currentTimeMillis()));
                    friends.add(friend);
                }
            }
        }
        for(int i =1; i< 1001;i++){
            int count = new Random().nextInt(100);
            for (int j = 0; j <count ; j++) {
                    fillPosts(SQLStatements.INSERT_POSTS, i, "HELLO", new Date(System.currentTimeMillis()));

            }
        }


    }
}
