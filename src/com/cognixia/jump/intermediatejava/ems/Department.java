package com.cognixia.jump.intermediatejava.ems;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Department implements Serializable {
  private String name;
  private final String deptId;
  private float budget;
  private String phoneNumber;
  private String building;
  private Employee manager;

  private Department(DepartmentBuilder builder) {
    this.name = builder.name;
    this.deptId = builder.deptId;
    this.budget = builder.budget;
    this.phoneNumber = builder.phoneNumber;
    this.building = builder.building;
    this.manager = builder.manager;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // No setter
  public String getDeptId() {
    return deptId;
  }

  public float getBudget() {
    return budget;
  }

  public void setBudget(float budget) {
    this.budget = DepartmentBuilder.sanitizeBudget(budget);
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getBuilding() {
    return building;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public Employee getManager() {
    return manager;
  }

  public void setManager(Employee manager) {
    this.manager = manager;
  }

  public static class DepartmentBuilder {
    private String name;
    private final String deptId;
    private float budget;
    private String phoneNumber;
    private String building;
    private Employee manager;

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

    public DepartmentBuilder budget(float budget) {
      this.budget = sanitizeBudget(budget);
      return this;
    }

    public DepartmentBuilder phoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    public DepartmentBuilder building(String building) {
      this.building = building;
      return this;
    }

    public DepartmentBuilder manager(Employee manager) {
      this.manager = manager;
      return this;
    }

    public Department build() {
      return new Department(this);
    }

    public static float sanitizeBudget(float budget) throws IllegalArgumentException {
      if (budget < 0) {
        throw new IllegalArgumentException("budget cannot be negative");
      }

      return Float.parseFloat(df.format(budget));
    }
  }
}
