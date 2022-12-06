// used for insert, update, select (to the list) a request

package com.fuel.bean;

public class Request {

    private int id;
    private double quantity;
    private double total;
    private String fuel;

    public Request(int id, double quantity, double total) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;
    }
    
    public Request(String fuel, double quantity, double total) {
        this.fuel = fuel;
        this.quantity = quantity;
        this.total = total;
    }
    
    public Request(int id, String fuel, double quantity, double total) {
        this.id = id;
        this.fuel = fuel;
        this.quantity = quantity;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
}
