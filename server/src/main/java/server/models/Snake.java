package server.models;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Cell> snakeParts = new ArrayList<>();

    public Snake() {
        addSnakePart(new Cell(0, 0, CellType.SNAKE));
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

    public Cell getTailSnake() {
        return this.snakeParts.get(0);
    }

    public Cell getHeadSnake() {
        return this.snakeParts.get(snakeParts.size());
    }

    public void setSnakeParts(List<Cell> snakeParts) {
        this.snakeParts = snakeParts;
    }
}
