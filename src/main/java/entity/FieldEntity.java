package entity;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.OneToMany;
import java.util.List;

public class FieldEntity {
     public int width;
     public int height;
     @OneToMany(mappedBy = "field_id")
     List<CellEntity> cells;

    public TaskEntity task;
}
