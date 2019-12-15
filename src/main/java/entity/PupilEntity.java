package entity;

import com.sun.istack.NotNull;
import enumeration.UserRolesEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pupil")
public class PupilEntity extends UserEntity implements EntityHibernate {
    public PupilEntity() {
        role = UserRolesEnum.PUPIL.getId();
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    public TeacherEntity teacher;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "pupil_to_task",
            joinColumns = { @JoinColumn(name = "pupil_id") },
            inverseJoinColumns = { @JoinColumn(name = "task_id") }
    )
    public List<TaskEntity> tasks;
    @OneToMany(mappedBy = "pupil", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<IssueEntity> issues;
}
