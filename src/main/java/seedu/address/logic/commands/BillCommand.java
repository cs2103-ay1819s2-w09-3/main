package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TABLENUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.Mode;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.statistics.Bill;
import seedu.address.model.statistics.Day;
import seedu.address.model.statistics.Month;
import seedu.address.model.statistics.Year;
import seedu.address.model.table.TableNumber;


/**
 * Retrieves the Bill for a Table.
 */
public class BillCommand extends Command {

    public static final String COMMAND_WORD = "bill";
    public static final String COMMAND_ALIAS = "b";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Retrieves the Bill for a Table. " + "Parameters: " + PREFIX_TABLENUMBER + "TABLE NUMBER" +
                    " " + PREFIX_DAY + "DAY " + PREFIX_MONTH + "MONTH " + PREFIX_YEAR + "YEAR " + "Example: " + COMMAND_WORD + " " + PREFIX_TABLENUMBER + "1 " + PREFIX_DAY + "30 " + PREFIX_MONTH + "12 " + PREFIX_YEAR + "2019 ";

    public static final String MESSAGE_SUCCESS = "Bill Calculated: %1$s";
    public static final String MESSAGE_TABLE_DOES_NOT_EXIST = "This table does not exist.";

    //private final Table toBill;
    private Bill bill;

    /**
     * Creates a BillCommand to find the total bill of the specified {@code Table}
     */
    public BillCommand(TableNumber tableNumber, Day day, Month month, Year year) {
        requireNonNull(tableNumber);
        requireNonNull(day);
        requireNonNull(month);
        requireNonNull(year);
        // toBill = Table.getTableFromNumber(tableNumber);
    }

    @Override
    public CommandResult execute(Mode mode, Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        //if (!model.hasTable(toBill)) {
        //    throw new CommandException(MESSAGE_TABLE_DOES_NOT_EXIST);
        //}

        //bill = calculateBill(toBill);
        model.updateRestOrRant();
        return new CommandResult(String.format(MESSAGE_SUCCESS, bill));
    }
    /**
     Bill calculateBill(Table table) {
     Bill bill = new Bill(table.getTableNumber(), );
     return 
     }
     **/

}
