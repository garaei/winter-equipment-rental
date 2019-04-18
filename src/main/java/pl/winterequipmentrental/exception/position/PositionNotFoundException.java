package pl.winterequipmentrental.exception.position;

public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException() {
        super("Not found position");
    }

    public PositionNotFoundException(String message) {
        super(message);
    }
}
