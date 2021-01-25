package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;
import duke.Storage;
import duke.task.*;
import duke.Ui;
import duke.Utility;

import java.time.LocalDate;

/**
 * Handles adding of ToDo, Event and Deadline tasks
 */
public class AddCommand extends Command {

    /**
     * Constructor for AddCommand, for ToDo task
     *
     * @param command ToDo command
     * @param description Task name of ToDo
     */
    public AddCommand(String command, String description) {
        super.command = command;
        super.description = description;
        super.date = "";
    }

    /**
     * Overloaded constructor for AddCommand, for Event and Deadline tasks
     *
     * @param command Event or Deadline command
     * @param description Task name of Event or Deadline
     */
    public AddCommand(String command, String description, String date) {
        super.command = command;
        super.description = description;
        super.date = date;
    }

    private void todoProcess(String taskName, TaskList tasks) {
        Task task = new ToDo(taskName);
        tasks.add(task);
        output = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks(tasks);
    }

    private void eventProcess(String description, String date, TaskList tasks) throws DukeException {
        if (!Utility.isValidDate(date)) {
            throw new DukeException(DukeExceptionType.INVALID_DATE_FORMAT);
        }
        Task task = new Event(description, LocalDate.parse(date));
        tasks.add(task);
        output = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks(tasks);
    }

    private void deadlineProcess(String description, String date, TaskList tasks) throws DukeException {
        if (!Utility.isValidDate(date)) {
            throw new DukeException(DukeExceptionType.INVALID_DATE_FORMAT);
        }
        Task task = new Deadline(description, LocalDate.parse(date));
        tasks.add(task);
        output = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks(tasks);
    }

    private String getRemainingTasks(TaskList tasks) {
        return "\n\tNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Adds task to TaskList, saves to storage file and outputs response to terminal
     *
     * @param tasks TaskList
     * @param ui Ui instance
     * @param storage Storage instance
     * @throws DukeException If invalid date given for event or deadline task
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (command) {
        case "todo":
            todoProcess(description, tasks);
            break;
        case "event":
            eventProcess(description, date, tasks);
            break;
        case "deadline":
            deadlineProcess(description, date, tasks);
            break;
        }
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
