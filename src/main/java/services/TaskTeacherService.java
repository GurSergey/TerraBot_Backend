package services;

import entity.FieldEntity;
import entity.TaskEntity;
import entity.TeacherEntity;

public class TaskTeacherService extends AbstractService {
    public void saveTask(int idTeacher, TaskEntity task) throws Exception {
        TeacherEntity teacherEntity = repositoryTeacher.findById(idTeacher);
        notNull(teacherEntity, new Exception());
        issue.pupil = pupilEntity;
        if(issue.id != AbstractEntity.ID_DEFAULT_VALUE){
            repositoryIssue.save(issue);
        }
        else
        {
            IssueEntity issueEntity = repositoryIssue.findById(issue.id);
            notNull(pupilEntity, new Exception());
            repositoryIssue.update(issue);
        }

    }
    public TaskEntity getTask(int idTeacher, TaskEntity task)
    {
        return null;
    }

}
