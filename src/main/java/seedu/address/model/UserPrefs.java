package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path menuFilePath = Paths.get("data", "menu.json");
    private Path ordersFilePath = Paths.get("data", "orders.json");
    private Path tablesFilePath = Paths.get("data", "tables.json");
    private Path statisticsFilePath = Paths.get("data", "statistics.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
    }

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setMenuFilePath(newUserPrefs.getMenuFilePath());
        setOrdersFilePath(newUserPrefs.getOrdersFilePath());
        setTablesFilePath(newUserPrefs.getTablesFilePath());
        setStatisticsFilePath(newUserPrefs.getStatisticsFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getMenuFilePath() {
        return menuFilePath;
    }

    public void setMenuFilePath(Path menuFilePath) {
        requireNonNull(menuFilePath);
        this.menuFilePath = menuFilePath;
    }

    public Path getOrdersFilePath() {
        return ordersFilePath;
    }

    public void setOrdersFilePath(Path ordersFilePath) {
        requireNonNull(ordersFilePath);
        this.ordersFilePath = ordersFilePath;
    }

    public Path getTablesFilePath() {
        return tablesFilePath;
    }

    public void setTablesFilePath(Path tablesFilePath) {
        requireNonNull(tablesFilePath);
        this.tablesFilePath = tablesFilePath;
    }

    public Path getStatisticsFilePath() {
        return statisticsFilePath;
    }

    public void setStatisticsFilePath(Path statsFilePath) {
        requireNonNull(statsFilePath);
        this.statisticsFilePath = statsFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings) && menuFilePath.equals(o.menuFilePath) && ordersFilePath.equals(o.ordersFilePath) && tablesFilePath.equals(o.tablesFilePath) && statisticsFilePath.equals(o.statisticsFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, menuFilePath, ordersFilePath, tablesFilePath, statisticsFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location for menu : " + menuFilePath);
        sb.append("\nLocal data file location for orders: " + ordersFilePath);
        sb.append("\nLocal data file location for tables: " + tablesFilePath);
        sb.append("\nLocal data file lovation for statistics: " + statisticsFilePath);
        return sb.toString();
    }

}
