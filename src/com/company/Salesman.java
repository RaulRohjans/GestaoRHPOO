package com.company;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.HashMap;

public class Salesman extends Employee{
    //Fields
    private ArrayList<Sale> sales;
    private Map <Double, Double> awardPercent;

    //Constructors
    public Salesman(int id, String name, Date entranceDate, double hourlyPay, double awardPercent) {
        super(id, name, entranceDate, hourlyPay);
        setType(EmployeeType.SALESMAN);
        sales = new ArrayList<Sale>();
        this.awardPercent = new HashMap <Double, Double>();
    }

    public Salesman() {
        super();
        setType(EmployeeType.SALESMAN);
        sales = new ArrayList<Sale>();
        this.awardPercent = new HashMap <Double, Double>();
    }

    //Methods
    public void addSale(Sale sale) {
        if(sale == null){
            System.out.println("Invalid sale.");
            return;
        }

        sales.add(sale);
    }

    public void addSale(double total, Date saleDate) {
        if(total < 0 || saleDate.after(new Date())){
            System.out.println("Invalid sale information.");
            return;
        }

        sales.add(new Sale(sales.get(sales.size()-1).getId() + 1, total, saleDate));
    }

    public void showSales() {
        System.out.println("----- " + getName() + "Sales Information -----");
        for(Sale sale : sales){
            System.out.println(sale.toString());
        }
        System.out.println("------------------------------");
    }

    public void showYearlySales() {
        System.out.println("----- " + getName() + "Sales Information -----");
        for(Sale sale : sales){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(sale.getSaleDate());

            if(calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR))
                System.out.println(sale.toString());
        }
        System.out.println("------------------------------");
    }

    public void showYearlySales(int year) {
        if(year < 0){
            System.out.println("Invalid year.");
            return;
        }

        System.out.println("----- " + getName() + "Sales Information -----");
        for(Sale sale : sales){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(sale.getSaleDate());

            if(calendar.get(Calendar.YEAR) == year)
                System.out.println(sale.toString());
        }
        System.out.println("------------------------------");
    }

    public void removeSale(int id){
        if(id < 1){
            System.out.println("Invalid ID");
            return;
        }

        sales.removeIf(sale -> sale.getId() == id);
        System.out.println("All sales with the ID " + id + " where removed.");
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sale> sales) {
        this.sales = sales;
    }

    //Getters and Setters
    public Map<Double, Double> getAwardPercent() {
        return awardPercent;
    }

    public void setAwardPercent(Map<Double, Double> awardPercent) {
        this.awardPercent = awardPercent;
    }
}
