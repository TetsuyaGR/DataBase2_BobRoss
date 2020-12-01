package gr.ihu.iee.bobross.controller;

import gr.ihu.iee.bobross.gui.TablesFrame;
import gr.ihu.iee.bobross.objects.BobItem;
import gr.ihu.iee.bobross.objects.BobTable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DatabaseController {
    
    private static String driverClassName = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185233";
    private static String username = "it185233";
    private static String password = "it185233Alex";
    
    private Connection dbConnection;
    
    private TablesFrame tFrame;
    
    public DatabaseController(TablesFrame tFrame) {
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
        this.tFrame = tFrame;
    }
    
    public TablesFrame getTablesFrame() { return this.tFrame; }
    
    public int putTrapezi(int receiptId) {
        String query = "SELECT putTrapezi(?)";
        PreparedStatement stmt = null;
        int tid = 0;
        try {
            stmt = dbConnection.prepareStatement(query);
            if(receiptId==0)
                stmt.setNull(1, receiptId);
            else
                stmt.setInt(1, receiptId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
                tid = rs.getInt(1);
            stmt.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return tid;
    }
    
    public BobTable getTrapezi(int trapeziId) {
        String query = "SELECT * FROM getTrapezi(?)";
        BobTable table = null;
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareStatement(query);
            stmt.setInt(1, trapeziId);
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
    
    /* 
    * TODO: Na valei h maria trigger sta drop gia na mhn mporei na ginei drop
    *       ama to trapezi exei receiptId gia na mporei to function na
    *       epistrefei boolean me ta exceptions
    */
    public void dropTrapezi(int trapeziId) {
        String query = "SELECT dropTrapezi(?)";
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareStatement(query);
            stmt.setInt(1, trapeziId);
            stmt.executeQuery();
            stmt.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
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
    
    public boolean insertItem(Map<String, Object> item) {
        String category = (String) item.get("category");
        NumberFormat f = NumberFormat.getInstance(Locale.getDefault());
        Number number;
        try {
            String s = (String) item.get("price");
            s = s.replace(',', '.');
            number = f.parse(s);
        }
        catch(ParseException ex) {
            return false;
        }
        double price = number.doubleValue();
        String name = (String) item.get("name");
        int availability = (int) item.get("availability");
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
        return true;
    }
    
    public int getServitorosId(String onoma) {
        String query = "SELECT getServitoros(?)";
        int servitorosId = 0;
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareStatement(query);
            stmt.setString(1, onoma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                servitorosId = rs.getInt(1);
            }
            stmt.close();
            return servitorosId;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    public void putServitoros(String name) {
        String query = "SELECT putServitoros(?)";
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public String getServitorosName(int receiptId) {
        String query = "SELECT getServitorosFromReceipt(?)";
        String servitoros = "";
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareStatement(query);
            stmt.setInt(1, receiptId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
                servitoros = rs.getString(1);
            stmt.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return servitoros;
    }
    
    public Map<Integer, String> getAllServitorous() {
        String query = "SELECT * FROM getAllServitorous()";
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
    
    public int getKatalogosId(String katalogos) {
        String query = "SELECT getKatalogos(?)";
        int katalogosId = 0;
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareStatement(query);
            stmt.setString(1, katalogos);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                katalogosId = rs.getInt(1);
            }
            stmt.close();
            return katalogosId;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    public float getLogariasmo(BobTable table) {
        int receiptId = table.getTableId();
        String query = "SELECT getLogariasmo(?)";
        float logariasmos = 0.0f;
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareStatement(query);
            stmt.setInt(1, receiptId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
                logariasmos = rs.getFloat(1);
            stmt.close();
            return logariasmos;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return logariasmos;
    }
    
    public List<BobItem> getAllParaggelies(int receiptId) {
        String query = "SELECT * FROM getAllParaggelies(?)";
        List<BobItem> paraggelies = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareStatement(query);
            stmt.setInt(1, receiptId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String katalogos = rs.getString("katalogos");
                int amount = rs.getInt("amount");
                float price = rs.getFloat("price");
                paraggelies.add(new BobItem(katalogos, amount, price));
            }
            stmt.close();
            return paraggelies;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public int getMerides(int katalogosId) {
        String query = "SELECT getMerides(?)";
        int availability = 0;
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareStatement(query);
            stmt.setInt(1, katalogosId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                availability = rs.getInt(1);
            }
            stmt.close();
            return availability;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    public void insertParaggelia(BobItem geuma) {
        int katalogosId = getKatalogosId(geuma.getGeuma());
        if(katalogosId == 0) {
            System.out.println("ERROR: Couldn't find proion " + geuma.getGeuma());
            return;
        }
        int amount = geuma.getAmount();
        int availability = getMerides(katalogosId);
        if(amount > availability) {
            System.out.println("ERROR: Δεν υπάρχουν αρκετές μερίδες για το προϊόν " + geuma.getGeuma());
            if(availability == 0)
                return;
            System.out.println("Θα περαστούν όσες υπάρχουν (" + availability + ")");
            geuma.setAmount(availability);
        }
        String query = "SELECT putParaggelia(?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareCall(query);
            stmt.setInt(1, katalogosId);
            stmt.setInt(2, geuma.getAmount());
            stmt.setInt(3, geuma.getReceiptId());
            stmt.executeQuery();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int insertReceipt(String servitoros) {
        int servitorosId = getServitorosId(servitoros);
        if(servitorosId == 0) {
            System.out.println("ERROR: Couldn't find servitoros " + servitoros);
            return 0;
        }
        String query = "SELECT putReceipt(?)";
        PreparedStatement stmt = null;
        int receiptId = 0;
        try {
            stmt = dbConnection.prepareCall(query);
            stmt.setInt(1, servitorosId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
                receiptId = rs.getInt(1);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return receiptId;
    }
    
    public void updateTable(int tableId, int receiptId) {
        String query = "SELECT updateTable(?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = dbConnection.prepareCall(query);
            stmt.setInt(1, tableId);
            if(receiptId == 0)
                stmt.setNull(2, java.sql.Types.INTEGER);
            else
                stmt.setInt(2, receiptId);
            stmt.executeQuery();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void close() {
        try {
            dbConnection.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
    }

}
