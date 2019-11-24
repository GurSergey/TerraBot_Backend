package entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "teacher")
public class TeacherEntity implements EntityHibernate {
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
    @Column(name = "login", unique = true)
    public String login;
    @Column(name = "token")
    @NotNull
    public String token;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    public List<PupilEntity> pupils;
}
