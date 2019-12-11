package entity;

import com.sun.istack.NotNull;

import javax.activation.MimetypesFileTypeMap;
import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "abstract_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id  = ID_DEFAULT_VALUE;
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
    @Column(name = "token", unique = true)
    @NotNull
    public String token;
    @Column(name = "role")
    @NotNull
    public int role;


}
