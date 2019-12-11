package controllers;

import entity.IssueEntity;
import entity.PupilEntity;
import entity.TeacherEntity;
import services.AuthService;
import services.IssueService;
import services.RepositoryDB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IssueController extends Controller {

    private IssueService issueService;

    private final String NAME_PROGRAM_PARAM = "program";

    public IssueController()
    {
        issueService = new IssueService(
                new RepositoryDB(PupilEntity.class),
                new RepositoryDB(IssueEntity.class));
    }

    public void methodToCheckTeacher(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String programID = req.getParameter(NAME_PROGRAM_PARAM);
        PupilEntity pupilEntity = (PupilEntity) getUserEntity(PupilEntity.class, req);
        issueService.toCheckTeacher(pupilEntity.id,  Integer.parseInt(programID));
        sendStandartAnswer(resp);
    }
    public void methodSaveIssue(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String issue = req.getParameter(NAME_TOKEN_PARAM);
        IssueEntity issueEntity = jsonGetterObject.fromJson(issue,IssueEntity.class);
        PupilEntity pupilEntity = (PupilEntity) getUserEntity(PupilEntity.class, req);
        issueService.saveIssue(pupilEntity.id,  issueEntity);
        sendStandartAnswer(resp);
    }

}
