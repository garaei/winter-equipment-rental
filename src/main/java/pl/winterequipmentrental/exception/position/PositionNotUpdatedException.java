package pl.winterequipmentrental.exception.position;

public class PositionNotUpdatedException extends RuntimeException {
    public PositionNotUpdatedException() {
        super("Position not updated");
    }

    public PositionNotUpdatedException(String message) {
        super(message);
    }
}
