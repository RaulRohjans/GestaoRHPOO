package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Employee {
    //Fields
    private int id;
    private String name;
    private Date entranceDate;
    private HashMap<String, Integer> workedDays;
    private double hourlyPay;
    public enum EmployeeType {NORMAL, MANAGER, DRIVER, SALESMAN};
    private EmployeeType type;

    private final static double DAILY_FOOD_SUBSIDY = 4.79;

    //Constructor
    public Employee(int id, String name, Date entranceDate, double hourlyPay) {
        this.id = id;
        this.name = name;
        this.entranceDate = entranceDate;
        this.workedDays = new HashMap<String, Integer>();
        this.type = EmployeeType.NORMAL;
        if(hourlyPay <= 0)
            throw new IllegalArgumentException("Invalid value for hourlyPay.");
        this.hourlyPay = hourlyPay;
    }

    public Employee() {
        this.id = -1;
        this.name = "";
        this.entranceDate = null;
        this.workedDays = new HashMap<String, Integer>();
        this.type = EmployeeType.NORMAL;
        this.hourlyPay = 0;
    }

    //Methods
    public void addWorkedDays(String d, int days){
        workedDays.put(d, days);
    }

    public void newEmployee() { //converter data para string
        Scanner sc = new Scanner(System.in);
        System.out.println("Name: ");
        name = sc.nextLine();
            if (name.isEmpty() || name == null) {
                System.out.println("This is an empty or null data");
            } else {
                this.name = name;
            }
        System.out.println("Entrance Date (yyyy-mm-dd): ");
        //entranceDate = sc.nextLine();
        System.out.println("Hourly Pay: ");
        this.hourlyPay = sc.nextDouble();
        System.out.println("Type: ");
        Methods.CategoryMenu();
        //Company.addEmployess();
    }
    public void addWorkedDays(int days){
        Calendar calendar = Calendar.getInstance();
        workedDays.put(String.valueOf(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH)), days);
    }

    /*public double getBasePay() {
        return workedDays * (hourlyPay * 8);
    }

    public double getExtraPay() {
        return workedDays * DAILY_FOOD_SUBSIDY;
    }

    public double getFullPay() {
        return getBasePay() + getExtraPay();
    }*/

    //Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getEntranceDate() {
        return entranceDate;
    }
    public void setEntranceDate(Date entranceDate) {
        this.entranceDate = entranceDate;
    }
    public void setEntranceDate(String entranceDate) throws ParseException {
        this.entranceDate = new SimpleDateFormat("yyyy-MM-dd").parse(entranceDate);
    }
    public HashMap<String, Integer> getWorkedDays() {
        return workedDays;
    }
    public void setWorkedDays(HashMap<String, Integer> workedDays) {
        this.workedDays = workedDays;
    }
    public EmployeeType getType() {
        return type;
    }
    public void setType(EmployeeType type) {
        this.type = type;
    }
    public double getHourlyPay() {
        return hourlyPay;
    }
    public void setHourlyPay(double hourlyPay) {
        this.hourlyPay = hourlyPay;
    }
}
