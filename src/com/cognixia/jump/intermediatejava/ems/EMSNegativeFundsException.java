package com.cognixia.jump.intermediatejava.ems;

public class EMSNegativeFundsException extends Exception {
  public EMSNegativeFundsException() {
    super("funds cannot be negative");
  }
}
