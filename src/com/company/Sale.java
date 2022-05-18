package com.company;

import java.util.Date;

public class Sale {
    //Fields
    private int id;
    private double total;
    private Date saleDate;

    //Constructors
    public Sale(int id, double total, Date saleDate) {
        this.id = id;
        this.total = total;
        this.saleDate = saleDate;
    }

    //Methods
    @Override
    public String toString(){
        return getId() + " - " + getTotal() + "€ (" + getSaleDate().toString() + ")";
    }

    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
}
