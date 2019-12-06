package enumeration;

import entity.PupilEntity;
import entity.TeacherEntity;

public enum UserRolesEnum {

    PUPIL(0), TEACHER(1), UNKNOWN(-1) ;
    private final String[] names = {"pupil", "teacher"};
    private final Class[] classes = {
        PupilEntity.class, TeacherEntity.class
    };
    private final int id;
    private final String name;
    private final Class clazz;
    private UserRolesEnum(int id)
    {
        this.id = id;
        this.name = names[id];
        this.clazz = classes[id];
    }
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }

    public static UserRolesEnum getById(int id)
    {
        for(UserRolesEnum e : values()) {
            if(e.id==id) return e;
        }
        return UNKNOWN;
    }

}
