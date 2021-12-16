package com.cognixia.jump.intermediatejava.ems;

import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * Class for representing Employees
 *
 * @author Luis Martinez
 * @see Person
 */

public class Employee extends Person {
  /**
   * Employee ID, unique
   */
  private final String empId;
  private Department department;
  private LocalDate employmentDate;
  private float salary;

  /**
   * Private class constructor; use EmployeeBuilder to get instances of Employee instead of calling
   * the constructor directly
   *
   * @param builder EmployeeBuilder object for creating Employee instances
   */

  private Employee(EmployeeBuilder builder) {
    super(builder.firstName, builder.lastName, builder.age);
    this.empId = builder.empId;
    this.department = builder.department;
    this.employmentDate = builder.employmentDate;
    this.salary = builder.salary;
  }

  /**
   * Employee ID getter
   * <p>
   * IDs should never change
   *
   * @return Employee's ID
   */
  public String getId() {
    return empId;
  }

  /**
   * @return Employee's assigned Department
   */

  public Department getDepartment() {
    return department;
  }

  /**
   * @param department Department to assign to Employee
   */

  public void setDepartment(Department department) {
    this.department = department;
  }

  /**
   * @return Employee's employment date
   */

  public LocalDate getEmploymentDate() {
    return employmentDate;
  }

  /**
   * @return Employee's salary
   */

  public float getSalary() {
    return salary;
  }

  /**
   * @return salary as a formatted String
   */

  public String getFormattedSalary() {
    return "$" + EmployeeBuilder.df.format(salary);
  }

  /**
   * @param salary Amount to change Employee's salary to
   */

  public void setSalary(float salary) {
    try {
      this.salary = EmployeeBuilder.sanitizeSalary(salary);
    } catch (EMSNegativeFundsException e) {
      System.out.println(e);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Employee [").append("name=" + getName() + ", ")
        .append("empId=" + empId + ", ")
        .append("department=" + (department == null ? "none" : department.getName()) + ", ")
        .append("employmentDate=" + employmentDate + ", ")
        .append("salary=$" + EmployeeBuilder.df.format(salary)).append("]");

    return sb.toString();
  }

  /**
   * Builder for Employee class
   * <p>
   * Use this class to create Employee instances instead of directly invoking the constructor
   *
   * @author Luis Martinez
   */

  public static class EmployeeBuilder {
    private String firstName;
    private String lastName;
    private int age;
    private final String empId;
    private Department department;
    private LocalDate employmentDate;
    private float salary;

    /** Tracks employee IDs, ensures no two are the same */
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

    /**
     * @param department Department to assign to pending Employee object
     * @return Calling Builder object
     * @apiNote Intermediate method: Chain this method to other intermediate methods to combine
     *          results.
     */

    public EmployeeBuilder department(Department department) {
      this.department = department;
      return this;
    }

    /**
     * @param salary Amount to set pending Employee object's salary to
     * @return Calling Builder object
     * @apiNote Intermediate method: Chain this method to other intermediate methods to combine
     *          results.
     */

    public EmployeeBuilder salary(float salary) {
      try {
        this.salary = sanitizeSalary(salary);
      } catch (EMSNegativeFundsException e) {
        e.printStackTrace();
      }
      return this;
    }

    /**
     * @param date The date to set pending Employee object's employmentDate to
     * @return Calling Builder object
     * @apiNote Intermediate method: Chain this method to other intermediate methods to combine
     *          results.
     */

    public EmployeeBuilder employmentDate(LocalDate date) {
      this.employmentDate = date;
      return this;
    }

    /**
     * @return A new Employee object using Builder object's intermediate data
     * @apiNote Terminal method: Use this method to finish a chain of intermediate method calls and
     *          yield a complete product.
     */

    public Employee build() {
      return new Employee(this);
    }

    /**
     * Helper method for setting salary in a dollar format
     *
     * @param salary Amount to change Employee object's salary to
     * @return salary after processing
     * @throws EMSNegativeFundsException If salary is less than zero
     */

    private static float sanitizeSalary(float salary) throws EMSNegativeFundsException {
      if (salary < 0) {
        throw new EMSNegativeFundsException();
      }

      return Float.parseFloat(df.format(salary));
    }
  }
}
