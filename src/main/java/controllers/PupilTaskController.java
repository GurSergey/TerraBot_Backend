package controllers;

import entity.CommandEntity;
import entity.IssueEntity;
import entity.PupilEntity;
import entity.TaskEntity;
import services.IssueService;
import services.RepositoryDB;
import services.TaskForPupilService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;

public class PupilTaskController extends Controller {
    private static final String ID_TASK_PARAM = "task_id";
    private TaskForPupilService taskForPupilService;

    private static String STATUS_NOT_STARTED = "not_started";
    private static String STATUS_NOT_VERIFIED = "not_verified";
    private static String STATUS_VERIFIED = "verified";

    private class TaskViewList
    {

        public TaskViewList(String name, int mark, int difficult, String status) {
            this.name = name;
            this.mark = mark;
            this.difficult = difficult;
            this.status = status;
        }

        private String name;
        private int mark;
        private int difficult;
        private String status;

    }

    public PupilTaskController()
    {
        taskForPupilService= new TaskForPupilService(
                new RepositoryDB<>(TaskEntity.class),
                new RepositoryDB<>(PupilEntity.class),
                new RepositoryDB<>(IssueEntity.class));
    }

    public void getList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PupilEntity pupilEntity = (PupilEntity) getUserEntity(PupilEntity.class, req);
        IssueService issueService = new IssueService(
                new RepositoryDB(PupilEntity.class),
                new RepositoryDB(IssueEntity.class),
                new RepositoryDB(TaskEntity.class),
                new RepositoryDB(CommandEntity.class));
        IssueEntity[] issueEntities = issueService.getMyIssues(pupilEntity.id);
        HashMap<Integer, Integer> setIdTaskWithIssue = new HashMap<>();
        for (IssueEntity issueEntity:issueEntities
             ) {
            setIdTaskWithIssue.put(issueEntity.task.id, issueEntity.mark);
        }

        TaskEntity[] taskEntities = taskForPupilService.getList(pupilEntity.id);
        TaskViewList[] taskViewList = new TaskViewList[taskEntities.length];
        for(int i = 0; i < taskEntities.length; i++)
        {
            int mark = setIdTaskWithIssue.getOrDefault(taskEntities[i].id, -1);
            String status;
            if(setIdTaskWithIssue.containsKey(taskEntities[i].id))
            {
                if(mark!=-1)
                    status = STATUS_NOT_VERIFIED;
                else
                    status = STATUS_VERIFIED;
            }
            else
            {
                status = STATUS_NOT_STARTED;
            }
            taskViewList[i] = new TaskViewList(
                    taskEntities[i].name,
                    mark,
                    taskEntities[i].difficulty,
                    status);
        }
        sendString(jsonGetterObject.toJson(taskViewList), resp);
    }

    public void getTask(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PupilEntity pupilEntity = (PupilEntity) getUserEntity(PupilEntity.class, req);
        int idTask = Integer.parseInt(req.getParameter(ID_TASK_PARAM));
        TaskEntity taskEntity = taskForPupilService.getTask(pupilEntity.id, idTask);
        sendString(jsonGetterObject.toJson(taskEntity), resp);
    }
//    public void methodTakeMyIssue(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        PupilEntity pupilEntity = (PupilEntity) getUserEntity(PupilEntity.class, req);
//        int idTask = Integer.parseInt(req.getParameter(ID_TASK_PARAM));
//        IssueEntity issueEntity = taskForPupilService.takeMyIssue(pupilEntity.id,idTask);
//        sendString(jsonGetterObject.toJson(issueEntity), resp);
//    }
}
