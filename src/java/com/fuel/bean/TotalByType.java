//used to get the total by fuel type 

package com.fuel.bean;

public class TotalByType {
    private String fuel;
    private double sum;

    public TotalByType(String fuel, double sum) {
        this.fuel = fuel;
        this.sum = sum;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
    
    
}
