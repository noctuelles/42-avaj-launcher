package com.plouvel.avajlauncher.exception;

public class InvalidAircraftType extends Exception {
    public InvalidAircraftType(String type) {
        super("invalid aircraft type : " + type);
    }
}
