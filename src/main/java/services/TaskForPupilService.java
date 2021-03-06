package services;

import entity.IssueEntity;
import entity.PupilEntity;
import entity.TaskEntity;


public class TaskForPupilService extends AbstractService {
    Repository<TaskEntity> repositoryTask;
    Repository<PupilEntity> repositoryPupil;
    Repository<IssueEntity> repositoryIssue;

    public TaskForPupilService(Repository<TaskEntity> repositoryTask,
                               Repository<PupilEntity> repositoryPupil,
                               Repository<IssueEntity> repositoryIssue){
        this.repositoryPupil = repositoryPupil;
        this.repositoryTask = repositoryTask;
        this.repositoryIssue = repositoryIssue;
    }

    public TaskEntity[] getList(int idPupil) throws Exception {
        PupilEntity pupilEntity = repositoryPupil.findById(idPupil);
        notNull(pupilEntity , new Exception());
        return pupilEntity.tasks.toArray(new TaskEntity[pupilEntity.tasks.size()]);
        //return (TaskEntity[]) pupilEntity.tasks.toArray();
    }

    public TaskEntity getTask(int idPupil, int idTask) throws Exception {
        PupilEntity pupilEntity = repositoryPupil.findById(idPupil);
        notNull(pupilEntity, new Exception());
        for(TaskEntity taskEntity: pupilEntity.tasks)
        {
            if(taskEntity.id==idTask)
                return taskEntity;
        }
        throw new Exception();

    }

//    public IssueEntity takeMyIssue(int idPupil, int idTask) throws Exception {
//        IssueEntity issueEntity = (IssueEntity) this.repositoryIssue.getBuilderQuery().select().
//                where(new SpecificationCriterion("task_id", idTask)).
//                where(new SpecificationCriterion("pupil_id", idPupil)).
//                getObject();
//        notNull(issueEntity, new Exception());
//        return issueEntity;
//    }
}
