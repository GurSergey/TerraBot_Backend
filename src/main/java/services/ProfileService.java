package services;

import entity.PupilEntity;
import entity.TeacherEntity;


import java.io.File;

public class ProfileService extends AbstractService {
    private Repository<TeacherEntity> repositoryTeacher ;
    private Repository<PupilEntity> repositoryPupil;
    public ProfileService(Repository<TeacherEntity> repositoryTeacher, Repository<PupilEntity> repositoryPupil)
    {
        this.repositoryTeacher = repositoryTeacher;
        this.repositoryPupil = repositoryPupil;
    }


    public void saveProfilePupil(int idPupil, PupilEntity pupil, File avatar) throws IllegalAccessException {
        PupilEntity oldPupil = repositoryPupil.findById(idPupil);
        copyFieldsNotNull(pupil, oldPupil, PupilEntity.class);
        this.repositoryPupil.update(oldPupil);
    }

    public void saveProfileTeacher(int idTeacher, TeacherEntity teacher, File avatar) throws IllegalAccessException {
        TeacherEntity oldTeacher = repositoryTeacher.findById(idTeacher);
        copyFieldsNotNull(teacher, oldTeacher, TeacherEntity.class);
        this.repositoryTeacher.update(oldTeacher);
    }


}
