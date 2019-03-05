package seedu.address.model.menu;

import java.util.Optional;

import javafx.beans.Observable;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of Menu
 */
public interface ReadOnlyMenu extends Observable {

    /**
     * Returns an unmodifiable view of the menu items list.
     */
    ObservableList<MenuItem> getMenuItemList();

    Optional<MenuItem> getItemFromCode(Code code);

}
