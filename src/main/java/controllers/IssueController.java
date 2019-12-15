package controllers;

import controllers.views.IssueView;
import controllers.views.ListIssueItem;
import entity.*;
import enumeration.CommandType;
import services.AuthService;
import services.IssueService;
import services.RepositoryDB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class IssueController extends Controller {

    private IssueService issueService;

    private final String NAME_TASK_PARAM = "task";
    private final String NAME_ID_ISSUE_PARAM = "issue";
    private final String NAME_ID_COMMAND_PARAM = "commands";
    private class CommandGet
    {
        final int DEFAULT_PARENT_NUM = -1;
        private int type;
        private int number;
        private int parent=DEFAULT_PARENT_NUM;
        private int count;
    }

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
        CommandGet[] commandGets = jsonGetterObject.fromJson(commands, CommandGet[].class);
        PupilEntity pupilEntity = (PupilEntity) getUserEntity(PupilEntity.class, req);
        CommandEntity[] commandEntities = new CommandEntity[commandGets.length];
        for(int i = 0; i < commandEntities.length; i++ )
        {
            //HashMap<Integer, CommandEntity> map = new HashMap<>();
            commandEntities[i] = new CommandEntity();
            commandEntities[i].number = commandGets[i].number;
            commandEntities[i].type = commandGets[i].type;
            commandEntities[i].repeat = commandGets[i].count;
            if(commandGets[i].parent != commandGets[i].DEFAULT_PARENT_NUM)
            {
                commandEntities[i].parent = commandEntities[commandEntities[i].number];
            }
            //map.put(commandEntities[i].number, commandEntities[i]);
        }

        if(issue!=null)
            issueService.updateIssue(pupilEntity.id, commandEntities, Integer.parseInt(issue));
        if(task!=null)
            issueService.saveIssue(pupilEntity.id, commandEntities, Integer.parseInt(task));

        sendStandartAnswer(resp);
    }

}
