package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TABLENUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.stream.Stream;

import seedu.address.logic.commands.BillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.statistics.Day;
import seedu.address.model.statistics.Month;
import seedu.address.model.statistics.Year;
import seedu.address.model.table.TableNumber;

/**
 * Parses input arguments and creates a new BillCommand object
 */
public class BillCommandParser implements Parser<BillCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the BillCommand
     * and returns a BillCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public BillCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TABLENUMBER, PREFIX_DAY, PREFIX_MONTH, PREFIX_YEAR);

        if (!arePrefixesPresent(argMultimap, PREFIX_TABLENUMBER, PREFIX_DAY, PREFIX_MONTH, PREFIX_YEAR) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BillCommand.MESSAGE_USAGE));
        }

        TableNumber tableNumber = ParserUtil.parseTableNumber(argMultimap.getValue(PREFIX_TABLENUMBER).get());
        Day day = ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get());
        Month month = ParserUtil.parseMonth(argMultimap.getValue(PREFIX_MONTH).get());
        Year year = ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get());

        return new BillCommand(tableNumber, day, month, year);
    }

}
