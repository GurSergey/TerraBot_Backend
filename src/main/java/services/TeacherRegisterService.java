package services;

import entity.TeacherEntity;
import enumeration.UserRolesEnum;

public class TeacherRegisterService extends AbstractService {
    public TeacherRegisterService(Repository repository) {
        super(repository);
    }

    public void register(TeacherEntity teacher)
    {
        teacher.role = UserRolesEnum.TEACHER.getId();
        teacher.token = AuthService.generateToken();
        this.repository.save(teacher);
    }
}
