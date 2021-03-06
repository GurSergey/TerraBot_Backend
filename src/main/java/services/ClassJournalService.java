package services;

import entity.IssueEntity;
import entity.PupilEntity;
import entity.TaskEntity;
import entity.TeacherEntity;

public class ClassJournalService extends AbstractService {
    private Repository<TeacherEntity> repositoryTeacher;
    private Repository<IssueEntity> repositoryIssue;
    private Repository<PupilEntity> repositoryPupil;
    private Repository<TaskEntity> repositoryTask;

    public ClassJournalService(Repository<TeacherEntity> repositoryTeacher,
                               Repository<IssueEntity> repositoryIssue,
                               Repository<PupilEntity> repositoryPupil,
                               Repository<TaskEntity> repositoryTask) {
        this.repositoryIssue = repositoryIssue;
        this.repositoryPupil = repositoryPupil;
        this.repositoryTeacher = repositoryTeacher;
        this.repositoryTask = repositoryTask;
    }

    public PupilEntity[] findByTemplate(String template)
    {
        QueryBuilder builder = repositoryPupil.getBuilderQuery();
        return (PupilEntity[]) builder.select().
                where(new SpecificationCriterion("teacher", null)).
                like(new SpecificationCriterion("name", template)).
                getObjects();
    }
    public PupilEntity[] getListPupils(int idTeacher)
    {
        QueryBuilder builder = repositoryPupil.getBuilderQuery();
        return (PupilEntity[]) builder.select().
                where(new SpecificationCriterion("teacher", idTeacher))
                .getObjects();
    }
    public IssueEntity[] getListIssues(int idTeacher, int idPupil) throws Exception {
        PupilEntity pupilEntity = repositoryPupil.findById(idPupil);
        if(pupilEntity.teacher.id!=idTeacher)
        {
            throw new Exception("Не ваш ученик");
        }
        return  pupilEntity.issues.toArray(new IssueEntity[pupilEntity.issues.size()]);
    }
    public IssueEntity getIssue(int idTeacher, int idIssue) throws Exception {
        IssueEntity issueEntity = repositoryIssue.findById(idIssue);
        notNull(issueEntity, new Exception());
        if(issueEntity.pupil.teacher.id!=idTeacher){
            throw new Exception("Не ваш ученик");
        }
        return issueEntity;
    }
    public void addPupil(int idTeacher, int idPupil) throws Exception {
        PupilEntity pupilEntity = repositoryPupil.findById(idPupil);
        notNull(pupilEntity, new Exception());
        TeacherEntity teacherEntity = repositoryTeacher.findById(idTeacher);
        notNull(teacherEntity, new Exception());
        pupilEntity.teacher = teacherEntity;
        repositoryPupil.update(pupilEntity);
    }

    public void removePupil(int idTeacher, int idPupil) throws Exception {
        PupilEntity pupilEntity = repositoryPupil.findById(idPupil);
        notNull(pupilEntity, new Exception());
        if(pupilEntity.teacher.id!=idTeacher){
            throw new Exception("Не ваш ученик");
        }
        pupilEntity.teacher = null;
        repositoryPupil.update(pupilEntity);
    }

    public void addTaskForPupil(int idTeacher, int idPupil, int idTask) throws Exception {
        PupilEntity pupilEntity = repositoryPupil.findById(idPupil);
        notNull(pupilEntity, new Exception());
        TaskEntity taskEntity = repositoryTask.findById(idTask);
        notNull(taskEntity, new Exception());
        if(pupilEntity.teacher.id!=idTeacher){
            throw new Exception("Не ваш ученик");
        }
        if(taskEntity.owner.id!=idTeacher) {
            throw new Exception();
        }
        taskEntity.pupils.add(pupilEntity);
        repositoryTask.update(taskEntity);
    }

    public void setMark(int idTeacher, int idIssue, int mark) throws Exception {
        IssueEntity issueEntity = repositoryIssue.findById(idIssue);
        notNull(issueEntity, new Exception());
        if(issueEntity.pupil.teacher.id!=idTeacher){
            throw new Exception("Не ваш ученик");
        }
        issueEntity.mark = mark;
        repositoryIssue.update(issueEntity);
    }
}
