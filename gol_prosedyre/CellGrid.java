package gol_prosedyre;

public class CellGrid {
    
    private double boardheight;
    private double boardwidth;    
    private cell[] cells;
    private int nextIndex = 0;
    
    private static CellGrid inst = new CellGrid();
    private CellGrid() { }
    public static CellGrid getInstance() {return inst; }
    
    public void init(double boardheight, double boardwidth) {
        this.boardheight = boardheight;
        this.boardwidth = boardwidth;
        int input = (int)boardheight*(int)boardwidth;
        
        this.cells = new cell[input];
    }
    
    
    public double getBoardHeight() {
        return this.boardheight;
    }
    
    public double getBoardWidth() {
        return this.boardwidth;
    }
    
    public cell[] getCells() {
        return this.cells;
    }
    
    public void createCell(int x, int y, boolean alive) {
        this.cells[nextIndex] = new cell(x, y, alive);
        nextIndex++;
    }
    
    public void setCellState(int x, int y, boolean state) {
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].getCellX() == x && cells[i].getCellY() == y) {
                cells[i].setState(state);
            }
        }
    }
    
    public boolean getCellState(int x, int y) {
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].getCellX() == x && cells[i].getCellY() == y) {
                return cells[i].getState();
            }

        }
        return false;
    }
    
    public void resetCells() {
        nextIndex = 0;
        for (int i = 0; i < boardwidth; i++) {
            for (int j = 0; j < boardheight; j++) {
                this.cells[nextIndex] = new cell(i, j, false);
                nextIndex++;
            }
        }
    }
    
    public void fillDead() {
        int teller = 0;
        for (int i = 0; i < boardwidth; i++) {
            for (int j = 0; j < boardheight; j++) {
                if (!cells[teller].getState()) {
                    this.cells[teller] = new cell(i, j, false);
                }
                teller++;
            }
        }
    }
    
    public void testPrint() {
        int teller = 0;
        for (int i = 0; i < boardwidth; i++) {
            for (int j = 0; j < boardheight; j++) {
                if (this.cells[teller].getState()) {
                    System.out.print("X");
                }
                else {
                    System.out.print("-");
                }
                teller++;
            }
            System.out.println();
        }
    }
    
    public void checkNextGeneration() {
        int neighbours = 0;
        
        for (int i = 0; i < this.boardwidth; i++) {
            for (int j = 0; j < this.boardwidth; j++) {
                if (getCellState(i, j-1)) {
                    neighbours++;
                }
                if (getCellState(i, j+1)) { 
                    neighbours++;
                }
                if (getCellState(i-1, j-1)) { 
                    neighbours++;
                }
                if (getCellState(i-1, j)) { 
                    neighbours++;
                }
                if (getCellState(i-1, j+1)) { 
                    neighbours++;
                }
                if (getCellState(i+1, j-1)) { 
                    neighbours++;
                }
                if (getCellState(i+1, j)) {
                    neighbours++;
                }
                if (getCellState(i+1, j+1)) { 
                    neighbours++;
                }
                setNeighbours(i, j, neighbours);
                neighbours = 0;
            }
            neighbours = 0;
        }
        nextGeneration();
    }
    
    public void setNeighbours(int x, int y, int neighbours) {
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].getCellX() == x && cells[i].getCellY() == y) {
                cells[i].setNeighbours(neighbours);
            }            
        }
    }
    
    public void nextGeneration() {
        // setter cellestatus i henhold til spillets regler
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].getState() == true && cells[i].getNeighbours() < 2) {
                cells[i].setState(false);
            }
            if (cells[i].getState() == true && cells[i].getNeighbours() == 2 || cells[i].getNeighbours() == 3) { // trengs vel egentlig ikke?
                cells[i].setState(true);
            }
            if (cells[i].getState() == true && cells[i].getNeighbours() > 3) {
                cells[i].setState(false);
            }
            if (cells[i].getState() == false && cells[i].getNeighbours() == 3) {
                cells[i].setState(true);
            }
        }
    }
    
}
