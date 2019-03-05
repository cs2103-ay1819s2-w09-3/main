package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddItemToMenuCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.menu.Code;
import seedu.address.model.menu.MenuItem;
import seedu.address.model.menu.Name;
import seedu.address.model.menu.Price;

/**
 * Parses input arguments and creates a new AddItemToMenuCommand object
 */
public class AddItemToMenuCommandParser implements Parser<AddItemToMenuCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddItemToMenuCommand
     * and returns an AddItemToMenuCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddItemToMenuCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CODE, PREFIX_NAME, PREFIX_PRICE);

        if (!arePrefixesPresent(argMultimap, PREFIX_CODE, PREFIX_NAME, PREFIX_PRICE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemToMenuCommand.MESSAGE_USAGE));
        }

        Code code = ParserUtil.parseCode(argMultimap.getValue(PREFIX_CODE).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Price price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());

        MenuItem menuItem = new MenuItem(name, code, price);

        return new AddItemToMenuCommand(menuItem);
    }

}
