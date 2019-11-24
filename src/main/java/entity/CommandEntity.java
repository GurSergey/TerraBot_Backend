package entity;

import javax.persistence.*;

@Entity
@Table(name = "command")
public class CommandEntity implements EntityHibernate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "type")
    public int type;
    @Column(name = "number")
    public int number;
    @ManyToOne
    @JoinColumn(name = "issue_id")
    public IssueEntity issue;
    @OneToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public CommandEntity parent;
}
