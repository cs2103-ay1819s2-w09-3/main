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
import seedu.address.model.statistics.ReadOnlyStatistics;

/**
 * A class to access RestOrRant data stored as a json file on the hard disk.
 */
public class JsonStatisticsStorage implements StatisticsStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMenuStorage.class);

    private final Path filePath;
    private final Path backupFilePath;

    public JsonStatisticsStorage(Path filePath) {
        this.filePath = filePath;
        backupFilePath = Paths.get(filePath.toString() + ".backup");
    }

    public Path getStatisticsFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyStatistics> readStatistics() throws DataConversionException {
        return readStatistics(filePath);
    }

    /**
     * Similar to {@link #readStatistics()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyStatistics> readStatistics(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableStatistics> jsonStats = JsonUtil.readJsonFile(filePath,
                JsonSerializableStatistics.class);
        if (!jsonStats.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonStats.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveStatistics(ReadOnlyStatistics stats) throws IOException {
        saveStatistics(stats, filePath);
    }

    /**
     * Similar to {@link #saveStatistics(ReadOnlyStatistics)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveStatistics(ReadOnlyStatistics stats, Path filePath) throws IOException {
        requireNonNull(stats);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableStatistics(stats), filePath);
    }

    @Override
    public void backupStatistics(ReadOnlyStatistics stats) throws IOException {
        saveStatistics(stats, backupFilePath);
    }

}
