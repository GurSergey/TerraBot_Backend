package services;

import entity.IssueEntity;
import entity.PupilEntity;
import entity.TeacherEntity;

public class ClassJournalService extends AbstractService {
    protected Class[] needRepositoriesEntities = {PupilEntity.class, TeacherEntity.class};

    ClassJournalService(Repository repository) {
        super(repository);
    }

    public PupilEntity[] findByTemplate(String template)
    {
        QueryBuilder builder = repository.getBuilderQuery();
        return (PupilEntity[]) builder.select().
                like(new SpecificationCriterion("name", template)).
                getObjects();
    }
    public PupilEntity[] getListPupils(int idTeacher)
    {
        QueryBuilder builder = repository.getBuilderQuery();
        return (PupilEntity[]) builder.select().
                where(new SpecificationCriterion("teacher_id", idTeacher))
                .getObjects();
    }
    public IssueEntity[] getListIssues(int idTeacher, int idPupil) throws Exception {
        PupilEntity pupilEntity = (PupilEntity) repository.findById(idPupil);
        TeacherEntity teacherEntity = (TeacherEntity) repository.findById(idTeacher);
        if(!pupilEntity.teacher.equals(teacherEntity))
        {
            throw new Exception();
        }
        return (IssueEntity[]) pupilEntity.issues.toArray();
    }
    public IssueEntity getIssue(int idTeacher, int idIssue)
    {
        IssueEntity issueEntity = (IssueEntity) repository.findById(idIssue);
        TeacherEntity teacherEntity = (TeacherEntity) repository.findById(idTeacher);
         issueEntity.pupil
    }
    public void addPupil(int idTeacher, int idPupil)
    {

    }
    public void addTaskForPupil(int idPupil, int idTask)
    {

    }

    public void setMark(int idIssue, int mark)
    {}
}
