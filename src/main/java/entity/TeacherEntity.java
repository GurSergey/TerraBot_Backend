package entity;

import com.sun.istack.NotNull;
import enumeration.UserRolesEnum;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "teacher")
public class TeacherEntity extends UserEntity implements EntityHibernate {

    public TeacherEntity(){
        role = UserRolesEnum.TEACHER.getId();
    }
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    public List<PupilEntity> pupils;
}
