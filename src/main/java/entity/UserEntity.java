package entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {
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
    @Column(name = "token", unique = true)
    @NotNull
    public String token;
    @Column(name = "role")
    @NotNull
    public int role;
}
