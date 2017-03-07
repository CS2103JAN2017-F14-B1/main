package seedu.onetwodo.logic;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.onetwodo.commons.core.ComponentManager;
import seedu.onetwodo.commons.core.LogsCenter;
import seedu.onetwodo.logic.commands.Command;
import seedu.onetwodo.logic.commands.CommandResult;
import seedu.onetwodo.logic.commands.exceptions.CommandException;
import seedu.onetwodo.logic.parser.Parser;
import seedu.onetwodo.model.Model;
import seedu.onetwodo.model.person.ReadOnlyTask;
import seedu.onetwodo.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager extends ComponentManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Parser parser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.parser = new Parser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        Command command = parser.parseCommand(commandText);
        command.setData(model);
        return command.execute();
    }

    @Override
    public ObservableList<ReadOnlyTask> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }
}