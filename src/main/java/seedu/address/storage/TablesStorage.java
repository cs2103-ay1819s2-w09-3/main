package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyRestOrRant;
import seedu.address.model.RestOrRant;
import seedu.address.model.table.ReadOnlyTables;

/**
 * s
 * Represents a storage for {@link RestOrRant}.
 */
public interface TablesStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTableFilePath();

    /**
     * Returns Tables data as a {@link ReadOnlyTables}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTables> readTables() throws DataConversionException, IOException;

    /**
     * @see #getTableFilePath()
     */
    Optional<ReadOnlyTables> readTables(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyRestOrRant} to the storage.
     *
     * @param tables cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTables(ReadOnlyTables tables) throws IOException;

    /**
     * @see #saveTables(ReadOnlyTables)
     */
    void saveTables(ReadOnlyTables tables, Path filePath) throws IOException;

    void backupTables(ReadOnlyTables tables) throws IOException;

}
