package com.company;

public class Main {

    public static void main(String[] args) {
        int opc = -1;

        do{
            opc = Methods.MainMenu();
            switch (opc){
                case 0: //Leave App
                    System.out.println("Bye bye!");
                    return;
                case 1: //Insert New Employee
                    System.out.println("Test 1");
                    break;
                case 2: //Check for  Employee
                    System.out.println("Test 2");
                    break;
                case 3: //Get Employee Record
                    System.out.println("Test 3");
                    break;
                case 4: //Import Employee List
                    System.out.println("Test 4");
                    break;
                case 5: //Get Employee Number per Category
                    System.out.println("Test 5");
                    break;
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
