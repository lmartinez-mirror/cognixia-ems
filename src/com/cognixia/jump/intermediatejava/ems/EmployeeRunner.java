package com.cognixia.jump.intermediatejava.ems;

import java.time.LocalDate;
import com.cognixia.jump.intermediatejava.ems.Employee.EmployeeBuilder;

public class EmployeeRunner {
  public static void main(String[] args) {
    Employee employee = new EmployeeBuilder("first", "last")
      .salary(100.98f)
      .department("accounting")
      .employmentDate(LocalDate.now())
      .build();
    System.out.println(employee);

    Employee empty = new EmployeeBuilder(null, null).build();
    System.out.println(empty);
  }
}
