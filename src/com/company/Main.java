package com.company;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
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
                case 1: //Insert New Employee
                    System.out.println("Test 1");
                    break;
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
                    System.out.println("Test 3");
                    break;
                case 4: //Import Employee List
                    System.out.println("Test 4");
                    break;
                case 5: { //Get Employee Number per Category
                    int category = Methods.CategoryMenu();

                    if(category < 0 || category > 3){
                        System.out.println("Invalid Category!");
                        Methods.AwaitInput();
                        break;
                    }

                    /*switch (category){
                        case 0: //NORMAL

                            break;

                        case 1: //MANAGER

                            break;

                        case 2: //DRIVER

                            break;

                        case 3: //SALESMAN

                            break;
                    }*/

                    break;
                }
                case 6: //Get all Employees
                    System.out.println("Test 6");
                    break;
                case 7: //Get All Employees per Category
                    System.out.println("Test 7");
                    break;
                case 8: //Calculate Total Paychecks to Pay
                    System.out.println("Test 8");
                    break;
                case 9: //Calculate All Costs
                    System.out.println("Test 9");
                    break;
                case 10: //Export User List
                    /* File Structure
                    {
                        Employees : [
                            {
                                id: 1,
                                name: Raul,
                                entranceDate: 2022-08-12,
                                workedDays: 19,
                                hourlyPay: 5.5,
                                type: 0
                            },
                            {
                                id: 2,
                                name: Fran,
                                entranceDate: 2022-02-12,
                                workedDays: 293,
                                hourlyPay: 7.5,
                                type: 3,
                                awardPercent: {
                                    2021: 0.25,
                                    2022: 0.5
                                },
                                sales: [1, 2, 4]
                            }
                        ],
                        sales: [
                            {
                                id: 1,
                                total: 59.9,
                                saleDate: 2021-08-09
                            },
                            {
                                id: 2,
                                total: 41.9,
                                saleDate: 2022-02-15
                            },
                        ]
                    }
                     */


                    System.out.println("Test 10");
                    break;
                default:
                    System.out.println("Error: Invalid option!");

                    Methods.AwaitInput();
                    break;
            }
        }while (opc != 0);

    }
}
