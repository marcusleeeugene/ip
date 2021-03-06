package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task for event
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for event
     *
     * @param description Task name
     * @param at Start at a specific time and ends at a specific time
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Content to write into storage file
     *
     * @return String format for task information
     */
    @Override
    public String writeContentFormat() {
        return String.format("E | %s | %s", super.writeContentFormat(), at);
    }

    /**
     * Returns task type, status icon, task name and specific start and end time
     *
     * @return String format specific to event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
