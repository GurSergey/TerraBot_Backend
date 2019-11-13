package services;

import entity.IssueEntity;
import entity.PupilEntity;

public class ClassJournalService {
    public PupilEntity[] findByTemplate(String template)
    {
        return null;
    }
    public PupilEntity[] getListPupils(int idTeacher)
    {
        return null;
    }
    public IssueEntity[] getListIssues(int idTeacher, int idPupil)
    {
        return null;
    }
    public IssueEntity getIssue(int idTeacher, int idIssue)
    {
        return null;
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
