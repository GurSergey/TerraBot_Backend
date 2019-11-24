package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Config;
import entity.EntityHibernate;
import entity.PupilEntity;
import services.PupilRegisterService;
import services.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterController extends Controller {
    private static String NAME_PUPIL_PARAM = "pupil";

    public void methodPupilRegistry(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String pupilString =  req.getParameter(NAME_PUPIL_PARAM);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        PupilEntity pupilEntity = gson.fromJson(pupilString, PupilEntity.class);
        PupilRegisterService pupilRegisterService = new PupilRegisterService(
                (Repository<EntityHibernate>) Config.serviceLocator.getInstance(Repository.class));
        pupilRegisterService.register(pupilEntity);
        this.sendStandartAnswer(resp);

    }
}
