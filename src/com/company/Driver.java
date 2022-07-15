package com.company;

import java.util.*;

public class Driver extends Employee{
    //Fields
    private HashMap<String, Double> distanceKms;
    private HashMap<String, Double> pricePerKm;

    //Constructors
    public Driver(int id, String name, Date entranceDate, double hourlyPay) {
        super(id, name, entranceDate, hourlyPay);
        setType(EmployeeType.DRIVER);
        this.distanceKms = new HashMap<String, Double>();
        this.pricePerKm = new HashMap<String, Double>();
    }

    public Driver() {
        super();
        setType(EmployeeType.DRIVER);
        this.distanceKms = new HashMap<String, Double>();
        this.pricePerKm = new HashMap<String, Double>();
    }

    //Methods
    public void addPricePerKm(int year, double price){
        if(price < 0){
            throw new IllegalArgumentException("Price cannot be negative!");
        }
        if (year < 1){
            throw new IllegalArgumentException("Year is invalid!");
        }
        pricePerKm.put(String.valueOf(year), price);
    }

    public void setKmMade(String date, double kms){
        if(date.isEmpty() || kms < 0){
            System.out.println("Set Kms input error. Check your parameters");
            return;
        }

        distanceKms.put(date, kms);
    }

    @Override
    public double calcPaycheck() {
        double distance = distanceKms.get(Calendar.getInstance().get(Calendar.YEAR) + "-" +
                Methods.getFormattedMonth());

        double price = pricePerKm.get(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));

        return super.calcPaycheck() + distance * price;
    }

    @Override
    public double calcTrimesterPaycheck(int year) {
        int kms = 0;
        double yearTotal = 0;

        if(year < 1)
            return 0;

        /* Fetch total distance */
        int monthCount = 0;
        for(String key: distanceKms.keySet()){
            if(Objects.equals(key.split("-")[0], String.valueOf(year))){
                kms += distanceKms.get(key);
                monthCount++;
            }
        }
        yearTotal = kms * pricePerKm.get(String.valueOf(year));

        //Do an average if worked more than 3 months that year
        if(monthCount >= 3)
            return super.calcTrimesterPaycheck(year) + (yearTotal / monthCount) * 3;
        else
            return super.calcTrimesterPaycheck(year) + yearTotal;
    }

    @Override
    public double calcSemesterPaycheck(int year) {
        int kms = 0;
        double yearTotal = 0;

        if(year < 1)
            return 0;

        /* Fetch total distance */
        int monthCount = 0;
        for(String key: distanceKms.keySet()){
            if(Objects.equals(key.split("-")[0], String.valueOf(year))){
                kms += distanceKms.get(key);
                monthCount++;
            }
        }
        yearTotal = kms * pricePerKm.get(String.valueOf(year));

        //Do an average if worked more than 3 months that year
        if(monthCount >= 6)
            return super.calcSemesterPaycheck(year) + (yearTotal / monthCount) * 6;
        else
            return super.calcSemesterPaycheck(year) + yearTotal;
    }

    @Override
    public double calcYearPaycheck(int year) {
        int kms = 0;

        if(year < 1)
            return 0;

        /* Fetch total distance */
        for(String key: distanceKms.keySet()){
            if(Objects.equals(key.split("-")[0], String.valueOf(year))){
                kms += distanceKms.get(key);
            }
        }

        return super.calcYearPaycheck(year) + kms * pricePerKm.get(String.valueOf(year));
    }

    //Getters and Setters
    public HashMap<String, Double> getDistanceKms() {
        return distanceKms;
    }

    public void setDistanceKms(HashMap<String, Double> distanceKms) {
        this.distanceKms = distanceKms;
    }

    public HashMap<String, Double> getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(HashMap<String, Double> pricePerKm) {
        this.pricePerKm = pricePerKm;
    }
}
