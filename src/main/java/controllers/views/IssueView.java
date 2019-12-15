package controllers.views;

import entity.CommandEntity;
import entity.IssueEntity;

public class IssueView
{
    private class CommandView
    {
        public CommandView(CommandEntity commandEntity) {
            this.type = commandEntity.type;
            this.number = commandEntity.number;
            this.parentNumber = commandEntity.parent!=null ?
                    commandEntity.parent.number : -1 ;
        }

        private int type;
        private int number;
        private int parentNumber;
    }

    public IssueView(IssueEntity issueEntity) {
        this.id = issueEntity.id;
        this.nameTask = issueEntity.task.name;
        this.description = issueEntity.task.description;
        this.countStep = issueEntity.countStep;
        this.countCommand = issueEntity.countCommand;
        this.commands = new CommandView[issueEntity.commands.size()];
        for(int i = 0; i < commands.length; i++)
        {
            this.commands[i] = new CommandView(issueEntity.commands.get(i));
        }
    }
    private int id;
    private String nameTask;
    private String description;
    private int countStep;
    private int countCommand;
    private CommandView[] commands;

}