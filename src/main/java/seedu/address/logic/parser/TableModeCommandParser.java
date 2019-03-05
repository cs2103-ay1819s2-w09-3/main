package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.TableModeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.table.TableNumber;

/**
 * Parses input arguments and creates a new TableModeCommand object
 */
public class TableModeCommandParser implements Parser<TableModeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TableModeCommand
     * and returns an TableModeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TableModeCommand parse(String args) throws ParseException {
        try {
            TableNumber tableNumber = ParserUtil.parseTableNumber(args);
            return new TableModeCommand(tableNumber);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TableModeCommand.MESSAGE_USAGE));
        }
    }
}
