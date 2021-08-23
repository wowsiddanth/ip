package petal;

import petal.command.Command;
import petal.components.Parser;
import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;

/**
 * The class for the Petal bot. It is able to respond to
 * a certain number of pre-determined commands in order to add certain
 * activities and track them.
 *
 * @author Siddanth
 */
public class Petal {

    private boolean isBye = false;
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;
    private final TaskList taskList;

    /**
     * The constructor for the Petal class
     */
    public Petal() {
        ui = new Ui(this);
        taskList = new TaskList(ui);
        storage = new Storage(taskList, ui);
        parser = new Parser();
    }

    /**
     * Method to flip the boolean isBye and terminate the process
     */
    public void stop() {
        this.isBye = true;
    }

    /**
     * Method to start the bot
     */
    public void run() {
        storage.createDirectory();
        while (!isBye) {
            String message = ui.readCommand();
            Command command = parser.handleInput(message);
            command.execute(taskList, ui, storage);
        }
    }

    public static void main(String[] args) {
        Petal petal = new Petal();
        petal.run();
    }
}

