package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.PupilEntity;
import entity.TeacherEntity;
import entity.UserEntity;
import enumeration.UserRolesEnum;
import services.AuthService;
import services.RepositoryDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static enumeration.UserRolesEnum.*;

public class AuthController extends Controller {
    private static String  NAME_LOGIN_PARAM = "login";
    private static String NAME_PASSWORD_PARAM = "password";
    private class AnswerToken{
        private String token = "";
        private String role = "";
        public AnswerToken(String token, String role)
        {   this.token = token;
            this.role = role;}
    }

    public void methodAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login =  req.getParameter(NAME_LOGIN_PARAM);
        String password = req.getParameter(NAME_PASSWORD_PARAM);
        AuthService userAuthService = new AuthService(
                new RepositoryDB(UserEntity.class));
        UserEntity user = userAuthService.signIn(login, password);
        PrintWriter out = resp.getWriter();
        String json = jsonGetterObject.toJson(new AnswerToken(user.token,
                UserRolesEnum.getById(user.role).getName()));
        out.print(json);
    }

    public void methodCheckToken(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String token = req.getParameter(NAME_TOKEN_PARAM);
        AuthService userAuthService = new AuthService(
                new RepositoryDB(UserEntity.class));
        sendString(jsonGetterObject.toJson(userAuthService.checkToken(token)), resp);
    }

}
