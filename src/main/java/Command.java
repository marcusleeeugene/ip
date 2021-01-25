public abstract class Command {
    protected String command = "";
    protected String description = "";
    protected String date = "";
    protected String output = "";

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
