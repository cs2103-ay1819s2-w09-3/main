package seedu.address.model.menu.exceptions;

/**
 * Signals that the operation will result in duplicate OrderItems (OrderItems are considered duplicates if they
 * have the same identity).
 */
public class DuplicateMenuItemException extends RuntimeException {
    public DuplicateMenuItemException() {
        super("Operation would result in duplicate menu items");
    }
}
