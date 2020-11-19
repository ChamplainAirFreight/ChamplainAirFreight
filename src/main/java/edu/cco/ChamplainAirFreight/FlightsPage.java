package edu.cco.ChamplainAirFreight;

import java.sql.Date;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @Author Name: Kelly May
 * @Assignment Name: caf
 * @Date: Oct 30, 2020
 * @Subclass FlightsPage Description: GUI panes for flight information
 */
//Begin Subclass FlightsPage
public class FlightsPage {
	
	//TextArea for the view -  Pierre 
	static TextArea texReaOne = new TextArea();
    
	// Client address labels - Pierre
	static Label lbstartairport;
	static Label lbendairport;
	static Label lbflightstarttime;
	static Label lbflightendtime;
	static Label lbShipmentEndDate;

	//Text field -  Pierre
	static TextField txstartairport;
	static TextField txendairport;
	static TextField txflightstarttime;
	static TextField txflightendtime;
	static TextField txShipmentEndDate;
	
    //variables
    BorderPane bPane = new BorderPane();

    //classes
    Styles s = new Styles();
    DBViewAllFlights viewAllFlights = new DBViewAllFlights(); 

    /**
     * Constructor - pulls the border pane from CAF (main page)
     *
     * @param bp
     */
    FlightsPage(BorderPane bp) {
        bPane = bp;
    }

    /**
     * getPane - this will call the Flights pane into the CAF main page.
     *
     * @return
     */
    public VBox getPane() {
        VBox vbox = new VBox();
        vbox.getChildren().add(flights());
        return vbox;
    }

    /**
     * flights - this is the main border pane for the flights page.
     *
     * @return
     */
    private BorderPane flights() {
        BorderPane box = new BorderPane();

        //create a title pane for the top
        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);
        Text title = new Text("Flights");
        Text instruct = new Text("View Flight Information Below:");
        //style text
        title.setFill(Color.DARKBLUE);
        title.setStrokeWidth(2);
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));

        instruct.setFill(Color.BLUE);
        instruct.setStrokeWidth(2);
        instruct.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
        //add titles to titlebox. 
        titleBox.getChildren().addAll(title, instruct);

        //create button HBox:
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(20);

        //make buttons
        Button btnView = new Button("View");
        Button btnAdd = new Button("Add Flight");
        Button btnEdit = new Button("Edit Flight");
        Button btnDelete = new Button("Delete Flight");
        Button btnEnter = new Button("Enter");
        Button btnCancel = new Button("Cancel");
        Button btnExit = new Button("Exit");

        //style buttons
        Arrays.asList(btnView, btnAdd, btnEdit, btnDelete, btnEnter,
                btnCancel).forEach((b) -> {
                    b.setStyle(s.entryButtons);
                    b.setMinHeight(30);
                    b.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
                });
        //Exit button style
        btnExit.setStyle(s.redEntryButtons);
        btnExit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
        btnExit.setMinHeight(30);

        //add buttons to button HBox
        buttonBox.getChildren().addAll(btnView, btnAdd, btnEdit, btnDelete, btnEnter,
                btnCancel, btnExit);

        //add title, center, and buttons to flights pane:
        box.setTop(titleBox);
        box.setCenter(getViewSelected()); //call a method to show db of flights  
        box.setBottom(buttonBox);

        //add actionables to change the setCenter based on button responses:
        btnView.setOnAction(e -> {
            box.setCenter(getViewLBs());
        });
        btnAdd.setOnAction(e -> {
            box.setCenter(getClientLBs()); 
        });
        btnEdit.setOnAction(e -> {
        	box.setCenter(editFlightPane()); 
        });
        btnDelete.setOnAction(e -> {

        });
        btnEnter.setOnAction(e -> {

        });
        btnCancel.setOnAction(e -> {

        });
        btnExit.setOnAction(e -> {
            //clear whatever actions doing
            //return to just the viewFlights page
            box.setCenter(getViewSelected());
        });

        return box;
    }

    /**
     * viewFlights - generates a chart of the flight information, which can be
     * added to flights() will have to call the database flights table
     *
     * @return
     */
    private ScrollPane viewFlights() {
        ScrollPane chart = new ScrollPane();
        chart.setMinHeight(360);
        chart.setMaxHeight(360);

        return chart;
    }
    /**
     * This is a function used to display the header section and the TextArea section for the result.
     * @return
     */
    private HBox getClientLBs() {
    	HBox hboxlb = new HBox();
    	hboxlb.setMinHeight(300);
    	hboxlb.setStyle("-fx-background-color: white");
    	hboxlb.setAlignment(Pos.CENTER_LEFT);
    	hboxlb.getChildren().addAll(getClientInfolb(),getClientInfotx());
    	return hboxlb;
    }   
    /*
     * This function will display the flight information for the flight page
     */
    private VBox getClientInfolb() {
    	VBox vboxi = new VBox();
        GridPane gPane = new GridPane();
		 lbstartairport = new Label("Start Airport");
		 lbendairport = new Label("End Airport");
		 lbflightstarttime = new Label("Flight Start Time");
		 lbShipmentEndDate = new Label("Flight End Time");
    	
    	vboxi.getChildren().addAll(lbstartairport,lbendairport,lbflightstarttime,lbShipmentEndDate);
    	return vboxi;
    }

    /*
     * This function will display the flight information for the flight page
     */
    private VBox getClientInfotx() {
    	VBox vboxi = new VBox();
    	 txstartairport = new TextField();
    	 txendairport = new TextField();
    	 txflightstarttime = new TextField();
    	 txflightendtime = new TextField();
    	vboxi.getChildren().addAll(txstartairport,txendairport ,txflightstarttime,txflightendtime);//, getClientInfotxZip());
    	return vboxi;
    } 

    /**
     * This is a function used to display the header section and the TextArea section for the result.
     * @return
     */
    private VBox getViewLBs() {
    	VBox vboxlb = new VBox();
    	vboxlb.setAlignment(Pos.CENTER);
    	vboxlb.getChildren().addAll(getViewLabel(), getTextAreaOne());
    	return vboxlb;
    }
    /**
     * This is a header section for the flight view page. This header displays the names of the fields
     * @return
     */
	private HBox getViewLabel() {
		HBox hboxv = new HBox();
		hboxv.setAlignment(Pos.CENTER);
		hboxv.setPadding(new Insets(3, 20, 3, 20));
		hboxv.setSpacing(60);
		hboxv.setPrefWidth(700);
		hboxv.setMaxWidth(900);
		hboxv.setStyle("-fx-background-color: white; -fx-border-color: black");
		//these labels no longer work with database info --- should remove 
		//Label lbstartairport = new Label("Start Airport");
		//Label lbtxendairport = new Label("End Airport");
		//Label lbtxflightstarttime = new Label("Flight Start Time");
		//Label lbtxflightendtime = new Label("Flight End Time");
		
		Label lbFlightID = new Label ("Flight ID"); 
		Label lbACID = new Label("Aircraft ID"); 
		Label lbPilotID = new Label("Pilot ID"); 
		Label lbStartLoc = new Label("Start Location"); 
		Label lbStartTime = new Label("Start Time"); 
		Label lbEndLoc = new Label ("End Location"); 
		Label lbEndTime = new Label("End Time"); 
//		name.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		Address.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		City.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		State.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
		hboxv.getChildren().addAll(lbFlightID, lbACID, lbPilotID, lbStartLoc, lbStartTime, lbEndLoc, lbEndTime);
		return hboxv;
	}

	/**
	 * This TextArea will display the output or result for the flight information 
	 * it will allow users to view a summary of the flight. Users will not be able to 
	 * change the flight information from this flight view.
	 * @return
	 * ** added database functionality 11/11/2020 - Kelly May
	 */
	private ScrollPane getTextAreaOne() {
		ScrollPane box = new ScrollPane(); 
		box.setStyle("-fx-border-color: black");
		box.setFitToWidth(true);
		
		GridPane gpane = new GridPane(); 
		gpane.setAlignment(Pos.TOP_CENTER);
		gpane.setPadding(new Insets(2,20,2,20)); 
		gpane.setHgap(90);
		gpane.setVgap(5);
		
		// dynamically add values from the database to the gridpane table 
		int row = 1; 
		 int i =0; 
		 while(i < viewAllFlights.getFlightID().size()) {
			 Label gridFlightID = new Label (String.valueOf(viewAllFlights.getFlightID().get(i))); 
			 Label gridACID = new Label (String.valueOf(viewAllFlights.getAircraftID().get(i))); 
			 Label gridPilotID = new Label(String.valueOf(viewAllFlights.getPilotID().get(i))); 
			 Label gridStartLoc = new Label(String.valueOf(viewAllFlights.getStartLocation().get(i))); 
			 Label gridStartTime = new Label(String.valueOf(viewAllFlights.getStartTime().get(i))); 
			 Label gridEndLoc = new Label(String.valueOf(viewAllFlights.getEndLocation().get(i))); 
			 Label gridEndTime = new Label(String.valueOf(viewAllFlights.getEndTime().get(i))); 
			 
			 
			 gpane.add(gridFlightID,  0,  row);
			 gpane.add(gridACID, 1, row); 
			 gpane.add(gridPilotID,  2,  row);
			 gpane.add(gridStartLoc,  3,  row);
			 gpane.add(gridStartTime,  4,  row);
			 gpane.add(gridEndLoc,  5,  row);
			 gpane.add(gridEndTime,  6,  row);
			 
			 
			 row++; 
			 i++; 
		 }
		
		box.setContent(gpane); 
		return box;
	}
	
	/**
	 * editFlightPane - pane will appear when user presses "Edit" - will allow user to select a flight, 
	 * and edit the information for that flight. 
	 * @return
	 */
public ScrollPane editFlightPane() {
	ScrollPane sp = new ScrollPane(); 
		VBox vbox = new VBox(); 
		vbox.setAlignment(Pos.TOP_CENTER); 
		vbox.setPadding(new Insets(3,20,3,20));
		vbox.setSpacing(20);
		vbox.setPrefWidth(1000); 
		vbox.setPrefHeight(500);
		vbox.setStyle("-fx-background-color: white; -fx-border-color: black");
		
		Text title = new Text("Edit Flight Information"); 
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD,
                FontPosture.ITALIC, 25));
		Text instructions = new Text("Select the flight you would like to edit, then "
				+ "change what you would like to change. Press \"Enter\" when you "
				+ "want to execute the changes. \"Cancel\" will clear your entries"); 
		
		ComboBox cbFlights = new ComboBox(); // will fill with flight IDs. 
		cbFlights.getItems().addAll(viewAllFlights.getFlightID()); 
		cbFlights.setVisibleRowCount(5);
		
		
		//add edit fields (start, end location and times, etc):
		
		
		vbox.getChildren().addAll(title,instructions,cbFlights); 
		sp.isFitToWidth(); 
		sp.setContent(vbox);
		return sp; 		
	}

/**
 * getViewSelected - the initial pane for the Flights page. Will hold a feature to view an 
 * individual flight with the DBViewSelectFlight class
 * Kelly May
 * 11/18/2020
 * @return
 */
private VBox getViewSelected() {
	DBViewAllFlights all = new DBViewAllFlights(); // for filling the combo box
	
    VBox centerBox = new VBox();
    centerBox.setAlignment(Pos.TOP_CENTER);
    centerBox.setMinHeight(300);
    centerBox.setStyle("-fx-background-color: white");
    
    /**
     * Add title and subtitle for instructions
     */
    Text title = new Text("View Selected Flight"); 
    Text instructions = new Text("Use the scroll bar to select a flight ID, then click SEARCH. \n"
    		+ "This will allow you to view all flight information for selected flight."); 
    
    // add a combobox and fill with all client names
    HBox selection = new HBox(); 
    selection.setAlignment(Pos.CENTER);
    ComboBox flightSelect = new ComboBox(FXCollections.observableArrayList(all.getFlightID())); 
    flightSelect.setVisibleRowCount(5); 
    Button flightSearch = new Button("Search"); 
    selection.getChildren().addAll(flightSelect, flightSearch); 
    
    //grid of information: 
    GridPane grid = new GridPane(); 
    grid.setAlignment(Pos.CENTER);
    Label lbID = new Label("Flight ID: "); 
	Text txtID = new Text(); 
	Label lbAirID = new Label("Aircraft ID: "); 
	Text txtAirID = new Text(); 
    Label lblStartTime = new Label("Start Time: "); 
    Text txtStartTime = new Text();  
    Label lblEndTime = new Label("End Time: "); 
    Text txtEndTime = new Text();  
    Label lblPilotName = new Label("Pilot Name: "); 
    Text txtPilotName = new Text();  
    Label lblStartAirportName = new Label("Start Airport Name: "); 
    Text txtStartAirportName = new Text(); 
    Label lblStartAirportLoc = new Label("Start Airport Location: "); 
    Text txtStartAirportLoc = new Text();  
    Label lblEndAirportName = new Label("End Airport Name: "); 
    Text txtEndAirportName = new Text(); 
    Label lblEndAirportLoc = new Label("End Airport Location: "); 
    Text txtEndAirportLoc = new Text(); 
    Label lblDistanceHub = new Label("Distance from Hub: "); 
    Text txtDistanceHub = new Text(); 
    
    grid.add(lbID, 0,  0);
    grid.add(txtID, 1,  0);
    grid.add(lbAirID, 0, 1);
    grid.add(txtAirID, 1,  1);
    grid.add(lblStartTime, 0,  2);
    grid.add(txtStartTime, 1,  2);
    grid.add(lblEndTime, 0, 3);
    grid.add(txtEndTime, 1, 3);
    grid.add(lblPilotName, 0, 4);
    grid.add(txtPilotName,1 ,4);
    grid.add(lblStartAirportName, 0, 5);
    grid.add(txtStartAirportName, 1, 5);
    grid.add(lblStartAirportLoc, 0, 6);
    grid.add(txtStartAirportLoc, 1, 6);
    grid.add(lblEndAirportName, 0, 7);
    grid.add(txtEndAirportName, 1, 7);
    grid.add(lblEndAirportLoc,  0,  8);
    grid.add(txtEndAirportLoc, 1, 8);
    grid.add(lblDistanceHub, 0,  9);
    grid.add(txtDistanceHub, 1, 9);
    
   // fill text with selected flight information
    flightSearch.setOnAction(e->{
	   try {
		DBViewSelectFlight view = new DBViewSelectFlight(); //to view a select flight
		
    	int id = Integer.parseInt(flightSelect.getValue().toString()); 
    	view.viewSelected(id);

    	txtID.setText(Integer.toString(view.getFlightID()));
    	txtAirID.setText(Integer.toString(view.getAircraftID()));
    	txtStartTime.setText(view.getStartTime());
    	txtEndTime.setText(view.getEndTime()); 
    	txtPilotName.setText(view.getPilotName());
    	txtStartAirportName.setText(view.getStartName());
    	txtStartAirportLoc.setText(view.getStartLoc());
    	txtEndAirportName.setText(view.getEndName()); 
    	txtEndAirportLoc.setText(view.getEndLoc()); 
    	txtDistanceHub.setText(view.getHubDist());
    	
    	
	   } catch(Exception ex) {
		   flightSelect.requestFocus(); 
	   }
	   
    });
    centerBox.getChildren().addAll(title, instructions, selection, grid);
    
    return centerBox; 
}

} //End Subclass FlightsPage
