package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Company {

    //fields
    private ArrayList<Employee> employees;

    //constructor
    public Company(){
        this.employees = new ArrayList<Employee>();
    }

    /* Methods */

    public void exportFile() {
        //Create a JSONArray
        JSONArray array = new JSONArray();
        for(Employee e : employees) {
            JSONObject object = new JSONObject();

            //Add values
            object.put("ID: ", e.getId());
            object.put("Name: ", e.getName());
            object.put("Entrance date: ", e.getEntranceDate());
            object.put("Worked days: ", e.getWorkedDays());
            object.put("Hourly pay: ", e.getHourlyPay());
            object.put("Type: ", e.getType());
            switch (e.getType()){
                case NORMAL -> {
                    array.put(object);
                }
                case DRIVER -> {
                    ((Driver)e).getDistanceKms();
                    ((Driver)e).getPricePerKm();
                    array.put(object);
                }
                case MANAGER -> {
                    Manager.getAWARD();
                    array.put(object);
                }
                case SALESMAN -> {
                    ((Salesman)e).getAwardPercent();
                    ((Salesman)e).getSales();
                    array.put(object);
                }
                default -> throw new IllegalStateException("Unexpected value: " + e.getType());
            }

        }
        //Create a new FileWriter object
        try {
            FileWriter file = new FileWriter(File()+"/employees.json");
            file.write(new JSONObject().put("Employees: ",array).toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON file created: " + array);
    }

    private String File(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Where you want to save the file?");
        String path = sc.nextLine();
        if(path.isEmpty()) { System.out.println("C:"); }
        return path;
    }

    public void addEmployee(Employee employee) {
        for(Employee emp : employees){
            if(emp.getId() == employee.getId()){
                System.out.println("An employee with this ID already exists!");
                return;
            }
        }

        employees.add(employee);
    }

    public void showEmployeePerCategory() {
        System.out.println("Categories: \n");
        System.out.println("1. NORMAL");
        System.out.println("2. MANAGER");
        System.out.println("3. DRIVER");
        System.out.println("4. SALESMAN");

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a option: ");
        while (sc.hasNext()) {
            int select = -1;
            if (sc.hasNextInt()) {
                select = sc.nextInt();
            }

            switch (select) {
                case 1:
                    for(Employee emp : employees){
                        if(emp.getType() == Employee.EmployeeType.NORMAL)
                            System.out.println(emp);
                    }
                    break;
                case 2:
                    for(Employee emp : employees){
                        if(emp.getType() == Employee.EmployeeType.MANAGER)
                            System.out.println(emp);
                    }
                    break;
                case 3:
                    for(Employee emp : employees){
                        if(emp.getType() == Employee.EmployeeType.DRIVER)
                            System.out.println(emp);
                    }
                    break;
                case 4:
                    for(Employee emp : employees){
                        if(emp.getType() == Employee.EmployeeType.SALESMAN)
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
            Methods.printEmployee(emp);
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

    public int newEmployeeID(){
        return employees.get(employees.size()-1).getId() + 1;
    }

    /* Getters and Setters*/
    public ArrayList<Employee> Employees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}

