package tests;

import database.DBConnection;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTest {
    private DBConnection dbConnection = new DBConnection();

    @Test
    public void selectStudentsTest() {
        dbConnection.connect();
        try {
            ResultSet resultSet = dbConnection.selectFrom("students");
            DBConnection.writeResultSetStudents(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbConnection.close();
    }

    @Test
    public void selectCityTest() {
        dbConnection.connect();
        try {
            ResultSet resultSet = dbConnection.selectFrom("cities");
            DBConnection.writeResultSetCity(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbConnection.close();
    }

    @Test
    public void insertIntoStudents() {
        dbConnection.connect();
        try {
            ResultSet resultSet = dbConnection.selectFrom("students");
            int expectedCountStudent = DBConnection.countStudent(resultSet);
            dbConnection.insertInto("students", "Nike", 3);
            ResultSet resultSet3 = dbConnection.selectFrom("students");
            int actualCountStudents = DBConnection.countStudent(resultSet3);
            Assert.assertEquals(actualCountStudents, expectedCountStudent + 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbConnection.close();
    }
}
