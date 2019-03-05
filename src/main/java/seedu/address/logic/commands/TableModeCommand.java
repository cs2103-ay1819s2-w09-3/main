package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODE_CHANGE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TABLE_NUMBER;

import java.util.Optional;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.Mode;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.table.Table;
import seedu.address.model.table.TableNumber;

/**
 * Change the RestOrRant's mode to {@code Mode.TABLE_MODE}.
 * Used to add, delete and edit order items for the table and call for bill.
 */
public class TableModeCommand extends ChangeModeCommand {
    public static final String COMMAND_WORD = "tableMode"; // change to standardize with other modes
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Change to Table Mode for the specified table. " + "Parameters: TABLE_NUMBER\n" + "Example: " + COMMAND_WORD + " 3";
    public static final String MESSAGE_SUCCESS = "Mode changed to Table Mode";

    private final TableNumber tableNumber;

    /**
     * Creates a TableModeCommand that changes mode to Table Mode for the table with {@code TableNumber}
     */
    public TableModeCommand(TableNumber tableNumber) {
        requireNonNull(tableNumber);
        this.tableNumber = tableNumber;
    }

    @Override
    public CommandResult execute(Mode mode, Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (isSameMode(mode) && model.getSelectedTable().getTableNumber().equals(tableNumber)) {
            throw new CommandException(MESSAGE_INVALID_MODE_CHANGE);
        }

        Optional<Table> tableOptional = model.getRestOrRant().getTables().getTableFromNumber(tableNumber);
        if (!tableOptional.isPresent()) {
            throw new CommandException(MESSAGE_INVALID_TABLE_NUMBER);
        }

        model.updateFilteredTableList(Model.PREDICATE_SHOW_ALL_TABLES);
        model.setSelectedTable(tableOptional.get());
        model.updateFilteredOrderItemList(orderItem -> orderItem.getTableNumber().equals(tableNumber));

        model.changeMode();

        return generateCommandResult();
    }

    @Override
    public CommandResult generateCommandResult() {
        return new CommandResult(String.format(MESSAGE_SUCCESS), false, false, Mode.TABLE_MODE);
    }

    @Override
    boolean isSameMode(Mode mode) {
        return mode.equals(Mode.TABLE_MODE);
    }
}
