package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODE;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.Mode;
import seedu.address.logic.commands.AddItemToMenuCommand;
import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.commands.AddTableCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.MenuModeCommand;
import seedu.address.logic.commands.RestaurantModeCommand;
import seedu.address.logic.commands.TableModeCommand;
import seedu.address.logic.commands.UpdateTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class RestOrRantParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(Mode mode, String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // General commands that work in all modes

        switch (commandWord) {
            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            case HelpCommand.COMMAND_ALIAS:
                return new HelpCommand();

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case ExitCommand.COMMAND_ALIAS:
                return new ExitCommand();

            case RestaurantModeCommand.COMMAND_WORD:
                return new RestaurantModeCommand();

            case MenuModeCommand.COMMAND_WORD:
                return new MenuModeCommand();

            case TableModeCommand.COMMAND_WORD:
                return new TableModeCommandParser().parse(arguments);

            // Commands that work in Restaurant Mode
            case AddTableCommand.COMMAND_WORD:
                if (mode != Mode.RESTAURANT_MODE) {
                    throw new ParseException(MESSAGE_INVALID_MODE);
                }
                return new AddTableCommandParser().parse(arguments);

            // Commands that work in Menu Mode
            case AddItemToMenuCommand.COMMAND_WORD:
                if (mode != Mode.MENU_MODE) {
                    throw new ParseException(MESSAGE_INVALID_MODE);
                }
                return new AddItemToMenuCommandParser().parse(arguments);

            // Commands that work in Table Mode
            case AddOrderCommand.COMMAND_WORD:
                if (mode != Mode.TABLE_MODE) {
                    throw new ParseException(MESSAGE_INVALID_MODE);
                }
                return new AddOrderCommandParser().parse(arguments);

            case UpdateTableCommand.COMMAND_WORD:
                if (mode != Mode.TABLE_MODE) {
                    throw new ParseException(MESSAGE_INVALID_MODE);
                }
                return new UpdateTableCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

    }

}
