package server.models;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Cell head;
    private List<Cell> snakeParts = new ArrayList<>();

    public Snake() {
        addSnakePart(new Cell(0,0,CellType.SNAKE));
    }

    public List<Cell> getSnakeParts() {
        return snakeParts;
    }

    public void addSnakePart(Cell cell) {
        this.snakeParts.add(cell);
    }

    public void removeTailSnake() {
        this.snakeParts.remove(0);
    }

    public void setSnakeParts(List<Cell> snakeParts) {
        this.snakeParts = snakeParts;
    }
}
