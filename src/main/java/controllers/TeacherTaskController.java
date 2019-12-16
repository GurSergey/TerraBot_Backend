package controllers;

import controllers.getters.GetterTask;
import controllers.views.TaskView;
import entity.*;
import services.RepositoryDB;
import services.TaskForTeacherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class TeacherTaskController extends Controller {
    private static final String ID_TASK_PARAM = "task";
    private static final String TASK_PARAM = "task";
    private TaskForTeacherService taskForTeacherService;




    public TeacherTaskController()
    {
        taskForTeacherService = new TaskForTeacherService(
                new RepositoryDB<>(TeacherEntity.class),
                new RepositoryDB<>(TaskEntity.class),
                new RepositoryDB<>(FieldEntity.class));
    }

    public void methodSaveTask(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        GetterTask getter = jsonGetterObject.fromJson(req.getParameter(TASK_PARAM),GetterTask.class);
        TaskEntity taskEntity = new TaskEntity();
        boolean isNew = false;
        if(getter.id!=null)
            taskEntity.id = getter.id;
        else
            isNew = true;
        taskEntity.name = getter.name;
        taskEntity.difficulty = getter.difficulty;
        taskEntity.description = getter.description;
        taskEntity.field = new FieldEntity();
        taskEntity.field.width = getter.field.width;
        taskEntity.field.height = getter.field.height;
        taskEntity.field.cells = new ArrayList<>();
        for (GetterTask.GetterField.GetterCell cell: getter.field.cells)
        {
            CellEntity cellEntity = new CellEntity();
            cellEntity.x = cell.x;
            cellEntity.y = cell.y;
            cellEntity.type = cell.type;
            cellEntity.field = taskEntity.field;
            taskEntity.field.cells.add(cellEntity);
        }
        if(isNew)
            taskForTeacherService.saveTask(teacherEntity.id, taskEntity);
        else
            taskForTeacherService.updateTask(teacherEntity.id, taskEntity);
        sendStandartAnswer(resp);
    }

    public void methodGetTask(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        int idTask = Integer.parseInt(req.getParameter(ID_TASK_PARAM));
        TaskEntity taskEntity = taskForTeacherService.getTask(teacherEntity.id, idTask);
        TaskView taskView = new TaskView(taskEntity);
        sendString(jsonGetterObject.toJson(taskView), resp);
    }

    public void methodGetMyTasks(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        TeacherEntity teacherEntity = (TeacherEntity) getUserEntity(TeacherEntity.class, req);
        TaskEntity[] taskEntities = taskForTeacherService.getMyTasks(teacherEntity.id);
        sendString(jsonGetterObject.toJson(taskEntities), resp);
    }
}
