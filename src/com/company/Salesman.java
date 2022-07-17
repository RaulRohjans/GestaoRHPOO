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
    private Map <Integer, Double> awardPercent;

    //Constructors
    public Salesman(int id, String name, Date entranceDate, double hourlyPay, double awardPercent) {
        super(id, name, entranceDate, hourlyPay);
        setType(EmployeeType.SALESMAN);
        sales = new ArrayList<Sale>();
        this.awardPercent = new HashMap <Integer, Double>();
    }

    public Salesman() {
        super();
        setType(EmployeeType.SALESMAN);
        sales = new ArrayList<Sale>();
        this.awardPercent = new HashMap <Integer, Double>();
    }

    //Methods
    public void addSale(Sale sale) {
        if(sale == null){
            System.out.println("Invalid sale.");
            return;
        }

        sales.add(sale);
    }

    public void addAwardPec(int year, double percent){
        if(year < 1000 || percent < 0)
            throw new IllegalArgumentException("Invalid award percent arguments.");
        awardPercent.put(year, percent);
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

    public ArrayList<Sale> getMonthlySales(){
        ArrayList<Sale> monthlySales = new ArrayList<Sale>();
        for(Sale sale : sales){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(sale.getSaleDate());

            if(calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)
                    && calendar.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH))
                monthlySales.add(sale);
        }
        return monthlySales;
    }

    public ArrayList<Sale> getYearlySales(int year){
        ArrayList<Sale> monthlySales = new ArrayList<Sale>();
        for(Sale sale : sales){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(sale.getSaleDate());

            if(calendar.get(Calendar.YEAR) == year)
                monthlySales.add(sale);
        }
        return monthlySales;
    }

    @Override
    public double calcPaycheck() {
        double percent = awardPercent.get(Calendar.getInstance().get(Calendar.YEAR));

        ArrayList<Sale> monthlySales = getMonthlySales();

        //Calculate total in monthly sales
        double total = 0;
        for(Sale sale: monthlySales){
            total += sale.getTotal();
        }

        return super.calcPaycheck() + (total * percent);
    }

    @Override
    public double calcTrimesterPaycheck(int year) {
        double percent = awardPercent.get(Calendar.getInstance().get(Calendar.YEAR));

        ArrayList<Sale> yearlySales = getYearlySales(year);

        //Calculate total in yearly sales
        double total = 0;
        for(Sale sale: yearlySales){
            total += sale.getTotal();
        }

        return super.calcTrimesterPaycheck(year) + ((total * percent)/12) * 3;
    }

    @Override
    public double calcSemesterPaycheck(int year) {
        double percent = awardPercent.get(Calendar.getInstance().get(Calendar.YEAR));

        ArrayList<Sale> yearlySales = getYearlySales(year);

        //Calculate total in yearly sales
        double total = 0;
        for(Sale sale: yearlySales){
            total += sale.getTotal();
        }

        return super.calcSemesterPaycheck(year) + ((total * percent)/12) * 6;
    }

    @Override
    public double calcYearPaycheck(int year) {
        double percent = awardPercent.get(Calendar.getInstance().get(Calendar.YEAR));

        ArrayList<Sale> yearlySales = getYearlySales(year);

        //Calculate total in yearly sales
        double total = 0;
        for(Sale sale: yearlySales){
            total += sale.getTotal();
        }

        return super.calcYearPaycheck(year) + total * percent;
    }

    //Getters and setters
    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sale> sales) {
        this.sales = sales;
    }

    //Getters and Setters
    public Map<Integer, Double> getAwardPercent() {
        return awardPercent;
    }

    public void setAwardPercent(Map<Integer, Double> awardPercent) {
        this.awardPercent = awardPercent;
    }
}
