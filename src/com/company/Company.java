package com.company;

import java.text.DecimalFormat;
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

        employees.add(employee);
    }

    public void addEmployees(Employee emp){
        employees.add(emp);
        System.out.println("Added employee");
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

    public void showAllCosts(int year){
        DecimalFormat df = new DecimalFormat("0.00");

        /* Trimester (3 months) */
        double trimester = 0;
        for(Employee emp : employees){
            trimester += emp.calcTrimesterPaycheck(year);
        }
        /*---------------------*/

        /* Semester (6 months) */
        double semester = 0;
        for(Employee emp : employees){
            semester += emp.calcSemesterPaycheck(year);
        }
        /*---------------------*/

        /* Yearly */
        double yearly = 0;
        for(Employee emp : employees){
            yearly += emp.calcYearPaycheck(year);
        }
        /*--------*/

        //Show results
        System.out.println("--------- " + year + " ---------");
        System.out.println("Trimestral Costs: " + df.format(trimester));
        System.out.println("Semestral Costs: " + df.format(semester));
        System.out.println("Yearly Costs: " + df.format(yearly));
        System.out.println("---------  //  ---------");
    }

    /* Getters and Setters*/
    public ArrayList<Employee> Employees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}

