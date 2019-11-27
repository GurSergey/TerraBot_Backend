package services;

import entity.TeacherEntity;

public class TeacherRegisterService extends AbstractService {
    public TeacherRegisterService(Repository repository) {
        super(repository);
    }

    public void register(TeacherEntity teacher)
    {
        teacher.token = AuthService.generateToken();
        this.repository.save(teacher);
    }
}
