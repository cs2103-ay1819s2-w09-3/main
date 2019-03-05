package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODE_CHANGE;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.Mode;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class ChangeModeCommand extends Command {

    // TODO: include and update in individual classes, remove once all change mode classes done.
    public static final String MESSAGE_SUCCESS = "Mode changed to ___________";

    /**
     * Executes the command and returns the result message.
     *
     * @param mode    {@code Mode} current mode of the RestOrRant.
     * @param model   {@code Model} which the command should operate on.
     * @param history {@code CommandHistory} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Mode mode, Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (isSameMode(mode)) {
            throw new CommandException(MESSAGE_INVALID_MODE_CHANGE);
        }

        model.changeMode();

        return generateCommandResult();
    }

    /**
     * Checks if mode to change to is same as the current mode.
     *
     * @param mode mode to change to.
     * @return true if mode is same as current mode, otherwise false.
     */
    abstract boolean isSameMode(Mode mode);

    /**
     * Generate CommandResult specific to each changeModeCommand.
     */
    abstract CommandResult generateCommandResult(); // TODO: for each mode change command insert here

}
