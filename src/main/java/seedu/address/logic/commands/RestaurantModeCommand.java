package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODE_CHANGE;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.Mode;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Change the RestOrRant's mode to {@code Mode.RESTAURANT_MODE}.
 * Used to add and delete tables from the RestOrRant.
 */
public class RestaurantModeCommand extends ChangeModeCommand {
    public static final String COMMAND_WORD = "restaurantMode"; // change to standardize with other modes
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Change to Restaurant Mode.\n" + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Mode changed to Restaurant Mode";

    @Override
    public CommandResult execute(Mode mode, Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (isSameMode(mode)) {
            throw new CommandException(MESSAGE_INVALID_MODE_CHANGE);
        }

        model.updateFilteredTableList(Model.PREDICATE_SHOW_ALL_TABLES);
        model.updateFilteredOrderItemList(Model.PREDICATE_SHOW_ALL_ORDER_ITEMS);

        model.changeMode();

        return generateCommandResult();
    }

    @Override
    public CommandResult generateCommandResult() {
        return new CommandResult(String.format(MESSAGE_SUCCESS), false, false, Mode.RESTAURANT_MODE);
    }

    @Override
    boolean isSameMode(Mode mode) {
        return mode.equals(Mode.RESTAURANT_MODE);
    }
}
