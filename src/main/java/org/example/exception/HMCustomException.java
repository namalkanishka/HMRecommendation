package org.example.exception;

public class HMCustomException extends RuntimeException {

  public HMCustomException(String message) {
    super(message);
  }

  public HMCustomException(String message, Throwable cause) {
    super(message, cause);
  }
}