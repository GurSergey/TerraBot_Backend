package services;

import config.Config;
import entity.PupilEntity;
import entity.TeacherEntity;
import entity.UserEntity;

import java.util.Random;

public class AuthService extends AbstractService {
    public AuthService(Repository<PupilEntity> repository) {
        super(repository);
    }

    public static String generateToken()
    {
        StringBuilder randString = new StringBuilder();
        int count = Config.SIZE_OF_TOKEN;
        for(int i=0;i<count;i++)
            randString.append(Config.SYMBOLS_FOR_TOKEN.charAt(
                    (int)(Math.random()*Config.SYMBOLS_FOR_TOKEN.length())));
        return randString.toString();
    }



    public String signInPupil(String login, String password)
    {
        SpecificationCriterion[] criterions = new SpecificationCriterion[]{
          new SpecificationCriterion("login", login),
          new SpecificationCriterion("password", password)
        };
        return ((PupilEntity)this.repository.specificObject(criterions)).token;
    }

    public String signInTeacher(String login, String password)
    {
        SpecificationCriterion[] criterions = new SpecificationCriterion[]{
                new SpecificationCriterion("login", login),
                new SpecificationCriterion("password", password)
        };
        return ((TeacherEntity)this.repository.specificObject(criterions)).token;
    }

    public boolean checkToken(String token)
    {
        return false;
    }

    public TeacherEntity getTeacherByToken(String token)
    {
        SpecificationCriterion[] criterions = new SpecificationCriterion[]{
                new SpecificationCriterion("token", token)
        };
        return ((TeacherEntity)this.repository.specificObject(criterions));
    }

    public PupilEntity getPupilByToken(String token)
    {
        SpecificationCriterion[] criterions = new SpecificationCriterion[]{
                new SpecificationCriterion("token", token)
        };
        return ((PupilEntity)this.repository.specificObject(criterions));
    }
}
