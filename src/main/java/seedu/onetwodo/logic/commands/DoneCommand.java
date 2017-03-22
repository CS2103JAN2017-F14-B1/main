package seedu.onetwodo.logic.commands;

import javafx.collections.transformation.FilteredList;
import seedu.onetwodo.commons.core.Messages;
import seedu.onetwodo.commons.core.UnmodifiableObservableList;
import seedu.onetwodo.commons.exceptions.IllegalValueException;
import seedu.onetwodo.logic.commands.exceptions.CommandException;
import seedu.onetwodo.model.task.ReadOnlyTask;
import seedu.onetwodo.model.task.TaskType;
import seedu.onetwodo.model.task.UniqueTaskList.TaskNotFoundException;

//@@author A0135739W
/**
 * Marks the task identified by the index number as completed.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number as completed.\n"
            + "Parameters: PREFIX_INDEX (must be a prefix positive integer)\n"
            + "Example: " + COMMAND_WORD + " e1";

    public static final String MESSAGE_DONE_TASK_SUCCESS = "Completed Task: %1$s";

    public final TaskType taskType;
    public final int targetIndex;

    public DoneCommand(char taskType, int targetIndex) {
        this.taskType = TaskType.getTaskTypeFromChar(taskType);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute() throws CommandException {
        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredTaskList();
        FilteredList<ReadOnlyTask> filteredByTaskType = lastShownList.filtered(t -> t.getTaskType() == taskType);
        FilteredList<ReadOnlyTask> filteredByDoneStatus = filterTasksByDoneStatus(filteredByTaskType);

        if (filteredByDoneStatus.size() < targetIndex || taskType == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask taskToComplete = filteredByDoneStatus.get(targetIndex - 1);
        int internalIndex = lastShownList.indexOf(taskToComplete);

        try {
            model.doneTask(internalIndex);
        } catch (TaskNotFoundException tnfe) {
            assert false : "The target task cannot be missing";
        } catch (IllegalValueException ive) {
            throw new CommandException(ive.getMessage());
        }

        return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS, taskToComplete));
    }
}
