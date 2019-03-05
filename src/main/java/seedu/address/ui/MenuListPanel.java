package seedu.address.ui;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.menu.MenuItem;

/**
 * Panel containing the list of menu items.
 */
public class MenuListPanel extends UiPart<Region> {
    private static final String FXML = "MenuListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MenuListPanel.class);

    @FXML
    private ListView<MenuItem> menuItemListView;

    public MenuListPanel(ObservableList<MenuItem> menuItemList, ObservableValue<MenuItem> selectedMenuItem,
                         Consumer<MenuItem> onSelectedMenuItemChange) {
        super(FXML);
        menuItemListView.setItems(menuItemList);
        menuItemListView.setCellFactory(listView -> new MenuItemListViewCell());
        menuItemListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            logger.fine("Selection in list panel changed to : '" + newValue + "'");
            onSelectedMenuItemChange.accept(newValue);
        });
        selectedMenuItem.addListener(((observable, oldValue, newValue) -> {
            logger.fine("Selected item changed to: " + newValue);

            // Don't modify selection if we are already selecting the selected item,
            // otherwise we would have an infinite loop.
            if (Objects.equals(menuItemListView.getSelectionModel().getSelectedItem(), newValue)) {
                return;
            }

            if (newValue == null) {
                menuItemListView.getSelectionModel().clearSelection();
            } else {
                int index = menuItemListView.getItems().indexOf(newValue);
                menuItemListView.scrollTo(index);
                menuItemListView.getSelectionModel().clearAndSelect(index);
            }
        }));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Item} using a {@code ItemCard}.
     */
    class MenuItemListViewCell extends ListCell<MenuItem> {
        @Override
        protected void updateItem(MenuItem item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MenuItemCard(item, getIndex() + 1).getRoot());
            }
        }
    }

}
