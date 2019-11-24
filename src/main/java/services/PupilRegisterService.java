package services;

import entity.PupilEntity;

public class PupilRegisterService extends AbstractService {
    public PupilRegisterService(Repository<entity.EntityHibernate> repository) {
        super(repository);
    }

    public void register(PupilEntity pupilEntity){
        pupilEntity.token = AuthService.generateToken();
        this.repository.save(pupilEntity);
    }

}
