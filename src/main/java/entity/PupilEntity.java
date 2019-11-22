package entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pupils")
public class PupilEntity implements EntityHibernate {
    public PupilEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "name")
    @NotNull
    public String name;
    @Column(name = "password")
    @NotNull
    public String password;
    @Column(name = "avatar")
    public String avatar;
    @Column(name = "login")
    public String login;
    @Column(name = "token")
    @NotNull
    public String token;
    @OneToOne(mappedBy = "pupil")
    @JoinColumn(name = "teacher_id")
    public TeacherEntity teacher;
    @ManyToMany(mappedBy = "tasks")
    public List<TaskEntity> tasks;

    public List<IssueEntity> issues;
}
