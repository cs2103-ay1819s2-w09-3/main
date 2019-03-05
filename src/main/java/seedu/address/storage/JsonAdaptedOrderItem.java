package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.menu.Code;
import seedu.address.model.order.OrderItem;
import seedu.address.model.table.TableNumber;

/**
 * Jackson-friendly version of {@link OrderItem}.
 */
class JsonAdaptedOrderItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order item's %s field is missing!";

    private final String tableNumber;
    private final String menuItemCode;
    private final String quantityOrdered;
    private final String quantityUnserved;

    /**
     * Constructs a {@code JsonAdaptedOrderItem} with the given order item details.
     */
    @JsonCreator
    public JsonAdaptedOrderItem(@JsonProperty("tableNumber") String tableNumber,
                                @JsonProperty("menuItemCode") String menuItem,
                                @JsonProperty("ordered") String quantityOrdered,
                                @JsonProperty("unserved") String quantityUnserved) {
        this.tableNumber = tableNumber;
        this.menuItemCode = menuItem;
        this.quantityOrdered = quantityOrdered;
        this.quantityUnserved = quantityUnserved;
    }

    /**
     * Converts a given {@code OrderItem} into this class for Jackson use.
     */
    public JsonAdaptedOrderItem(OrderItem source) {
        tableNumber = source.getTableNumber().getTableNumber();
        menuItemCode = source.getMenuItemCode().toString();
        quantityOrdered = String.valueOf(source.getQuantity());
        quantityUnserved = String.valueOf(0);
    }

    /**
     * Converts this Jackson-friendly adapted order item object into the model's {@code OrderItem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order item.
     */
    public OrderItem toModelType() throws IllegalValueException {
        if (tableNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "tableNumber"));
        }
        // TODO: check if table number is valid
        //if (!Name.isValidName(name)) {
        //    throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        //}
        final TableNumber modelTableNumber = new TableNumber(tableNumber);

        if (menuItemCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "menuItemCode"));
        }
        // TODO: check if menu item code is legal
        //if (!Phone.isValidPhone(phone)) {
        //    throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        //}
        final Code modelMenuItemCode = new Code(menuItemCode);

        if (quantityOrdered == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "ordered"));
        }

        final int modelQuantityOrdered = Integer.parseInt(quantityOrdered); // TODO: handle NumberFormatException

        if (quantityUnserved == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "unserved"));
        }

        return new OrderItem(modelTableNumber, modelMenuItemCode, modelQuantityOrdered);
    }

}
