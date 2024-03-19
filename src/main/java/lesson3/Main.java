package lesson3;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\RESTO;" +
                "encrypt=false;databaseName=Test;user=java;password=java");
        // createTableStudent(connection);
        // insertData(connection);
        updateId(connection);
        selectData(connection);
    }

    private static void createTableStudent(Connection connection) throws SQLException {

        try (Statement statement = connection.createStatement()) {
            statement.execute("create table Student  (id int, firtName varchar(256), secondName varchar(256)," +
                    "age int)");
        }

    }

    private static void insertData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                            insert into student (id,firtName,secondName,age) values 
                            (1,'Alex','Neroda',25),
                            (2,'Katya','Bezruk',21),
                            (3,'Vova','Neroda',54)
                    """);
        }


    }

    private static void selectData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select id,firtName,secondName,age from student");
            while (resultSet.next()) {
                System.out.println("id=" + resultSet.getInt("id") + " firtsName=" + resultSet.getString("firtName"));
            }
        }
    }

    private static void updateId(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            int affectedRows = statement.executeUpdate("""
                    update student  set id ='999' where id ='3'
                    """);
        }
    }
}
