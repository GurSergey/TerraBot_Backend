package controllers.views;

import entity.IssueEntity;

public class ListIssueItem
{
    private int id;

    public ListIssueItem(IssueEntity issueEntity) {
        this.id = issueEntity.id;
        this.name = issueEntity.task.name;
        this.complited = issueEntity.completed;
    }

    private String name;
    private boolean complited;
}
