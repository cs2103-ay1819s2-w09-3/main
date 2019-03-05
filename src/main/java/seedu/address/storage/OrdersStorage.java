package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.order.Orders;
import seedu.address.model.order.ReadOnlyOrders;

/**
 * Represents a storage for RestOrRant's {@link Orders}.
 */
public interface OrdersStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getOrdersFilePath();

    /**
     * Returns order data as part of a {@link ReadOnlyOrders}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyOrders> readOrders() throws DataConversionException, IOException;

    /**
     * @see #getOrdersFilePath()
     */
    Optional<ReadOnlyOrders> readOrders(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyOrders} to the storage.
     *
     * @param orders cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveOrders(ReadOnlyOrders orders) throws IOException;

    /**
     * @see #saveOrders(ReadOnlyOrders)
     */
    void saveOrders(ReadOnlyOrders orders, Path filePath) throws IOException;

    void backupOrders(ReadOnlyOrders orders) throws IOException;

}
