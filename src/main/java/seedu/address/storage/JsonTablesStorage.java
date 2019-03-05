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
import seedu.address.model.table.ReadOnlyTables;

/**
 * A class to access RestOrRant data stored as a json file on the hard disk.
 */
public class JsonTablesStorage implements TablesStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMenuStorage.class);

    private final Path filePath;
    private final Path backupFilePath;

    public JsonTablesStorage(Path filePath) {
        this.filePath = filePath;
        backupFilePath = Paths.get(filePath.toString() + ".backup");
    }

    public Path getTableFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTables> readTables() throws DataConversionException {
        return readTables(filePath);
    }

    /**
     * Similar to {@link #readTables()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTables> readTables(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTables> jsonTables = JsonUtil.readJsonFile(filePath, JsonSerializableTables.class);
        if (!jsonTables.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTables.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTables(ReadOnlyTables tables) throws IOException {
        saveTables(tables, filePath);
    }


    /**
     * Similar to {@link #saveTables(ReadOnlyTables)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTables(ReadOnlyTables tables, Path filePath) throws IOException {
        requireNonNull(tables);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTables(tables), filePath);
    }

    @Override
    public void backupTables(ReadOnlyTables tables) throws IOException {
        saveTables(tables, backupFilePath);
    }

}
