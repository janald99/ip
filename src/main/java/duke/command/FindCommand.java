package duke.command;

import duke.exception.InvalidArgumentException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

/**
 * A type of command that searches for task according to user input keyword.
 */
public class FindCommand extends Command {

    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Searches for the task that user wants to find from a given keyword and prints it.
     *
     * @param tasks TaskList that command is executed on.
     * @param ui User interface that interacts with the user.
     * @param storage Storage that saves and loads tasks after Command is executed.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        try {
            String keyword = Parser.parseDescription(input);
            output = Ui.append(output, Messages.FINDING_MSG);
            int matchCount = 0;
            for (int i = 0; i < tasks.getSize(); i++) {
                if (tasks.get(i).getTaskData().contains(keyword)) {
                    matchCount++;
                    output = Ui.append(output, matchCount + "." + tasks.getTaskStatement(i));
                }
            }
            if (matchCount == 0) {
                output = ui.showToUser(Messages.NO_FIND_MATCH_MSG);
            } else {
                output = Ui.append(output, ui.showToUser(Messages.getMatchCountMsg(matchCount)));
            }
            //use string.contains(string) method to search
        } catch (InvalidArgumentException e) {
            output = ui.showError(e.getMessage());
        }

        return output;
    }
}
