package enumeration;

public enum CellType {
    PLAYER(0), STONE(1), PIT(2), EMPTY(3), WALL(4);
    private int id;
    CellType(int id) {
        this.id = id;
    }
}
