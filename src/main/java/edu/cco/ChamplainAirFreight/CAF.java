package edu.cco.ChamplainAirFreight;
/**
 * @Author Name: Kelly May
 * @Assignment Name: caf
 * @Date: Oct 26, 2020
 * @Description: Champlain Air Freight main GUI - a refactoring of GUI code made
 * by Dony Pierre to discuss methods for creating a functional GUI.
 */
//Imports
import java.util.Arrays;

import edu.cco.ChamplainAirFreight.Database.DBConnection;
import edu.cco.ChamplainAirFreight.Database.Client.DBViewAllClient;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class CAF extends Application {

	   private static final String JavaMailUtil = null;

	BorderPane bPane = new BorderPane();

	    //Buttons:
	    static Button btonhomepage = new Button("HOMEPAGE");
	    static Button btonclients = new Button("CLIENTS");
	    static Button btonflights = new Button("FLIGHTS");
	    static Button btonpilot = new Button("PILOT");
	    static Button btonshipment = new Button("SHIPMENT");
	    static Button btonaircraft = new Button("AIRCRAFT"); 
	    // btonhelp removed - 
	    static Button btoncontact = new Button("CONTACT US");
	    static Button btonexit = new Button("EXIT");
	    static Button btonmodel = new Button("Model");

	    // Classes:
	    Styles s = new Styles(); //styles class for colors/fonts 
	    HomePage homePage = new HomePage(bPane); // homepage panes 
	    ClientsPage clientsPage = new ClientsPage(bPane); // clients panes 
	    FlightsPage flightsPage = new FlightsPage(bPane); //flights panes 
	    ShipmentsPage shipPage = new ShipmentsPage(bPane); //shipments panes
	    PilotPage pilotPage = new PilotPage(bPane); //pilots page 
	   // help page removed-  
	    AircraftPage aircraftPage = new AircraftPage(bPane); //
	   // ContactPage contactPage = new ContactPage(bPane); //contact us page 
	    ContactPage contactPage = new ContactPage(bPane);//contact us page 
//	    Model model = new Model(bPane); //Model page 
	    
	    //Database classes
	    DBConnection connection = new DBConnection(); 
	    DBViewAllClient allClient = new DBViewAllClient(); 


/**
 * start - this is the main pane of the GUI. Holds the outer shell for all
 * other panes.
 *
 * @param primaryStage
 * @throws Exception
 */
@Override
public void start(Stage primaryStage) throws Exception {
		
    bPane.setTop(topNavigation()); // navigation buttons in here
    bPane.setCenter(homePage.getPane()); // change the center pane to each new page
    bPane.setBottom(bottomCreds()); // bottom credentials/copyright

    //button actionables:
    btonhomepage.setOnAction(e -> {
        bPane.setCenter(homePage.getPane());
    });

    btonclients.setOnAction(e -> {
        bPane.setCenter(clientsPage.getPane());
    });


    btonflights.setOnAction(e -> {
        // set center to flights page
        bPane.setCenter(flightsPage.getPane());
    });

    btonpilot.setOnAction(e -> {
        // set center to pilot page
        bPane.setCenter(pilotPage.getPane());
    });
    btonshipment.setOnAction(e -> {
        // set center to shipment page
        bPane.setCenter(shipPage.getPane());
    });
   
    btonaircraft.setOnAction(e->{
   	bPane.setCenter(aircraftPage.getPane());
    });

    btoncontact.setOnAction(e -> {
        // set center to contact page
        bPane.setCenter(contactPage.getPane());
        

    });

    btonexit.setOnAction(e -> {
        // add log-out actions
        Platform.exit(); //leave CAF
    });
    
  //SendMail("champlainairfreight@gmail.com");

    Scene scene = new Scene(bPane, 1000, 700);
    primaryStage.setTitle("Champlain Air Freight");
    primaryStage.setScene(scene);
    primaryStage.show();
}

/**
 * topNavigation - this houses the name of the program, and the navigation
 * buttons.
 *
 * @return
 */
private VBox topNavigation() {
    VBox vboxt = new VBox();
    vboxt.setAlignment(Pos.CENTER);
    vboxt.setMinHeight(90);
    // hboxt.setPadding(new Insets(10,10,10,10));  
    vboxt.setStyle("-fx-background-color: lightblue");
    Text headertex = new Text("CHAMPLAIN AIR FREIGHT");
    headertex.setFill(Color.BLUE);
    headertex.setStrokeWidth(2);
    headertex.setFont(Font.font("Times New Roman", FontWeight.BOLD,
            FontPosture.REGULAR, 30));
    vboxt.getChildren().addAll(headertex);
    vboxt.getChildren().add(getNavButtons());

    return vboxt;
}

/**
 * getNavButtons - this holds all the navigation buttons for the top pane.
 *
 * @return
 */
private HBox getNavButtons() {
    HBox hboxnv = new HBox();
    hboxnv.setStyle("-fx-background-color: lightblue");
    hboxnv.setSpacing(20);
    hboxnv.setPadding(new Insets(10, 10, 10, 10));
    hboxnv.setPrefSize(1000, 60);
    hboxnv.setAlignment(Pos.CENTER);

    //add color, height and font to all buttons:
    Arrays.asList(btonhomepage, btonclients, btonflights,
            btonpilot, btonshipment, btonaircraft, btoncontact).stream().map((b) -> {
                b.setStyle(s.button);
                return b;
            }).map((b) -> {
        b.setMinHeight(30);
        return b;
    }).forEachOrdered((b) -> {
        b.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
    });
    // exit button is different:
    btonexit.setStyle(s.redButton);
    btonexit.setMinHeight(30);
    btonexit.setFont(Font.font("Times New Roman", FontWeight.BOLD,
            FontPosture.REGULAR, 14));

    hboxnv.getChildren().addAll(btonhomepage, btonclients, btonflights,
            btonpilot, btonshipment, btonaircraft, btoncontact, btonexit);
    return hboxnv;
}

/**
 * bottomCreds - this holds the information at the bottom of the page, such
 * as copyright and general group info.
 *
 * @return
 */
private VBox bottomCreds() {
    VBox vboxb = new VBox();
    vboxb.setMinSize(1000, 150);
    vboxb.setStyle("-fx-background-color: black");
    Text toptext = new Text("Copyright © 2020 · All Rights Reserved:  "
            + "Champlain Air-Freight");
    // toptext.setMargin(new Insets(20,20,20,20));
    toptext.setFill(Color.WHITE);
    Text bottext = new Text("Designed by: Group 1");
    toptext.setFont(Font.font("Times New Roman", FontWeight.NORMAL,
            FontPosture.REGULAR, 15));
    bottext.setFont(Font.font("Times New Roman", FontWeight.NORMAL,
            FontPosture.REGULAR, 15));
    bottext.setFill(Color.WHITE);
    vboxb.setAlignment(Pos.CENTER);
    VBox.setMargin(toptext, new Insets(0, 0, 20, 0));
    vboxb.getChildren().addAll(toptext, bottext);
    return vboxb;
}

} //End Class CAF
