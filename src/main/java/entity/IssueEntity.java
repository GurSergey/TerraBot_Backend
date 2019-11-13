package entity;

import javax.persistence.OneToMany;
import java.util.List;

public class IssueEntity {
    public int id;
    public int mark;
    public boolean completed;
    public PupilEntity owner;
    public TaskEntity task;
    @OneToMany(mappedBy = "teacher")
    public List<PupilEntity> commands;
    public int countCommand;
    public int countStep;

}
