package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Company {

    //fields
    public ArrayList<Employee> employees;
    
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
    }

    public void addEmployees(Employee emp){
        employees.add(emp);
    }

    public void returnEmployee(int id) {
        for(Employee emp: employees){
            if(emp.getId() == id){
                System.out.println("Name: \n" + emp.getName()+ "Entrance Date: \n"+ emp.getEntranceDate() +
                        "Type: \n" + emp.getType() + "Hourly Pay: \n" + emp.getHourlyPay() +
                        "Worked Days: \n" + emp.getWorkedDays());
                return;
            } else {
                System.out.println("Employee doesn't exist");
            }
        }
    }

    public void showEmployees() {
        for(Employee e: employees){
            System.out.println("Name: \n" + e.getName()+ "Entrance Date: \n"+ e.getEntranceDate() +
                    "Type: \n" + e.getType() + "Hourly Pay: \n" + e.getHourlyPay() +
                    "Worked Days: \n" + e.getWorkedDays());
        } //(e)
    }

    public void showEmployeePerCategory() {
        System.out.println("Categories: \n");
        System.out.println("1. NORMAL\n");
        System.out.println("2. MANAGER\n");
        System.out.println("3. DRIVER\n");
        System.out.println("4. SALESMAN\n");

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a option: ");
        while (sc.hasNext()) {
            int select = -1;
            if (sc.hasNextInt()) {
                select = sc.nextInt();
            }

            switch (select) {
                case 1:
                    for(Employee.EmployeeType emp : Employee.EmployeeType.NORMAL.values()){
                        System.out.println(emp);
                    }
                    break;
                case 2:
                    for(Employee.EmployeeType emp : Employee.EmployeeType.MANAGER.values()){
                        System.out.println(emp);
                    }
                    break;
                case 3:
                    for(Employee.EmployeeType emp : Employee.EmployeeType.DRIVER.values()){
                        System.out.println(emp);
                    }
                    break;
                case 4:
                    for(Employee.EmployeeType emp : Employee.EmployeeType.SALESMAN.values()){
                        System.out.println(emp);
                    }
                    break;
                default:
                    System.out.println("Choose a valid option");
            }
        }
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

