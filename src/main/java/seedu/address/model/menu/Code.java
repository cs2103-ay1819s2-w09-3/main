package seedu.address.model.menu;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Code {

    public static final String MESSAGE_CONSTRAINTS = "Item codes should be in the format <uppercase alphabet><double digit integer>, and it should not be blank";

    /*
     * The first character of the item code must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[A-Z][0-9][0-9]";

    public final String itemCode;

    /**
     * Constructs a {@code Code}.
     *
     * @param code A valid code.
     */
    public Code(String code) {
        requireNonNull(code);
        checkArgument(isValidCode(code), MESSAGE_CONSTRAINTS);
        itemCode = code;
    }

    /**
     * Returns true if a given string is a valid code.
     */
    public static boolean isValidCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return itemCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Code // instanceof handles nulls
                && itemCode.equals(((Code) other).itemCode)); // state check
    }

    @Override
    public int hashCode() {
        return itemCode.hashCode();
    }

}
