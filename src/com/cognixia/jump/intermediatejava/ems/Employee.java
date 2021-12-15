package com.cognixia.jump.intermediatejava.ems;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Employee extends Person implements Serializable {
  private final String empId;
  private Department department;
  private LocalDate employmentDate;
  private float salary;

  private Employee(EmployeeBuilder builder) {
    super(builder.firstName, builder.lastName, builder.age);
    this.empId = builder.empId;
    this.department = builder.department;
    this.employmentDate = builder.employmentDate;
    this.salary = builder.salary;
  }

  // Id should not ever change; do not implement setter
  public String getId() {
    return empId;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public LocalDate getEmploymentDate() {
    return employmentDate;
  }

  public float getSalary() {
    return salary;
  }

  public void setSalary(float salary) {
    try {
      this.salary = EmployeeBuilder.sanitizeSalary(salary);
    } catch (EMSNegativeFundsException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Employee [")
      .append("name=" + getName() + ", ")
      .append("empId=" + empId + ", ")
      .append("department=" + department.getName() + ", ")
      .append("employmentDate=" + employmentDate + ", ")
      .append("salary=$" + EmployeeBuilder.df.format(salary))
      .append("]");

    return sb.toString();
  }

  public static class EmployeeBuilder {
    private String firstName;
    private String lastName;
    private int age;
    private final String empId;
    private Department department;
    private LocalDate employmentDate;
    private float salary;

    private static int counter = 1000;
    private static final DecimalFormat df = new DecimalFormat("#.00");

    public EmployeeBuilder(String firstName, String lastName, int age) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.age = age;
      this.empId = Integer.toString(counter++);
      this.department = null;
      this.salary = 0f;
    }

    public EmployeeBuilder department(Department department) {
      this.department = department;
      return this;
    }

    public EmployeeBuilder salary(float salary) {
      try {
        this.salary = sanitizeSalary(salary);
      } catch (EMSNegativeFundsException e) {
        e.printStackTrace();
      }
      return this;
    }

    public EmployeeBuilder employmentDate(LocalDate date) {
      this.employmentDate = date;
      return this;
    }

    public Employee build() {
      return new Employee(this);
    }

    public static float sanitizeSalary(float salary) throws EMSNegativeFundsException {
      if (salary < 0) {
        throw new EMSNegativeFundsException();
      }

      return Float.parseFloat(df.format(salary));
    }
  }
}