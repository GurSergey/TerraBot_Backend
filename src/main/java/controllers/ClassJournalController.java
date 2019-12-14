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

    private class ListPupilItem
    {
        public ListPupilItem(PupilEntity pupilEntity) {
            this.id = pupilEntity.id;
            this.name = pupilEntity.name;
            this.login = pupilEntity.login;
        }
        private int id;
        private String name;
        private String login;
    }

    private class ListIssueItem
    {
        private int id;

        public ListIssueItem(IssueEntity issueEntity) {
            this.id = issueEntity.id;
            this.name = issueEntity.task.name;
            this.complited = issueEntity.completed;
        }

        private String name;
        private boolean complited;
    }

    private class IssueView
    {
        private class CommandView
        {
            public CommandView(CommandEntity commandEntity) {
                this.type = commandEntity.type;
                this.number = commandEntity.number;
                this.parentNumber = commandEntity.parent!=null ?
                        commandEntity.parent.number : -1 ;
            }

            private int type;
            private int number;
            private int parentNumber;
        }

        public IssueView(IssueEntity issueEntity) {
            this.id = issueEntity.id;
            this.nameTask = issueEntity.task.name;
            this.description = issueEntity.task.description;
            this.countStep = issueEntity.countStep;
            this.countCommand = issueEntity.countCommand;
            this.commands = new CommandView[issueEntity.commands.size()];
            for(int i = 0; i < commands.length; i++)
            {
                this.commands[i] = new CommandView(issueEntity.commands.get(i));
            }
        }

        private int id;
        private String nameTask;
        private String description;
        private int countStep;
        private int countCommand;
        private CommandView[] commands;

    }

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
        ListPupilItem[] list = new ListPupilItem[pupils.length];
        for(int i = 0; i< list.length; i++ )
        {
            list[i] = new ListPupilItem(pupils[i]);
        }
        this.sendString(jsonGetterObject.toJson(list), resp);
    }

    public void methodGetListPupils(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        PupilEntity[] pupils = classJournalService.getListPupils(teacherEntity.id);
        ListPupilItem[] list = new ListPupilItem[pupils.length];
        for(int i = 0; i< list.length; i++ )
        {
            list[i] = new ListPupilItem(pupils[i]);
        }
        this.sendString(jsonGetterObject.toJson(list), resp);
    }

    public void methodGetListIssues(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String pupilId = req.getParameter(NAME_ID_PUPIL_PARAM);
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        IssueEntity[] issues = classJournalService.getListIssues(teacherEntity.id, Integer.parseInt(pupilId));
        ListIssueItem[] list = new ListIssueItem[issues.length];
        for(int i = 0; i< list.length; i++ )
        {
            list[i] = new ListIssueItem(issues[i]);
        }
        this.sendString(jsonGetterObject.toJson(list), resp);
    }

    public void methodGetIssue(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String issueID = req.getParameter(NAME_ID_ISSUE_PARAM);
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        IssueEntity issue = classJournalService.getIssue(teacherEntity.id, Integer.parseInt(issueID));
        IssueView issueView = new IssueView(issue);
        this.sendString(jsonGetterObject.toJson(issueView), resp);
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
