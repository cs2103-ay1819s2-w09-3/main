package seedu.address.model.menu;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.menu.exceptions.DuplicateMenuItemException;
import seedu.address.model.menu.exceptions.MenuItemNotFoundException;

/**
 * A list of menu items that enforces uniqueness between its elements and does not allow nulls.
 * A menu item is considered unique by comparing using {@code MenuItem#isSameMenuItem(MenuItem)}.
 * As such, adding and updating of menu items uses MenuItem#isSameMenuItem(MenuItem) for equality
 * so as to ensure that the menu item being added or updated is unique in terms of identity in the
 * UniqueMenuItemList. However, the removal of a menu item uses MenuItem#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see MenuItem#isSameMenuItem(MenuItem)
 */
public class UniqueMenuItemList {

    private final ObservableList<MenuItem> internalList = FXCollections.observableArrayList();
    private final ObservableList<MenuItem> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(MenuItem toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameMenuItem);
    }

    /**
     * Adds a menu item to the list.
     * The menu item must not already exist in the list.
     */
    public void add(MenuItem toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMenuItemException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the menu item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the list.
     * The menu item identity of {@code editedItem} must not be the same as another existing person in the list.
     */
    public void setMenuItem(MenuItem target, MenuItem editedItem) {
        requireAllNonNull(target, editedItem);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MenuItemNotFoundException();
        }

        if (!target.isSameMenuItem(editedItem) && contains(editedItem)) {
            throw new DuplicateMenuItemException();
        }

        internalList.set(index, editedItem);
    }

    /**
     * Removes the equivalent menu item from the list.
     * The menu item must exist in the list.
     */
    public void remove(MenuItem toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MenuItemNotFoundException();
        }
    }

    public void setMenuItems(UniqueMenuItemList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code items}.
     * {@code items} must not contain duplicate persons.
     */
    public void setMenuItems(List<MenuItem> items) {
        requireAllNonNull(items);
        if (!menuItemsAreUnique(items)) {
            throw new DuplicateMenuItemException();
        }

        internalList.setAll(items);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<MenuItem> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    //    @Override
    public Iterator<MenuItem> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueMenuItemList // instanceof handles nulls
                && internalList.equals(((UniqueMenuItemList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code items} contains only unique menu items.
     */
    private boolean menuItemsAreUnique(List<MenuItem> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).isSameMenuItem(items.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
