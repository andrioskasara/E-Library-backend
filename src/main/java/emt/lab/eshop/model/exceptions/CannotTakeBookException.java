package emt.lab.eshop.model.exceptions;

public class CannotTakeBookException extends RuntimeException {
    public CannotTakeBookException() {
        super("There is no more available copies of the book");
    }
}
