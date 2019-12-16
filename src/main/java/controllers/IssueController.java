package controllers;

import controllers.getters.GetterCommand;
import controllers.getters.GetterIssue;
import controllers.views.IssueView;
import controllers.views.ListIssueItem;
import entity.*;
import services.IssueService;
import services.RepositoryDB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IssueController extends Controller {

    private IssueService issueService;

    private final String NAME_TASK_PARAM = "task";
    private final String NAME_ID_ISSUE_PARAM = "issue";
    private final String NAME_ID_COMMAND_PARAM = "commands";

    public IssueController()
    {
        issueService = new IssueService(
                new RepositoryDB(PupilEntity.class),
                new RepositoryDB(IssueEntity.class),
                new RepositoryDB(TaskEntity.class),
                new RepositoryDB(CommandEntity.class));
    }

    public void methodGetMyIssues(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PupilEntity pupilEntity = (PupilEntity) getUserEntity(PupilEntity.class, req);
        IssueEntity[] issues = issueService.getMyIssues(pupilEntity.id);
        ListIssueItem[] list = new ListIssueItem[issues.length];
        for(int i = 0; i< list.length; i++ )
        {
            list[i] = new ListIssueItem(issues[i]);
        }
        this.sendString(jsonGetterObject.toJson(list), resp);
    }

    public void methodGetMyIssue(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String issueID = req.getParameter(NAME_ID_ISSUE_PARAM);
        PupilEntity pupilEntity = (PupilEntity) getUserEntity(PupilEntity.class, req);
        IssueEntity issue = issueService.getMyIssue(pupilEntity.id, Integer.parseInt(issueID));
        IssueView issueView = new IssueView(issue);
        this.sendString(jsonGetterObject.toJson(issueView), resp);
    }

    public void methodToCheckTeacher(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String programID = req.getParameter(NAME_ID_ISSUE_PARAM);
        PupilEntity pupilEntity = (PupilEntity) getUserEntity(PupilEntity.class, req);
        issueService.toCheckTeacher(pupilEntity.id,  Integer.parseInt(programID));
        sendStandartAnswer(resp);
    }
    public void methodSaveIssue(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String issue = req.getParameter(NAME_ID_ISSUE_PARAM);
        String task = req.getParameter(NAME_TASK_PARAM);
        String commands = req.getParameter(NAME_ID_COMMAND_PARAM);
        GetterIssue getterIssue = jsonGetterObject.fromJson(commands, GetterIssue.class);
        PupilEntity pupilEntity = (PupilEntity) getUserEntity(PupilEntity.class, req);
        CommandEntity[] commandEntities = new CommandEntity[getterIssue.commands.length];
        for(int i = 0; i < commandEntities.length; i++ )
        {
            commandEntities[i] = new CommandEntity();
            commandEntities[i].number = getterIssue.commands[i].number;
            commandEntities[i].type = getterIssue.commands[i].type;
            commandEntities[i].repeat = getterIssue.commands[i].count;
            if(getterIssue.commands[i].parent != GetterCommand.DEFAULT_PARENT_NUM)
            {
                commandEntities[i].parent = commandEntities[commandEntities[i].number];
            }
        }

        if(getterIssue.issue!=null)
            issueService.updateIssue(pupilEntity.id, commandEntities, getterIssue.issue);
        if(getterIssue.task!=null)
            issueService.saveIssue(pupilEntity.id, commandEntities, getterIssue.task);

        sendStandartAnswer(resp);
    }

}
