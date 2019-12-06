package services;

import entity.PupilEntity;
import enumeration.UserRolesEnum;

public class PupilRegisterService extends AbstractService {
    public PupilRegisterService(Repository<PupilEntity> repository) {
        super(repository);
    }

    public void register(PupilEntity pupilEntity){
        pupilEntity.role = UserRolesEnum.PUPIL.getId();
        pupilEntity.token = AuthService.generateToken();
        this.repository.save(pupilEntity);
    }

}
