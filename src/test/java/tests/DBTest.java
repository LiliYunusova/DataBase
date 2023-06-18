package tests;

import database.DBConnection;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTest {
    private DBConnection dbConnection = new DBConnection();

    @Test
    public void selectStudentsTest(){
        dbConnection.connect();
        try {
            ResultSet resultSet = dbConnection.selectFrom("students");
            DBConnection.writeResultSetStudents(resultSet);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        dbConnection.close();
    }

    @Test
    public void selectCityTest(){
        dbConnection.connect();
        try {
            ResultSet resultSet = dbConnection.selectFrom("cities");
            DBConnection.writeResultSetCity(resultSet);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        dbConnection.close();
    }

    @Test
    public void insertIntoStudents(){
        dbConnection.connect();
        int insertIntoStudents = dbConnection.insertInto("students", "Jack", 3);
        try {
            ResultSet resultSet = dbConnection.selectFrom("students");
            DBConnection.writeResultSetStudents(resultSet);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        dbConnection.close();
    }
}
