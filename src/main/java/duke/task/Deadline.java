package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task for deadline
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for deadline
     *
     * @param description Task name
     * @param by Specific date/time which task is to be done by
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Content to write into storage file
     *
     * @return String format for task information
     */
    @Override
    public String writeContentFormat() {
        return String.format("D | %s | %s", super.writeContentFormat(), by);
    }

    /**
     * Returns task type, status icon, task name and due date
     *
     * @return String format specific to deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
