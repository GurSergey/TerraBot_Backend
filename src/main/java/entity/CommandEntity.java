package entity;

import javax.persistence.*;

@Entity
@Table(name = "command")
public class CommandEntity implements EntityHibernate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id = ID_DEFAULT_VALUE;
    @Column(name = "type")
    public int type;
    @Column(name = "number")
    public int number;
    @Column(name = "repeat")
    public int repeat;
    @ManyToOne
    @JoinColumn(name = "issue_id")
    public IssueEntity issue;
    @OneToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public CommandEntity parent;

//    public validate()
//    {
//        if(type)
//    }
}
