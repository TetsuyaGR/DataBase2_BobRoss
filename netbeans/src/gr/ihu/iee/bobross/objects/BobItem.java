package gr.ihu.iee.bobross.objects;

public class BobItem {
    
    private String geuma;
    private int amount;
    private int receiptId;

    public BobItem(String geuma, int amount, int receiptId) {
        this.geuma = geuma;
        this.amount = amount;
        this.receiptId = receiptId;
    }

    public String getGeuma() {
        return geuma;
    }

    public int getAmount() {
        return amount;
    }
    
    public int getReceiptId() {
        return receiptId;
    }
    
    @Override
    public String toString() {
        return geuma;
    }

}
