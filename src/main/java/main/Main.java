package main;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SessionsServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import servlets.UsersServlet;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;


/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    //private static final Logger LOG = Log.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        //LOG.info("Server started");
        AccountService accountService = new AccountService();
        DBService dbService = new DBService();
        //accountService.addNewUser(new UserProfile("admin"));
        //accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(dbService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(dbService)), "/signin");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
