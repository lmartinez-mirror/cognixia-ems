package com.cognixia.jump.intermediatejava.ems;

import java.util.List;

/**
 * View class for pretty-printing data
 * <p>
 * All methods are static
 * @author Luis Martinez
 */

public abstract class View {
  /** Do not instantiate a View object */
  private View() {}
  private static final String d = System.lineSeparator();

  /**
   * Pretty-prints an Employee object's data
   * @param employee Employee to read out information from
   * @apiNote Static method
   */
  public static void printEmployee(Employee employee) {
    StringBuilder sb = new StringBuilder("====== EMPLOYEE ======" + d)
      .append("Name: " + employee.getName() + d)
      .append("Age: " + employee.getAge() + d)
      .append("Employee ID: " + employee.getId() + d)
      .append("Department: " + employee.getDepartment() + d)
      .append("Employment Date: " + employee.getEmploymentDate() + d)
      .append("Salary: " + employee.getSalary() + d);

    System.out.println(sb.toString());
  }

  /**
   * Prints all Employees' data
   * @param employees List of all employees
   * @apiNote Static method
   */
  public static void printEmployees(List<Employee> employees) {
    System.out.println("====== EMPLOYEES ======");

    if (employees.isEmpty()) {
      System.out.println("there's nothing here");
      return;
    }

    employees.stream().forEach(System.out::println);
  }

  /**
   * Pretty-prints a Department object's data
   * @param department Department to read information from
   * @apiNote Static method
   */

  public static void printDepartment(Department department) {
    StringBuilder sb = new StringBuilder("====== DEPARTMENT ======" + d)
      .append("Name: " + department.getName() + d)
      .append("Department ID: " + department.getDeptId() + d)
      .append("Budget: " + department.getBudget() + d)
      .append("Phone Number: " + department.getPhoneNumber() + d)
      .append("Building: " + department.getBuilding() + d)
      .append("Manager: " + department.getManager() + d);

    System.out.println(sb.toString());
  }

  /**
   * Prints all Departments' data
   * @param departments List of all Departments
   * @apiNote Static method
   */

  public static void printDepartments(List<Department> departments) {
    System.out.println("====== DEPARTMENTS ======");

    if (departments.isEmpty()) {
      System.out.println("there's nothing here");
      return;
    }

    departments.stream().forEach(System.out::println);
  }
}
