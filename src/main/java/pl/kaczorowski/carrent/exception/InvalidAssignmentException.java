package pl.kaczorowski.carrent.exception;

public class InvalidAssignmentException extends RuntimeException{

    public InvalidAssignmentException(ExceptionType type, String value) {
        super(String.format(type.getMessage(), value));
    }
}
