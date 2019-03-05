package seedu.address.model.order;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.menu.Code;
import seedu.address.model.table.TableNumber;

/**
 * Represents an order item in an Order from the RestOrRant.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class OrderItem {

    private final TableNumber tableNumber;
    private final Code menuItemCode;
    private final int quantityOrdered; // TODO: implement ItemStatus and change type, quantity part of status

    /**
     * Every field must be present and not null.
     * TODO: create constructor with default status as unserved
     */
    public OrderItem(TableNumber tableNumber, Code menuItemCode, int quantityOrdered) {
        requireAllNonNull(tableNumber, menuItemCode, quantityOrdered);
        this.tableNumber = tableNumber;
        this.menuItemCode = menuItemCode;
        this.quantityOrdered = quantityOrdered;
    }

    public TableNumber getTableNumber() {
        return tableNumber;
    }

    public Code getMenuItemCode() {
        return menuItemCode;
    } // TODO: get the individual menu item details by calling from model

    public int getQuantity() {
        return quantityOrdered;
    }

    /**
     * Returns true if both order items have the same menu item code and table number.
     * This defines a weaker notion of equality between two order items.
     */
    public boolean isSameOrderItem(OrderItem otherOrderItem) {
        if (otherOrderItem == this) {
            return true;
        }

        return otherOrderItem != null && otherOrderItem.getTableNumber().equals(getTableNumber()) && otherOrderItem.getMenuItemCode().equals(getMenuItemCode());
    }

    /**
     * Returns true if both order items have the same table number, menu item code and quantity ordered.
     * This defines a stronger notion of equality between two order items.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OrderItem)) {
            return false;
        }

        OrderItem otherOrderItem = (OrderItem) other;
        return isSameOrderItem(otherOrderItem) && otherOrderItem.getQuantity() == getQuantity();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(tableNumber, menuItemCode, String.valueOf(quantityOrdered));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[Table ").append(getTableNumber()).append("] ").append(getMenuItemCode()).append(" | Qty Ordered: ").append(getQuantity());
        return builder.toString();
    }

}
