package gr.ihu.iee.bobross.objects;

import javax.swing.JLabel;

public class BobTable extends JLabel {
    
    private int tableId;
    private int receiptId;
    
    public BobTable(int id) {
        this(id, 0);
    }
    
    public BobTable(int id, int receiptId) {
        this.tableId = id;
        this.receiptId = receiptId;
    }
    
    public int getTableId() { return this.tableId; }
    public boolean isAvailable() { return (receiptId == 0); }
    public int getReceiptId() { return receiptId; }
    
    public void setReceiptId(int receiptId) { this.receiptId = receiptId; }

}
