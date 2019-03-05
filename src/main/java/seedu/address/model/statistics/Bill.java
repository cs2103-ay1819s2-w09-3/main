package seedu.address.model.statistics;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Bill from a UniqueOrderItemList from the RestOrRant.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Bill {

    private final int tableNumber;
    private final int day;
    private final int month;
    private final int year;
    private final int totalBill;
    //private final Table table;

    /**
     * Every field must be present and not null.
     */
    public Bill(int tableNumber, int day, int month, int year) {
        requireAllNonNull(tableNumber);
        this.tableNumber = tableNumber;
        //this.table = getTable(tableNumber);
        this.day = day;
        this.month = month;
        this.year = year;
        this.totalBill = calculateBill();
    }

    private int calculateBill() {
        int totalBill = 0;
        // TODO: iterate through the UniqueOrderList from the Table
        // Use getMenuItem().getPrice() after merging with the rest
        return totalBill;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getTotalBill() {
        return totalBill;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(tableNumber, day, month, year, totalBill);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[Table ").append(getTableNumber()).append("] [Date: ").append(getDay()).append("/").append(getMonth()).append("/").append(getYear()).append("] [Total Cost of the Bill: $").append(getTotalBill()).append("] ");
        return builder.toString();
    }

}
