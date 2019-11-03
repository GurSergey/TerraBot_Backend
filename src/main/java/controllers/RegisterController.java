package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import structs.Pupil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterController extends Controller {
    public void methodPupil(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pupilString =  req.getParameter("pupil");
        String test = req.getQueryString();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Pupil pupil = gson.fromJson(pupilString, Pupil.class);

        PrintWriter out = resp.getWriter();
        out.print("Hello");
    }
}
