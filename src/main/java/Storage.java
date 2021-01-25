import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final String dirPath;

    public Storage(String filePath) {
        this.filePath = filePath;
        dirPath = filePath.replace("/duke.txt", "");
    }

    public List<Task> load() throws DukeException {
        try {
            List<Task> taskList = new ArrayList<>();
            File file = new File(filePath);
            File dir = new File(dirPath);
            handleNonExistentFiles(file, dir);
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String[] taskInfo = sc.nextLine().split(" \\| ");
                Task task = new Task("");
                switch(taskInfo[0]) {
                case "T":
                    // ToDo task
                    task = new ToDo(taskInfo[2]);
                    break;
                case "D":
                    // Deadline task
                    task = new Deadline(taskInfo[2], LocalDate.parse(taskInfo[3]));
                    break;
                case "E":
                    // Event task
                    task = new Event(taskInfo[2], LocalDate.parse(taskInfo[3]));
                    break;
                default:
                    break;
                }

                // Previously marked as done
                if (Integer.parseInt(taskInfo[1]) == 1) {
                    task.markAsDone();
                }
                taskList.add(task);
            }
            return taskList;
        } catch (IOException ex) {
            throw new DukeException(DukeExceptionType.LOAD_ERROR);
        }
    }

    public void save(TaskList tasks) throws DukeException {
        File file = new File(filePath);
        File dir = new File(dirPath);
        handleNonExistentFiles(file, dir);

        try {
            FileWriter fileWriter = new FileWriter(file, false);
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i);
                fileWriter.write(task.writeContentFormat() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException ex) {
            throw new DukeException(DukeExceptionType.SAVE_ERROR);
        }
    }

    private void handleNonExistentFiles(File file, File dir) throws DukeException {
        try {
            if (!Files.isDirectory(Paths.get(dirPath))) {
                // Create data folder and duke.txt if do not exist
                dir.mkdir();
                file.createNewFile();
            } else if (!file.exists()) {
                // Create duke.txt if do not exist
                file.createNewFile();
            }
        } catch (IOException ex) {
            throw new DukeException(DukeExceptionType.FILE_CREATION_ERROR);
        }
    }

}
