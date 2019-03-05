package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.order.exceptions.DuplicateOrderItemException;
import seedu.address.model.order.exceptions.OrderItemNotFoundException;

/**
 * A list of order items that enforces uniqueness between its elements and does not allow nulls.
 * An order item is considered unique by comparing using {@code OrderItem#isSameOrderItem(OrderItem)}. As such,
 * adding and updating of order items uses OrderItem#isSameOrderItem(OrderItem) for equality so as to ensure that
 * the order item being added or updated is unique in terms of identity in the UniqueOrderItemList. However, the removal
 * of an order item uses OrderItem#equals so as to ensure that the order item with exactly the same
 * fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see OrderItem#isSameOrderItem(OrderItem)
 */
public class UniqueOrderItemList implements Iterable<OrderItem> {

    private final ObservableList<OrderItem> internalList = FXCollections.observableArrayList();
    private final ObservableList<OrderItem> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent order item as the given argument.
     */
    public boolean contains(OrderItem toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameOrderItem);
    }

    /**
     * Adds an order item to the list.
     * The order item must not already exist in the list.
     */
    public void add(OrderItem toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateOrderItemException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the order item {@code target} in the list with {@code editedOrderItem}.
     * {@code target} must exist in the list.
     * The order item identity of {@code editedOrderItem} must not be the same as another existing order item
     * in the list.
     */
    public void setOrderItem(OrderItem target, OrderItem editedOrderItem) {
        requireAllNonNull(target, editedOrderItem);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new OrderItemNotFoundException();
        }

        if (!target.isSameOrderItem(editedOrderItem) && contains(editedOrderItem)) {
            throw new DuplicateOrderItemException();
        }

        internalList.set(index, editedOrderItem);
    }

    /**
     * Removes the equivalent order item from the list.
     * The order item must exist in the list.
     */
    public void remove(OrderItem toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new OrderItemNotFoundException();
        }
    }

    public void setOrderItems(UniqueOrderItemList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code orderItems}.
     * {@code orderItems} must not contain duplicate order items.
     */
    public void setOrderItems(List<OrderItem> orderItems) {
        requireAllNonNull(orderItems);
        if (!orderItemsAreUnique(orderItems)) {
            throw new DuplicateOrderItemException();
        }

        internalList.setAll(orderItems);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<OrderItem> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<OrderItem> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueOrderItemList // instanceof handles nulls
                && internalList.equals(((UniqueOrderItemList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code orderItems} contains only unique order items.
     */
    private boolean orderItemsAreUnique(List<OrderItem> orderItems) {
        for (int i = 0; i < orderItems.size() - 1; i++) {
            for (int j = i + 1; j < orderItems.size(); j++) {
                if (orderItems.get(i).isSameOrderItem(orderItems.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
