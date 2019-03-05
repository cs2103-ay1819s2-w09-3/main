package seedu.address.logic.commands;

import seedu.address.logic.Mode;

/**
 * Change the RestOrRant's mode to {@code Mode.MENU_MODE}.
 * Used to adding, deleting, editing menu items from menu.
 */
public class MenuModeCommand extends ChangeModeCommand {
    public static final String COMMAND_WORD = "menuMode"; // change to standardize with other modes
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Change to Menu Mode.\n" + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Mode changed to Menu Mode";

    @Override
    public CommandResult generateCommandResult() {
        return new CommandResult(String.format(MESSAGE_SUCCESS), false, false, Mode.MENU_MODE);
    }

    @Override
    boolean isSameMode(Mode mode) {
        return mode.equals(Mode.MENU_MODE);
    }

}
