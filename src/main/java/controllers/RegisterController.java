package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.PupilEntity;
import services.PupilRegisterService;
import structs.Pupil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class RegisterController extends Controller {
    public void methodPupil(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String pupilString =  req.getParameter("pupil");
        String test = req.getQueryString();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Pupil pupil = gson.fromJson(pupilString, Pupil.class);
        PupilRegisterService service = new PupilRegisterService();
        service.register(new PupilEntity());
        PrintWriter out = resp.getWriter();
        out.print("Hello");
    }
}
