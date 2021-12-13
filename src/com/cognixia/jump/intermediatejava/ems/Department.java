package com.cognixia.jump.intermediatejava.ems;

enum Building {
  A("Building A"),
  B("Building B"),
  C("Building C"),
  D("Building D"),
  E("Building E");

  private final String name;

  private Building(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}

public enum Department {
  HUMAN_RESOURCES(10000f, "800-283-9201", Building.A, null),
  TECHNOLOGY(200000f, "800-829-9910", Building.B, null),
  ACCOUNTING(50000f, "800-983-2831", Building.C, null),
  ADMINISTRATION(500000f, "800-982-2913", Building.D, null);

  private float budget;
  private String phoneNumber;
  private Building building;
  private Employee manager;

  Department(float budget, String phoneNumber, Building building, Employee manager) {
    this.budget = budget;
    this.phoneNumber = phoneNumber;
    this.building = building;
    this.manager = null;
  }

  public float getBudget() {
    return budget;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public Building getBuilding() {
    return building;
  }

  public Employee getManager() {
    return manager;
  }

  public void setManager(Employee manager) {
    this.manager = manager;
  }
}
