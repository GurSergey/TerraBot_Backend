package controllers;

import entity.IssueEntity;
import entity.PupilEntity;
import entity.TaskEntity;
import services.RepositoryDB;
import services.TaskForPupilService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PupilTaskController extends Controller {
    private static final String ID_TASK_PARAM = "task_id";
    private TaskForPupilService taskForPupilService;

    public PupilTaskController()
    {
        taskForPupilService= new TaskForPupilService(
                new RepositoryDB<>(TaskEntity.class),
                new RepositoryDB<>(PupilEntity.class),
                new RepositoryDB<>(IssueEntity.class));
    }

    public void getList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PupilEntity pupilEntity = (PupilEntity) getUserEntity(PupilEntity.class, req);
        TaskEntity[] taskEntities = taskForPupilService.getList(pupilEntity.id);
        sendString(jsonGetterObject.toJson(taskEntities), resp);
    }

    public void getTask(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PupilEntity pupilEntity = (PupilEntity) getUserEntity(PupilEntity.class, req);
        int idTask = Integer.parseInt(req.getParameter(ID_TASK_PARAM));
        TaskEntity taskEntity = taskForPupilService.getTask(pupilEntity.id,idTask);
        sendString(jsonGetterObject.toJson(taskEntity), resp);
    }
    public void methodTakeMyIssue(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PupilEntity pupilEntity = (PupilEntity) getUserEntity(PupilEntity.class, req);
        int idTask = Integer.parseInt(req.getParameter(ID_TASK_PARAM));
        IssueEntity issueEntity = taskForPupilService.takeMyIssue(pupilEntity.id,idTask);
        sendString(jsonGetterObject.toJson(issueEntity), resp);
    }
}
