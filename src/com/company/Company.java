package com.company;

import java.util.ArrayList;

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

    /* Getters and Setters*/
    public ArrayList<Employee> Employees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}
