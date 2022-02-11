package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

public class InvalidCommand extends Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = Messages.UNKNOWN_COMMAND;
        output = Ui.append(output, ui.showCommands());
        output += Messages.TRY_HELP_MSG;
        return output;
    }
}
