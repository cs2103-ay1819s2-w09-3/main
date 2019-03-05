package seedu.address.model.table;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.order.exceptions.DuplicateOrderItemException;
import seedu.address.model.order.exceptions.OrderItemNotFoundException;
import seedu.address.model.table.exceptions.DuplicateTableException;
import seedu.address.model.table.exceptions.TableNotFoundException;

/**
 * A list of tables that enforces uniqueness between its elements and does not allow nulls.
 * An table is considered unique by comparing using {@code Table#isSameTable(Table)}. As such,
 * adding and updating of table uses Table#isSameTable(Table) for equality so as to ensure that
 * the table being added or updated is unique in terms of identity in the UniqueOrderTableList. However, the removal
 * of a table uses Table#isSameTable(Table) so as to ensure that the table with exactly the same
 * fields will be removed. TODO: this is quite pointless now, may use the same function for both.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Table#isSameTable(Table)
 */
public class UniqueTableList implements Iterable<Table> {

    private final ObservableList<Table> internalList = FXCollections.observableArrayList();
    private final ObservableList<Table> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent table as the given argument.
     */
    public boolean contains(Table toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTable);
    }

    /**
     * Returns table if table exists in UniqueTableList and is occupied.
     */
    public Table getTable(TableNumber tableNumber) {
        FilteredList<Table> filteredList = internalList.filtered(table -> tableNumber.equals(table.getTableNumber()));
        if (filteredList.isEmpty()) {
            return null;
        }
        return filteredList.get(0);
    }

    /**
     * Adds a table item to the list.
     * The table must not already exist in the list.
     */
    public void add(Table toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTableException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the table {@code target} in the list with {@code editedTable}.
     * {@code target} must exist in the list.
     * The table identity of {@code editedOrderItem} must not be the same as another existing table
     * in the list.
     */
    public void setTable(Table target, Table editedTable) {
        requireAllNonNull(target, editedTable);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TableNotFoundException();
        }

        if (!target.isSameTable(editedTable) && contains(editedTable)) {
            throw new DuplicateTableException();
        }

        internalList.set(index, editedTable);
    }

    /**
     * Removes the equivalent order item from the list.
     * The order item must exist in the list.
     */
    public void remove(Table toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new OrderItemNotFoundException();
        }
    }

    public void setTables(UniqueTableList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tables}.
     * {@code tables} must not contain duplicate tables.
     */
    public void setTables(List<Table> tables) {
        requireAllNonNull(tables);
        if (!tablesAreUnique(tables)) {
            throw new DuplicateOrderItemException();
        }

        internalList.setAll(tables);
    }

    /**
     * Returns the number of tables in the UniqueTableList
     */
    public int getSize() {
        return internalList.size();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Table> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Table> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTableList // instanceof handles nulls
                && internalList.equals(((UniqueTableList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code orderItems} contains only unique order items.
     */
    private boolean tablesAreUnique(List<Table> tables) {
        for (int i = 0; i < tables.size() - 1; i++) {
            for (int j = i + 1; j < tables.size(); j++) {
                if (tables.get(i).isSameTable(tables.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
