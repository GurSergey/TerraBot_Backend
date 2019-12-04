package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.PupilEntity;
import entity.TeacherEntity;
import services.AuthService;
import services.RepositoryDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthController extends Controller {
    private static String  NAME_LOGIN_PARAM = "login";
    private static String NAME_PASSWORD_PARAM = "password";
    private class AnswerToken{
        private String token = "";
        public AnswerToken(String token)
        { this.token = token;}
    }

    public void methodAuthPupil(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login =  req.getParameter(NAME_LOGIN_PARAM);
        String password = req.getParameter(NAME_PASSWORD_PARAM);
        AuthService pupilAuthService = new AuthService(
                new RepositoryDB(PupilEntity.class));
        String token = pupilAuthService.signInPupil(login, password);
        PrintWriter out = resp.getWriter();
        String json = jsonGetterObject.toJson(new AnswerToken(token));
        out.print(json);
    }

    public void methodAuthTeacher(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login =  req.getParameter(NAME_LOGIN_PARAM);
        String password = req.getParameter(NAME_PASSWORD_PARAM);
        AuthService pupilAuthService = new AuthService(
                new RepositoryDB(TeacherEntity.class));
        String token = pupilAuthService.signInTeacher(login, password);
        PrintWriter out = resp.getWriter();
        String json = jsonGetterObject.toJson(new AnswerToken(token));
        out.print(json);
    }

}
