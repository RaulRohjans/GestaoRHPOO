package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Methods {
    public static int MainMenu() {
        System.out.flush();

        System.out.println("*-*-*-*-*-*-*-*-*-*-*Gestão*RH-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("1. Insert New Employee");
        System.out.println("2. Check for  Employee");
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

        System.out.print("\nOpção:");
        Scanner scnr = new Scanner(System.in);

        System.out.flush();

        try{ return Integer.parseInt(scnr.nextLine()); } catch (Exception e) {return -1;}
    }

    public static void AwaitInput() {
        System.out.println("Press enter to continue!");
        Scanner scnr = new Scanner(System.in);
        scnr.nextLine();
    }
}
