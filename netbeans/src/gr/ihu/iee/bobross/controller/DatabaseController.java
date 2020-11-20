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
import java.util.Map;

public class DatabaseController {
    
    private static String driverClassName = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185240";
    private static String username = "it185240";
    private static String password = "528639417";
    
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
                int receiptId = rs.getInt("receiptid");
                table = new BobTable(tid, receiptId);
            }
            stmt.close();
            return table;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public HashMap<Integer, Integer> getAllTrapezia() {
        String query = "SELECT * FROM trapezi";
        HashMap<Integer, Integer> table = new HashMap<>();
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int tid = rs.getInt("tid");
                int receiptId = rs.getInt("receiptId");
                table.put(tid, receiptId);
            }
            stmt.close();
            return table;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<String> getGeumata(String category) {
        String query = "SELECT * FROM getGeumata(?)";
        List<String> geumata = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareStatement(query);
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String geuma = rs.getString("getgeumata");
                geumata.add(geuma);
            }
            stmt.close();
            return geumata;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public String[] getCategories() {
        String query = "SELECT DISTINCT category FROM katalogos";
        List<String> categories = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String category = rs.getString("category");
                categories.add(category);
            }
            stmt.close();
            return categories.toArray(new String[categories.size()]);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void insertItem(Map<String, String> item) {
        String category = item.get("category");
        double price = Double.parseDouble(item.get("price"));
        String name = item.get("name");
        int availability = 20; // TODO: Change this when GUI availability is implemented
        String query = "INSERT INTO katalogos(konoma, price, availability, category) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareCall(query);
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, availability);
            stmt.setString(4, category);
            stmt.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Map<Integer, String> getAllServitorous() {
        String query = "SELECT * FROM servitoros";
        Map<Integer, String> onomata = new HashMap<>();
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("sid");
                String onoma = rs.getString("onoma");
                onomata.put(id, onoma);
            }
            stmt.close();
            return onomata;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void insertParaggelia(String katalogos, int amount, int receiptid) {
        
    }
    
    public void insertReceipt(String servitoros) {
        
    }
    
    public void close() {
        try {
            dbConnection.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
    }

}
