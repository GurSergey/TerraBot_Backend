package services;

import entity.TeacherEntity;
import enumeration.UserRolesEnum;

import java.io.File;

public class TeacherRegisterService extends AbstractService {
    private Repository<TeacherEntity> repositoryTeacher;

    public TeacherRegisterService(Repository<TeacherEntity> repositoryTeacher) {
        this.repositoryTeacher = repositoryTeacher;
    }

    public void register(TeacherEntity teacher, File avatar)
    {
        teacher.role = UserRolesEnum.TEACHER.getId();
        teacher.token = AuthService.generateToken();
        repositoryTeacher.save(teacher);
    }
}
