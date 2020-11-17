package edu.cco.ChamplainAirFreight;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Kelly
 * @Date: Nov 16, 2020
 *  @Description - GUI Panes for Aircraft Information
 */

public class AircraftPage {
	
	//classes
	Styles s = new Styles(); 
	
	//passed border pane from CAF
	BorderPane bPane = new BorderPane(); 
	
	/**
	 * Constructor pulls the border pane from CAF
	 */
	AircraftPage(BorderPane bp){
		bPane = bp; 
	}
	
	  /**
     * getPane - what gets called in caf to view aircraftPage data 
     * @return 
     */
    public VBox getPane() {
        VBox vclients = new VBox();
        //vclients.getChildren().addAll(aircraft());
        return vclients;
    }
    
    
}
