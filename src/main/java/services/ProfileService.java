package services;

import entity.PupilEntity;
import entity.TeacherEntity;
import enumeration.UserRolesEnum;

import java.io.File;

public class ProfileService {
    private Repository<TeacherEntity> repositoryTeacher ;
    private Repository<PupilEntity> repositoryPupil;
    public ProfileService(Repository<TeacherEntity> repositoryTeacher, Repository<PupilEntity> repositoryPupil)
    {
        this.repositoryTeacher = repositoryTeacher;
        this.repositoryPupil = repositoryPupil;
    }


    public void saveProfilePupil(PupilEntity pupil, File avatar) {
        this.repositoryPupil.update(pupil);
    }

    public void saveProfileTeacher(TeacherEntity teacher, File avatar){
        this.repositoryTeacher.update(teacher);
    }


}
