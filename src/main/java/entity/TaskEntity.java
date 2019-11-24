package entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
public class TaskEntity implements EntityHibernate {
    public TaskEntity() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "name")
    @NotNull
    public String name;
    @Column(name = "description")
    @NotNull
    public String description;
    @Column(name = "difficulty")
    @NotNull
    public int difficulty;
    @OneToOne
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    public FieldEntity field;
    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    public TeacherEntity owner;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "pupil_to_task",
            joinColumns = { @JoinColumn(name = "task_id") },
            inverseJoinColumns = { @JoinColumn(name = "pupil_id") }
    )
    public List<PupilEntity> pupils;
}
