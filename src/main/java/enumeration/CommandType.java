package enumeration;

import java.util.ArrayList;

public enum CommandType {
    FORWARD(0), BACK(1), LEFT(2), RIGTH(3), FOR(4),JUMP_FORWARD(5),
    JUMP_BACK(6), JUMP_LEFT(7), JUMP_RIGTH(8);
    private int id;
    private String name;
    CommandType(int id)
    {
        this.id = id;
        this.name = this.toString().toLowerCase();
//       this.clazz = classes[id];
    }

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public static int[] idValues()
    {
        int[] values = new int[CommandType.values().length];
        int i = 0;
        for (CommandType value : CommandType.values())
        {
            values[i] = value.id;
            i++;
        }
        return values;
    }
}
