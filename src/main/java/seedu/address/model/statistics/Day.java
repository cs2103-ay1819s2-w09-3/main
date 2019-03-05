package seedu.address.model.statistics;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Day {

    public static final String MESSAGE_CONSTRAINTS = "Day should be in the format <double digit integer>, and it " +
            "should not be blank";

    /*
     * The first character of the item code must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-9][0-9]";

    public final String day;

    /**
     * Constructs a {@code Day}.
     *
     * @param day A valid code.
     */
    public Day(String day) {
        requireNonNull(day);
        checkArgument(isValidDay(day), MESSAGE_CONSTRAINTS);
        this.day = day;
    }

    /**
     * Returns true if a given string is a valid code.
     */
    public static boolean isValidDay(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return day;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && day.equals(((Day) other).day)); // state check
    }

    @Override
    public int hashCode() {
        return day.hashCode();
    }

}
