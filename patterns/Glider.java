package patterns;

import gol_prosedyre.cell;
import gol_prosedyre.CellGrid;

public class Glider extends Shape {
    
    CellGrid cells = CellGrid.getInstance();
    
    @Override
    public void createPattern() {
        int nextIndex = 0;
        for (int i = 0; i < cells.getBoardWidth(); i++) {
            for (int j = 0; j < cells.getBoardHeight(); j++) {
                if (i < 1) {
                    cells.getCells()[nextIndex] = new cell(i, j, false);
                    nextIndex++;
                }
                if (i == 1) {
                    if (j == 2) {
                        cells.getCells()[nextIndex] = new cell(i, j, true);
                        nextIndex++;
                    }
                    else {
                        cells.getCells()[nextIndex] = new cell(i, j, false);
                        nextIndex++;
                    }
                }
                if (i == 2) {
                    if (j == 3) {
                        cells.getCells()[nextIndex] = new cell(i, j, true);
                        nextIndex++;                        
                    }
                    else {
                        cells.getCells()[nextIndex] = new cell(i, j, false);
                        nextIndex++;                        
                    }
                }
                if (i == 3) {
                    if (j == 1 || j == 2 || j == 3) {
                        cells.getCells()[nextIndex] = new cell(i, j, true);
                        nextIndex++;                        
                    }
                    else {
                        cells.getCells()[nextIndex] = new cell(i, j, false);
                        nextIndex++;                        
                    }
                }
                if (i > 3) {
                    cells.getCells()[nextIndex] = new cell(i, j, false);
                    nextIndex++;                       
                }
            }
        }
    } 
    
}