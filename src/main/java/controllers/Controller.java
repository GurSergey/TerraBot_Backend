package controllers;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Controller {
    public static String STANDART_ANSWER = "ok";

    protected void sendStandartAnswer(HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.print(STANDART_ANSWER);
    }

}
