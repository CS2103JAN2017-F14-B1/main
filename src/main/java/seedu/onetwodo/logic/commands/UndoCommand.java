package seedu.onetwodo.logic.commands;

import seedu.onetwodo.commons.exceptions.EmptyHistoryException;
import seedu.onetwodo.logic.commands.exceptions.CommandException;

//@@author A0135739W
/**
 * Undo the most recent command that modifies the toDoList.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undo the most recent command that modifies any of the 3 lists.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() throws CommandException {
        try {
            String previousCounterCommand = model.undo();
            return new CommandResult(COMMAND_WORD + " successfully.\n" + previousCounterCommand);
        } catch (EmptyHistoryException ehe) {
            throw new CommandException(ehe.getMessage());
        }
    }
}