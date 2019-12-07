package services;

import entity.IssueEntity;
import entity.PupilEntity;
import entity.TaskEntity;


public class TaskForPupilService extends AbstractService {
    Repository<TaskEntity> repositoryTask;
    Repository<PupilEntity> repositoryPupil;

    public TaskForPupilService(Repository<TaskEntity> repositoryTask,
                               Repository<PupilEntity> repositoryPupil){
        this.repositoryPupil = repositoryPupil;
        this.repositoryTask = repositoryTask;
    }

    public TaskEntity[] getList(int idPupil) throws Exception {
        PupilEntity pupilEntity = repositoryPupil.findById(idPupil);
        notNull(pupilEntity , new Exception());
        return (TaskEntity[]) pupilEntity.tasks.toArray();
    }

    public TaskEntity getTask(int idPupil, int idTask) throws Exception {
        PupilEntity pupilEntity = repositoryPupil.findById(idPupil);
        notNull(pupilEntity, new Exception());
        TaskEntity taskEntity = repositoryTask.findById(idTask);
        notNull(taskEntity, new Exception());
        if(!pupilEntity.tasks.contains(taskEntity))
        {
            throw new Exception();
        }
        return taskEntity;

    }

    public IssueEntity takeMyIssue(int idPupil, int idTask)
    {
        return null;
    }
}
