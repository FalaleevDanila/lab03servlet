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


public class SignUpServlet extends HttpServlet {

    //private final AccountService accountService;

    private final DBService dbService;

    public SignUpServlet(DBService dbService) {
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
        //UserProfile prof = new UserProfile(login,pass,pass);

        //if (prof == null) {
            //response.setContentType("text/html;charset=utf-8");
            //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //} else {
        try {
            dbService.addUser(login,pass);
        } catch (DBException e) {
            e.printStackTrace();
        }
        //}
    }
}
