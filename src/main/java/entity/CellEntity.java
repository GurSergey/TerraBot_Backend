package entity;

import javax.persistence.*;

@Entity
@Table(name = "cell")
public class CellEntity implements EntityHibernate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id = ID_DEFAULT_VALUE;
    @ManyToOne
    @JoinColumn(name = "field_id")
    public FieldEntity field;
    @Column(name = "type_id")
    public int type;
}
