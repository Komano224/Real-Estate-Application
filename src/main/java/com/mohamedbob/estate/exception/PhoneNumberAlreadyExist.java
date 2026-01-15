package com.mohamedbob.estate.exception;

public class PhoneNumberAlreadyExist extends RuntimeException {
    public PhoneNumberAlreadyExist(String phoneNumber) {
        super(phoneNumber);
    }
}
