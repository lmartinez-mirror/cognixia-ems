package com.cognixia.jump.intermediatejava.ems;

import java.util.List;

public abstract class View {
  private View() {}

  public static void printEmployee(Employee employee) {
    System.out.println("====== EMPLOYEE ======");
    StringBuilder sb = new StringBuilder();

    sb.append("Name: " + employee.getName() + '\n');
    sb.append("Employee ID: " + employee.getId() + '\n');
    sb.append("Department: " + employee.getDepartment() + '\n');
    sb.append("Employment Date: " + employee.getEmploymentDate() + '\n');
    sb.append("Salary: " + employee.getSalary() + '\n');

    System.out.println(sb.toString());
  }

  public static void printEmployees(List<Employee> employees) {
    System.out.println("====== EMPLOYEES ======");
    employees.stream().forEach(System.out::println);
  }

  public static void printDepartment(Department department) {
    System.out.println("====== DEPARTMENT ======");
    StringBuilder sb = new StringBuilder();

    sb.append("Name: " + department.getName() + '\n');
    sb.append("Department ID: " + department.getDeptId() + '\n');
    sb.append("Budget: " + department.getBudget() + '\n');
    sb.append("Phone Number: " + department.getPhoneNumber() + '\n');
    sb.append("Building: " + department.getBuilding() + '\n');
    sb.append("Manager: " + department.getManager() + '\n');

    System.out.println(sb.toString());
  }

  public static void printDepartments(List<Department> departments) {
    System.out.println("====== DEPARTMENTS ======");
    departments.stream().forEach(System.out::println);
  }
}
