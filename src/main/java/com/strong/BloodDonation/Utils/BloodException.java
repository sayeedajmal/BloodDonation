package com.strong.BloodDonation.Utils;

public class BloodException extends Exception {

    public BloodException() {
        super();
    }

    public BloodException(String Message) {
        super(Message);
    }

    public BloodException(String Message, Throwable throwable) {
        super(Message, throwable);
    }

    public BloodException(Throwable throwable) {
        super(throwable);
    }

}