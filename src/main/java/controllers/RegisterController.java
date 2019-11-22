package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.PupilEntity;
import services.PupilRegisterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterController extends Controller {
    public void methodPupilRegistry(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String pupilString =  req.getParameter("pupil");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        PupilEntity pupilEntity = gson.fromJson(pupilString, PupilEntity.class);
        PupilRegisterService service = new PupilRegisterService();
        service.register();
        PrintWriter out = resp.getWriter();
        out.print("ok");
    }
}
