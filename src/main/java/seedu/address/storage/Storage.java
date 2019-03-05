package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.menu.ReadOnlyMenu;
import seedu.address.model.order.ReadOnlyOrders;
import seedu.address.model.table.ReadOnlyTables;

/**
 * API of the Storage component
 */
public interface Storage extends UserPrefsStorage, OrdersStorage, MenuStorage, TablesStorage, StatisticsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getMenuFilePath();

    @Override
    Path getOrdersFilePath();

    @Override
    Optional<ReadOnlyMenu> readMenu() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyOrders> readOrders() throws DataConversionException, IOException;

    @Override
    void saveMenu(ReadOnlyMenu menu) throws IOException;

    @Override
    void saveOrders(ReadOnlyOrders orders) throws IOException;

    @Override
    Path getTableFilePath();

    @Override
    Optional<ReadOnlyTables> readTables() throws DataConversionException, IOException;

    @Override
    void saveTables(ReadOnlyTables tables) throws IOException;

    @Override
    void backupTables(ReadOnlyTables tables) throws IOException;

}
