package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.Mode;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.table.Table;

/**
 * Updates the status of the table.
 */
public class UpdateTableCommand extends Command {

    public static final String COMMAND_WORD = "updateTable";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the status of the table." + "Parameters: " +
            "TABLE_NUMBER NEW_STATUS\n" + "Example: " + COMMAND_WORD + " 2 0";

    public static final String MESSAGE_SUCCESS = "Table status updated: \nTable%1$s: %2$s";

    private final String newTableStatus;

    /**
     * Creates an UpdateTableCommand to update the status of the table specified by the table number.
     */
    public UpdateTableCommand(String newTableStatusInString) {
        this.newTableStatus = newTableStatusInString;
    }

    @Override
    public CommandResult execute(Mode mode, Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        Table selectedTable = model.getSelectedTable();
        Table updatedTable = model.getSelectedTable();
        updatedTable.setTableStatus(newTableStatus);
        model.setTable(selectedTable, updatedTable);
        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedTable.getTableNumber(),
                selectedTable.getTableStatus()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof UpdateTableCommand && newTableStatus.equals(((UpdateTableCommand) other).newTableStatus));
    }
}
