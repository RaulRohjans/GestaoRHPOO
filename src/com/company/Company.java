package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class Company {

    //fields
    private ArrayList<Employee> employees;
    
    //constructor
    public Company(){
        this.employees = new ArrayList<Employee>();
    }

    /* Methods */
    public void addEmployee(Employee employee) {
        for(Employee emp : employees){
            if(emp.getId() == employee.getId()){
                System.out.println("An employee with this ID already exists!");
                return;
            }
        }

        employees.add(employee);
    }

    public Employee getEmployee(int id){
        for(Employee emp : employees){
            if(emp.getId() == id){
                return emp;
            }
        }
        return null;
    }

    public void deleteEmployees(){
        employees = new ArrayList<Employee>();
    }

    public int employeeCountPerCategory(Employee.EmployeeType e){
        int cnt = 0;
        for(Employee emp : employees){
            if(emp.getType() == e){
                cnt++;
            }
        }
        return cnt;
    }

    public void showAllEmployees(){
        if(employees.size() < 1){
            System.out.println("Not employees to show.");
            return;
        }


        System.out.println("--------- // ----------");
        for(Employee emp: employees){
            System.out.println("ID: " + emp.getId());
            System.out.println("Name: " + emp.getName());
            System.out.println("Entrance Date: " + emp.getEntranceDate().toString());
            System.out.println("Worked Days:");

            //Fetch worked days
            for (String key : emp.getWorkedDays().keySet()) {
                System.out.println("    " + key + ": " + emp.getWorkedDays().get(key));
            }
            System.out.println("Hourly Pay: " + emp.getHourlyPay());

            switch (emp.getType()){
                case NORMAL -> {
                    System.out.println("Type: Normal");
                }
                case MANAGER -> {
                    System.out.println("Type: Manager");
                    System.out.println("Award: " + Manager.getAWARD());
                }
                case DRIVER -> {
                    System.out.println("Type: Driver");
                    System.out.println("Distance Made (Kms): " + ((Driver) emp).getDistanceKms());

                    //Fetch price per km
                    System.out.println("Price per Km:");
                    for (String key : ((Driver)emp).getPricePerKm().keySet()) {
                        System.out.println("    " + key + ": " + ((Driver)emp).getPricePerKm().get(key));
                    }
                }
                case SALESMAN -> {
                    System.out.println("Type: Salesman");

                    //Fetch award percent
                    System.out.println("Award Percent:");
                    for (Integer key : ((Salesman)emp).getAwardPercent().keySet()) {
                        System.out.println("    " + key + ": " + ((Salesman)emp).getAwardPercent().get(key));
                    }

                    //Fetch Sales
                    System.out.println("Sales:");
                    for (Sale sale : ((Salesman)emp).getSales()) {
                        System.out.println("    Sale ID: " + sale.getId());
                        System.out.println("    Sale Total: " + sale.getTotal());
                        System.out.println("    Sale ID: " + sale.getSaleDate().toString());
                        System.out.println("    --- * ---");
                    }
                }
            }

            System.out.println("--------- // ----------");
        }
    }

    /* Getters and Setters*/
    public ArrayList<Employee> Employees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}

