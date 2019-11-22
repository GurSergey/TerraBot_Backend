package entity;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

public class TaskEntity implements EntityHibernate {
    public int id;
    public String description;
    public int difficulty;
    public FieldEntity field;
    public TeacherEntity owner;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "pupil_to_task",
            joinColumns = { @JoinColumn(name = "task_id") },
            inverseJoinColumns = { @JoinColumn(name = "pupil_id") }
    )
    public List<PupilEntity> pupils;
}
