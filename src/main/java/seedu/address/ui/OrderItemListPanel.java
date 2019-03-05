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
import seedu.address.model.order.OrderItem;

/**
 * Panel containing the list of menu items.
 */
public class OrderItemListPanel extends UiPart<Region> {
    private static final String FXML = "OrderItemListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(OrderItemListPanel.class);

    @FXML
    private ListView<OrderItem> orderItemListView;

    public OrderItemListPanel(ObservableList<OrderItem> orderItemList, ObservableValue<OrderItem> selectedOrderItem,
                              Consumer<OrderItem> onSelectedOrderItemChange) {
        super(FXML);
        orderItemListView.setItems(orderItemList);
        orderItemListView.setCellFactory(listView -> new OrderItemListViewCell());
        orderItemListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            logger.fine("Selection in list panel changed to : '" + newValue + "'");
            onSelectedOrderItemChange.accept(newValue);
        });
        selectedOrderItem.addListener(((observable, oldValue, newValue) -> {
            logger.fine("Selected item changed to: " + newValue);

            // Don't modify selection if we are already selecting the selected item,
            // otherwise we would have an infinite loop.
            if (Objects.equals(orderItemListView.getSelectionModel().getSelectedItem(), newValue)) {
                return;
            }

            if (newValue == null) {
                orderItemListView.getSelectionModel().clearSelection();
            } else {
                int index = orderItemListView.getItems().indexOf(newValue);
                orderItemListView.scrollTo(index);
                orderItemListView.getSelectionModel().clearAndSelect(index);
            }
        }));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Item} using a {@code ItemCard}.
     */
    class OrderItemListViewCell extends ListCell<OrderItem> {
        @Override
        protected void updateItem(OrderItem item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new OrderItemCard(item, getIndex() + 1).getRoot());
            }
        }
    }

}
