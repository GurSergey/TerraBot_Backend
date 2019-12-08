package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.UserEntity;
import services.AuthService;
import services.RepositoryDB;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public abstract class Controller {
    protected static String STANDART_ANSWER = "ok";

    protected final String NAME_TOKEN_PARAM = "token";

    protected Gson jsonGetterObject;

    Controller()
    {
        GsonBuilder builder = new GsonBuilder();
        Gson jsonGetterObject = builder.create();
    }

    private class Answer
    {
        public String status = "ok";
    }

    protected void sendStandartAnswer(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String json = gson.toJson(new Answer());
        out.print(json);
    }

    protected void sendString(String str, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        out.print(str);
    }

    protected void checkAuthToken(String token) throws Exception {
        AuthService userAuthService = new AuthService(
                new RepositoryDB(UserEntity.class));
        if(!userAuthService.checkToken(token))
        {
            throw new Exception("");
        }
    }

}
