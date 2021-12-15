package com.cognixia.jump.intermediatejava.ems;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.intermediatejava.ems.Employee.EmployeeBuilder;

public class Controller {
  private static final Controller instance = new Controller();
  private List<Employee> employees = new ArrayList<Employee>();
  private List<Department> departments = new ArrayList<Department>();

  private Controller() {}

  public static Controller getInstance() {
    return instance;
  }

  public void printEmployees() {
    View.printEmployees(employees);
  }

  public void printDepartments() {
    View.printDepartments(departments);
  }

  public void createEmployee() {
    System.out.println("====== CREATE EMPLOYEE ======");
    EmployeeBuilder builder;

    try (Scanner scanner = new Scanner(System.in)) {
      builder = null;
      System.out.print("First Name:  ");
      String firstName = scanner.nextLine();
      System.out.print("Last Name:  ");
      String lastName = scanner.nextLine();
      System.out.print("Age:  ");
      int age = scanner.nextInt();

      builder = new EmployeeBuilder(firstName, lastName, age);

      if (!departments.isEmpty()) {
        View.printDepartments(departments);
        System.out.print("Department:  ");
      }

      System.out.print("Employment Date (YYYY-MM-DD):  ");
      builder.employmentDate(LocalDate.parse(scanner.nextLine()));

      System.out.print("Salary (do not add signs):  ");
      builder.salary(scanner.nextFloat());
      employees.add(builder.build());
    }
    catch (InputMismatchException e) {
      System.out.print(e.toString());
    }
  }

  public void readEmployee() {
    System.out.println("====== READ EMPLOYEE =====");
  }

  public void updateEmployee() {
    System.out.println("====== UPDATE EMPLOYEE =====");

  }

  public void removeEmployee() {
    System.out.println("====== REMOVE EMPLOYEE =====");

  }

  public void createDepartment() {
    System.out.println("====== CREATE DEPARTMENT =====");

  }

  public void readDepartment() {
    System.out.println("====== READ DEPARTMENT =====");

  }

  public void updateDepartment() {
    System.out.println("====== UPDATE DEPARTMENT =====");

  }

  public void removeDepartment() {
    System.out.println("====== DELETE DEPARTMENT =====");

  }
}
