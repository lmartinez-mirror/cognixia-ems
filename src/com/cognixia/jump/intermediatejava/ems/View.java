package com.cognixia.jump.intermediatejava.ems;

import java.util.List;

public abstract class View {
  private View() {}
  private static final String d = System.lineSeparator();

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

  public static void printEmployees(List<Employee> employees) {
    System.out.println("====== EMPLOYEES ======");

    if (employees.isEmpty()) {
      System.out.println("there's nothing here");
      return;
    }

    employees.stream().forEach(System.out::println);
  }

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

  public static void printDepartments(List<Department> departments) {
    System.out.println("====== DEPARTMENTS ======");

    if (departments.isEmpty()) {
      System.out.println("there's nothing here");
      return;
    }

    departments.stream().forEach(System.out::println);
  }
}
