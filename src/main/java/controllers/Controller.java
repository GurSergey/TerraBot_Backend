package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Controller {
    public static String STANDART_ANSWER = "ok";

    protected void sendStandartAnswer(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(new Object(){
            public String status = "ok";
        });
        out.print(json);
    }

}
