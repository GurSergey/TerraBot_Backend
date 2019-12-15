package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.PupilEntity;
import entity.TeacherEntity;
import services.AuthService;
import services.ProfileService;
import services.RepositoryDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ProfileController extends Controller {
    private static final String TEACHER_PARAM = "teacher";
    private static final String PUPIL_PARAM = "pupil";
    private ProfileService profileService;

    private class GetProfile
    {
        private String login;
        private String name;
        private String password;
    }

    public ProfileController()
    {
        profileService = new ProfileService(
                new RepositoryDB(TeacherEntity.class),
                new RepositoryDB(PupilEntity.class));
    }

    public void methodGetPupilProfile(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PupilEntity pupilEntity = (PupilEntity) getUserEntity(PupilEntity.class, req);
        PrintWriter out = resp.getWriter();
        String json = jsonGetterObject.toJson(pupilEntity);
        out.print(json);
    }

    public void methodGetTeacherProfile(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        PrintWriter out = resp.getWriter();
        String json = jsonGetterObject.toJson(teacherEntity);
        out.print(json);
    }

    public void methodSaveProfileTeacher(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int idTeacher = getUserEntity(TeacherEntity.class, req).id;
        String teacherString = req.getParameter(TEACHER_PARAM);
        GetProfile teacherEntityFromClient =
                jsonGetterObject.fromJson(teacherString, GetProfile.class);
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.login = teacherEntityFromClient.login;
        teacherEntity.password = teacherEntityFromClient.password;
        teacherEntity.name = teacherEntityFromClient.name;


        profileService.saveProfileTeacher(idTeacher,teacherEntity, null);
        sendStandartAnswer(resp);
    }
    public void methodSaveProfilePupil(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int idPupil = getUserEntity(PupilEntity.class, req).id;
        String pupilString = req.getParameter(PUPIL_PARAM);
        GetProfile pupilEntityFromClient =
                jsonGetterObject.fromJson(pupilString, GetProfile.class);
        PupilEntity pupilEntity = new PupilEntity();
        pupilEntity.login = pupilEntityFromClient.login;
        pupilEntity.password = pupilEntityFromClient.password;
        pupilEntity.name = pupilEntityFromClient.name;

        profileService.saveProfilePupil(idPupil, pupilEntity, null);
        sendStandartAnswer(resp);
    }


}
