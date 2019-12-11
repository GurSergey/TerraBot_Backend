package services;

import entity.*;

public class TaskForTeacherService extends AbstractService {
    Repository<TeacherEntity> repositoryTeacher;
    Repository<TaskEntity> repositoryTask;

    public TaskForTeacherService(Repository<TeacherEntity> repositoryTeacher,
                                 Repository<TaskEntity> repositoryTask){
        this.repositoryTask = repositoryTask;
        this.repositoryTeacher = repositoryTeacher;
    }

    public void saveTask(int idTeacher, TaskEntity task) throws Exception {
        TeacherEntity teacherEntity = repositoryTeacher.findById(idTeacher);
        notNull(teacherEntity, new Exception());

        if(task.id != AbstractEntity.ID_DEFAULT_VALUE){
            repositoryTask.save(task);
        }
        else
        {
            TaskEntity taskEntity = repositoryTask.findById(task.id);
            notNull(taskEntity, new Exception());
            repositoryTask.update(task);
        }
    }

    public TaskEntity[] getMyTasks(int idTeacher){
        TaskEntity[] taskEntities = (TaskEntity[]) repositoryTask.getBuilderQuery().select().
                where(new SpecificationCriterion("owner_id", idTeacher)).getObjects();
        return taskEntities;
    }

    public TaskEntity getTask(int idTeacher, int idTask) throws Exception {
        TaskEntity taskEntity = (TaskEntity) repositoryTask.getBuilderQuery().select().
                where(new SpecificationCriterion("id", idTask)).
                where(new SpecificationCriterion("owner_id", idTeacher)).
                getObject();
        notNull(taskEntity, new Exception());
        return taskEntity;

    }

}
