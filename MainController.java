package gol_prosedyre;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Slider;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Button;
import patterns.ShapeCreator;

public class MainController implements Initializable {
    
    private static MainController inst = new MainController();
    public static MainController getInstance() { return inst; } 
    
    // Grid variabler
    private double boardwidth = 60;
    private double boardheight = 60;
    private double cellSize = 13;
    private Color farge = Color.YELLOW;

    CellGrid cells = CellGrid.getInstance();
    ShapeCreator shape = new ShapeCreator();
    
    @FXML private MenuBar menuBar;
    @FXML private Canvas graphics;
    @FXML private ColorPicker colorPicker;
    @FXML private ChoiceBox dropdown;
    @FXML private Slider sizeSlider;
    @FXML private Slider FPSSlider;
    @FXML private Button simKnapp;
    
    private double FPS = 2;
    private Timeline timeline = new Timeline();
    
    
    @FXML private void handleButtonAction(ActionEvent event) {
        // knappetrykk "nextgeneration"
        printCells();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cells.init(boardheight, boardwidth);
	colorPicker.setValue(farge); 
        shape.Glider();
	draw();
        
        ObservableList<String> premades = FXCollections.observableArrayList("Glider","Small Exploder","Exploder","10 Cell Row","Lightweight Spaceship","Tumbler","Clear");  // dropdown
        dropdown.setItems(premades);
        

        Duration duration = Duration.millis(1000/FPS);
        KeyFrame keyframe = new KeyFrame(duration, (ActionEvent e) -> {draw();});
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(keyframe);    
        timeline.setRate(FPS);
    }
    
    public CellGrid getCellGrid() {
        return this.cells;
    }
    
    
    private void draw() {
    	GraphicsContext gc = graphics.getGraphicsContext2D();
	gc.clearRect(0, 0, graphics.widthProperty().doubleValue(), graphics.heightProperty().doubleValue());
        
        for (int i = 0; i < boardwidth; i++) {
            for (int j = 0; j < boardheight; j++) {
                if (cells.getCellState(j, i)) {
                    gc.setFill(farge);
                }                                                                                  
                else {
                    gc.setFill(Color.DARKGREY);
                }
                gc.fillRect(i*cellSize+(i), j*cellSize+(j), cellSize, cellSize);
            }
        }
        if(timeline.getStatus() == Animation.Status.RUNNING) {  
            cells.checkNextGeneration();
        }
    }    
        
    public void exitEvent(ActionEvent event) {
        // Exit event
    	System.exit(0);
    }
	
    public void clearEvent(ActionEvent event) {
        // Clear button
        cells.resetCells();
        setPattern();
        draw();
    }
	
    public void newColorEvent(ActionEvent event) {
        // Colorpicker
        farge = colorPicker.getValue();
	draw();
    }
	
    public void newSizeEvent(MouseEvent e) {
        // Scale Slider
        this.cellSize = (sizeSlider.getValue());
	draw();
    }        
    
    public void printCells() {
        // Knappetrykk
        cells.checkNextGeneration();
        draw();
        
    }   
    
    public void setPattern() {
        // Dropdown meny - velger mønster
        String state = dropdown.getValue().toString();
        switch (state) {
            case "Glider":
                shape.Glider();
                draw();
                break;
            case "Small Exploder":
                shape.SmallExploder();
                draw();
                break;
            case "Exploder":
                shape.Exploder();
                draw();
                break;
            case "10 Cell Row":
                shape.TenCellRow();
                draw();
                break;
            case "Lightweight Spaceship": 
                shape.LighWeightSpaceShip();
                draw();
                break;     
            case "Tumbler":
                shape.Tumbler();
                draw();
                break;
            case "Clear":
                cells.resetCells();
                draw();
                break;
        }
	draw();        
    }
    
    public void simulate() {
        // Knappetrykk simulate
        if(timeline.getStatus() == Animation.Status.RUNNING) {
                timeline.pause();
                simKnapp.setText("Sim is Off");
                draw();
                
        } else {
                timeline.play();
                simKnapp.setText("Sim is On");
        }        
    }
    
    public void setFPS() {
        // sliderbar - velg FPS
        this.FPS = FPSSlider.getValue();
        timeline.setRate(this.FPS);
        draw();
    }
    
    public void clicked(MouseEvent e) {  
        // Onclick Event - for å kunne trykke på individuelle celler
        double x = e.getX();
        double y = e.getY();
        
        double rectX;
        double rectY;

        for (int i = 0; i < boardwidth; i++) {
            for (int j = 0; j < boardheight; j++) {
                rectX = i*cellSize+(i);
                rectY = j*cellSize+(j);
                
                if (x > rectX && x < rectX+cellSize) {
                    if (y > rectY && y < rectY+cellSize) {
                        
                        if (cells.getCellState(j, i)) {
                            cells.setCellState(j, i, false); 
                            draw();
                        }
                        else {
                            cells.setCellState(j, i, true);
                            draw();
                        }
                    }
                }
            }
        }
    }
    
}
