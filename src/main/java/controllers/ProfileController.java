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
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        String teacherString = req.getParameter(TEACHER_PARAM);
        TeacherEntity teacherEntityFromClient =
                jsonGetterObject.fromJson(teacherString, TeacherEntity.class);
        teacherEntityFromClient.id = teacherEntity.id;

        profileService.saveProfileTeacher(teacherEntityFromClient, null);
        sendStandartAnswer(resp);
    }
    public void methodSaveProfilePupil(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PupilEntity pupilEntity = (PupilEntity) getUserEntity(PupilEntity.class, req);
        String pupilString = req.getParameter(PUPIL_PARAM);
        PupilEntity pupilEntityFromClient =
                jsonGetterObject.fromJson(pupilString, PupilEntity.class);
        pupilEntityFromClient.id = pupilEntity.id;

        profileService.saveProfilePupil(pupilEntityFromClient, null);
        sendStandartAnswer(resp);
    }


}
