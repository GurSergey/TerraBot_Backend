package services;

import entity.*;

import java.util.Arrays;


public class IssueService extends AbstractService {
    private Repository<PupilEntity> repositoryPupil;
    private Repository<IssueEntity> repositoryIssue;
    private Repository<TaskEntity> repositoryTask;
    private Repository<CommandEntity> repositoryCommand;

    public IssueService(Repository<PupilEntity> repositoryPupil,
                       Repository<IssueEntity> repositoryIssue,
                        Repository<TaskEntity> repositoryTask,
                        Repository<CommandEntity> repositoryCommand) {
        this.repositoryIssue = repositoryIssue;
        this.repositoryPupil = repositoryPupil;
        this.repositoryTask = repositoryTask;
        this.repositoryCommand = repositoryCommand;
    }

    public IssueEntity[] getMyIssues(int idPupil)
    {
        PupilEntity pupilEntity = repositoryPupil.findById(idPupil);
        return pupilEntity.issues.toArray(new IssueEntity[pupilEntity.issues.size()]);
    }

    public IssueEntity getMyIssue(int idPupil, int idProgram) throws Exception {
        IssueEntity issueEntity = (IssueEntity) repositoryIssue.getBuilderQuery().select().
                where(new SpecificationCriterion("pupil", idPupil)).
                where(new SpecificationCriterion("id", idProgram)).
                getObject();
        notNull(issueEntity, new Exception(""));
        return issueEntity;
    }

    public void toCheckTeacher(int idPupil, int idProgram) throws Exception {
        IssueEntity issueEntity = (IssueEntity) repositoryIssue.getBuilderQuery().select().
                where(new SpecificationCriterion("pupil", idPupil)).
                where(new SpecificationCriterion("id", idProgram)).
                getObject();
        notNull(issueEntity, new Exception(""));
        issueEntity.completed = true;
        repositoryIssue.update(issueEntity);
    }

    public void updateIssue(int idPupil, CommandEntity[]  commandEntities , int idIssue ) throws Exception {
        PupilEntity pupilEntity = repositoryPupil.findById(idPupil);
        notNull(pupilEntity, new Exception());
        IssueEntity issueEntity = repositoryIssue.findById(idIssue);
        notNull(issueEntity, new Exception(""));
        CommandEntity[] commandEntitiesOld = (CommandEntity[]) repositoryCommand.getBuilderQuery().select().
                where(new SpecificationCriterion("issue", idIssue)).
                getObjects();
        for (CommandEntity commandEntityOld: commandEntitiesOld)
        {
            repositoryCommand.delete(commandEntityOld);
        }
        for(CommandEntity commandEntity: commandEntities)
        {
            commandEntity.issue = issueEntity;
        }
        issueEntity.commands = Arrays.asList(commandEntities);
        repositoryIssue.update(issueEntity);
    }

    public void saveIssue(int idPupil, CommandEntity[] commandEntities,
                          Integer idTask) throws Exception {
        PupilEntity pupilEntity = repositoryPupil.findById(idPupil);
        notNull(pupilEntity, new Exception());

        TaskEntity taskEntity = repositoryTask.findById(idTask);
        if(repositoryIssue.getBuilderQuery().select().
                where(new SpecificationCriterion("pupil", pupilEntity.id)).
                where(new SpecificationCriterion("task", taskEntity.id)).
                getObjects().length!=0)
        {
            throw new Exception();
        }

        IssueEntity issueEntity = new IssueEntity();
        for(CommandEntity commandEntity: commandEntities)
        {
            commandEntity.issue = issueEntity;
        }
        issueEntity.pupil = pupilEntity;
        issueEntity.completed = false;
        issueEntity.task = taskEntity;
        issueEntity.commands = Arrays.asList(commandEntities);
        repositoryIssue.save(issueEntity);
    }
}
