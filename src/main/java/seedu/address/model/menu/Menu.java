package seedu.address.model.menu;


import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.InvalidationListenerManager;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Menu implements ReadOnlyMenu {

    private final UniqueMenuItemList menuItems;
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */ {
        menuItems = new UniqueMenuItemList();
    }

    public Menu() {
    }

    /**
     * Creates an RestOrRant using the Persons in the {@code toBeCopied}
     */
    public Menu(ReadOnlyMenu toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the menu list with {@code menuItems}.
     * {@code menuItems} must not contain duplicate persons.
     */
    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems.setMenuItems(menuItems);
        indicateModified();
    }
    //    public void setPersons(List<Person> persons) {
    //        this.persons.setPersons(persons);
    //        indicateModified();
    //    }

    /**
     * Resets the existing data of this {@code RestOrRant} with {@code newData}.
     */
    public void resetData(ReadOnlyMenu newData) {
        requireNonNull(newData);

        setMenuItems(newData.getMenuItemList());
    }

    //// person-level operations

    /**
     * Returns true if a menu item with the same identity as {@code menuItem} exists in the address book.
     */
    public boolean hasMenuItem(MenuItem menuItem) {
        requireNonNull(menuItem);
        return menuItems.contains(menuItem);
    }

    /**
     * Adds a menu item to the menu.
     * The menu item must not already exist in the address book.
     */
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
        indicateModified();
    }

    /**
     * Replaces the given menu item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the address book.
     * The item identity of {@code editedItem} must not be the same as another existing menu item in the address book.
     */
    public void setMenuItem(MenuItem target, MenuItem editedItem) {
        requireNonNull(editedItem);

        menuItems.setMenuItem(target, editedItem);
    }

    /**
     * Removes {@code key} from this {@code RestOrRant}.
     * {@code key} must exist in the menu.
     */
    public void removeMenuItem(MenuItem key) {
        menuItems.remove(key);
        indicateModified();
    }

    /**
     * Given the menu item's {@code Code code}, returns the MenuItem with the corresponding code.
     */
    @Override
    public Optional<MenuItem> getItemFromCode(Code code) {
        Iterator<MenuItem> iterator = menuItems.iterator();
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            if (menuItem.getCode().equals(code)) {
                return Optional.of(menuItem);
            }
        }
        return Optional.empty();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the restOrRant has been modified.
     */
    protected void indicateModified() {
        invalidationListenerManager.callListeners(this);
    }

    //// util methods

    @Override
    public String toString() {
        return menuItems.asUnmodifiableObservableList().size() + " menu items";
        // TODO: refine later
    }

    @Override
    public ObservableList<MenuItem> getMenuItemList() {
        return menuItems.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Menu) // instanceof handles nulls
                && menuItems.equals(((Menu) other).menuItems);
    }

    @Override
    public int hashCode() {
        return menuItems.hashCode();
    }

}
