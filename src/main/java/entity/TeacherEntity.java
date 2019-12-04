package entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "teacher")
public class TeacherEntity extends UserEntity implements EntityHibernate {

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    public List<PupilEntity> pupils;
}
