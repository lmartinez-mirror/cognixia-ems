package com.cognixia.jump.intermediatejava.ems;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.intermediatejava.ems.Department.DepartmentBuilder;
import com.cognixia.jump.intermediatejava.ems.Employee.EmployeeBuilder;

/**
 * Controller class for EMS
 * <p>
 * This program uses a model-view-controller (MVC) paradigm to separate view logic from I/O and
 * interaction logic
 *
 * @author Luis Martinez
 */

public class Controller {
  private static final Controller instance = new Controller();
  private List<Employee> employees = new ArrayList<Employee>();
  private List<Department> departments = new ArrayList<Department>();
  public Scanner scanner = new Scanner(System.in);

  /**
   * Private constructor to enforce Singleton pattern
   */

  private Controller() {}

  /**
   * @return Static instance of Controller object
   * @apiNote Implements Singleton pattern
   */

  public static Controller getInstance() {
    return instance;
  }

  /**
   * Calls View class to print list of Employees
   */

  public void printEmployees() {
    View.printEmployees(employees);
  }

  /**
   * Calls View class to print list of Departments
   */

  public void printDepartments() {
    View.printDepartments(departments);
  }

  /**
   * Helper method for getting a single Employee with a user prompt
   *
   * @return Employee object, or null if getEmployeeIndex returns -1
   */

  private Employee getEmployee() {
    Employee target = null;
    int idx = getEmployeeIndex();

    try {
      target = employees.get(idx - 1);
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e);
    }

    return target;
  }

  /**
   * Helper method for getEmployee, handles index logic
   *
   * @return Index of Employee within the list of Employees, or -1 if no Employees are available at
   *         the specified index
   */

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
    } catch (InputMismatchException e) {
      System.out.println(e);
      return -1;
    }

    return idx;
  }

  /**
   * Controller method for Employee creation. Top-level logic should go here; call the method within
   * the main loop.
   */

  public void createEmployee() {
    System.out.println("====== CREATE EMPLOYEE ======");
    EmployeeBuilder builder;
    Employee employee = null;

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
      employee = builder.build();
      employees.add(employee);
    } catch (Exception e) {
      System.out.println("failed to create employee");
      e.printStackTrace();
      return;
    }

    System.out.println("Added employee " + employee);
  }

  /**
   * Controller method for reading Employee data. Top-level logic should go here; call the method
   * within the main loop.
   */

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

  /**
   * Controller method for updating employee data. Top-level logic should go here; call the method
   * within the main loop.
   */

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
    StringBuilder sb = new StringBuilder("Change?  \n").append("(1) Assign Department\n")
        .append("(2) Remove Department\n").append("(3) Salary\n");

    System.out.println(sb.toString());
    try {
      idx = scanner.nextInt();
      scanner.nextLine();
    } catch (InputMismatchException e) {
      System.out.println("invalid argument");
    } finally {
      switch (idx) {
        case 1: {
          Department department = getDepartment();
          if (department != null) {
            employee.setDepartment(department);
            System.out.println("successfully assigned to department " + department.getName());
          } else {
            System.out.println("failed to set department");
          }
          break;
        }

        case 2: {
          employee.setDepartment(null);
          System.out.println("removed from department");
          break;
        }

        case 3: {
          float salary;
          System.out.print("Enter new salary:  ");
          try {
            salary = scanner.nextFloat();
            scanner.nextLine();
          } catch (InputMismatchException e) {
            System.out.println("could not update salary");
            e.printStackTrace();
            return;
          }
          employee.setSalary(salary);
          System.out.println("successfully updated salary");
          break;
        }

        default:
          System.out.println("invalid option");
      }
    }
  }

  /**
   * Controller method for removing an Employee. Top-level logic should go here; call the method
   * within the main loop.
   */

  public void removeEmployee() {
    if (employees.isEmpty()) {
      System.out.println("No employees available");
      return;
    }

    System.out.println("====== REMOVE EMPLOYEE =====");
    int idx = getEmployeeIndex();

    if (idx != -1) {
      Employee target = employees.get(idx - 1);
      System.out.print("removing employee " + target + "...  ");
      employees.remove(idx);
      System.out.println("removed");
    }
  }

  /**
   * Controller method for removing all Employees. Top-level logic should go here; call the method
   * within the main loop.
   */

  public void removeAllEmployees() {
    removeAll(employees);
  }

  /**
   * Helper method for getting Department index within the Departments list.
   */

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
    } catch (InputMismatchException e) {
      System.out.println(e);
    }

    return idx;
  }

  /**
   * Helper method for getting a Department within the Departments list;
   *
   * @return Reference to target Department object
   *         <p>
   *         Null if list is empty
   */

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
      target = departments.get(idx - 1);
    } catch (Exception e) {
      System.out.println("failed to fetch department");
      e.printStackTrace();
    }
    return target;
  }

  /**
   * Controller method for creating Departments. Top-level logic should go here; call the method
   * within the main loop.
   */

  public void createDepartment() {
    System.out.println("====== CREATE DEPARTMENT =====");
    DepartmentBuilder builder;
    Department department = null;

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
        System.out.println("Assign Manager:  ");
        builder.manager(getEmployee());
      }

      department = builder.build();
      departments.add(department);
    } catch (InputMismatchException e) {
      System.out.println("invalid input");
      e.printStackTrace();
      return;
    }
    System.out.println("successfully added department " + department.getName());
  }

  /**
   * Controller method for reading Department data. Top-level logic should go here; call the method
   * within the main loop.
   */

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

  /**
   * Controller method for updating Department data. Top-level logic should go here; call the method
   * within the main loop.
   */

  public void updateDepartment() {
    if (departments.isEmpty()) {
      System.out.println("No departments available");
      return;
    }

    System.out.println("====== UPDATE DEPARTMENT =====");
    Department department = getDepartment();
    updateDepartment(department);
  }

  /**
   * Helper method for updateDepartment. This processes a single Department for targeting.
   *
   * @param department Target Department to change attribute data for
   */

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
    } catch (InputMismatchException e) {
      System.out.println(e);
    } finally {
      switch (idx) {
        case 1: { // set budget
          float budget;
          System.out.println("Enter new budget:  ");
          try {
            budget = scanner.nextFloat();
            scanner.nextLine();
          } catch (InputMismatchException e) {
            System.out.println("could not update budget");
            e.printStackTrace();
            return;
          }

          department.setBudget(budget);
          System.out.println("New budget:  " + department.getBudget());
          break;
        }

        case 2: { // set phone number
          System.out.println("Enter new phone number:  ");
          String phoneNumber;

          try {
            phoneNumber = scanner.nextLine();
          } catch (InputMismatchException e) {
            System.out.println("could not update phone number");
            e.printStackTrace();
            return;
          }

          department.setPhoneNumber(phoneNumber);
          System.out.println("New phone number: " + department.getPhoneNumber());
          break;
        }

        case 3: { // set building
          System.out.println("Enter new building:  ");
          String building;

          try {
            building = scanner.nextLine();
          } catch (InputMismatchException e) {
            System.out.println("could not update building");
            e.printStackTrace();
            return;
          }

          department.setBuilding(building);
          System.out.println("New building: " + department.getBuilding());
          break;
        }

        case 4: { // set manager
          Employee manager = getEmployee();

          if (manager != null) {
            department.setManager(manager);
            System.out.println("New manager: " + department.getManager().getName());
          }

          else {
            System.out.println("failed to set manager");
          }

          break;
        }

        default:
          System.out.println("invalid option");
      }
    }
  }

  /**
   * Controller method for removing Departments. Top-level logic should go here; call the method
   * within the main loop.
   */

  public void removeDepartment() {
    if (departments.isEmpty()) {
      System.out.println("No departments available");
      return;
    }

    System.out.println("====== DELETE DEPARTMENT =====");
    int idx = getDepartmentIndex();

    if (idx != -1) {
      int target = idx - 1;
      departments.remove(target);
      System.out.println("removed");
    }
  }

  /**
   * What it says on the tin.
   */

  public void removeAllDepartments() {
    removeAll(departments);
  }

  /**
   * Clears out both Employees and Departments lists.
   * <p>
   * Use this if you want to get fired.
   */

  public void removeAll() {
    System.out.println("this is a destructive operation, are you sure?  y/n");
    String response = null;

    try {
      response = scanner.nextLine();
    } catch (InputMismatchException e) {
      System.out.println(e);
      return;
    }

    if (response.toLowerCase().startsWith("y")) {
      employees.clear();
      departments.clear();
      System.out.println("it's gone, it's all gone");
    }
  }

  /**
   * For clearing out specific lists
   *
   * @param <T> Employee
   * @param <T> Department
   * @param list Either of the two Controller lists
   */

  public <T> void removeAll(List<T> list) {
    System.out.println("this is a destructive operation, are you sure?  y/n");
    String response = null;

    try {
      response = scanner.nextLine();
    } catch (InputMismatchException e) {
      System.out.println(e);
      return;
    }

    if (response.toLowerCase().startsWith("y")) {
      list.clear();
      System.out.println("aaaaaaand it's gone");
    }
  }
}
