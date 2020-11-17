package gr.ihu.iee.bobross.objects;

import javax.swing.JLabel;

public class BobTable extends JLabel {
    
    private int tableId;
    private boolean available;
    
    public BobTable(int id) {
        this(id, false);
    }
    
    public BobTable(int id, boolean available) {
        this.tableId = id;
        this.available = available;
    }
    
    public int getTableId() { return this.tableId; }
    public boolean isAvailable() { return this.available; }
    
    public void setAvailable(boolean available) { this.available = available; }

}
