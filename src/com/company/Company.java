package com.company;

import java.util.ArrayList;

public class Company {

   //field
    private final ArrayList<Employee> employees;
    
    //constructor
    public Company(){
        this.employees = new ArrayList<>();
    } 


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

    /* Getters and Setters*/
    public ArrayList<Employee> Employees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}

