package by.htp.ex.controller;

import by.htp.ex.controller.constant.RequestParameterName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FrontController extends HttpServlet {
   /* private static final long serialVersionUID = 1L;
    private final CommandProvider provider = new CommandProvider();

    public FrontController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
        Command command = provider.getCommand(commandName);
        command.execute(request, response);

    }*/
}
