package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import org.apache.commons.io.IOUtils;
import org.json.*;

public class Main {

    public static void main(String[] args) throws ParseException {
        int opc = -1;
        Scanner scanner = new Scanner(System.in);
        Company company = new Company();

        /*
         * Codigo para teste
         */

        company.addEmployee(new Employee(1, "Test1", Date.valueOf(LocalDateTime.now().toLocalDate()), 5.5));
        company.addEmployee(new Employee(2, "Test2", Date.valueOf(LocalDateTime.now().toLocalDate()), 5.5));
        company.addEmployee(new Employee(3, "Test3", Date.valueOf(LocalDateTime.now().toLocalDate()), 5.5));
        company.addEmployee(new Employee(4, "Test4", Date.valueOf(LocalDateTime.now().toLocalDate()), 5.5));
        company.addEmployee(new Employee(5, "Test5", Date.valueOf(LocalDateTime.now().toLocalDate()), 5.5));
        company.addEmployee(new Employee(6, "Test6", Date.valueOf(LocalDateTime.now().toLocalDate()), 5.5));
        company.addEmployee(new Employee(7, "Test7", Date.valueOf(LocalDateTime.now().toLocalDate()), 5.5));
        company.addEmployee(new Employee(8, "Test8", Date.valueOf(LocalDateTime.now().toLocalDate()), 5.5));
        company.addEmployee(new Employee(9, "Test9", Date.valueOf(LocalDateTime.now().toLocalDate()), 5.5));
        company.addEmployee(new Employee(10, "Test10", Date.valueOf(LocalDateTime.now().toLocalDate()), 5.5));
        /*-----------------*/

        do{
            opc = Methods.MainMenu();
            switch (opc){
                case 0: //Leave App
                    System.out.println("Bye bye!");
                    return;
                case 1: { //Insert New Employee
                    Employee emp = new Employee();
                    emp.setId(company.newEmployeeID());
                    System.out.println("Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Entrance Date (yyyy-mm-dd): ");
                    String dateEntrance = scanner.nextLine();
                    System.out.println("Hourly Pay: ");
                    double hourly = scanner.nextDouble();

                    switch (Methods.CategoryMenu()) {
                        case NORMAL -> {
                            company.addEmployee(emp);
                            System.out.println("Added employee");
                        }
                        case DRIVER -> {
                            emp = new Driver();
                            ((Driver)emp).setDistanceKms(new HashMap<String,Double>());
                            ((Driver)emp).setPricePerKm(new HashMap<String,Double>());
                            company.addEmployee(emp);
                            System.out.println("Added employee");
                        }
                        case MANAGER -> {
                            emp = new Manager();
                            company.addEmployee(emp);
                            System.out.println("Added employee");
                        }
                        case SALESMAN -> {
                            emp = new Salesman();
                            ((Salesman)emp).setAwardPercent(new HashMap<Integer,Double>());
                            ((Salesman)emp).setSales(new ArrayList<Sale>());
                            company.addEmployee(emp);
                            System.out.println("Added employee");
                        }
                        default -> System.out.println("Invalid employee type.");
                    }
                    if (name.isEmpty()) {
                        System.out.println("This is an empty or null data");
                    } else {
                        emp.setName(name);
                    }
                    emp.setEntranceDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateEntrance));
                    emp.setHourlyPay(hourly);
                    Methods.AwaitInput();
                    break;
                }
                case 2: //Check for Employee
                    System.out.print("Employee ID: ");

                    try {
                        if(company.getEmployee(Integer.parseInt(scanner.nextLine())) != null)
                            System.out.println("This employee is in the system!");
                        else
                            System.out.println("This employee does not exist!");
                    }
                    catch (NumberFormatException e){
                        System.out.println("Invalid employee ID!");
                    }

                    Methods.AwaitInput();
                    break;

                case 3: //Get Employee Record
                    System.out.println("ID: ");
                    int id = scanner.nextInt();
                    Methods.printEmployee(company.getEmployee(id));
                    break;

                case 4: //Import Employee List
                    System.out.print("Importing files will delete all current data.\n" +
                            "Do you wish to proceed? (y/n) ");

                    if(!scanner.nextLine().toUpperCase(Locale.ROOT).equals("Y")){
                        System.out.println("Action canceled.");
                        Methods.AwaitInput();
                    }

                    //Read File path
                    System.out.print("File Path: ");
                    File f;
                    String path = scanner.nextLine();

                    if(path.isEmpty()){
                        System.out.println("Invalid file path!");
                        Methods.AwaitInput();
                        break;
                    }

                    f = new File(path);
                    if(!f.exists()){
                        System.out.println("File does not exist");
                        Methods.AwaitInput();
                        break;
                    }

                    //Read file
                    JSONObject obj = null;
                    try{
                        InputStream is = new FileInputStream(path);
                        String jsonTxt = IOUtils.toString(is, StandardCharsets.ISO_8859_1);
                        obj = new JSONObject(jsonTxt);
                    }
                    catch (IOException e){
                        System.out.println("File does not exist");
                        Methods.AwaitInput();
                        break;
                    }

                    //Insert Employees
                    try{
                        JSONArray employees = obj.getJSONArray("employees");
                        company.deleteEmployees();
                        for(int i = 0; i < employees.length(); i++ ){
                            Employee employee = Methods.GetEmployeeFromJSON(employees.getJSONObject(i));
                            if(employee == null)
                                throw new NullPointerException("Could not read employee, there has been an error parsing the file.\n" +
                                        "Make sure the employee types are correct.");
                            company.addEmployee(employee);
                        }
                    }
                    catch (ParseException | NumberFormatException e){
                        System.out.println(e.getMessage());
                        Methods.AwaitInput();
                        break;
                    }
                    catch (Exception e){
                        System.out.println("The following unknown error has occurred: \n" + e.getMessage());
                        Methods.AwaitInput();
                        break;
                    }

                    Methods.AwaitInput();
                    break;
                case 5: { //Get Employee Number per Category
                    Employee.EmployeeType category = Methods.CategoryMenu();

                    if(category == Employee.EmployeeType.NULL){
                        System.out.println("Invalid Category!");
                        Methods.AwaitInput();
                        break;
                    }

                    System.out.println("There are " + company.employeeCountPerCategory(category) +
                            " employees in that category!");
                    Methods.AwaitInput();
                    break;
                }
                case 6: //Get all Employees
                    System.out.flush();
                    company.showAllEmployees();
                    Methods.AwaitInput();
                    break;
                case 7: //Get All Employees per Category
                    System.out.flush();
                    company.showEmployeePerCategory();
                    Methods.AwaitInput();
                    break;
                case 8: //Calculate Total Paychecks to Pay
                    double totalToPay = 0;
                    for(Employee emp: company.Employees()){
                        totalToPay = emp.calcPaycheck();
                    }

                    System.out.flush();
                    System.out.println("Total due this month: " + totalToPay);
                    Methods.AwaitInput();
                    break;
                case 9: //Calculate All Costs
                    System.out.print("Year: ");

                    try{
                        int year = Integer.parseInt(scanner.nextLine());

                        if(year < 1){
                            System.out.println("Year is invalid.");
                            Methods.AwaitInput();
                            break;
                        }

                        System.out.flush();
                        company.showAllCosts(year);
                    }
                    catch (NumberFormatException e){
                        System.out.println("Year is invalid.");
                    }

                    Methods.AwaitInput();
                    break;
                case 10: //Export User List
                    company.exportFile();
                    Methods.AwaitInput();
                    break;

                default:
                    System.out.println("Error: Invalid option!");
                    Methods.AwaitInput();
                    break;
            }
        }while (opc != 0);

    }
}
