package services;

import entity.PupilEntity;
import enumeration.UserRolesEnum;

public class PupilRegisterService extends AbstractService {
    private Repository<PupilEntity> repositoryPupil;

    public PupilRegisterService(Repository<PupilEntity> repositoryPupil) {
        this.repositoryPupil = repositoryPupil;
    }

    public void register(PupilEntity pupilEntity){
        pupilEntity.role = UserRolesEnum.PUPIL.getId();
        pupilEntity.token = AuthService.generateToken();
        this.repositoryPupil.save(pupilEntity);
    }

}
