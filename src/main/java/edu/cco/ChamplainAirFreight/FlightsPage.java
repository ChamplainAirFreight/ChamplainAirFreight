package edu.cco.ChamplainAirFreight;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import edu.cco.ChamplainAirFreight.Database.DBFinder;
import edu.cco.ChamplainAirFreight.Database.Aircraft.DBViewAllAircraft;
import edu.cco.ChamplainAirFreight.Database.Flight.DBAddFlight;
import edu.cco.ChamplainAirFreight.Database.Flight.DBDeleteFlight;
import edu.cco.ChamplainAirFreight.Database.Flight.DBUpdateFlight;
import edu.cco.ChamplainAirFreight.Database.Flight.DBViewAllFlights;
import edu.cco.ChamplainAirFreight.Database.Flight.DBViewSelectFlight;
import edu.cco.ChamplainAirFreight.Database.Pilot.DBViewAllPilot;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
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
    //used for validation
    ValidateFields valid=new ValidateFields();
    
    //make buttons
    Button btnView = new Button("View");
    Button btnAdd = new Button("Add Flight");
    Button btnEdit = new Button("Edit Flight");
    Button btnDelete = new Button("Delete Flight");
    Button btnEnter = new Button("Enter");
    Button btnCancel = new Button("Cancel");
    Button btnExit = new Button("Exit");

    //classes
    Styles s = new Styles();
    

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
        buttonBox.setPadding(new Insets(20,0,0,0));
        buttonBox.setSpacing(20);

       

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
            box.setCenter(addPane()); 
        });
        btnEdit.setOnAction(e -> {
        	box.setCenter(editFlightPane()); 
        });
        btnDelete.setOnAction(e -> {
        	box.setCenter(deleteFlightPane()); 
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
    	vboxi.setSpacing(20);
   	 	vboxi.setPadding(new Insets(23,30,0,20));
   	 	
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
    	vboxi.setSpacing(12);
    	vboxi.setPadding(new Insets(20,10,0,20));
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
				
		Label lbFlightID = new Label ("Flight ID"); 
		Label lbACID = new Label("Aircraft ID"); 
		Label lbPilotID = new Label("Pilot ID"); 
		Label lbStartLoc = new Label("Start Location"); 
		Label lbStartTime = new Label("Start Time"); 
		Label lbEndLoc = new Label ("End Location"); 
		Label lbEndTime = new Label("End Time"); 

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
		DBViewAllFlights viewAllFlights = new DBViewAllFlights(); 
		ScrollPane box = new ScrollPane(); 
		box.setStyle("-fx-border-color: black");
		box.setFitToWidth(true);
		
		GridPane gpane = new GridPane();
		gpane.setStyle("-fx-background-color: white; -fx-border-color: black");
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
	 * Modified by Matt Ridgway be a VBox instead of a ScrollPane using the code from getViewSelected
	 * @return
	 */
public VBox editFlightPane() {
DBViewAllFlights all = new DBViewAllFlights(); // for filling the combo box
DBUpdateFlight update =new DBUpdateFlight();
DateTimePicker dtStart = new DateTimePicker();  
DateTimePicker dtEnd = new DateTimePicker(); 

    VBox centerBox = new VBox();
    centerBox.setAlignment(Pos.TOP_CENTER);
    centerBox.setMinHeight(300);
    centerBox.setStyle("-fx-background-color: white");
    
    /**
     * Add title and subtitle for instructions
     */
    Text title = new Text("Select a Flight"); 
    Text instructions = new Text("Use Drop Down Box to select a flight and hit select flight."); 
    
    // add a combobox and fill with all client names
    HBox selection = new HBox(); 
    selection.setAlignment(Pos.CENTER);
    ComboBox flightSelect = new ComboBox(FXCollections.observableArrayList(all.getFlightID())); 
    flightSelect.setVisibleRowCount(5); 
    Button flightSearch = new Button("Select Flight"); 
    selection.getChildren().addAll(flightSelect, flightSearch); 
    
    //grid of information: 
    GridPane grid = new GridPane(); 
    grid.setAlignment(Pos.CENTER);
    Label lbID = new Label("Flight ID: "); 
	TextField txtID = new TextField();
	txtID.setEditable(false);
	Label lbAirID = new Label("Aircraft ID: "); 
	TextField txtAirID = new TextField(); 
    Label lblStartTime = new Label("Start Time: "); 
    TextField txtStartTime = new TextField();  
    Label lblEndTime = new Label("End Time: "); 
    TextField txtEndTime = new TextField();  
    Label lblPilotID = new Label("Pilot ID: "); 
    TextField txtPilotID = new TextField();  
    Label lblStartAirport = new Label("Start Airport: "); 
    TextField txtStartAirport = new TextField();     
    Label lblEndAirport = new Label("End Airport: "); 
    TextField txtEndAirport = new TextField(); 
    
 
    
    grid.add(lbID, 0,  0);
    grid.add(txtID, 1,  0);
    grid.add(lbAirID, 0, 1);
    grid.add(txtAirID, 1,  1);
    grid.add(lblStartTime, 0,  2);
    grid.add(txtStartTime, 1,  2);
    grid.add(lblEndTime, 0, 3);
    grid.add(txtEndTime, 1, 3);
    grid.add(lblPilotID, 0, 4);
    grid.add(txtPilotID,1 ,4);
    grid.add(lblStartAirport, 0, 5);
    grid.add(txtStartAirport, 1, 5);
    grid.add(lblEndAirport, 0, 6);
    grid.add(txtEndAirport, 1, 6);
 
    
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
    	txtPilotID.setText(Integer.toString(view.getPilotID()));
    	txtStartAirport.setText(Integer.toString(view.getStartAirport()));
    	txtEndAirport.setText(Integer.toString(view.getEndAirport())); 
   
    	
	   } catch(Exception ex) {
		   flightSelect.requestFocus(); 
	   }
	   
    });
    centerBox.getChildren().addAll(title, instructions, selection, grid);
    
    btnEnter.setOnAction(e->{
    	// variables head for header and cont for content
    	String head="Flight ID";
		String cont="Not a int";		
		// check flightID
    	int flightID=valid.intChecker(txtID.getText(),head,cont);
    	
    	// check aircraftId
    	head ="Aircraft ID";
    	int airCraftID=valid.intChecker(txtAirID.getText(),head,cont);
    	
    	//time
    	LocalDateTime start = dtStart.getDateTimeValue();
		String startDate = start.toString();  
		startDate = startDate.replace("T", " "); 
		LocalDateTime end = dtEnd.getDateTimeValue(); 
		String endDate = end.toString(); 
		endDate = endDate.replace("T", " "); 
		
		//System.out.println("start date: " + startDate); 
		//System.out.println("End date: " + endDate); 
		// pilot ID check
    	head="Pilot ID";
    	int pilotID=valid.intChecker(txtPilotID.getText(),head,cont);
    	
    	head="Starting Airport";
    	int startAirport =valid.intChecker(txtStartAirport.getText(),head,cont);
    	
    	head="Ending Airport";
    	int endAirport =valid.intChecker(txtEndAirport.getText(),head,cont);
    	//for start end time
    	head="Time";
    	cont="Not Blank";
    	
    	if (flightID==0) {
    		txtID.clear();
    	}else if(airCraftID==0) {
    		txtAirID.clear();
    	}else if(pilotID==0) {
    		txtPilotID.clear();
    	}else if(startAirport==0) {
    		txtStartAirport.clear();
    	}else if (endAirport==0) {	
           	txtEndAirport.clear(); 	
    	}else if (startDate=="") {    		
    		valid.error.setError(head, cont);
    	}else if(endDate=="") {
    		valid.error.setError(head, cont);
    	}
    	else if(valid.afterDateString(startDate, endDate)) {//check if start time is after end time
    		valid.error.setError("Date Error", "Start time can't be before End time");
    	} 
    	else{
    		update.updateFlight(flightID, airCraftID, pilotID, startAirport, endAirport, startDate, endDate);
    		 //clear textFields after update has been sent
    		flightSelect.valueProperty().set(null);
    	    txtID.clear();
    	    txtAirID.clear();
    	    txtStartTime.clear();
    	    txtEndTime.clear();
    	    txtPilotID.clear();
    	    txtStartAirport.clear();
    	    txtEndAirport.clear();
    	}
    	
    	
    	
    	
    });
    
    btnCancel.setOnAction(e->{
    	flightSelect.valueProperty().set(null);
    	txtID.clear();
    	txtAirID.clear();
    	txtStartTime.clear();
    	txtEndTime.clear(); 
    	txtPilotID.clear();
    	txtStartAirport.clear();
    	txtEndAirport.clear(); 
    });
   
    return centerBox; 
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
    
    btnCancel.setOnAction(e->{
    	flightSelect.valueProperty().set(null);
    	txtID.setText("");
    	txtAirID.setText(""); 
    	txtStartTime.setText("");
    	txtEndTime.setText(""); 
    	txtPilotName.setText("");
    	txtStartAirportName.setText("");
    	txtStartAirportLoc.setText("");
    	txtEndAirportName.setText("");
    	txtEndAirportLoc.setText("");
    	txtDistanceHub.setText("");
    	
    });
    return centerBox; 
}

/**
 * addPane - pane for adding a flight in the database. 
 * Kelly May
 */
private VBox addPane() {
	VBox box = new VBox(); 
	box.setAlignment(Pos.CENTER); 
	box.setSpacing(10);
	box.setPadding(new Insets(2,20,2,20));
	//add classes to grab ID arraylists
	DBViewAllPilot pilot = new DBViewAllPilot(); 
	DBViewAllAircraft aircraft = new DBViewAllAircraft();
	DBFinder finder = new DBFinder(); 
	finder.findAirports(); 
	
	//title and instructions 
	Text title = new Text("Add a new Flight"); 
	Text instructions = new Text("Enter valid information for a Flight, and then press Enter"); 
	//labels
	Label lblAirID = new Label ("AircraftID: "); 
	Label lblPilotID = new Label ("PilotID: "); 
	Label lblStartLoc = new Label ("Start Airport: "); 
	Label lblEndLoc = new Label("End Airport: "); 
	Label lblStartTime = new Label("Start Time: "); 
	Label lblEndTime = new Label("End Time: "); 
	
	//style labels
	Arrays.asList(lblAirID, lblPilotID, lblStartLoc, lblEndLoc, lblStartTime, lblEndTime).stream().map((b)->{
		b.setStyle(s.clientLB); 
		return b; 
	}); 
			
	//entry fields
	ComboBox<Integer> cbAirID = new ComboBox(); 
	cbAirID.getItems().addAll(aircraft.getAircraftID()); 
	
	ComboBox<Integer> cbPilotID = new ComboBox(); 
	cbPilotID.getItems().addAll(pilot.getPilotID());
	
	ComboBox<String> cbStartLoc = new ComboBox(); 
	cbStartLoc.getItems().addAll(finder.getAirportNames()); 
	
	ComboBox<String> cbEndLoc = new ComboBox(); 
	cbEndLoc.getItems().addAll(finder.getAirportNames()); 
	
	//need to incorporate a time function to these... 
	 DateTimePicker dtStart = new DateTimePicker();  
	 DateTimePicker dtEnd = new DateTimePicker(); 
	
	//add input values into a gridpane
	GridPane grid = new GridPane(); 
	grid.setAlignment(Pos.CENTER);
	grid.setHgap(10);
	grid.setVgap(4);
	grid.add(lblAirID, 0, 0);
	grid.add(lblPilotID, 0, 1);
	grid.add(lblStartLoc, 0, 2);
	grid.add(lblEndLoc, 0, 3);
	grid.add(lblStartTime, 0, 4);
	grid.add(lblEndTime, 0, 5);
	
	grid.add(cbAirID, 1, 0);
	grid.add(cbPilotID, 1, 1);
	grid.add(cbStartLoc, 1, 2);
	grid.add(cbEndLoc, 1, 3);
	grid.add(dtStart, 1, 4);
	grid.add(dtEnd, 1, 5);
		
	box.getChildren().addAll(title,instructions,grid); 
	btnEnter.setOnAction(e->{
		//variables for SQL stored procedure
		//with validation		
		int airID =0;
		airID = cbAirID.getValue(); 
		int pilotID =0;
		pilotID = cbPilotID.getValue(); 
		
		//grab the location id from the lookup table
		String startLoc = cbStartLoc.getValue(); 
		int index = finder.getAirportNames().indexOf(startLoc); 
		int startLocID = finder.getAirportIDs().get(index); 
		
		String endLoc = cbEndLoc.getValue(); 
		int endIndex = finder.getAirportNames().indexOf(endLoc); 
		int endLocID = finder.getAirportIDs().get(endIndex);
		
		LocalDateTime start = dtStart.getDateTimeValue();
		String startDate = start.toString();  
		startDate = startDate.replace("T", " "); 
		LocalDateTime end = dtEnd.getDateTimeValue(); 
		String endDate = end.toString(); 
		endDate = endDate.replace("T", " "); 
		
		System.out.println("start date: " + startDate); 
		System.out.println("End date: " + endDate); 
		
		String head="AirCraft ID";
		String cont="Cant be unselected";	
		if (airID==0) {
			valid.error.setError(head, cont);
		}else if(pilotID==0) {
			head="Pilot ID";
			valid.error.setError(head, cont);
			
		}
		else {
			
			//add flight
			DBAddFlight add = new DBAddFlight(airID, pilotID, startLocID, endLocID, startDate, endDate); 		
			//clear entry fields
			cbAirID.valueProperty().set(null);
			cbPilotID.valueProperty().set(null);
			cbStartLoc.valueProperty().set(null);
			cbEndLoc.valueProperty().set(null); 
			dtStart.valueProperty().set(null);
			dtEnd.valueProperty().set(null);
		}
			
		    		
	});
	
	btnCancel.setOnAction(e->{
		cbAirID.valueProperty().set(null);
		cbPilotID.valueProperty().set(null);
		cbStartLoc.valueProperty().set(null);
		cbEndLoc.valueProperty().set(null); 
		dtStart.valueProperty().set(null);
		dtEnd.valueProperty().set(null);
	});
	    	
	return box; 
}

/**
 * deleteFlightPane - deletes a flight from the database'
 * Kelly May
 * 12/3/2020
 * @return
 */
public VBox deleteFlightPane() {
	DBDeleteFlight delete = new DBDeleteFlight(); 
	DBViewAllFlights view = new DBViewAllFlights(); //for getting combobox of flight IDs
	
	VBox box = new VBox(); 
	box.setAlignment(Pos.CENTER); 
	box.setSpacing(10);
	box.setPadding(new Insets(2,20,2,20));
	
	//title and instructions 
	Text title = new Text("Delete Flight"); 
	Text instructions = new Text("Select the flightID you wish to delete, then press Enter"); 
	
	//add input values into a gridpane
	GridPane grid = new GridPane(); 
	grid.setAlignment(Pos.CENTER);
	grid.setHgap(10);
	grid.setVgap(4);
	Text txtFlightID = new Text("Flight ID: "); 
	ComboBox cbFlightID = new ComboBox(FXCollections.observableArrayList(view.getFlightID()));
	grid.add(txtFlightID, 0, 0);
	grid.add(cbFlightID, 1, 0);
	
	box.getChildren().addAll(title, instructions, grid); 
	
	//actionables:
	btnEnter.setOnAction(e->{
		int flightID = Integer.parseInt(cbFlightID.getValue().toString()); //get flight ID
		delete.deleteFlight(flightID); //delete flight
		cbFlightID.valueProperty().set(null); //clear combobox
	});
	
	btnCancel.setOnAction(e->{
		cbFlightID.valueProperty().set(null); //clear combobox
	});
	
	return box; 
}

} //End Subclass FlightsPage
