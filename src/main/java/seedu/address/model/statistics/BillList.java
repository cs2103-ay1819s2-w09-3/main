package seedu.address.model.statistics;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.statistics.exception.BillNotFoundException;

/**
 * A list of bills
 * <p>
 * Supports a minimal set of list operations.
 */
public class BillList implements Iterable<Bill> {

    private final ObservableList<Bill> internalList = FXCollections.observableArrayList();
    private final ObservableList<Bill> internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a bill to the list.
     */
    public void add(Bill toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the order item {@code target} in the list with {@code editedOrderItem}.
     * {@code target} must exist in the list.
     * The order item identity of {@code editedOrderItem} must not be the same as another existing order item
     * in the list.
     */
    public void setBills(Bill target, Bill editedBillItem) {
        requireAllNonNull(target, editedBillItem);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BillNotFoundException();
        }

        internalList.set(index, editedBillItem);
    }

    /**
     * Removes the equivalent order item from the list.
     * The order item must exist in the list.
     */
    public void remove(Bill toRemove) {
        requireNonNull(toRemove);
    }

    public void setBills(BillList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code billList}.
     */
    public void setBillList(List<Bill> billList) {
        requireAllNonNull(billList);
        internalList.setAll(billList);
    }


    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Bill> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Bill> iterator() {
        return internalList.iterator();
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
