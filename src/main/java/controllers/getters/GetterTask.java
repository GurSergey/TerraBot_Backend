package controllers.getters;

public class GetterTask
{
    public class GetterField
    {
        public class GetterCell
        {
            public int getType() {
                return type;
            }

            public int getX() {
                return x;
            }

            public int getY() {
                return y;
            }

            public int type;
            public int x;
            public int y;

        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public GetterCell[] getCells() {
            return cells;
        }

        public int height;
        public int width;
        public GetterCell [] cells;


    }

    public String getDescription() {
        return description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getName() {
        return name;
    }

    public GetterField getField() {
        return field;
    }

    public Integer id;
    public String description;
    public int difficulty;
    public String name;
    public GetterField field;
}