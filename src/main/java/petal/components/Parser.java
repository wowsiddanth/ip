package petal.components;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import petal.command.ArchiveCommand;
import petal.command.ByeCommand;
import petal.command.Command;
import petal.command.DateCommand;
import petal.command.DeleteCommand;
import petal.command.DoneCommand;
import petal.command.DossiersCommand;
import petal.command.FindCommand;
import petal.command.ListCommand;
import petal.command.TaskCommand;
import petal.command.UnintelligibleCommand;

/**
 * The Parser deals with comprehending the user's input
 */
public class Parser {

    /**
     * Comprehends the user input.
     * It splits the input and takes the first word (assumed to be command if format followed)
     * and reacts accordingly.
     *
     * @param message User input
     */
    public Command handleInput(String message) {
        //So blank inputs can be handled
        message += " ";
        String command = message.substring(0, message.indexOf(" "));
        String formatted = message.substring(message.indexOf(' ') + 1).trim();
        switch (command) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        case "dossiers":
            return new DossiersCommand();
        case "done":
            return new DoneCommand(formatted);
        case "delete":
            return new DeleteCommand(formatted);
        case "todo":
        case "deadline":
        case "event":
            //Fallthrough
            return new TaskCommand(command, formatted);
        case "date":
            return new DateCommand(formatted);
        case "find":
            return new FindCommand(formatted);
        case "archive":
            return new ArchiveCommand(formatted);
        default:
            return new UnintelligibleCommand();
        }
    }

    /**
     * Parses a given string (if given correctly), creating a LocalTime object
     * It is static as it is available to any object that wants to parse time.
     *
     * @param time The given String
     * @return LocalTime object
     * @throws DateTimeParseException Thrown if string cannot be represented as a time object
     */
    public static LocalTime parseTime(String time) throws DateTimeParseException {
        String formattedTime = time.indexOf(":") > 0
                ? time
               : time.substring(0, 2) + ":" + time.substring(2);
        return LocalTime.parse(formattedTime);
    }

    /**
     * Parses a given string (if given correctly), creating a LocalDate
     * It is static as it is available to any object that wants to parse time.
     *
     * @param date The given String
     * @return LocalDate Object
     * @throws DateTimeParseException Thrown if string cannot be represented as a Date object
     */
    public static LocalDate parseDate(String date) {
        String[] dayMonthYear = date.split("/");
        //This is done if the user inputs the day as 1 digit, rather than 2
        if (dayMonthYear[0].length() == 1) {
            dayMonthYear[0] = "0" + dayMonthYear[0];
        }
        LocalDate parsedDate = LocalDate.parse(dayMonthYear[2] + "-" + dayMonthYear[1] + "-" + dayMonthYear[0]);
        return parsedDate;
    }

}
