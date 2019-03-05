package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.order.ReadOnlyOrders;

/**
 * A class to access order data stored as a json file on the hard disk.
 */
public class JsonOrdersStorage implements OrdersStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonOrdersStorage.class);

    private final Path filePath;
    private final Path backupFilePath;

    public JsonOrdersStorage(Path filePath) {
        this.filePath = filePath;
        backupFilePath = Paths.get(filePath.toString() + ".backup");
    }

    public Path getOrdersFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyOrders> readOrders() throws DataConversionException {
        return readOrders(filePath);
    }

    /**
     * Similar to {@link #readOrders()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyOrders> readOrders(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableOrders> jsonOrders = JsonUtil.readJsonFile(filePath, JsonSerializableOrders.class);
        if (!jsonOrders.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonOrders.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveOrders(ReadOnlyOrders orders) throws IOException {
        saveOrders(orders, filePath);
    }

    /**
     * Similar to {@link #saveOrders(ReadOnlyOrders)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveOrders(ReadOnlyOrders orders, Path filePath) throws IOException {
        requireNonNull(orders);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableOrders(orders), filePath);
    }

    @Override
    public void backupOrders(ReadOnlyOrders orders) throws IOException {
        saveOrders(orders, backupFilePath);
    }

}
