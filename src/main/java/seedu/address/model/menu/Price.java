package seedu.address.model.menu;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Price {

    public static final String MESSAGE_CONSTRAINTS = "Item prices should be in the format <integer><.><double digit " +
            "integer>" + ", and it should not be blank";

    /*
     * The first character of the item code must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * None of the characters must contain alphabets or symbols (except the period)
     * else is not a valid price input.
     */
    public static final String VALIDATION_REGEX = "(\\d+\\.\\d{1,2})";

    public final String itemPrice;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid code.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        itemPrice = price;
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return itemPrice;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && itemPrice.equals(((Price) other).itemPrice)); // state check
    }

    @Override
    public int hashCode() {
        return itemPrice.hashCode();
    }

}
