package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.net.URL;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.menu.MenuItem;
import seedu.address.model.statistics.Bill;

/**
 * The Browser Panel for the stats.
 */

public class StatsBrowserPanel extends UiPart<Region> {

    public static final URL DEFAULT_PAGE =
            requireNonNull(MainApp.class.getResource(FXML_FILE_FOLDER + "default.html"));
    public static final String SEARCH_PAGE_URL = "https://se-education.org/dummy-search-page/?name=";

    private static final String FXML = "BrowserPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @javafx.fxml.FXML
    private WebView browser;

    // TODO: constructors for different modes
    public StatsBrowserPanel(ObservableValue<Bill> selectedBill) {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        // Load person page when selected person changes.
        selectedBill.addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                loadDefaultPage();
                return;
            }
            loadBillPage(newValue);
        });

        loadDefaultPage();
    }

    public StatsBrowserPanel() {
        super(FXML);
        loadDefaultPage();
    }

    // TODO: methods for different modes
    private void loadBillPage(Bill bill) {
        loadPage(SEARCH_PAGE_URL + bill.getTableNumber() + bill.getTotalBill());
    }

    public void loadPage(String url) {
        Platform.runLater(() -> browser.getEngine().load(url));
    }

    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    private void loadDefaultPage() {
        loadPage(DEFAULT_PAGE.toExternalForm());
    }
}
