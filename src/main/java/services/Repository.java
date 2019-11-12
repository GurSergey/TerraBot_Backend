package services;

import entity.AbstractEntity;

interface Repository<T extends AbstractEntity> {
    public void save(AbstractEntity entity);
    public void update(AbstractEntity entity);
    public void delete(AbstractEntity entity);
    public AbstractEntity findById(int id);
}
