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

    public String signIn(String login, String password)
    {
        QueryBuilder builder = this.repository.getBuilderQuery();
        return ((UserEntity) builder.select()
                .where(new SpecificationCriterion("login", login))
                .where(new SpecificationCriterion("password", password))
                .getObject()).token;
    }

    public boolean checkToken(String token)
    {

        QueryBuilder builder = this.repository.getBuilderQuery();
        return builder.select()
                .where(new SpecificationCriterion("token", token))
                .getObject() != null;
    }

    public UserEntity getByToken(String token)
    {
        SpecificationCriterion[] criterions = new SpecificationCriterion[]{
                new SpecificationCriterion("token", token)
        };
        QueryBuilder builder = this.repository.getBuilderQuery();
        return (UserEntity) builder.select().
                where(new SpecificationCriterion("token", token)).getObject();
    }

}
