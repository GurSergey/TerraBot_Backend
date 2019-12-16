package services;

import entity.*;


public class TaskForTeacherService extends AbstractService {
    Repository<TeacherEntity> repositoryTeacher;
    Repository<TaskEntity> repositoryTask;
    Repository<FieldEntity> repositoryField;

    public TaskForTeacherService(Repository<TeacherEntity> repositoryTeacher,
                                 Repository<TaskEntity> repositoryTask,
                                 Repository<FieldEntity> repositoryField){
        this.repositoryTask = repositoryTask;
        this.repositoryTeacher = repositoryTeacher;
        this.repositoryField = repositoryField;
    }

    public void saveTask(int idTeacher, TaskEntity task) throws Exception {
        TeacherEntity teacherEntity = repositoryTeacher.findById(idTeacher);
        notNull(teacherEntity, new Exception());
        task.owner = teacherEntity;
        repositoryTask.save(task);
    }

    public void updateTask(int idTeacher, TaskEntity taskEntity) throws Exception {
        TeacherEntity teacherEntity = repositoryTeacher.findById(idTeacher);
        notNull(teacherEntity, new Exception());
        TaskEntity oldTaskEntity  = repositoryTask.findById(taskEntity.id);
        notNull(oldTaskEntity, new Exception());
        copyFieldsNotNull(taskEntity, oldTaskEntity, TaskEntity.class);
        repositoryTask.update(oldTaskEntity);

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
