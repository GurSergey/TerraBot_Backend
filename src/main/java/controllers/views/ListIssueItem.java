package controllers.views;

import entity.IssueEntity;

public class ListIssueItem
{
    private int id;

    public ListIssueItem(IssueEntity issueEntity) {
        this.id = issueEntity.id;
        this.name = issueEntity.task.name;
        this.complited = issueEntity.completed;
        this.mark = issueEntity.mark;
    }
    private int mark;
    private String name;
    private boolean complited;
}
