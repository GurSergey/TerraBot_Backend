package entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "pupil")
public class PupilEntity {
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

    @Column(name = "token")
    @NotNull
    public String token;

}
