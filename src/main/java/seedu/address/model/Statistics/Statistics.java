package seedu.address.model.Statistics;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.Statistics.ReadOnlyStatistics;

/**
 * Wraps all order-related data
 * Duplicates are not allowed (by .isSameOrderItem comparison)
 */
public class Statistics implements ReadOnlyStatistics {

    private final BillList billList;
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
   {
        billList = new BillList();
    }

    public Statistics() {}

    /**
     * Creates an RestOrRant using the Persons in the {@code toBeCopied}
     */
    public Statistics(ReadOnlyStatistics toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the bill list with {@code billList}.
     */
    public void setBillList(List<Bill> billList) {
        this.billList.setBillList(billList);
        indicateModified();
    }

    /**
     * Resets the existing data of this RestOrRant's {@code Statistics} with {@code newData}.
     */
    public void resetData(ReadOnlyStatistics newData) {
        requireNonNull(newData);

        setBillList(newData.getBillList());
    }

    /**
     * Adds a Bill to the RestOrRant's Bill List.
     * The order item must not already exist in the orders.
     */
    public void addBill(Bill bill) {
        billList.add(bill);
        indicateModified();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the address book has been modified.
     */
    protected void indicateModified() {
        invalidationListenerManager.callListeners(this);
    }

    //// util methods

    @Override
    public String toString() {
        return billList.asUnmodifiableObservableList().size() + " order items";
        // TODO: refine later
    }

    @Override
    public ObservableList<Bill> getBillList() {
        return billList.asUnmodifiableObservableList();
    }

    @Override
    public int hashCode() {
        return billList.hashCode();
    }
}