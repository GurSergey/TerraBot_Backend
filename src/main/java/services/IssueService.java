package services;

import entity.AbstractEntity;
import entity.IssueEntity;
import entity.PupilEntity;


public class IssueService extends AbstractService {
    private Repository<PupilEntity> repositoryPupil;
    private Repository<IssueEntity> repositoryIssue;

    public IssueService(Repository<PupilEntity> repositoryPupil,
                       Repository<IssueEntity> repositoryIssue) {
        this.repositoryIssue = repositoryIssue;
        this.repositoryPupil = repositoryPupil;
    }

    public void toCheckTeacher(int idPupil, int idProgram) throws Exception {
        IssueEntity issueEntity = (IssueEntity) repositoryPupil.getBuilderQuery().select().
                where(new SpecificationCriterion("pupil_id", idPupil)).
                where(new SpecificationCriterion("id", idProgram)).
                getObject();
        notNull(issueEntity, new Exception(""));
        issueEntity.completed = true;
        repositoryIssue.save(issueEntity);
    }
    public void saveIssue(int idPupil, IssueEntity issue) throws Exception {
        PupilEntity pupilEntity = repositoryPupil.findById(idPupil);
        notNull(pupilEntity, new Exception());
        issue.pupil = pupilEntity;
        if(issue.id != AbstractEntity.ID_DEFAULT_VALUE){
            repositoryIssue.save(issue);
        }
        else  {
            IssueEntity issueEntity = repositoryIssue.findById(issue.id);
            notNull(pupilEntity, new Exception());
            repositoryIssue.update(issue);
        }

    }
}
