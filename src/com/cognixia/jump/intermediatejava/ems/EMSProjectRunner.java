package com.cognixia.jump.intermediatejava.ems;

import java.util.InputMismatchException;

public class EMSProjectRunner {

  public static void main(String[] args) {
    Controller controller = Controller.getInstance();
    System.out.println("====== EMPLOYEE MANAGEMENT SYSTEM 2022 EDITION ======\n\n");

    StringBuilder sb = new StringBuilder("Main Menu\n==========\n");
    sb.append("(1) Create Employee\n");
    sb.append("(2) Read Employee\n");
    sb.append("(3) Read All Employees\n");
    sb.append("(4) Update Employee\n");
    sb.append("(5) Delete Employee\n");
    sb.append("(6) Delete All Employees\n");
    sb.append("(7) Create Department\n");
    sb.append("(8) Read Department\n");
    sb.append("(9) Read All Departments\n");
    sb.append("(10) Update Department\n");
    sb.append("(11) Delete Department\n");
    sb.append("(12) Delete All Departments\n");
    sb.append("\n\n");
    sb.append("(99) Burn It All Down\n");

    while (true) {
      int choice = 0;
      System.out.println(sb.toString());

      System.out.print("make a selection:  ");
      try {
        choice = controller.scanner.nextInt();
        controller.scanner.nextLine(); // apparently nextFoo does not consume newlines
      }
      catch (InputMismatchException e) {
        continue;
      }

      switch (choice) {
        case 1: controller.createEmployee(); break;
        case 2: controller.readEmployee(); break;
        case 3: controller.printEmployees(); break;
        case 4: controller.updateEmployee(); break;
        case 5: controller.removeEmployee(); break;
        case 6: controller.removeAllEmployees(); break;
        case 7: controller.createDepartment(); break;
        case 8: controller.readDepartment(); break;
        case 9: controller.printDepartments(); break;
        case 10: controller.updateDepartment(); break;
        case 11: controller.removeDepartment(); break;
        case 12: controller.removeAllDepartments(); break;
        case 99: controller.removeAll(); break;
        case 0: System.exit(0);
      }

      System.out.println("\n\n");
    }
  }
}
