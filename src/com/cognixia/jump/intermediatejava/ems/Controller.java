package com.cognixia.jump.intermediatejava.ems;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.intermediatejava.ems.Department.DepartmentBuilder;
import com.cognixia.jump.intermediatejava.ems.Employee.EmployeeBuilder;

public class Controller {
  private static final Controller instance = new Controller();
  private List<Employee> employees = new ArrayList<Employee>();
  private List<Department> departments = new ArrayList<Department>();
  public Scanner scanner = new Scanner(System.in);

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

  private Employee getEmployee() {
    Employee target = null;
    int idx = getEmployeeIndex();

    try {
      target = employees.get(idx);
    }
    catch (IndexOutOfBoundsException e) {
      System.out.println(e);
    }

    return target;
  }

  private int getEmployeeIndex() {
    int idx = -1;
    if (employees.isEmpty()) {
      System.out.println("no employees available");
      return idx;
    }

    printEmployees();
    System.out.print("Choose an employee:  ");

    try {
      idx = scanner.nextInt();
      scanner.nextLine();

      /* if (idx < 0 || idx > employees.size()) {
        throw new IndexOutOfBoundsException("index not within range");
      } */
    }
    catch (InputMismatchException e) {
      System.out.println(e);
      return -1;
    }
    /* catch (IndexOutOfBoundsException e) {
      System.out.println(e);
      return -1;
    } */

    return idx;
  }

  public void createEmployee() {
    System.out.println("====== CREATE EMPLOYEE ======");
    EmployeeBuilder builder;

    try {
      builder = null;
      System.out.print("First Name:  ");
      String firstName = scanner.nextLine();
      System.out.print("Last Name:  ");
      String lastName = scanner.nextLine();
      System.out.print("Age:  ");
      int age = scanner.nextInt();
      scanner.nextLine();

      builder = new EmployeeBuilder(firstName, lastName, age);

      if (!departments.isEmpty()) {
        builder.department(getDepartment());
      }

      System.out.print("Employment Date (YYYY-MM-DD):  ");
      builder.employmentDate(LocalDate.parse(scanner.nextLine()));

      System.out.print("Salary (do not add signs):  ");
      builder.salary(scanner.nextFloat());
      scanner.nextLine();
      Employee employee = builder.build();
      employees.add(employee);
      System.out.println("Added employee " + employee);
    }
    catch (Exception e) {
      System.out.println("failed to create employee:  " + e);
    }
  }

  public void readEmployee() {
    if (employees.isEmpty()) {
      System.out.println("No employees available");
      return;
    }

    System.out.println("====== READ EMPLOYEE ======");
    Employee employee = getEmployee();
    if (employee != null) {
      View.printEmployee(employee);
    }
  }

  public void updateEmployee() {
    if (employees.isEmpty()) {
      System.out.println("No employees available");
      return;
    }

    System.out.println("====== UPDATE EMPLOYEE ======");
    Employee employee = getEmployee();
    updateEmployee(employee);
  }

  private void updateEmployee(Employee employee) {
    if (employee == null) {
      System.out.println("no employee provided, no changes made");
      return;
    }

    View.printEmployee(employee);
    int idx = 0;
    StringBuilder sb = new StringBuilder("Change?  \n");
    sb.append("(1) Department\n");
    sb.append("(2) Salary\n");

    System.out.println(sb.toString());
    try {
      idx = scanner.nextInt();
      scanner.nextLine();
    }
    catch (InputMismatchException e) {
      System.out.println(e);
    }
    finally {
      switch (idx) {
        case 1: {
          Department department = getDepartment();
          if (department != null) {
            employee.setDepartment(department);
          }
        }

        case 2: {
          float salary;
          System.out.print("Enter new salary:  ");
          try {
            salary = scanner.nextFloat();
            scanner.nextLine();
          }
          catch (InputMismatchException e) {
            System.out.println("could not update salary:  " + e);
            return;
          }
          employee.setSalary(salary);
        }

        default: System.out.println("invalid option");
      }
    }
  }

  public void removeEmployee() {
    if (employees.isEmpty()) {
      System.out.println("No employees available");
      return;
    }

    System.out.println("====== REMOVE EMPLOYEE =====");
    int idx = getEmployeeIndex();

    if (idx != -1) {
      Employee target = employees.get(idx);
      System.out.print("removing employee " + target + "...  ");
      employees.remove(idx);
      System.out.println("removed");
    }
  }

  public void removeAllEmployees() {
    removeAll(employees);
  }

  private int getDepartmentIndex() {
    int idx = -1;
    if (departments.isEmpty()) {
      System.out.println("no departments available");
      return idx;
    }

    printDepartments();
    System.out.print("Choose a department:  ");

    try {
      idx = scanner.nextInt();
      scanner.nextLine();
    }
    catch (InputMismatchException e) {
      System.out.println(e);
    }

    return idx;
  }

  private Department getDepartment() {
    if (departments.isEmpty()) {
      System.out.println("no departments available");
      return null;
    }

    printDepartments();
    Department target = null;
    System.out.println("Choose a department:  ");

    try {
      int idx = scanner.nextInt();
      scanner.nextLine();
      target = departments.get(idx);
    }
    catch (Exception e) {
      System.out.println("failed to fetch department: " + e);
    }
    return target;
  }

  public void createDepartment() {
    System.out.println("====== CREATE DEPARTMENT =====");
    DepartmentBuilder builder;

    try {
      builder = null;
      System.out.print("Name:  ");
      builder = new DepartmentBuilder(scanner.nextLine());
      System.out.print("Budget:  ");
      builder.budget(scanner.nextFloat());
      scanner.nextLine();
      System.out.print("Phone Number:  ");
      builder.phoneNumber(scanner.nextLine());
      System.out.print("Building:  ");
      builder.building(scanner.nextLine());

      if (!employees.isEmpty()) {
        builder.manager(getEmployee());
      }

      departments.add(builder.build());
    }

    catch (InputMismatchException e) {
      System.out.println(e);
    }
  }

  public void readDepartment() {
    if (departments.isEmpty()) {
      System.out.println("No departments available");
      return;
    }

    System.out.println("====== READ DEPARTMENT =====");
    Department department = getDepartment();
    if (department != null) {
      View.printDepartment(department);
    }
  }

  public void updateDepartment() {
    if (departments.isEmpty()) {
      System.out.println("No departments available");
      return;
    }

    System.out.println("====== UPDATE DEPARTMENT =====");
    Department department = getDepartment();
    updateDepartment(department);
  }

  private void updateDepartment(Department department) {
    if (department == null) {
      System.out.println("no department provided, no changes made");
      return;
    }

    View.printDepartment(department);
    int idx = 0;
    StringBuilder sb = new StringBuilder("Change?:  \n");
    sb.append("(1) Budget\n");
    sb.append("(2) Phone Number\n");
    sb.append("(3) Building\n");
    sb.append("(4) Manager\n");
    System.out.println(sb.toString());

    try {
      idx = scanner.nextInt();
      scanner.nextLine();
    }
    catch (InputMismatchException e) {
      System.out.println(e);
    }
    finally {
      switch (idx) {
        case 1: { // set budget
          float budget;
          System.out.println("Enter new budget:  ");
          try {
            budget = scanner.nextFloat();
            scanner.nextLine();
          }
          catch (InputMismatchException e) {
            System.out.println("could not update budget:  " + e);
            return;
          }

          department.setBudget(budget);
          System.out.println("New budget:  " + department.getBudget());
          break;
        }

        case 2: {
          System.out.println("Enter new phone number:  ");
          String phoneNumber;

          try {
            phoneNumber = scanner.nextLine();
          }
          catch (InputMismatchException e) {
            System.out.println("could not update phone number: " + e);
            return;
          }

          department.setPhoneNumber(phoneNumber);
          System.out.println("New phone number: " + department.getPhoneNumber());
          break;
        }

        case 3: {
          System.out.println("Enter new building:  ");
          String building;

          try {
            building = scanner.nextLine();
          }
          catch (InputMismatchException e) {
            System.out.println("could not update building: " + e);
            return;
          }

          department.setBuilding(building);
          System.out.println("New building: " + department.getBuilding());
          break;
        }

        case 4: {
          Employee manager = getEmployee();

          if (manager != null) {
            department.setManager(manager);
            System.out.println("New manager: " + department.getManager().getName());
          }

          break;
        }

        default: System.out.println("invalid option");
      }
    }
  }

  public void removeDepartment() {
    if (departments.isEmpty()) {
      System.out.println("No departments available");
      return;
    }

    System.out.println("====== DELETE DEPARTMENT =====");
    int idx = getDepartmentIndex();

    if (idx != -1) {
      Department target = departments.get(idx);
      System.out.print("removing department " + target + "...  ");
      departments.remove(idx);
      System.out.println("removed");
    }
  }

  public void removeAllDepartments() {
    removeAll(departments);
  }

  public void removeAll() {
    System.out.println("this is a destructive operation, are you sure?  y/n");
    String response = null;

    try {
      response = scanner.nextLine();
    }
    catch (InputMismatchException e) {
      System.out.println(e);
      return;
    }

    if (response.toLowerCase().startsWith("y")) {
      employees.clear();
      departments.clear();
      System.out.println("it's gone, it's all gone");
    }
  }

  public <T> void removeAll(List<T> list) {
    System.out.println("this is a destructive operation, are you sure?  y/n");
    String response = null;

    try {
      response = scanner.nextLine();
    }
    catch (InputMismatchException e) {
      System.out.println(e);
      return;
    }

    if (response.toLowerCase().startsWith("y")) {
      list.clear();
      System.out.println("aaaaaaand it's gone");
    }
  }
}