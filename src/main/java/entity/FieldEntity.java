package entity;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "field")
public class FieldEntity implements EntityHibernate  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id = ID_DEFAULT_VALUE;
    @Column(name = "width")
    public int width;
    @Column(name = "height")
    public int height;
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    public List<CellEntity> cells;
//    @OneToOne
//    @JoinColumn(name = "task_id", referencedColumnName = "id")
//    public TaskEntity task;
}
