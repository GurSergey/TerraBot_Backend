package entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "issue")
public class IssueEntity implements EntityHibernate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id = ID_DEFAULT_VALUE;
    @Column(name = "mark")
    public int mark;
    @Column(name = "completed")
    @NotNull
    public boolean completed;
    @ManyToOne
    @JoinColumn(name = "pupil_id")
    public PupilEntity pupil;
    @OneToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    public TaskEntity task;
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<CommandEntity> commands;
    @Column(name = "count_command")
    public int countCommand;
    @Column(name = "count_step")
    public int countStep;

}
