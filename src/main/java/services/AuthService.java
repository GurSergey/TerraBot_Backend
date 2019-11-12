package services;

import entity.PupilEntity;
import entity.TeacherEntity;

public class AuthService {
    public String signIn(String login, String password)
    {
        return "123";
    }

    public boolean checkToken(String token)
    {
        return false;
    }

    public TeacherEntity getTeacherByToken(String token)
    {
        return null;
    }

    public PupilEntity getPupilByToken(String token)
    {
        return null;
    }
}
