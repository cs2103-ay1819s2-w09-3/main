package seedu.address.model.table;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Represents a table in RestOrRant.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Table {

    private TableNumber tableNumber;
    private TableStatus tableStatus;

    public Table(String tableNumber, String numberOfSeats) {
        this.tableNumber = new TableNumber(tableNumber);
        this.tableStatus = new TableStatus(numberOfSeats);
    }

    public Table(TableNumber tableNumber, TableStatus tableStatus) {
        this.tableNumber = tableNumber;
        this.tableStatus = tableStatus;
    }

    public TableStatus getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String newTableStatus) throws CommandException {
        tableStatus.setTableStatus(newTableStatus);
    }

    public TableNumber getTableNumber() {
        return tableNumber;
    }

    public boolean isSameTable(Table otherTable) {
        if (otherTable == this) {
            return true;
        }

        return otherTable != null && otherTable.getTableNumber().equals(getTableNumber());
    }
}
