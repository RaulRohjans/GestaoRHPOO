package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

public class Methods {
    public static int MainMenu() {
        System.out.flush();

        System.out.println("*-*-*-*-*-*-*-*-*-*-*Gestão*RH-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("1. Insert New Employee");
        System.out.println("2. Check for Employee");
        System.out.println("3. Get Employee Record");
        System.out.println("4. Import Employee List");
        System.out.println("5. Get Employee Number per Category");
        System.out.println("6. Get all Employees");
        System.out.println("7. Get All Employees per Category");
        System.out.println("8. Calculate Total Paychecks to Pay");
        System.out.println("9. Calculate All Costs");
        System.out.println("10. Export User List");
        System.out.println("\n0. Leave App");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");

        System.out.print("\nOption: ");
        Scanner scnr = new Scanner(System.in);

        System.out.flush();

        try{ return Integer.parseInt(scnr.nextLine()); } catch (Exception e) {return -1;}
    }

    public static void AwaitInput() {
        System.out.println("Press enter to continue!");
        Scanner scnr = new Scanner(System.in);
        scnr.nextLine();
    }

    /* This method returns the category and not the number the person choose! */
    public static Employee.EmployeeType CategoryMenu() {
        System.out.flush();

        System.out.println("*-*-*-*-*-*-*-*-*-*-*Gestão*RH-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("1. NORMAL");
        System.out.println("2. MANAGER");
        System.out.println("3. DRIVER");
        System.out.println("4. SALESMAN");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");

        System.out.print("\nOpção: ");
        Scanner scnr = new Scanner(System.in);

        System.out.flush();

        try{
            return switch (Integer.parseInt(scnr.nextLine())) {
                case 1 -> Employee.EmployeeType.NORMAL;
                case 2 -> Employee.EmployeeType.MANAGER;
                case 3 -> Employee.EmployeeType.DRIVER;
                case 4 -> Employee.EmployeeType.SALESMAN;
                default -> null;
            };
        } catch (Exception e) {
            return null;
        }
    }

    public static Employee GetEmployeeFromJSON(JSONObject obj) throws ParseException {
        Employee employee;
        try{
            switch (obj.getInt("type")){
                case 0: { //NORMAL
                    employee = new Employee();
                    break;
                }
                case 1: { //MANAGER
                    employee = new Manager();
                    break;
                }
                case 2: { //DRIVER
                    employee = new Driver();
                    //Fetch km made
                    JSONObject distanceKm = obj.getJSONObject("distanceKms");
                    Iterator<String> distKeys = distanceKm.keys();
                    while (distKeys.hasNext()) {
                        String key = distKeys.next();
                        ((Driver) employee).setKmMade(key, distanceKm.getDouble(key));
                    }

                    //Fetch price per km
                    JSONObject priceKM = obj.getJSONObject("pricePerKm");
                    Iterator<String> priceKeys = priceKM.keys();
                    while (priceKeys.hasNext()) {
                        String key = priceKeys.next();
                        ((Driver) employee).addPricePerKm(Integer.parseInt(key), priceKM.getDouble(key));
                    }
                    break;
                }
                case 3: { //SALESMAN
                    employee = new Salesman();

                    //Fetch award percent
                    JSONObject awardPerc = obj.getJSONObject("awardPercent");
                    Iterator<String> keys = awardPerc.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        ((Salesman) employee).addAwardPec(Integer.parseInt(key), awardPerc.getDouble(key));
                    }

                    //Fetch Sales
                    JSONArray sales = obj.getJSONArray("sales");
                    for (int i = 0; i < sales.length(); i++) {
                        ((Salesman) employee).addSale(new Sale(
                                sales.getJSONObject(i).getInt("id"),
                                sales.getJSONObject(i).getDouble("total"),
                                new SimpleDateFormat("yyyy-MM-dd").parse(sales.getJSONObject(i).getString("saleDate"))
                        ));
                    }
                    break;
                }
                default:
                    return null;
            }

            employee.setId(obj.getInt("id"));
            employee.setName(obj.getString("name"));
            employee.setEntranceDate(obj.getString("entranceDate"));
            employee.setHourlyPay(obj.getDouble("hourlyPay"));

            //Fetch worked days
            JSONObject workedDays = obj.getJSONObject("workedDays");
            Iterator<String> keys = workedDays.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                employee.addWorkedDays(key, workedDays.getInt(key));
            }
        }
        catch (ParseException e){
            throw new ParseException("There has been an error parsing the entrance date of employee " +
                    obj.getString("name"), e.getErrorOffset());
        }
        catch (NumberFormatException e) {
            throw new NumberFormatException("There has been an error parsing the number of worked days of employee " +
                    obj.getString("name"));
        }

        return employee;
    }

    public static String getFormattedMonth() {
        String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);

        return (month.length() > 1) ? month : "0" + month;
    }
}
