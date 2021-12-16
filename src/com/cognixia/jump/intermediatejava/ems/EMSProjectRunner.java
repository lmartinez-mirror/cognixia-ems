package com.cognixia.jump.intermediatejava.ems;

import java.util.InputMismatchException;

public class EMSProjectRunner {

  public static void main(String[] args) {
    Controller controller = Controller.getInstance();
    boolean flag = true;
    System.out.println("====== EMPLOYEE MANAGEMENT SYSTEM 2022 EDITION ======\n\n");

    // Please forgive this godawful formatting, this is apparently within Google style guidelines
    final String menu = new StringBuilder("Main Menu\n==========\n").append("(1) Create Employee\n")
        .append("(2) Read Employee\n").append("(3) Read All Employees\n")
        .append("(4) Update Employee\n").append("(5) Delete Employee\n")
        .append("(6) Delete All Employees\n").append("(7) Create Department\n")
        .append("(8) Read Department\n").append("(9) Read All Departments\n")
        .append("(10) Update Department\n").append("(11) Delete Department\n")
        .append("(12) Delete All Departments\n").append("\n\n").append("(99) Burn It All Down\n")
        .toString();

    while (flag) {
      int choice = 0;

      System.out.println(menu);
      System.out.print("Make a selection (0 to exit):  ");

      try {
        choice = controller.scanner.nextInt();
        controller.scanner.nextLine(); // apparently nextFoo does not consume newlines
      } catch (InputMismatchException e) {
        continue;
      }

      switch (choice) {
        case 1:
          controller.createEmployee();
          break;
        case 2:
          controller.readEmployee();
          break;
        case 3:
          controller.printEmployees();
          break;
        case 4:
          controller.updateEmployee();
          break;
        case 5:
          controller.removeEmployee();
          break;
        case 6:
          controller.removeAllEmployees();
          break;
        case 7:
          controller.createDepartment();
          break;
        case 8:
          controller.readDepartment();
          break;
        case 9:
          controller.printDepartments();
          break;
        case 10:
          controller.updateDepartment();
          break;
        case 11:
          controller.removeDepartment();
          break;
        case 12:
          controller.removeAllDepartments();
          break;
        case 99:
          controller.removeAll();
          break;
        case 0:
          flag = false;
      }

      System.out.println();
    }

    System.out.println("goodbye");
  }
}
