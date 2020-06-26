package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;
import dbService.DBException;
import dbService.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    //private final AccountService accountService;
    private final DBService dbService;

    public SignInServlet(/*AccountService accountService*/DBService dbService) {
        this.dbService = dbService;
    }

    //get public user profile
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (login == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = null;
        try {
            profile = dbService.getUser(login);
        } catch (DBException e) {
            e.printStackTrace();
        }
        if (profile == null || !profile.getPass().equals(pass)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;

        }

        //accountService.addSession(request.getSession().getId(), profile);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Authorized: " + login);
        response.setStatus(HttpServletResponse.SC_OK);

    }


}
