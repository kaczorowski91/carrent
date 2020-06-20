package pl.kaczorowski.carrent.exception;

import lombok.Getter;

@Getter
public enum ExceptionType {
    USER_NOT_FOUND("ERROR: User with id=%s was not found."),
    USER_NOT_CHANGE("ERROR: Can't change user data. Pesel %s is exist in database."),
    VEHICLE_NOT_CHANGE("ERROR: Can't change vehicle data. Vehicle Identifier %s is exist in database."),
    VEHICLE_NOT_FOUND("ERROR: Vehicle with id=%s was not found."),
    VEHICLE_IS_RENTED("ERROR: Vehicle with id=%s is currently rented");

    private String message;

    ExceptionType(String message) {
        this.message = message;
    }

    }
