package gol_prosedyre;
// testtest
public class CellGrid {
    
    private double boardheight;
    private double boardwidth;    
    private cell[] cells;
    private int nextIndex = 0;
    
    public CellGrid(double boardheight, double boardwidth) {
        // Constructor
        this.boardheight = boardheight;
        this.boardwidth = boardwidth;
        int input = (int)boardheight*(int)boardwidth;
        
        this.cells = new cell[input]; // Constructor vs declared?
    }
    
    public void createCell(int x, int y, boolean alive) {
        // metode som oppretter en individuell celle
        this.cells[nextIndex] = new cell(x, y, alive);
        nextIndex++;
    }
    
    public void setCellState(int x, int y, boolean state) {
        // Metode for å sette individuell cellestatus
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].getCellX() == x && cells[i].getCellY() == y) {
                cells[i].setState(state);
            }
        }
    }
    
    public boolean getCellState(int x, int y) {
        // Metode for å sjekke individuell cellestatus
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].getCellX() == x && cells[i].getCellY() == y) {
                return cells[i].getState();
            }

        }
        return false;
    }
    
    public void resetCells() {
        // Setter alle celler til dead
        nextIndex = 0;
        for (int i = 0; i < boardwidth; i++) {
            for (int j = 0; j < boardheight; j++) {
                this.cells[nextIndex] = new cell(i, j, false);
                nextIndex++;
            }
        }
    }
    
    public void createGlider() {
        // Metode som lager en "Glider"
        nextIndex = 0;
        for (int i = 0; i < boardwidth; i++) {
            for (int j = 0; j < boardheight; j++) {
                if (i < 1) {
                    this.cells[nextIndex] = new cell(i, j, false);
                    nextIndex++;
                }
                if (i == 1) {
                    if (j == 2) {
                        this.cells[nextIndex] = new cell(i, j, true);
                        nextIndex++;
                    }
                    else {
                        this.cells[nextIndex] = new cell(i, j, false);
                        nextIndex++;
                    }
                }
                if (i == 2) {
                    if (j == 3) {
                        this.cells[nextIndex] = new cell(i, j, true);
                        nextIndex++;                        
                    }
                    else {
                        this.cells[nextIndex] = new cell(i, j, false);
                        nextIndex++;                        
                    }
                }
                if (i == 3) {
                    if (j == 1 || j == 2 || j == 3) {
                        this.cells[nextIndex] = new cell(i, j, true);
                        nextIndex++;                        
                    }
                    else {
                        this.cells[nextIndex] = new cell(i, j, false);
                        nextIndex++;                        
                    }
                }
                if (i > 3) {
                    this.cells[nextIndex] = new cell(i, j, false);
                    nextIndex++;                       
                }
            }
        }
    }
    
    public void createSmallExploder() {
        // Metode som lager en "Small exploder"
        int nextindex = 0; // tellevariabel
        int startdown = ((int)this.boardheight/2)-5; // hvilken rad exploderen skal starte
        int startleft = ((int)this.boardwidth/2)-5; // hvilken kollonne exploderen skal starte
        
        for (int i = 0; i < boardwidth; i++) {
            for (int j = 0; j < boardheight; j++) {
                if (i == startdown) {
                    if (j == startleft+1) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                }
                if (i == startdown+1) {
                    if (j == startleft) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+1) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }    
                    if (j == startleft+2) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }        
                }
                if (i == startdown+2) {
                    if (j == startleft) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+2) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                }
                if (i == startdown+3) {
                    if (j == startleft+1) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                }
                nextindex++;
            }
        }
        fillDead();
    }
    
    public void createExploder() {
        // lager en "Exploder"
        int nextindex = 0; // tellevariabel;
        int startdown = ((int)this.boardheight/2)-5;
        int startleft = ((int)this.boardwidth/2)-5;

        for (int i = 0; i < boardwidth; i++) {
            for (int j = 0; j < boardheight; j++) {
                if (i == startdown) {
                   if (j == startleft) {
                       this.cells[nextindex] = new cell(i, j, true);
                   }
                   if (j == startleft+2) {
                       this.cells[nextindex] = new cell(i, j, true);
                   }
                   if (j == startleft+4) {
                       this.cells[nextindex] = new cell(i, j, true);
                   }
                }
                if (i > startdown && i < startdown+4) {
                    if (j == startleft) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+4) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                }
                if (i == startdown+4) {
                    if (j == startleft) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+2) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+4) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                }
                nextindex++;
            }
        }
        fillDead();
    }
    
    public void createTenRow() {
        // lager en 10-cell-row
        int nextindex = 0;
        int startdown = ((int)this.boardheight/2)-5;
        int startleft = ((int)this.boardwidth/2)-7;
        
        for (int i = 0; i < boardwidth; i++) {
            for (int j = 0; j < boardheight; j++) {
                if (i == startdown) {
                    if (j >= startleft && j < startleft+10) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                }
                nextindex++;
            }
        }
        fillDead();
    }
    
    public void createSpaceShip() {
        // lager et "lightweight spaceship"
        int nextindex = 0;
        int startdown = ((int)this.boardheight/2)-5;
        int startleft = ((int)this.boardwidth/2)-7;
        
        for (int i = 0; i < boardwidth; i++) {
            for (int j = 0; j < boardheight; j++) {
                if (i == startdown) {
                    if (j > startleft && j < startleft+5) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                }
                if (i == startdown+1) {
                    if (j == startleft) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+4) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                }
                if (i == startdown+2) {
                    if (j == startleft+4) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                }
                if (i == startdown+3) {
                    if (j == startleft) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+3) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                }
                nextindex++;
            }
        }
        fillDead();
    }
    
    public void createTumbler() {
        // lager en "tumbler"
        int nextindex = 0;
        int startdown = ((int)this.boardheight/2)-5;
        int startleft = ((int)this.boardwidth/2)-7;
        
        for (int i = 0; i < boardwidth; i++) {
            for (int j = 0; j < boardheight; j++) {   
                if (i == startdown) {
                    if (j == startleft+1) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+2) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+4) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+5) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                }
                if (i == startdown+1) {
                    if (j == startleft+1) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+2) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+4) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+5) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }                    
                }
                if (i == startdown+2) {
                    if (j == startleft+2) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+4) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                }
                if (i == startdown+3) {
                    if (j == startleft) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+2) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+4) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+6) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                }
                if (i == startdown+4) {
                    if (j == startleft) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+2) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+4) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+6) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                }   
                if (i == startdown+5) {
                    if (j == startleft) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+1) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+5) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+6) {
                        this.cells[nextindex] = new cell(i, j, true);
                    }
                }
                nextindex++;
            }   
        }
        fillDead();
    }
    
    public void fillDead() {
        // Metode for å fylle in døde celler i arrayet
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
        // Printer ut grid med celler til konsoll
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
        // Metode som teller antall naboer for hver individuelle celle
        int neighbours = 0;
        
        for (int i = 0; i < this.boardwidth; i++) {
            for (int j = 0; j < this.boardwidth; j++) {
                if (getCellState(i, j-1)) { // sjekker til venstre
                    neighbours++;
                }
                if (getCellState(i, j+1)) { // sjekker til høyre
                    neighbours++;
                }
                if (getCellState(i-1, j-1)) { // sjekker oppe til venstre
                    neighbours++;
                }
                if (getCellState(i-1, j)) { // sjekker over
                    neighbours++;
                }
                if (getCellState(i-1, j+1)) { // sjekker oppe til høyre
                    neighbours++;
                }
                if (getCellState(i+1, j-1)) { // sjekker nede til venstre
                    neighbours++;
                }
                if (getCellState(i+1, j)) { // sjekker under
                    neighbours++;
                }
                if (getCellState(i+1, j+1)) { // sjekker nede til høyre
                    neighbours++;
                }
                setNeighbours(i, j, neighbours); // kaller metode for å skrive antall naboer til celleobjekt
                neighbours = 0;
            }
            neighbours = 0;
        }
        nextGeneration();
    }
    
    public void setNeighbours(int x, int y, int neighbours) {
        // metode som skriver antall naboer til celleobjekt
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].getCellX() == x && cells[i].getCellY() == y) {
                cells[i].setNeighbours(neighbours);
            }            
        }
    }
    
    public void nextGeneration() {
        // metode som setter cellestatus i henhold til spillets regler
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
    
    public void testNextGeneration() {
        // testmetode for ny generation - setter levende -> død og omvendt
        int teller = 0;
        for (int i = 0; i < boardwidth; i++) {
            for (int j = 0; j < boardheight; j++) {
                if (cells[teller].getState()) {
                    this.cells[teller].setState(false);
                }
                else {
                    this.cells[teller].setState(true);
                }
                teller++;
            }
        }
    }
    
}
