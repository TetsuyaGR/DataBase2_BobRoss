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
public class BobHmeras {
    
    private int tid;
    private double logariasmos;
    private String servitoros;

    public BobHmeras(int tid, double logariasmos, String servitoros) {
        this.tid = tid;
        this.logariasmos = logariasmos;
        this.servitoros = servitoros;
    }

    public double getLogariasmos() {
        return logariasmos;
    }
    
    @Override
    public String toString() {
        return "Τραπέζι " + tid + " Λογαριασμός: " + logariasmos + " Σερβιτόρος: " + servitoros;
    }
    
}
