package seedu.address.model.statistics;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Month {

    public static final String MESSAGE_CONSTRAINTS = "Month should be in the format <double digit integer>, and it " +
            "should not be blank";

    /*
     * The first character of the item code must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-9][0-9]";

    public final String month;

    /**
     * Constructs a {@code Month}.
     *
     * @param month A valid code.
     */
    public Month(String month) {
        requireNonNull(month);
        checkArgument(isValidMonth(month), MESSAGE_CONSTRAINTS);
        this.month = month;
    }

    /**
     * Returns true if a given string is a valid code.
     */
    public static boolean isValidMonth(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return month;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Month // instanceof handles nulls
                && month.equals(((Month) other).month)); // state check
    }

    @Override
    public int hashCode() {
        return month.hashCode();
    }

}
