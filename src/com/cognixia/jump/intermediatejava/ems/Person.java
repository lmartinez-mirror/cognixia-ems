package com.cognixia.jump.intermediatejava.ems;

/**
 * The base class for Employee
 *
 * @author Luis Martinez
 */
abstract class Person {
  private final String firstName;
  private final String lastName;
  private int age;

  public Person(String firstName, String lastName, int age) {
    /**
     * Initializes Person with required attributes
     */
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  /**
   * Full name getter
   *
   * @returns Person's first and last name combined
   */

  public String getName() {
    return firstName + " " + lastName;
  }

  /**
   * @return Person's first name
   */

  public String getFirstName() {
    return firstName;
  }

  /**
   * @return Person's last name
   */

  public String getLastName() {
    return lastName;
  }

  /**
   * @return Person's age
   */

  public int getAge() {
    return age;
  }

  /**
   * @param age New age to set
   */

  public void setAge(int age) {
    this.age = sanitizeAge(age);
  }

  /**
   * Ensures that age meets spec (cannot be negative)
   *
   * @param age Nonnegative value to check
   * @return Same age value after checking
   * @throws IllegalArgumentException If age is less than zero
   */

  private static int sanitizeAge(int age) {
    if (age < 0) {
      throw new IllegalArgumentException("age cannot be negative");
    }

    return age;
  }
}
