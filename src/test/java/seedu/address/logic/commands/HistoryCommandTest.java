package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.Mode;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HistoryCommandTest {
    private CommandHistory history = new CommandHistory();
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute() {
        assertCommandSuccess(Mode.RESTAURANT_MODE, new HistoryCommand(), model, history,
                HistoryCommand.MESSAGE_NO_HISTORY, expectedModel);
        assertCommandSuccess(Mode.TABLE_MODE, new HistoryCommand(), model, history,
                HistoryCommand.MESSAGE_NO_HISTORY, expectedModel);
        assertCommandSuccess(Mode.MENU_MODE, new HistoryCommand(), model, history,
                HistoryCommand.MESSAGE_NO_HISTORY, expectedModel);

        String command1 = "clear";
        history.add(command1);
        assertCommandSuccess(Mode.RESTAURANT_MODE, new HistoryCommand(), model, history,
                String.format(HistoryCommand.MESSAGE_SUCCESS, command1), expectedModel);
        assertCommandSuccess(Mode.TABLE_MODE, new HistoryCommand(), model, history,
                String.format(HistoryCommand.MESSAGE_SUCCESS, command1), expectedModel);
        assertCommandSuccess(Mode.MENU_MODE, new HistoryCommand(), model, history,
                String.format(HistoryCommand.MESSAGE_SUCCESS, command1), expectedModel);

        String command2 = "randomCommand";
        String command3 = "select 1";
        history.add(command2);
        history.add(command3);

        String expectedMessage = String.format(HistoryCommand.MESSAGE_SUCCESS,
                String.join("\n", command3, command2, command1));
        assertCommandSuccess(Mode.RESTAURANT_MODE, new HistoryCommand(), model, history, expectedMessage,
                expectedModel);
        assertCommandSuccess(Mode.TABLE_MODE, new HistoryCommand(), model, history, expectedMessage,
                expectedModel);
        assertCommandSuccess(Mode.MENU_MODE, new HistoryCommand(), model, history, expectedMessage,
                expectedModel);
    }

}
