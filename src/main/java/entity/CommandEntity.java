package entity;

public class CommandEntity implements EntityHibernate {
    public int id;
    public int type;
    public int number;
    public IssueEntity issie;
    public CommandEntity parent;
}
