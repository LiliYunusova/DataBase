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
            ResultSet resultSetBeforeInsertTable = dbConnection.selectFrom("students");
            int expectedCountStudents = DBConnection.countStudent(resultSetBeforeInsertTable);
            dbConnection.insertInto("students", "Nike", 3);
            ResultSet resultSetAfterInsertTable = dbConnection.selectFrom("students");
            int actualCountStudents = DBConnection.countStudent(resultSetAfterInsertTable);
            Assert.assertEquals(actualCountStudents, expectedCountStudents + 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbConnection.close();
    }
}
