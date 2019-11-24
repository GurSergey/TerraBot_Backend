package services;

import config.Config;
import entity.PupilEntity;
import entity.TeacherEntity;
import entity.UserEntity;

import java.util.Random;

public class AuthService {
    public static String generateToken()
    {
        StringBuilder randString = new StringBuilder();
        int count = Config.SIZE_OF_TOKEN;
        for(int i=0;i<count;i++)
            randString.append(Config.SYMBOLS_FOR_TOKEN.charAt(
                    (int)(Math.random()*Config.SYMBOLS_FOR_TOKEN.length())));
        return randString.toString();
    }

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
