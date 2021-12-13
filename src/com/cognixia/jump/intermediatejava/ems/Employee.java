package com.cognixia.jump.intermediatejava.ems;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class Employee extends Person {
  private final String empId;
  private String department;
  private LocalDate employmentDate;
  private float salary;

  private Employee(EmployeeBuilder builder) {
    super(builder.firstName, builder.lastName);
    this.empId = builder.empId;
    this.department = builder.department;
    this.employmentDate = builder.employmentDate;
    this.salary = builder.salary;
  }

  // Id should not ever change; do not implement setter
  public String getId() {
    return empId;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public LocalDate getEmploymentDate() {
    return employmentDate;
  }

  public float getSalary() {
    return salary;
  }

  public void setSalary(float salary) {
    this.salary = EmployeeBuilder.sanitizeSalary(salary);
  }

  /* TODO: Rewrite to not use String.format because performance */
  @Override
  public String toString() {
    return String.format(
      "Employee [name=%s, empId=%s, department=%s, employmentDate=%s, salary=$%.2f]",
      getName(),
      empId,
      department,
      employmentDate,
      salary
    );
  }

  public static class EmployeeBuilder {
    private String firstName;
    private String lastName;
    private final String empId;
    private String department;
    private LocalDate employmentDate;
    private float salary;

    private static int counter = 1000;
    private static final DecimalFormat df = new DecimalFormat("#.00");

    public EmployeeBuilder(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.empId = Integer.toString(counter++);
      this.department = null;
      this.employmentDate = null;
      this.salary = 0;
    }

    public EmployeeBuilder department(String department) {
      this.department = department;
      return this;
    }

    public EmployeeBuilder salary(float salary) {
      this.salary = sanitizeSalary(salary);
      return this;
    }

    public EmployeeBuilder employmentDate(LocalDate date) {
      this.employmentDate = date;
      return this;
    }

    public Employee build() {
      return new Employee(this);
    }

    private static float sanitizeSalary(float salary) throws IllegalArgumentException {
      if (salary < 0) {
        throw new IllegalArgumentException("salary cannot be negative");
      }

      return Float.parseFloat(df.format(salary));
    }
  }
}