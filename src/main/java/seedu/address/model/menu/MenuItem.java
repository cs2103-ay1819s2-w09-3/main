package seedu.address.model.menu;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a MenuItem in the menu.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class MenuItem {

    // Identity fields
    private final Name name;
    private final Code code;

    // Data fields
    private final Price price;
    //    private final Address address;
    //    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public MenuItem(Name name, Code code, Price price) {
        requireAllNonNull(name, code, price);
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public Name getName() {
        return name;
    }

    public Code getCode() {
        return code;
    }

    public Price getPrice() {
        return price;
    }

    //    public Address getAddress() {
    //        return address;
    //    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    //    public Set<Tag> getTags() {
    //        return Collections.unmodifiableSet(tags);
    //    }

    /**
     * Returns true if both menu items of the same name have both identity fields that is the same.
     * This defines a weaker notion of equality between two menu items.
     */
    public boolean isSameMenuItem(MenuItem otherItem) {
        if (otherItem == this) {
            return true;
        }

        return otherItem != null && otherItem.getName().equals(getName()) && (otherItem.getCode().equals(getCode()));
    }

    /**
     * Returns true if both menu items have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MenuItem)) {
            return false;
        }

        MenuItem otherItem = (MenuItem) other;
        return otherItem.getName().equals(getName()) && otherItem.getCode().equals(getCode()) && otherItem.getPrice().equals(getPrice());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, code, price);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName()).append(" Code: ").append(getCode()).append(" Price: ").append(getPrice());
        return builder.toString();
    }

}
