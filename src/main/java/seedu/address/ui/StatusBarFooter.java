package seedu.address.ui;

import java.time.Clock;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    public static final String SYNC_STATUS_INITIAL = "Not updated yet in this session";
    public static final String SYNC_STATUS_UPDATED = "Last Updated: %s";
    private static final String FXML = "StatusBarFooter.fxml";
    /**
     * Used to generate time stamps.
     * <p>
     * TODO: change clock to an instance variable.
     * We leave it as a static variable because manual dependency injection
     * will require passing down the clock reference all the way from MainApp,
     * but it should be easier once we have factories/DI frameworks.
     */
    private static Clock clock = Clock.systemDefaultZone();
    @FXML
    private Label syncStatus;
    @FXML
    private Label currentMode;


    public StatusBarFooter(String mode) {
        super(FXML);
        currentMode.setText(mode);
        //restOrRant.addListener(observable -> updateSyncStatus());
        //syncStatus.setText(SYNC_STATUS_INITIAL);
        //saveLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());
    }

    public StatusBarFooter() {
        super(FXML);
    }

    /**
     * Returns the clock currently in use.
     */
    public static Clock getClock() {
        return clock;
    }

    /**
     * Sets the clock used to determine the current time.
     */
    public static void setClock(Clock clock) {
        StatusBarFooter.clock = clock;
    }

    /**
     * Updates the current mode display to the specified mode.
     */
    public void updateMode(String newMode) {
        currentMode.setText(newMode);
    }

    /**
     * Updates "last updated" status to the current time.
     */
    private void updateSyncStatus() {
        long now = clock.millis();
        String lastUpdated = new Date(now).toString();
        syncStatus.setText(String.format(SYNC_STATUS_UPDATED, lastUpdated));
    }

}
