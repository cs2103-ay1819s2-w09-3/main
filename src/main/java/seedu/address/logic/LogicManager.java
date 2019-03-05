package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.RestOrRantParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyRestOrRant;
import seedu.address.model.menu.MenuItem;
import seedu.address.model.order.OrderItem;
import seedu.address.model.statistics.Bill;
import seedu.address.model.table.Table;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CommandHistory history;
    private final RestOrRantParser restOrRantParser;
    private boolean modeModified;
    private boolean menuModified;
    private boolean ordersModified;
    private boolean tablesModified;
    private boolean statisticsModified;
    private Mode mode;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        history = new CommandHistory();
        restOrRantParser = new RestOrRantParser();
        mode = Mode.RESTAURANT_MODE;

        // Set modeModified to true whenever the models' mode is modified.
        model.getRestOrRant().addListener(observable -> modeModified = true);
        // Set menuModified to true whenever the models' RestOrRant's menu is modified.
        model.getRestOrRant().getMenu().addListener(observable -> menuModified = true);
        // Set ordersModified to true whenever the models' RestOrRant's orders is modified.
        model.getRestOrRant().getOrders().addListener(observable -> ordersModified = true);
        // Set tablesModified to true whenever the models' RestOrRant's tables is modified.
        model.getRestOrRant().getTables().addListener(observable -> tablesModified = true);
        // Set billsModified to true whenever the models' RestOrRant's bills is modified.
        model.getRestOrRant().getStatistics().addListener(observable -> statisticsModified = true);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        modeModified = false;
        menuModified = false;
        ordersModified = false;
        tablesModified = false;
        statisticsModified = false;

        CommandResult commandResult;
        try {
            Command command = restOrRantParser.parseCommand(mode, commandText);
            commandResult = command.execute(mode, model, history);
        } finally {
            history.add(commandText);
        }

        if (modeModified) {
            logger.info("Application mode modified, changing UI");
            changeMode(commandResult.newModeStatus());
        }

        if (menuModified) {
            logger.info("Menu modified, saving to file.");
            try {
                storage.saveMenu(model.getRestOrRant().getMenu());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }

        if (ordersModified) {
            logger.info("Orders modified, saving to file.");
            try {
                storage.saveOrders(model.getRestOrRant().getOrders());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }

        if (tablesModified) {
            logger.info("Tables modified, saving to file.");
            try {
                storage.saveTables(model.getRestOrRant().getTables());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }

        if (statisticsModified) {
            logger.info("Statistics modified, saving to file.");
            try {
                storage.saveStatistics(model.getRestOrRant().getStatistics());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }
        return commandResult;
    }

    @Override
    public void changeMode(Mode mode) {
        this.mode = mode;
    } // TODO: add defensive check? mode may be null if mode listener is accidentally triggered

    @Override
    public ReadOnlyRestOrRant getRestOrRant() {
        return model.getRestOrRant();
    }

    @Override
    public ObservableList<MenuItem> getFilteredMenuItemList() {
        return model.getFilteredMenuItemList();
    }

    public ObservableList<OrderItem> getFilteredOrderItemList() {
        return model.getFilteredOrderItemList();
    }

    @Override
    public ObservableList<Table> getFilteredTableList() {
        return model.getFilteredTableList();
    }

    @Override
    public ObservableList<Bill> getFilteredBillList() {
        return model.getFilteredBillList();
    }

    @Override
    public ObservableList<String> getHistory() {
        return history.getHistory();
    }

    @Override
    public Path getMenuFilePath() {
        return model.getMenuFilePath();
    }

    public Path getOrdersFilePath() {
        return model.getOrdersFilePath();
    }

    public Path getTablesFilePath() {
        return model.getTablesFilePath();
    }

    public Path getStatisticsFilePath() {
        return model.getStatisticsFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public ReadOnlyProperty<MenuItem> selectedMenuItemProperty() {
        return model.selectedMenuItemProperty();
    }

    @Override
    public void setSelectedMenuItem(MenuItem item) {
        model.setSelectedMenuItem(item);
    }

    public ReadOnlyProperty<OrderItem> selectedOrderItemProperty() {
        return model.selectedOrderItemProperty();
    }

    @Override
    public void setSelectedOrderItem(OrderItem orderItem) {
        model.setSelectedOrderItem(orderItem);
    }

    @Override
    public ReadOnlyProperty<Table> selectedTableProperty() {
        return model.selectedTableProperty();
    }

    @Override
    public void setSelectedTable(Table table) {
        model.setSelectedTable(table);
    }

    @Override
    public ReadOnlyProperty<Bill> selectedBillProperty() {
        return model.selectedBillProperty();
    }

    @Override
    public void setSelectedBill(Bill bill) {
        model.setSelectedBill(bill);
    }

}
