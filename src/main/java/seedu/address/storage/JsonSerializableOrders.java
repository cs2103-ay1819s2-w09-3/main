package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.Orders;
import seedu.address.model.order.ReadOnlyOrders;

/**
 * An Immutable order list that is serializable to JSON format.
 */
@JsonRootName(value = "orderItems")
class JsonSerializableOrders {

    public static final String MESSAGE_DUPLICATE_ORDER_ITEMS = "Order list contains duplicate order item(s).";

    private final List<JsonAdaptedOrderItem> orderItems = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableOrders} with the given order items.
     */
    @JsonCreator
    public JsonSerializableOrders(@JsonProperty("orderItems") List<JsonAdaptedOrderItem> orderItems) {
        this.orderItems.addAll(orderItems);
    }

    /**
     * Converts a given {@code ReadOnlyOrders} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableOrders}.
     */
    public JsonSerializableOrders(ReadOnlyOrders source) {
        orderItems.addAll(source.getOrderItemList().stream().map(JsonAdaptedOrderItem::new).collect(Collectors.toList()));
    }

    /**
     * Converts this order list into the model's {@code Orders} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Orders toModelType() throws IllegalValueException {
        Orders orders = new Orders();
        for (JsonAdaptedOrderItem jsonAdaptedOrderItem : orderItems) {
            OrderItem orderItem = jsonAdaptedOrderItem.toModelType();
            if (orders.hasOrderItem(orderItem)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ORDER_ITEMS);
            }
            orders.addOrderItem(orderItem);
        }
        return orders;
    }

}
