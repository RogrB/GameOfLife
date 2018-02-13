package gol_prosedyre;

public class cell {
    
    private int cellX;
    private int cellY;
    private boolean alive;
    private int neighbours;
    
    public cell(int x, int y, boolean alive) {
        this.cellX = x;
        this.cellY = y;
        this.alive = alive;
    }
    
    public int getCellX() {
        return this.cellX;
    }
    
    public int getCellY() {
        return this.cellY;
    }
    
    public boolean getState() {
        return this.alive;
    }
    
    public void setState(boolean state) {
        this.alive = state;
    }
    
    public void setNeighbours(int neighbours) {
        this.neighbours = neighbours;
    }
    
    public int getNeighbours() {
        return this.neighbours;
    }
    
}
