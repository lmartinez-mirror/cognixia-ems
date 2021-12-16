package com.cognixia.jump.intermediatejava.ems;

/**
 * Custom Exception for handling negative funding values since you cannot assign a negative amount
 * of dollars for a salary or budget.
 */

public class EMSNegativeFundsException extends Exception {
  public EMSNegativeFundsException() {
    super("funds cannot be negative");
  }
}
