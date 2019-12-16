package controllers.views;

import entity.CellEntity;
import entity.FieldEntity;
import entity.TaskEntity;

public class TaskView
{
    private class FieldView
    {
        private class CellView
        {
            private int type;
            private int x;
            private int y;
            public CellView(CellEntity cellEntity)
            {
                type = cellEntity.type;
                x = cellEntity.x;
                y = cellEntity.y;
            }
        }

        private int height;
        private int width;
        CellView [] cells;

        public FieldView(FieldEntity fieldEntity)
        {
            height = fieldEntity.height;
            width = fieldEntity.width;
            cells = new CellView[fieldEntity.cells.size()];
            for(int i = 0; i < cells.length; i++)
            {
                cells[i] = new CellView(fieldEntity.cells.get(i));
            }
        }
    }
    private String description;
    private int difficulty;
    private String name;
    private FieldView field;

    public TaskView(TaskEntity taskEntity)
    {
        description = taskEntity.description;
        difficulty = taskEntity.difficulty;
        name = taskEntity.name;
        field = new FieldView(taskEntity.field);
    }
}