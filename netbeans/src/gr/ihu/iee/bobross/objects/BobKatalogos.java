/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.ihu.iee.bobross.objects;

/**
 *
 * @author alexm
 */
public class BobKatalogos {
    
    private int katalogosId;
    private String onoma;
    private double price;
    private int availability;
    private String category;

    public BobKatalogos(int katalogosId, String onoma, double price, int availability, String category) {
        this.katalogosId = katalogosId;
        this.onoma = onoma;
        this.price = price;
        this.availability = availability;
        this.category = category;
    }

    public int getKatalogosId() {
        return katalogosId;
    }

    public String getOnoma() {
        return onoma;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailability() {
        return availability;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return onoma;
    }

    public void setOnoma(String onoma) {
        this.onoma = onoma;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
    
    
}
