package controllers;

import entity.*;
import services.AuthService;
import services.ClassJournalService;
import services.PupilRegisterService;
import services.RepositoryDB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ClassJournalController extends Controller{

    private final String NAME_TEMPLATE_PARAM = "template";
    private final String NAME_ID_PUPIL_PARAM = "pupil";
    private final String NAME_ID_ISSUE_PARAM = "issue";
    private final String NAME_MARK_PARAM = "mark";
    private final String NAME_TASK_PARAM = "task";

    ClassJournalService classJournalService;
    public ClassJournalController()
    {
         classJournalService = new ClassJournalService(
                new RepositoryDB(TeacherEntity.class),
                new RepositoryDB(IssueEntity.class),
                new RepositoryDB(PupilEntity.class),
                new RepositoryDB(TaskEntity.class));
    }
    public void methodFindByTemplate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String templateString = req.getParameter(NAME_TEMPLATE_PARAM);
        String tokenString = req.getParameter(NAME_TOKEN_PARAM);
        checkAuthToken(tokenString);
        PupilEntity[] pupils = classJournalService.findByTemplate(templateString);
        this.sendString(jsonGetterObject.toJson(pupils), resp);
    }

    public void methodGetListPupils(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        PupilEntity[] pupils = classJournalService.getListPupils(teacherEntity.id);
        this.sendString(jsonGetterObject.toJson(pupils), resp);
    }

    public void methodGetListIssues(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String pupilId = req.getParameter(NAME_ID_PUPIL_PARAM);
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        IssueEntity[] issues = classJournalService.getListIssues(teacherEntity.id, Integer.parseInt(pupilId));
        this.sendString(jsonGetterObject.toJson(issues), resp);
    }

    public void methodGetIssue(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String issueID = req.getParameter(NAME_ID_ISSUE_PARAM);
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        IssueEntity issue = classJournalService.getIssue(teacherEntity.id, Integer.parseInt(issueID));
        this.sendString(jsonGetterObject.toJson(issue), resp);
    }

    public void methodAddPupil(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String tokenString = req.getParameter(NAME_TOKEN_PARAM);
        String pupilId = req.getParameter(NAME_ID_PUPIL_PARAM);
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        classJournalService.addPupil(teacherEntity.id, Integer.parseInt(pupilId));
        sendStandartAnswer(resp);
    }

    public void methodRemovePupil(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String pupilId = req.getParameter(NAME_ID_PUPIL_PARAM);
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        classJournalService.removePupil(teacherEntity.id, Integer.parseInt(pupilId));
        sendStandartAnswer(resp);
    }

    public void methodAddTaskForPupil(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String pupilId = req.getParameter(NAME_ID_PUPIL_PARAM);
        String taskId = req.getParameter(NAME_TASK_PARAM);
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        classJournalService.addTaskForPupil(teacherEntity.id, Integer.parseInt(pupilId), Integer.parseInt(taskId));
        sendStandartAnswer(resp);
    }

    public void methodSetMark(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String mark = req.getParameter(NAME_MARK_PARAM);
        String issueID = req.getParameter(NAME_ID_ISSUE_PARAM);
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        classJournalService.setMark(teacherEntity.id, Integer.parseInt(issueID), Integer.parseInt(mark));
        sendStandartAnswer(resp);
    }

}
