package controllers;

import entity.PupilEntity;
import entity.TaskEntity;
import entity.TeacherEntity;
import services.RepositoryDB;
import services.TaskForTeacherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TeacherTaskController extends Controller {
    private static final String ID_TASK_PARAM = "task_id";
    private static final String TASK_PARAM = "task";
    private TaskForTeacherService taskForTeacherService;

    public TeacherTaskController()
    {
        taskForTeacherService = new TaskForTeacherService(
                new RepositoryDB<>(TeacherEntity.class),
                new RepositoryDB<>(TaskEntity.class));
    }

    public void methodSaveTask(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        TaskEntity taskEntity = jsonGetterObject.fromJson(req.getParameter(TASK_PARAM),TaskEntity.class);
        taskForTeacherService.saveTask(teacherEntity.id,taskEntity);
        sendStandartAnswer(resp);
    }

    public void methodGetTask(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        int idTask = Integer.parseInt(req.getParameter(ID_TASK_PARAM));
        TaskEntity taskEntity = taskForTeacherService.getTask(teacherEntity.id, idTask);
        sendString(jsonGetterObject.toJson(taskEntity), resp);
    }
    public void methodGetMyTasks(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        TaskEntity[] taskEntities = taskForTeacherService.getMyTasks(teacherEntity.id);
        sendString(jsonGetterObject.toJson(taskEntities), resp);
    }
}
