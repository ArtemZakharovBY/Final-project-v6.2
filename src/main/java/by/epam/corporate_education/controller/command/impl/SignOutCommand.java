package by.epam.corporate_education.controller.command.impl;

import by.epam.corporate_education.controller.command.Command;
import by.epam.corporate_education.controller.command.CommandException;
import by.epam.corporate_education.controller.util.ControllerUtilFactory;
import by.epam.corporate_education.controller.util.ParameterName;
import by.epam.corporate_education.controller.util.api.AttributesInitializer;
import by.epam.corporate_education.controller.util.api.ControllerValueChecker;
import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import by.epam.corporate_education.controller.util.api.PathCreator;
import by.epam.corporate_education.service.ServiceFactory;
import by.epam.corporate_education.service.api.UserService;
import by.epam.corporate_education.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignOutCommand implements Command {

    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getINSTANCE();
    private ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
    private UserService userService;

    public SignOutCommand(){
        userService = serviceFactory.getUserServiceImpl();
    }

    //annotation
    public SignOutCommand(UserService userService, ControllerUtilFactory utilFactory){
        this.userService = userService;
        this.utilFactory = utilFactory;
    }

    @Override
    public String execute() throws CommandException {
        ControllerValueChecker controllerValueChecker = utilFactory.getControllerValueChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();
        HttpRequestResponseKeeper keeper = utilFactory.getHttpRequestResponseKeeper();
        AttributesInitializer attributesInitializer = utilFactory.getAttributesInitializer();

        HttpServletRequest request = keeper.getRequest();
        HttpServletResponse response = keeper.getResponse();

        String path = pathCreator.getError();

        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute(ParameterName.ID_USER);
        String onlineStatus = request.getParameter(ParameterName.ONLINE);
        try {
                boolean onlineStatusBoolean = Boolean.parseBoolean(onlineStatus);
                userService.signOut(idUser, onlineStatusBoolean);
                session.invalidate();
                path = pathCreator.getSignIn();
        } catch (ServiceException e){
            throw new CommandException(e);
        }
        return path;
    }
}
