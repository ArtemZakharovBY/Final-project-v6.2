package by.epam.corporate_education.controller.command.factory;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.impl.*;
import by.epam.corporate_education.controller.command.impl.ChangeLocaleCommand;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Log4j
public class CommandFactory {
    @Getter
    private static final CommandFactory INSTANCE = new CommandFactory();
    private final Map<String, Command> operations = new HashMap<>();

    private CommandFactory(){
        operations.put(CommandName.COMMAND_SIGN_IN, new SignInCommand());
        operations.put(CommandName.COMMAND_SIGN_UP, new SignUpCommand());
        operations.put(CommandName.COMMAND_SIGN_OUT, new SignOutCommand());
        operations.put(CommandName.COMMAND_VIEW_ALL_TRAININGS, new ViewAllTrainingsCommand());
        operations.put(CommandName.COMMAND_FORWARD_TO_MAIN, new ForwardToMainPageCommand());
        operations.put(CommandName.COMMAND_VIEW_ALL_USERS, new ViewAllUsersCommand());
        operations.put(CommandName.COMMAND_VIEW_USER, new ViewUserCommand());
        operations.put(CommandName.COMMAND_VIEW_TRAINING, new ViewTrainingCommand());
        operations.put(CommandName.COMMAND_REMOVE_TRAINING, new RemoveTrainingCommand());
        operations.put(CommandName.COMMAND_RESTORE_TRAINING, new RestoreTrainingCommand());
        operations.put(CommandName.COMMAND_EDIT_TRAINING, new EditTrainingCommand());
        operations.put(CommandName.COMMAND_FORWARD_EDIT_TRAINING, new ForwardEditTrainingCommand());
        operations.put(CommandName.COMMAND_CHANGE_BANNED_STATUS, new ChangeBannedStatusCommand());
        operations.put(CommandName.COMMAND_FORWARD_ENROLL_TRAINING, new ForwardQueryTrainingCommand());
        operations.put(CommandName.COMMAND_CHANGE_LOCALE, new ChangeLocaleCommand());
        operations.put(CommandName.COMMAND_ENROLL_TRAINING, new WriteTrainingQueryCommand());
        operations.put(CommandName.COMMAND_VIEW_ALL_QUERIES, new ViewAllQueriesCommand());
        operations.put(CommandName.COMMAND_UNDO_QUERY, new UndoQueryCommand());
        operations.put(CommandName.COMMAND_SET_LIKE, new PutTrainingLikeCommand());
        operations.put(CommandName.COMMAND_SET_DISLIKE, new PutTrainingDislikeCommand());
        operations.put(CommandName.COMMAND_VIEW_ALL_TRAINER_TRAININGS, new ViewAllTrainerTrainingsCommand());
        operations.put(CommandName.COMMAND_PUT_OFF_DISLIKE, new PutOffTrainingDislikeCommand());
        operations.put(CommandName.COMMAND_PUT_OFF_LIKE, new PutOffTrainingLikeCommand());
        operations.put(CommandName.COMMAND_VIEW_TRAINING_QUERIES, new ViewTrainingQueriesCommand());
        operations.put(CommandName.COMMAND_SET_QUERY_ANSWER, new SetQueryAnswerCommand());
        operations.put(CommandName.COMMAND_VIEW_PROFILE, new ViewProfileCommand());
        operations.put(CommandName.COMMAND_FORWARD_EDIT_PROFILE, new ForwardEditProfileCommand());
    }

    public Command createCommand(String commandName, HttpServletRequest request, HttpServletResponse response){
        ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
        HttpRequestResponseKeeper keeper = utilFactory.getHttpRequestResponseKeeper();
        keeper.setAll(request, response);

        Command command = null;
        try{
            command = operations.get(commandName);
        } catch (IllegalArgumentException e){
            log.error(e);
        }
        return command;
    }
}
