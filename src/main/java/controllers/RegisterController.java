package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Config;
import entity.EntityHibernate;
import entity.PupilEntity;
import entity.TeacherEntity;
import services.PupilRegisterService;
import services.Repository;
import services.RepositoryDB;
import services.TeacherRegisterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterController extends Controller {
    private static String NAME_PUPIL_PARAM = "pupil";
    private static String NAME_TEACHER_PARAM = "teacher";


    public RegisterController()
    {

    }


    public void methodPupilRegistry(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String pupilString =  req.getParameter(NAME_PUPIL_PARAM);
        PupilEntity pupilEntity = jsonGetterObject.fromJson(pupilString, PupilEntity.class);
        PupilRegisterService pupilRegisterService = new PupilRegisterService(
                new RepositoryDB(PupilEntity.class));
        pupilRegisterService.register(pupilEntity);
        this.sendStandartAnswer(resp);
    }

    public void methodTeacherRegistry(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String pupilString =  req.getParameter(NAME_TEACHER_PARAM);
        TeacherEntity teacherEntity = jsonGetterObject.fromJson(pupilString, TeacherEntity.class);
        TeacherRegisterService teacherRegisterService = new TeacherRegisterService(
                new RepositoryDB(TeacherEntity.class));
        teacherRegisterService.register(teacherEntity);
        this.sendStandartAnswer(resp);
    }


}
