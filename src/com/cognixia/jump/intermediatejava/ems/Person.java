package com.cognixia.jump.intermediatejava.ems;

abstract class Person {
  private final String firstName;
  private final String lastName;
  private int age;

  public Person(String firstName, String lastName, int age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  public String getName() {
    return firstName + " " + lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = sanitizeAge(age);
  }

  public static int sanitizeAge(int age) {
    if (age < 0) {
      throw new IllegalArgumentException("age cannot be negative");
    }

    return age;
  }
}