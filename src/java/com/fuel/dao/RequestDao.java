package com.fuel.dao;

import com.fuel.bean.Request;
import com.fuel.bean.Total;
import com.fuel.bean.TotalByType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/userdb?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";
    private String jdbcDriver = "com.mysql.jdbc.Driver";

    private static final String INSERT_REQUEST_SQL = "INSERT INTO users" + " (fuel, quantity, total) VALUES" + " (?,?,?);"; 
    private static final String SELECT_REQUEST_BY_ID = "select id,fuel,quantity,total from users where id = ?;";
    private static final String SELECT_ALL_USERS = "select * from users;";
    private static final String DELETE_REQUEST_SQL = "delete from users where id = ?;"; 
    private static final String UPDATE_REQUEST_SQL = "update users set fuel = ?, quantity = ?, total = ? where id = ?;";
    private static final String GET_TOTAL_BY_FUEL = "select fuel,sum(total) as sum from users group by fuel;"; 
    private static final String GET_TOTAL = "select count(id) as count, sum(total) as sums from users;"; 
    
    public RequestDao() {
    }

    protected Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        return connection;
    }

    //insert request
    public void insertRequest(Request request) throws SQLException { 

        try ( Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST_SQL)) {
        //INSERT INTO requests (fuel, quantity, total) VALUES (?,?,?);
            preparedStatement.setString(1, request.getFuel());
            preparedStatement.setDouble(2, request.getQuantity());
            preparedStatement.setDouble(3, request.getTotal());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    //select request by id
    public Request selectRequest(int id) { 
        Request request = null;
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REQUEST_BY_ID);) {
            preparedStatement.setInt(1, id);
            
            ResultSet rs = preparedStatement.executeQuery();
        //"select id,fuel,quantity,total from requests where id = ?;";
            while (rs.next()) {
                String fuel = rs.getString("fuel");
                double quantity = rs.getDouble("quantity");
                double total = rs.getDouble("total");
                request = new Request(id,fuel,quantity,total);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return request;
    }

    //select all requests
    public List<Request> selectAllRequests(){ 
        //select * from users;
        List<Request> data = new ArrayList<>();
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
           
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String fuel = rs.getString("fuel");
                double quantity = rs.getDouble("quantity");
                double total = rs.getDouble("total");
                
                
                data.add(new Request(id, fuel,quantity,total));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return data;
    }
    
    //update request
    public boolean updateRequest(Request request) throws SQLException{ 
        boolean rowUpdated;
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST_SQL)) {
            //update requests set fuel = ?, quantity = ?, total = ? where id = ?;
            
            preparedStatement.setString(1, request.getFuel());
            preparedStatement.setDouble(2, request.getQuantity());
            preparedStatement.setDouble(3, request.getTotal());
            preparedStatement.setInt(4, request.getId());
            
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } 
        return rowUpdated;
    }
    
    //delete request
    public boolean deleteRequest(int id) throws SQLException { 
        boolean rowDeleted;
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST_SQL)) {
            preparedStatement.setInt(1, id);
            //delete from requests where id = ?;
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } 
        return rowDeleted;
    }
    
        //Get total by type
    public List<TotalByType> getTotalByType(){ 
        //select fuel,sum(total) as sum from users group by fuel
        List<TotalByType> data = new ArrayList<>();
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(GET_TOTAL_BY_FUEL);) {
           
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String fuel = rs.getString("fuel");
                double sum = rs.getDouble("sum");
                
                data.add(new TotalByType(fuel, sum));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return data;
    }
    
    // get Total and Count
     public Total getTotalAndCount(){ 
        //select count(id) as count, sum(total) as sums from users
        Total data = null;
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(GET_TOTAL);) {
           
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                String count = rs.getString("count");
                String sums = rs.getString("sums");
                
                data = new Total(count, sums);
            }
                
        } catch (SQLException e) {
            printSQLException(e);
        }
        return data;
    }
     
    //SQLException
    private void printSQLException(SQLException ex) { 
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + ((SQLException) e).getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
    //to retun the fuel type
    public String fuelType(int fuel){ 
        switch (fuel) {
            case 1:
                return "Petrol 95";
            case 2:
                return "Petrol 92";
            case 3:
                return "Diesel";
            default:
                return "Super Diesel";
        }
    }

}
