package edu.cco.ChamplainAirFreight;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * * @Author Name: Kelly May
 * @Assignment Name: caf
 * @Date: Oct 28, 2020
 * @Subclass HomePage Description: This is all the code related to the HomePage
 * pane of the GUI
 */
//Begin Subclass HomePage
public class HomePage {

    // classes 
    Styles s = new Styles();

    //passed border pane from CAF. 
    BorderPane bPane = new BorderPane();

    //other pages to navigate to (classes) 
    ClientsPage clientsPage = new ClientsPage(bPane);
    FlightsPage flightsPage = new FlightsPage(bPane);
    ShipmentsPage shipPage = new ShipmentsPage(bPane);
    PilotPage pilotPage = new PilotPage(bPane);
 //   HelpPage helpPage = new HelpPage(bPane); //help/FAQ page 
    ContactPage contactPage = new ContactPage(bPane); //contact us page 
    AircraftPage aircraftPage = new AircraftPage(bPane); //contact us page
    /**
     * constructor pulls the borderpane
     */
    HomePage(BorderPane bp) {
        bPane = bp;
    }

    /**
     * getPane - this is the first page of CAF a use will see. Holds basic
     * navigation information. this is what the main CAF file will pull the
     * homePage from.
     *
     * @return
     */
    public VBox getPane() {
        VBox home = new VBox();
        home.setStyle("-fx-background-color: white");

        home.getChildren().add(getAllButton());
        return home;
    }

    /**
     * getAllButton - method to get all the buttons for the home page
     *
     * @return
     */
    private VBox getAllButton() {
    	VBox vboxall = new VBox();
    	vboxall.getChildren().addAll(getBtons());
    	vboxall.setAlignment(Pos.CENTER);
        return vboxall;
    }
    /**
     * getAllButton - method to get all the buttons for the home page
     *
     * @return
     */
    private HBox getBtons() {
        HBox hboxall = new HBox();
        hboxall.getChildren().addAll(getCenterLeftButton(), getCenterMiddleButton(),
                getCenterRightButton());
        hboxall.setAlignment(Pos.CENTER);
        return hboxall;
    }


    /**
     * getCenterLeftButton - buttons for the home page
     *
     * @return
     */
    private VBox getCenterLeftButton() {
        VBox vboxcb = new VBox();
        vboxcb.setMaxSize(300, 220);
        Text cltext = new Text("Enter Client Information");//
        Text shiptext = new Text("Shipping Details");
        VBox.setMargin(cltext, new Insets(20, 0, 20, 20));
        VBox.setMargin(shiptext, new Insets(20, 0, 20, 20));
        Button bthomeclients = new Button("CLIENT");
        Button bthomeshipment = new Button("SHIPMENT");
        bthomeclients.setFont(Font.font("Time New Roman", FontWeight.BOLD,
                FontPosture.REGULAR, 30));
        bthomeshipment.setFont(Font.font("Time New Roman", FontWeight.BOLD,
                FontPosture.REGULAR, 30));
        bthomeclients.setStyle(s.button);
        bthomeshipment.setStyle(s.button);
        bthomeclients.setMinSize(250, 150);
        bthomeshipment.setMinSize(250, 150);
        vboxcb.getChildren().addAll(cltext, bthomeclients, shiptext, bthomeshipment);

        bthomeclients.setOnAction(e -> {
            bPane.setCenter(clientsPage.getPane());
        });

        bthomeshipment.setOnAction(e -> {
            // set bpane center to shipment page
            bPane.setCenter(shipPage.getPane());
        });

        return vboxcb;
    }

    /**
     * getCenterMiddleButton - central buttons for the home page.
     *
     * @return
     */
    private VBox getCenterMiddleButton() {
        VBox vboxcb = new VBox();
        vboxcb.setAlignment(Pos.CENTER);
        Text fltext = new Text("All Flight Records");
        vboxcb.setMargin(fltext, new Insets(20, 0, 20, 20));
        Button bthomeflights = new Button("FLIGHTS");
        bthomeflights.setFont(Font.font("Time New Roman", FontWeight.BOLD,
                FontPosture.REGULAR, 30));
        bthomeflights.setStyle(s.button);
        vboxcb.setMargin(bthomeflights, new Insets(0, 0, 0, 20));
        bthomeflights.setMinSize(250, 150);
        vboxcb.getChildren().addAll(fltext, bthomeflights, getCraftBton());

        bthomeflights.setOnAction(e -> {
            //set bpane center to flights
            bPane.setCenter(flightsPage.getPane());
        });

        return vboxcb;
    }

    /**
     * getCenterRightButton - right buttons for the home page
     *
     * @return
     */
    private VBox getCenterRightButton() {
        VBox vboxcb = new VBox();
        Text airtext = new Text("Aircraft Operations Info");
        Text contact = new Text("How to reach us");
        vboxcb.setMargin(airtext, new Insets(20, 0, 20, 20));
        vboxcb.setMargin(contact, new Insets(20, 0, 20, 20));
        Button bthomepilot = new Button("PILOT");
        Button bthomecontact = new Button("CONTACT US");
        bthomepilot.setFont(Font.font("Time New Roman", FontWeight.BOLD,
                FontPosture.REGULAR, 30));
        bthomecontact.setFont(Font.font("Time New Roman", FontWeight.BOLD,
                FontPosture.REGULAR, 30));
        bthomepilot.setStyle(s.button);
        bthomecontact.setStyle(s.button);
        vboxcb.setMargin(bthomepilot, new Insets(0, 0, 0, 20));
        vboxcb.setMargin(bthomecontact, new Insets(0, 0, 0, 20));
        bthomepilot.setMinSize(250, 150);
        bthomecontact.setMinSize(250, 150);
        vboxcb.getChildren().addAll(airtext, bthomepilot, contact, bthomecontact);

        bthomepilot.setOnAction(e -> {
            // set bpane cetner to pilot
            bPane.setCenter(pilotPage.getPane());
        });
        bthomecontact.setOnAction(e -> {
            // set bpane center to contact page
            bPane.setCenter(contactPage.getPane());
        });

        return vboxcb;
    }
    /**
     * getCraftBton -button on the homepage to access aircraft models 
     *
     * @return
     */
    private VBox getCraftBton() {
        VBox vboxcb = new VBox();
        vboxcb.setMaxSize(850, 200);
        Text crafttext = new Text("Access Aircraft & Model");
        vboxcb.setMargin(crafttext, new Insets(20, 0, 20, 20));
        Button aircraftBton = new Button("AirCraft");
        aircraftBton.setFont(Font.font("Time New Roman", FontWeight.BOLD,
                FontPosture.REGULAR, 30));
        aircraftBton.setStyle(s.button);
        vboxcb.setMargin(aircraftBton, new Insets(0, 0, 0, 20));
        aircraftBton.setMinSize(250, 150);
        vboxcb.getChildren().addAll(crafttext, aircraftBton);//, contact);

        aircraftBton.setOnAction(e -> {
            // set bpane cetner to pilot
            bPane.setCenter(aircraftPage.getPane());
        });


        return vboxcb;
    }

} //End Subclass HomePage
