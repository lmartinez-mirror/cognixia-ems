package com.cognixia.jump.intermediatejava.ems;

import java.text.DecimalFormat;

/**
 * Class for representing Departments within the EMS system
 *
 * @author Luis Martinez
 * @apiNote Implements Department class bonus
 */

public class Department {
  private String name;
  private final String deptId;
  private float budget;
  private String phoneNumber;
  private String building;
  private Employee manager;

  /**
   * Private class constructor; use DepartmentBuilder to get Department instances instead of calling
   * the constructor directly
   *
   * @param builder DepartmentBuilder object for creating Department instances
   */

  private Department(DepartmentBuilder builder) {
    this.name = builder.name;
    this.deptId = builder.deptId;
    this.budget = builder.budget;
    this.phoneNumber = builder.phoneNumber;
    this.building = builder.building;
    this.manager = builder.manager;
  }

  /**
   * @return Department name
   */

  public String getName() {
    return name;
  }

  /**
   * @param name Name to rename Department to
   */

  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return Department's ID
   */

  public String getDeptId() {
    return deptId;
  }

  /**
   * @return Department's budget
   */

  public float getBudget() {
    return budget;
  }

  /**
   * @param budget Amount to set Department's budget to
   */

  public void setBudget(float budget) {
    try {
      this.budget = DepartmentBuilder.sanitizeBudget(budget);
    } catch (EMSNegativeFundsException e) {
      System.out.println("failed to set budget");
    }
  }

  /**
   * @return Department's phone number
   */

  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * @param phoneNumber String to set Department's phone number to
   */

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * @return Department's building as a String
   */

  public String getBuilding() {
    return building;
  }

  /**
   * @param Department's assigned building
   */

  public void setBuilding(String building) {
    this.building = building;
  }

  /**
   * @return Department's manager as an Employee object
   */

  public Employee getManager() {
    return manager;
  }

  /**
   * @param manager New manager to assign to the Department
   */

  public void setManager(Employee manager) {
    this.manager = manager;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Department [").append("name=" + name + ", ")
        .append("deptId=" + deptId + ", ").append("budget=" + budget + ", ")
        .append("phoneNumber=" + phoneNumber + ", ").append("building=" + building + ", ")
        .append("manager=" + manager.getName()).append("]");

    return sb.toString();
  }

  /**
   * Builder for Department class
   * <p>
   * Use this class to create Department instances instead of directly invoking the constructor
   *
   * @author Luis Martinez
   */

  public static class DepartmentBuilder {
    private String name;
    private final String deptId;
    private float budget;
    private String phoneNumber;
    private String building;
    private Employee manager;

    /** Tracks employee IDs, ensures no two are the same */
    private static int counter = 10;
    private static final DecimalFormat df = new DecimalFormat("#.00");

    public DepartmentBuilder(String name) {
      this.name = name;
      this.deptId = Integer.toString(counter++);
      this.budget = 0;
      this.phoneNumber = null;
      this.building = null;
      this.manager = null;
    }

    /**
     * @param budget Amount to set pending Department object's budget to
     * @return Calling Builder object
     * @apiNote Intermediate method: Chain this method to other intermediate methods to combine
     *          results.
     */

    public DepartmentBuilder budget(float budget) {
      try {
        this.budget = sanitizeBudget(budget);
      } catch (EMSNegativeFundsException e) {
        System.out.println("failed to set budget: " + e);
      }
      return this;
    }

    /**
     * @param phoneNumber String to assign pending Department object's phone number to
     * @return Calling Builder object
     * @apiNote Intermediate method: Chain this method to other intermediate methods to combine
     *          results.
     */

    public DepartmentBuilder phoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    /**
     * @param building String to assign pending Department object's building to
     * @return Calling Builder object
     * @apiNote Intermediate method: Chain this method to other intermediate methods to combine
     *          results.
     */

    public DepartmentBuilder building(String building) {
      this.building = building;
      return this;
    }

    /**
     * @param manager Department's new manager
     * @return Calling Builder object
     * @apiNote Intermediate method: Chain this method to other intermediate methods to combine
     *          results.
     */

    public DepartmentBuilder manager(Employee manager) {
      this.manager = manager;
      return this;
    }

    /**
     * @return A new Department object using Builder object's intermediate data
     * @apiNote Terminal method: Use this method to finish a chain of intermediate method calls and
     *          yield a complete product.
     */

    public Department build() {
      return new Department(this);
    }

    /**
     * Helper method for formatting budget to USD
     *
     * @param budget Amount to change Department's budget to
     * @return budget after processing
     * @throws EMSNegativeFundsException If budget is less than zero
     */

    public static float sanitizeBudget(float budget) throws EMSNegativeFundsException {
      if (budget < 0) {
        throw new EMSNegativeFundsException();
      }

      return Float.parseFloat(df.format(budget));
    }
  }
}
