package seedu.address.model.order;

import javafx.beans.Observable;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of RestOrRant's orders
 */
public interface ReadOnlyOrders extends Observable {

    /**
     * Returns an unmodifiable view of the order items list.
     */
    ObservableList<OrderItem> getOrderItemList();

}
