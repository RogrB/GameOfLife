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

public class MainController implements Initializable {
    
    // Grid variabler
    private double boardwidth = 60;
    private double boardheight = 60;
    private double cellSize = 13;
    private Color farge = Color.YELLOW;

    CellGrid cells = new CellGrid(boardheight, boardwidth); 
    
    // FXML Deklarering
    @FXML private MenuBar menuBar;
    @FXML private Canvas graphics;
    @FXML private ColorPicker colorPicker;
    @FXML private ChoiceBox dropdown;
    @FXML private Slider sizeSlider;
    @FXML private Slider FPSSlider;
    @FXML private Button simKnapp;
    
    // Animasjonskontrollere
    private double FPS = 2;
    private Timeline timeline = new Timeline();
    
    
    @FXML private void handleButtonAction(ActionEvent event) {
        // knappetrykk "nextgeneration"
        printCells();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Metode for initialisering av brettet
	colorPicker.setValue(farge);       // Setter opp grid ved launch 
        cells.createGlider();        
	draw();  
        
        ObservableList<String> premades = FXCollections.observableArrayList("Glider","Small Exploder","Exploder","10 Cell Row","Lightweight Spaceship","Tumbler","Clear");  // dropdown
        dropdown.setItems(premades);
        
        // initialiserer animation data
        Duration duration = Duration.millis(1000/FPS);
        KeyFrame keyframe = new KeyFrame(duration, (ActionEvent e) -> {draw();});
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(keyframe);    
        timeline.setRate(FPS);
    }
    
    
    private void draw() {
        // Metode for å tegne celler til canvas
    	GraphicsContext gc = graphics.getGraphicsContext2D();
	gc.clearRect(0, 0, graphics.widthProperty().doubleValue(), graphics.heightProperty().doubleValue());
        
        for (int i = 0; i < boardwidth; i++) {
            for (int j = 0; j < boardheight; j++) {
                if (cells.getCellState(j, i)) {
                    gc.setFill(farge); // setter farge på levende celler
                }                                                                                  
                else {
                    gc.setFill(Color.DARKGREY); // setter farge på døde celler
                }
                gc.fillRect(i*cellSize+(i), j*cellSize+(j), cellSize, cellSize); // tegner individuelle celler som rektangler
            }
        }
        if(timeline.getStatus() == Animation.Status.RUNNING) { // animate      
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
            case "Glider": cells.createGlider();
                           draw();
                           break;
            case "Small Exploder": cells.resetCells();
                          cells.createSmallExploder();
                          draw();
                          break;
            case "Exploder": cells.resetCells();
                            cells.createExploder();
                            draw();
                            break;
            case "10 Cell Row": cells.resetCells();
                                cells.createTenRow();
                                draw();
                                break;
            case "Lightweight Spaceship": cells.resetCells();
                                          cells.createSpaceShip();
                                          draw();
                                          break;     
            case "Tumbler": cells.resetCells();
                            cells.createTumbler();
                            draw();
                            break;
            case "Clear": cells.resetCells();
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
        // Onclick Event - finner individuell celle som har blitt trykket på, finner status
        // og setter levende eller død tilsvarende
        
        double x = e.getX(); // mouseX verdi
        double y = e.getY(); // mouseY verdi
        
        double rectX;
        double rectY;

        for (int i = 0; i < boardwidth; i++) {
            for (int j = 0; j < boardheight; j++) {
                rectX = i*cellSize+(i); // startpunkt for cellenes X-verdi
                rectY = j*cellSize+(j); // startpunkt for cellenes Y-verdi
                
                if (x > rectX && x < rectX+cellSize) { // sjekker om punktet som har blitt trykket på er innenfor angitt rektangel på X-aksen
                    if (y > rectY && y < rectY+cellSize) { // sjekker om punktet er innenfor rektangel på Y-aksen
                        
                        if (cells.getCellState(j, i)) { // funnet riktig celle som har blitt trykket på - sjekker status på cellen
                            cells.setCellState(j, i, false); // setter død
                            draw();
                        }
                        else {
                            cells.setCellState(j, i, true); // setter levende
                            draw();
                        }
                    }
                }
            }
        }
    }
    // endring
}
