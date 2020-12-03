package edu.cco.ChamplainAirFreight;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import edu.cco.ChamplainAirFreight.Database.DBFinder;
import edu.cco.ChamplainAirFreight.Database.Client.DBViewAllClient;
import edu.cco.ChamplainAirFreight.Database.Shipment.DBAddShipment;
import edu.cco.ChamplainAirFreight.Database.Shipment.DBDeleteShipment;
import edu.cco.ChamplainAirFreight.Database.Shipment.DBViewAllShipments;
import edu.cco.ChamplainAirFreight.Database.Shipment.DBViewSelectShipment;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
 * @Subclass Shipments Description: GUI class for Shipment data
 */
//Begin Subclass ShipmentsPage
public class ShipmentsPage {
    //classes
    Styles s = new Styles();
	static TextArea texReaOne = new TextArea();
    
	// Client address labels
	static Label lbShipmentVolume = new Label("Shipment Volume");
	static Label lbShipmentWeight = new Label("Shipment Weight");
	static Label lbShipmentStartDate = new Label("Shipment Start Date");
	static Label lbShipmentEndDate  = new Label("Shipment End Date");
	static Label lbShipmentNotes = new Label("Shipment Notes ");

	static TextField txShipmentID;
	static TextField txShipmentVolume;
	static TextField txShipmentWeight;
	static TextField txShipmentStartDate;
	static TextField txShipmentEndDate;

	static TextArea texRea;
	
	//make buttons
    Button btnView = new Button("View");
    Button btnAdd = new Button("Add Shipment");
    Button btnEdit = new Button("Edit Shipment");
    Button btnDelete = new Button("Delete Shipment");
    Button btnEnter = new Button("Enter");
    Button btnCancel = new Button("Cancel");
    Button btnExit = new Button("Exit");
	
    //variables
    BorderPane bPane = new BorderPane();

    /**
     * Constructor - pulls the border pane from CAF (main page)
     *
     * @param bp
     */
    ShipmentsPage(BorderPane bp) {
        bPane = bp;
    }

    /**
     * getPane - this will call the shipments pane into the CAF main page.
     *
     * @return
     */
    public VBox getPane() {
        VBox vbox = new VBox();
        vbox.getChildren().add(shipments());
        return vbox;
    }

    /**
     * Shipments- main pane for shipment information
     *
     * @return
     */
    private BorderPane shipments() {
        BorderPane box = new BorderPane();

        //create a title pane for the top
        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);
        Text title = new Text("Shipments");
        Text instruct = new Text("View Shipment Information Below:");
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

        //add title, center, and buttons to shipment pane:
        box.setTop(titleBox);
        box.setCenter(getViewSelected()); //call a method to show db of shipments  
        box.setBottom(buttonBox);

        //add actionables to change the setCenter based on button responses:
        btnView.setOnAction(e -> {
        	box.setCenter(getViewLBs());
        });
        btnAdd.setOnAction(e -> {              	 
        	 box.setCenter(addShipmentPane());
        });
        btnEdit.setOnAction(e -> {
        	 box.setCenter(getClientLBs() );
        });
        btnDelete.setOnAction(e -> {
        	box.setCenter(deleteShipmentPane());
        });
        btnEnter.setOnAction(e -> {

        });
        btnCancel.setOnAction(e -> {

        });
        btnExit.setOnAction(e -> {
            //clear whatever actions doing
            //return to just the viewShipments page
            box.setCenter(getViewSelected());
        });

        return box;
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
    	hboxlb.getChildren().addAll( getShipInfolb(), getClientInfotx() );
    	return hboxlb;
    }   
    /**
     * This function displays the labels for the shipments.
     * @return
     */
    private VBox getShipInfolb() {
    	VBox vboxi = new VBox();
    	vboxi.setSpacing(20);
    	vboxi.setPadding(new Insets(23,30,0,20));
    	Label lbShipmentID = new Label("Shipment ID"); 
		Label lbShipmentVolume = new Label("Shipment Volume");
		Label lbShipmentWeight = new Label("Shipment Weight");
		Label lbShipmentStartDate = new Label("Shipment Start Date");
		Label lbShipmentEndDate = new Label("Shipment End Date");
		Label lbShipmentNotes = new Label("Shipment Notes");
    	vboxi.getChildren().addAll(lbShipmentID, lbShipmentVolume, lbShipmentWeight, lbShipmentStartDate,
    			lbShipmentEndDate, lbShipmentNotes);
    	return vboxi;
    }
    /**
     * This function displays the text fields. User's will be able to enter information in the textfields
     * @return
     */
    private VBox getClientInfotx() {
    	VBox vboxi = new VBox();
    	vboxi.setSpacing(12);
    	vboxi.setPadding(new Insets(20,10,0,20));
    	TextField txShipmentID = new TextField();
    	TextField txShipmentVolume = new TextField();
    	TextField txShipmentWeight = new TextField();
    	TextField txShipmentStartDate = new TextField();
    	TextField txShipmentEndDate = new TextField();
        texRea = new TextArea("");
        texRea = new TextArea();
        texRea.setStyle("-fx-border-color: black");
        texRea.setFont(new Font("Time New Roman", 10));
        texRea.setEditable(true);
        texRea.setWrapText(true);
        texRea.setPrefSize(600, 120);
    	vboxi.getChildren().addAll(txShipmentID,txShipmentVolume, txShipmentWeight, txShipmentStartDate, 
    			txShipmentEndDate,texRea);
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
     * This is a header section for the shipment view page. This header displays the names of the fields
     * @return
     */
	private HBox getViewLabel() {
		HBox hboxv = new HBox();
		hboxv.setAlignment(Pos.CENTER);
		hboxv.setPadding(new Insets(2,20,2,20));
		hboxv.setSpacing(100);
		hboxv.setPrefWidth(700);
		hboxv.setMaxWidth(900);
		hboxv.setStyle("-fx-background-color: white; -fx-border-color: black");
		Label id = new Label("ID"); 
		Label volume = new Label("Volume");
		Label weight = new Label("Weight"); 
		Label startDate = new Label("Start Date");
		Label endDate = new Label("End Date");
		Label notes = new Label("Notes");

		hboxv.getChildren().addAll(id, volume, weight, startDate, endDate, notes);
		return hboxv;
	}
	
	/**
	 * This TextArea will display the output or result for the shipment information 
	 * it will allow users to view a summary of the shipments. Users will not be able to 
	 * change the shipment information from this shipment view.
	 * @return
	 */
	private ScrollPane getTextAreaOne() {
		DBViewAllShipments view = new DBViewAllShipments();  //view all client information 
		ScrollPane box = new ScrollPane();
		box.setFitToWidth(true);
		box.setStyle("-fx-background-color: white; -fx-border-color: black"); 
		
		
		 GridPane gpane = new GridPane(); 
		 gpane.setStyle("-fx-background-color: white; -fx-border-color: black");
		 gpane.setPadding(new Insets(2,20,2,20));
		 gpane.setAlignment(Pos.TOP_CENTER); 
		 gpane.setHgap(100);
		 gpane.setVgap(5);
		 		 
		 int row = 1; 
		 int i =0; 
		 while(i < view.getShipID().size()) {
			 Label gridShipID = new Label(String.valueOf(view.getShipID().get(i))); 
			 Label gridShipVol = new Label(String.valueOf(view.getShipVolume().get(i))); 
			 Label gridShipWeight = new Label(String.valueOf(view.getShipWeight().get(i))); 
			 Label gridShipStart = new Label(String.valueOf(view.getStartDate().get(i))); 
			 Label gridShipEnd = new Label(String.valueOf(view.getEndDate().get(i))); 
			 Label gridShipNotes = new Label(String.valueOf(view.getNotes().get(i))); 
			 
			 gpane.add(gridShipID, 0, row);
			 gpane.add(gridShipVol, 1, row);
			 gpane.add(gridShipWeight, 2, row);
			 gpane.add(gridShipStart, 3, row);
			 gpane.add(gridShipEnd, 4, row);
			 gpane.add(gridShipNotes, 5, row);
			 
			 row++; 
			 i++; 
		 }
		
		 box.setContent(gpane);
		return box;
	}
	
	/**
	 * getViewSelected - the initial pane for the Shipment Page. will hold a feature to view an 
	 * individual shipment with the DBViewSelectShipment class
	 * Kelly May
	 * 11/18/2020
	 */
	private VBox getViewSelected() {
		DBViewAllShipments all = new DBViewAllShipments(); // for filling the combo box
		
	    VBox centerBox = new VBox();
	    centerBox.setAlignment(Pos.TOP_CENTER);
	    centerBox.setMinHeight(300);
	    centerBox.setStyle("-fx-background-color: white");	    
	    
	    // Add title and subtitle for instructions
	    Text title = new Text("View Selected Shipment"); 
	    Text instructions = new Text("Use the scroll bar to select a Shipment ID, then click SEARCH. \n"
	    		+ "This will allow you to view all Shipment information for selected shipment."); 
	    
	    // add a combobox and fill with all client names
	    HBox selection = new HBox(); 
	    selection.setAlignment(Pos.CENTER);
	   
	    ComboBox shipSelect = new ComboBox(FXCollections.observableArrayList(all.getShipID())); 
	   shipSelect.setVisibleRowCount(5); 
	    
	    Button shipSearch = new Button("Search"); 
	    selection.getChildren().addAll(shipSelect, shipSearch); 
	    
	    //grid of information: 
	    GridPane grid = new GridPane(); 
	    grid.setAlignment(Pos.CENTER);
	    Label lbID = new Label("Shipment ID: "); 
		Text txtID = new Text(); 
		Label lbVolume = new Label("Shipment Volume: "); 
		Text txtVolume = new Text(); 
	    Label lblWeight = new Label("Shipment Weight: "); 
	    Text txtWeight = new Text();  
	    Label lblStatus = new Label("Shipment Status: "); 
	    Text txtStatus = new Text();  
	    Label lblStart = new Label("Start Date: "); 
	    Text txtStart = new Text();  
	    Label lblEnd = new Label("End Date: "); 
	    Text txtEnd = new Text(); 
	    Label lblNotes = new Label("Notes: "); 
	    Text txtNotes = new Text();  
	     	    
	    grid.add(lbID, 0,  0);
	    grid.add(txtID, 1,  0);
	    grid.add(lbVolume, 0, 1);
	    grid.add(txtVolume, 1,  1);
	    grid.add(lblWeight, 0,  2);
	    grid.add(txtWeight, 1,  2);
	    grid.add(lblStatus, 0, 3);
	    grid.add(txtStatus, 1, 3);
	    grid.add(lblStart, 0, 4);
	    grid.add(txtStart,1 ,4);
	    grid.add(lblEnd, 0, 5);
	    grid.add(txtEnd, 1, 5);
	    grid.add(lblNotes, 0, 6);
	    grid.add(txtNotes, 1, 6);
	   
	   // fill text with selected flight information
	    shipSearch.setOnAction(e->{
		   try {
			DBViewSelectShipment view = new DBViewSelectShipment(); //to view a select flight
			String index = shipSelect.getValue().toString(); 
	    	int id = Integer.parseInt(index);  
	    	view.viewSelected(id);

	    	txtID.setText(Integer.toString(view.getShipID()));
	    	txtVolume.setText(Double.toString(view.getShipVolume()));
	    	txtWeight.setText(Double.toString(view.getShipWeight()));
	    	txtStatus.setText(Integer.toString(view.getStatusID()));
	    	txtStart.setText(String.valueOf(view.getStartDate()));
	    	txtEnd.setText(String.valueOf(view.getEndDate()));
	    	txtNotes.setText(view.getNotes());    	   	
	    	
		   } catch(Exception ex) {
			   shipSelect.requestFocus(); 
		   }
		   
	    });
	    centerBox.getChildren().addAll(title, instructions, selection, grid);
	    
	    return centerBox; 
	}
	
	/**
	 * addShipmentPane - GUI pane for adding in new shipment. calls the DBAddShipment class. 
	 * @return
	 */
	private VBox addShipmentPane() {
		//addshipment class imports
		DBFinder finder = new DBFinder(); 
		finder.findStatusID(); //set the values in the lookup table arraylists 
		DBAddShipment add = new DBAddShipment(); 
		DBViewAllClient viewClient = new DBViewAllClient(); 
		
		VBox centerBox = new VBox();
	    centerBox.setAlignment(Pos.TOP_CENTER);
	    centerBox.setMinHeight(300);
	    centerBox.setStyle("-fx-background-color: white");
	    
	    // Add title and subtitle for instructions
	    Text title = new Text("Add a Shipment"); 
	    Text instructions = new Text("Add shipment information into the fields below, then press Enter");
	    
	    //gridpane for information
	    GridPane grid = new GridPane(); 
	    grid.setAlignment(Pos.CENTER);
	    
	    Label lbID = new Label("Client ID: "); 
		ComboBox cbID = new ComboBox(FXCollections.observableArrayList(viewClient.getID())); //get usable client IDs
		Label lbVolume = new Label("Shipment Volume: "); 
		TextField txtVolume = new TextField(); 
	    Label lblWeight = new Label("Shipment Weight: "); 
	    TextField txtWeight = new TextField();  
	    Label lblStatus = new Label("Shipment Status: "); 
	    ComboBox cbStatus = new ComboBox(FXCollections.observableArrayList(finder.getStatusVals()));   //add status values
	    Label lblStart = new Label("Start Date: "); 
	    DatePicker dpStart = new DatePicker();   
	    Label lblEnd = new Label("End Date: "); 
	    DatePicker dpEnd = new DatePicker(); 
	    Label lblNotes = new Label("Notes: "); 
	    TextField txtNotes = new TextField();  
	    
	    grid.add(lbID, 0,  0);
	    grid.add(cbID, 1,  0);
	    grid.add(lbVolume, 0, 1);
	    grid.add(txtVolume, 1,  1);
	    grid.add(lblWeight, 0,  2);
	    grid.add(txtWeight, 1,  2);
	    grid.add(lblStatus, 0, 3);
	    grid.add(cbStatus, 1, 3);
	    grid.add(lblStart, 0, 4);
	    grid.add(dpStart,1 ,4);
	    grid.add(lblEnd, 0, 5);
	    grid.add(dpEnd, 1, 5);
	    grid.add(lblNotes, 0, 6);
	    grid.add(txtNotes, 1, 6);
	    
	    //actionables
	    btnEnter.setOnAction(e->{
	    	//variables for sql stored procedure
	    	int clientIndex = viewClient.getID().indexOf(cbID.getValue()); 
	    	int clientID = viewClient.getID().get(clientIndex);   
	    	Float volume = Float.parseFloat(txtVolume.getText()); 
	    	Float weight = Float.parseFloat(txtWeight.getText()); 
	    	int statusIndex = finder.getStatusVals().indexOf(cbStatus.getValue()); 
	    	int statusID = finder.getStatusIDs().get(statusIndex); 
	    	Date startDate = Date.valueOf(dpStart.getValue());
	    	Date endDate = Date.valueOf(dpEnd.getValue()); 
	    	String notes = txtNotes.getText(); 
	    	
	    	//add shipment
	    	add.insertSQL(clientID, volume, weight, statusID, startDate, endDate, notes);
	    	//clear entry fields
	    	cbID.valueProperty().set(null);
	    	txtVolume.clear(); 
	    	txtWeight.clear(); 
	    	cbStatus.valueProperty().set(null);
	    	dpStart.valueProperty().set(null);
	    	dpEnd.valueProperty().set(null);
	    	txtNotes.clear(); 
	    });
	    
	    centerBox.getChildren().addAll(title, instructions, grid); 
	    return centerBox; 
	}
	
	/**
	 * deleteShipmentPane - pane for deleting a shipment functionality
	 * @return
	 */
	private VBox deleteShipmentPane() {
		DBDeleteShipment delete = new DBDeleteShipment(); 
		DBViewAllShipments view = new DBViewAllShipments(); 
		VBox centerBox = new VBox();
	    centerBox.setAlignment(Pos.TOP_CENTER);
	    centerBox.setMinHeight(300);
	    centerBox.setStyle("-fx-background-color: white");
	    
	    // Add title and subtitle for instructions
	    Text title = new Text("Delete a Shipment"); 
	    Text instructions = new Text("select shipment id you wish to delete, then press Enter");
	    
	    GridPane grid = new GridPane();
	    grid.setAlignment(Pos.CENTER);
	    Label lblID = new Label("Shipment ID: "); 
	    ComboBox cbID = new ComboBox(FXCollections.observableArrayList(view.getShipID()));
	    grid.add(lblID, 0,0);
	    grid.add(cbID, 1, 0);
	    
	    centerBox.getChildren().addAll(title,instructions, grid); 
	    
	    btnEnter.setOnAction(e->{
	    	int shipID = Integer.parseInt(cbID.getValue().toString());
	    	delete.deleteShipment(shipID);
	    	cbID.valueProperty().set(null);
	    }); 
	    
	    return centerBox; 
	}
	
} //End Subclass ShipmentsPage
