package seedu.address.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.address.model.order.OrderItem;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of RestOrRant
 */
public interface ReadOnlyRestOrRant extends Observable {

    /**
     * Returns an unmodifiable view of the order items list.
     */
    ObservableList<OrderItem> getOrderItemList();

    ObservableList<Person> getPersonList(); // TODO: remove

}