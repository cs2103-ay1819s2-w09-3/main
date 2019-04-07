package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.Mode;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
/**
 * Changes the UI to monthly calendar view.
 */
public class MonthlyCommand extends Command {

    public static final String COMMAND_WORD = "monthly";
    public static final String COMMAND_ALIAS = "m";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Retrieves the latest 20 monthly revenue with the " +
            "most recent month at the top."
            + "Example: " + COMMAND_WORD + "or " + COMMAND_ALIAS;
    public static final String MESSAGE_SUCCESS = "Change view to monthly.";
    public static final String MESSAGE_INCORRECT_MODE = "Incorrect Mode, unable to execute command. Enter " +
            "statisticsMode or SM.";

    /**
     * Creates a MonthlyCommand
     */
    public MonthlyCommand() {
    }

    @Override
    public CommandResult execute(Mode mode, Model model, CommandHistory history) throws CommandException {
        requireAllNonNull(mode, model, history);

        if (!mode.equals(Mode.STATISTICS_MODE)) {
            throw new CommandException(MESSAGE_INCORRECT_MODE);
        }

        model.setStatisticsStatus(false, true, false);
        return new CommandResult(String.format(MESSAGE_SUCCESS), false, false, Mode.STATISTICS_MODE);
    }
}