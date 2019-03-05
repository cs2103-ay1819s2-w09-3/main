package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.statistics.Bill;
import seedu.address.model.statistics.Statistics;

/**
 * Jackson-friendly version of {@link Statistics}.
 */
class JsonAdaptedBill {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Bill item's %s field is missing!";

    private final String tableNumber;
    private final String day;
    private final String month;
    private final String year;
    private final String totalBill;

    /**
     * Constructs a {@code JsonAdaptedBill} with the given bill details.
     */
    @JsonCreator
    public JsonAdaptedBill(@JsonProperty("tableNumber") String tableNumber, @JsonProperty("day") String day, @JsonProperty("month") String month, @JsonProperty("year") String year, @JsonProperty("totalBill") String totalBill) {
        this.tableNumber = tableNumber;
        this.day = day;
        this.month = month;
        this.year = year;
        this.totalBill = totalBill;
    }

    /**
     * Converts a given {@code Bill} into this class for Jackson use.
     */
    public JsonAdaptedBill(Bill source) {
        tableNumber = String.valueOf(source.getTableNumber());
        day = String.valueOf(source.getDay());
        month = String.valueOf(source.getMonth());
        year = String.valueOf(source.getYear());
        totalBill = String.valueOf(source.getTotalBill());
    }

    /**
     * Converts this Jackson-friendly adapted bill object into the model's {@code Bill} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order item.
     */
    public Bill toModelType() throws IllegalValueException {
        if (tableNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "tableNumber"));
        }
        // TODO: check if table number is valid
        //if (!Name.isValidName(name)) {
        //    throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        //}
        final int modelTableNumber = Integer.parseInt(tableNumber); // TODO: handle NumberFormatException

        if (day == null && month == null && year == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "date"));
        }
        // TODO: check if year, month and day legal
        //if (!Phone.isValidPhone(phone)) {
        //    throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        //}
        final int modelDay = Integer.parseInt(day); // TODO: handle NumberFormatException

        final int modelMonth = Integer.parseInt(month);
        final int modelYear = Integer.parseInt(year);
        final int modelBill = Integer.parseInt(totalBill); // TODO: change to MenuItem object

        return new Bill(modelTableNumber, modelDay, modelMonth, modelYear);
    }

}
