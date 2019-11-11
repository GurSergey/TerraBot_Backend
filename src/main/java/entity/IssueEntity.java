package entity;

import javax.persistence.OneToMany;
import java.util.List;

public class IssueEntity {
    public int mark;
    public boolean completed;
    public PupilEntity owner;
    public TaskEntity task;
    @OneToMany(mappedBy = "teacher")
    public List<PupilEntity> commands;

}
