package exceptions;

public class NoSuchQuantityOfProductException extends RuntimeException {
    public NoSuchQuantityOfProductException(String message) {
        super(message);
    }
}