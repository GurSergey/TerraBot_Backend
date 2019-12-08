package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.PupilEntity;
import entity.TeacherEntity;
import services.AuthService;
import services.RepositoryDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ProfileController extends Controller {
    private static String NAME_TOKEN_PARAM = "token";


    public void methodGetPupilProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String token =  req.getParameter(NAME_TOKEN_PARAM);
        AuthService pupilAuthService = new AuthService(
                new RepositoryDB(PupilEntity.class));
        PupilEntity pupil = (PupilEntity) pupilAuthService.getByToken(token);
        PrintWriter out = resp.getWriter();
        String json = jsonGetterObject.toJson(pupil);
        out.print(json);
    }

    public void methodGetTeacherProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String token =  req.getParameter(NAME_TOKEN_PARAM);
        AuthService teacherAuthService = new AuthService(
                new RepositoryDB(TeacherEntity.class));
        TeacherEntity teacher = (TeacherEntity) teacherAuthService.getByToken(token);
        PrintWriter out = resp.getWriter();
        String json = jsonGetterObject.toJson(teacher);
        out.print(json);
    }
}
