package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.menu.Menu;
import seedu.address.model.menu.MenuItem;
import seedu.address.model.menu.ReadOnlyMenu;

/**
 * An Immutable RestOrRant that is serializable to JSON format.
 */
@JsonRootName(value = "menuItems")
class JsonSerializableMenu {

    public static final String MESSAGE_DUPLICATE_ITEM = "Menu list contains duplicate items(s).";
    private final List<JsonAdaptedMenu> menuItems = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMenu} with the given persons.
     */
    @JsonCreator
    public JsonSerializableMenu(@JsonProperty("menuItems") List<JsonAdaptedMenu> menuItems) {
        this.menuItems.addAll(menuItems);
    }

    /**
     * Converts a given {@code ReadOnlyRestOrRant} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMenu}.
     */
    public JsonSerializableMenu(ReadOnlyMenu source) {
        menuItems.addAll(source.getMenuItemList().stream().map(JsonAdaptedMenu::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code RestOrRant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Menu toModelType() throws IllegalValueException {
        Menu menu = new Menu();
        for (JsonAdaptedMenu jsonAdaptedMenu : menuItems) {
            MenuItem item = jsonAdaptedMenu.toModelType();
            if (menu.hasMenuItem(item)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ITEM);
            }
            menu.addMenuItem(item);
        }
        return menu;
    }

}
