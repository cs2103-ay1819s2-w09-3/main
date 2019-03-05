package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.menu.Code;
import seedu.address.model.menu.MenuItem;
import seedu.address.model.menu.Name;
import seedu.address.model.menu.Price;

/**
 * Jackson-friendly version of {@link MenuItem}.
 */
class JsonAdaptedMenu {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Menu Item's %s field is missing!";

    private final String name;
    private final String code;
    private final String price;

    /**
     * Constructs a {@code JsonAdaptedMenu} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedMenu(@JsonProperty("name") String name, @JsonProperty("code") String code, @JsonProperty("price") String price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    /**
     * Converts a given {@code MenuItem} into this class for Jackson use.
     */
    public JsonAdaptedMenu(MenuItem item) {
        name = item.getName().itemName;
        code = item.getCode().itemCode;
        price = item.getPrice().itemPrice;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public MenuItem toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (code == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Code.class.getSimpleName()));
        }
        if (!Code.isValidCode(code)) {
            throw new IllegalValueException(Code.MESSAGE_CONSTRAINTS);
        }
        final Code modelCode = new Code(code);

        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelPrice = new Price(price);

        return new MenuItem(modelName, modelCode, modelPrice);
    }

}
