package database;

import java.sql.*;

public class DBConnection {
    private Connection connect = null;
    private Statement statement = null;

    public void connect() {
        try {
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/aqa20?useSSL=false&serverTimezone=GMT",
                            "root", "32kq4WxW");
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet selectFrom(String tableName) {
        try {
            return statement
                    .executeQuery(String.format("select * from %s", tableName));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ResultSet select(String query) {
        try {
            return statement
                    .executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public int executeQuery(String query) {
        try {
            return statement
                    .executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public int insertInto(String tableName, String studentName, int cityId) {
        try {
            return statement
                    .executeUpdate(String.format("INSERT INTO %s(name, cityid) VALUES ('%s', %d)",
                            tableName, studentName, cityId));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public static int countStudent(ResultSet resultSet) throws SQLException {
        int count = 0;
        while (resultSet.next()) {
            count++;
        }
        return count;
    }

    public static void writeResultSetCity(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            System.out.println("ID: " + id);
            System.out.println("NAME: " + name);
        }
    }

    public static void writeResultSetStudents(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String studentName = resultSet.getString("name");
            String cityName = resultSet.getString("cityid");
            System.out.println("ID: " + id);
            System.out.println("NAME: " + studentName);
            System.out.println("City" + cityName);
        }
    }

    // You need to close the resultSet
    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception ignored) {
        }
    }

}
