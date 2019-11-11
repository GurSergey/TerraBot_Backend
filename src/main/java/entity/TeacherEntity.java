package entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "teachers")
public class TeacherEntity {
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
    @OneToMany(mappedBy = "teacher_id")
    public List<PupilEntity> pupils;
}
