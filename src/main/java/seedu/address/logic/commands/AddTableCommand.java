package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.Mode;
import seedu.address.model.Model;
import seedu.address.model.table.TableNumber;
import seedu.address.model.table.TableStatus;

/**
 * Adds a table to Tables.
 */
public class AddTableCommand extends Command {

    public static final String COMMAND_WORD = "addTable";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds table(s) to the RestOrRant application." + "Parameters: NUMBER_OF_SEATS [NUMBER_OF_SEATS]...\n" + "Example: " + COMMAND_WORD + " 2 1 4";

    public static final String MESSAGE_SUCCESS = "New table added:\nTable%1$s: %2$s";

    private final List<TableStatus> tableStatusList;

    /**
     * Creates an AddTableCommand to add table specified by the number of seats.
     */
    public AddTableCommand(List<TableStatus> tableStatuses) {
        requireNonNull(tableStatuses);
        tableStatusList = tableStatuses;
    }

    @Override
    public CommandResult execute(Mode mode, Model model, CommandHistory history) {
        requireNonNull(model);
        TableNumber addedTableNumber = null;

        for (TableStatus tableStatus : tableStatusList) {
            addedTableNumber = model.addTable(tableStatus);
        }
        requireNonNull(addedTableNumber);

        return new CommandResult(String.format(MESSAGE_SUCCESS, addedTableNumber.toString(), tableStatusList)); // TODO: Beautify resultDisplay
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTableCommand && tableStatusList.equals(((AddTableCommand) other).tableStatusList));
    }
}
