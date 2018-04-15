package patterns;

import gol_prosedyre.cell;
import gol_prosedyre.CellGrid;

public class Exploder extends Shape {
    
    CellGrid cells = CellGrid.getInstance();
    
    @Override
    public void createPattern() {
        cells.resetCells();
        int nextindex = 0;
        int startdown = ((int)cells.getBoardHeight()/2)-5;
        int startleft = ((int)cells.getBoardWidth()/2)-5;

        for (int i = 0; i < cells.getBoardWidth(); i++) {
            for (int j = 0; j < cells.getBoardHeight(); j++) {
                if (i == startdown) {
                   if (j == startleft) {
                       cells.getCells()[nextindex] = new cell(i, j, true);
                   }
                   if (j == startleft+2) {
                       cells.getCells()[nextindex] = new cell(i, j, true);
                   }
                   if (j == startleft+4) {
                       cells.getCells()[nextindex] = new cell(i, j, true);
                   }
                }
                if (i > startdown && i < startdown+4) {
                    if (j == startleft) {
                        cells.getCells()[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+4) {
                        cells.getCells()[nextindex] = new cell(i, j, true);
                    }
                }
                if (i == startdown+4) {
                    if (j == startleft) {
                        cells.getCells()[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+2) {
                        cells.getCells()[nextindex] = new cell(i, j, true);
                    }
                    if (j == startleft+4) {
                        cells.getCells()[nextindex] = new cell(i, j, true);
                    }
                }
                nextindex++;
            }
        }
        cells.fillDead();
    } 
    
}
