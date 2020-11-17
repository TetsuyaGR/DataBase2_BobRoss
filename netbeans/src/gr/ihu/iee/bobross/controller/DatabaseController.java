package gr.ihu.iee.bobross.controller;

import gr.ihu.iee.bobross.objects.BobTable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseController {
    
    private static String driverClassName = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://localhost:54322/it185233";
    private static String username = "it185233";
    private static String password = "it185233Alex";
    
    private Connection dbConnection;
    
    public DatabaseController() {
        try {
            Class.forName(driverClassName);
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        try {
            dbConnection = DriverManager.getConnection(url, username, password);
        } catch(SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }
    
    public BobTable getTrapezi(int id) {
        String query = "SELECT * FROM trapezi WHERE tid = ?";
        BobTable table = null;
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int tid = rs.getInt("tid");
                boolean available = rs.getBoolean("available");
                table = new BobTable(tid, available);
            }
            stmt.close();
            return table;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public HashMap<Integer, Boolean> getAllTrapezia() {
        String query = "SELECT * FROM trapezi";
        HashMap<Integer, Boolean> table = new HashMap<>();
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int tid = rs.getInt("tid");
                boolean available = rs.getBoolean("available");
                table.put(tid, available);
            }
            stmt.close();
            return table;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void close() {
        try {
            dbConnection.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
    }

}
