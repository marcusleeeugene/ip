package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

/**
 * Handles deletion of task
 */
public class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand
     *
     * @param command Delete command
     * @param selection Selected task
     */
    public DeleteCommand(String command, String selection) {
        this.command = command;
        this.description = selection;
        this.date = "";
    }

    private void deleteProcess(String selection, TaskList tasks) {
        int taskNum = Integer.parseInt(selection);
        Task task = tasks.get(taskNum);
        tasks.remove(task);
        output = "Noted. I've removed this task: \n\t  "
                + task.toString() + getRemainingTasks(tasks);
    }

    private String getRemainingTasks(TaskList tasks) {
        return "\n\tNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Deletes task from TaskList, saves to storage file and outputs response to terminal
     *
     * @param tasks TaskList
     * @param ui Ui instance
     * @param storage Storage instance
     * @throws DukeException If invalid selection given
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (Integer.parseInt(description) > tasks.size() || Integer.parseInt(description) <= 0) {
            // Selection out of taskList range
            throw new DukeException(command, DukeExceptionType.SELECTION_EXCEED_RANGE);
        }
        deleteProcess(description, tasks);
        storage.save(tasks);
        ui.response(output);
    }

    /**
     * Determines if Exit is called by user
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
