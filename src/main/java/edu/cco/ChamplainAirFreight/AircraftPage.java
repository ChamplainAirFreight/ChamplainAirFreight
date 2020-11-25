package edu.cco.ChamplainAirFreight;

import java.util.Arrays;

import edu.cco.ChamplainAirFreight.Database.Aircraft.DBViewAllAircraft;
import edu.cco.ChamplainAirFreight.Database.Aircraft.DBViewSelectAircraft;
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
 * @Author Name: Dony Pierre
 * @Assignment Name: caf
 * @Date: Oct 30, 2020
 * @Subclass FlightsPage Description: GUI panes for Model information
 */
//Begin Subclass AircraftPage
public class AircraftPage {
	
	//TextArea for the view -  Pierre 
	static TextArea texReaOne = new TextArea();
    
	// Client address labels - Pierre

	static Label lbcraftMake;
	static Label lbcraftModel;
	static Label lbcraftRang;
	static Label lbcraftRC;
	static Label lbtxPayload ;
	static Label lbtxLoadVolume;

	//Text field -  Pierre
	static TextField txcraftMake;
	static TextField txcraftModel;
	static TextField txcraftRang;
	static TextField txcraftRC;
	static TextField txPayload ;
	static TextField txLoadVolume;
	
	//make buttons
    Button btnView = new Button("View");
    Button btnAdd = new Button("Add Flight");
    Button btnEdit = new Button("Edit Flight");
    Button btnDelete = new Button("Delete Flight");
    Button btnEnter = new Button("Enter");
    Button btnCancel = new Button("Cancel");
    Button btnExit = new Button("Exit");
	
/*	  = new Label("AirCraft Make");
	  = new Label("AirCraft Model");
	  = new Label("AirCraft Range");
	  = new Label("Range Clasification");*/
	
    //variables
    BorderPane bPane = new BorderPane();

    //classes
    Styles s = new Styles();
    DBViewAllAircraft viewAllAir = new DBViewAllAircraft(); 

    /**
     * Constructor - pulls the border pane from CAF (main page)
     *
     * @param bp
     */
    AircraftPage(BorderPane bp) {
        bPane = bp;
    }

    /**
     * getPane - this will call the Flights pane into the CAF main page.
     *
     * @return
     */
    public VBox getPane() {
        VBox vbox = new VBox();
        vbox.getChildren().add(modelInfo());
        return vbox;
    }

    /**
     * flights - this is the main border pane for the flights page.
     *
     * @return
     */
    private BorderPane modelInfo() {
        BorderPane box = new BorderPane();

        //create a title pane for the top
        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);
        Text title = new Text("Aircraft");
        Text instruct = new Text("View Aircraft Information Below:");
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

        //add title, center, and buttons to Aircraft pane:
        box.setTop(titleBox);
        box.setCenter(getViewSelected()); //call a method to show db of flights  
        box.setBottom(buttonBox);

        //add actionables to change the setCenter based on button responses:
        btnView.setOnAction(e -> {
            box.setCenter(getCraftLBs());
        });
        btnAdd.setOnAction(e -> {
            box.setCenter(getCraftViewLBs()); 
        });
        btnEdit.setOnAction(e -> {
        	box.setCenter(getCraftViewLBs()); 
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
     * This is a function used to display the header section and the TextArea section for the result.
     * @return
     */
    private HBox getCraftViewLBs() {
    	HBox hboxlb = new HBox();
    	hboxlb.setMinHeight(300);
    	hboxlb.setStyle("-fx-background-color: white");
    	hboxlb.setAlignment(Pos.CENTER);
    	hboxlb.getChildren().addAll(getCraftInfolb(),getCraftlInfotx());
    	return hboxlb;
    }   
    /*
     * This function will display the Aircraft information for the AircraftPage 
     */
    private VBox getCraftInfolb() {
    	VBox vboxi = new VBox();
    	vboxi.setSpacing(20);
    	vboxi.setPadding(new Insets(23,0,0,20));
    	lbcraftMake = new Label("AirCraft Make");
        lbcraftModel = new Label("AirCraft Model");
        lbcraftRang = new Label("AirCraft Range");
        lbcraftRC = new Label("Range Clasification");
        lbtxPayload = new Label("Payload");
        lbtxLoadVolume = new Label("Load Volume");
        //Adding Styles
        Arrays.asList(lbcraftMake, lbcraftModel, lbcraftRang, lbcraftRC, lbtxPayload, lbtxLoadVolume).forEach((b)->{
        	b.setStyle(s.LBTextColor);
        	b.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15)); 
        }); 
        vboxi.getChildren().addAll(lbcraftMake, lbcraftModel, lbcraftRang, lbcraftRC,
    			lbtxPayload, lbtxLoadVolume);
    	return vboxi;
    }
/**
 * This function displays the text field	
 * @return
 */
    private VBox getCraftlInfotx() {
    	VBox vboxi = new VBox();
    	vboxi.setSpacing(12);
    	vboxi.setPadding(new Insets(20,10,0,20));
    	txcraftMake = new TextField();
    	txcraftModel = new TextField();
    	txcraftRang = new TextField();
    	txcraftRC = new TextField();
    	txPayload = new TextField();
    	txLoadVolume = new TextField();
    	vboxi.getChildren().addAll(txcraftMake, txcraftModel, txcraftRang, txcraftRC, txPayload, txLoadVolume);//, getClientInfotxZip());
    	return vboxi;
    } 

    /**
     * This is a function used to display the header section and the TextArea section for the result.
     * @return
     */
    private VBox getCraftLBs() {
    	VBox vboxlb = new VBox();
    	vboxlb.setAlignment(Pos.CENTER);
    	vboxlb.getChildren().addAll(getAircraftLabel(), getTextAreaOne());
    	return vboxlb;
    }
    /**
     * This is a header section for the Aircraft view page. This header displays the names of the fields
     * @return
     */
	private HBox getAircraftLabel() {
		HBox hboxv = new HBox();
		hboxv.setAlignment(Pos.CENTER);
		hboxv.setPadding(new Insets(2, 20, 2, 20));
		hboxv.setSpacing(70);
		hboxv.setPrefWidth(700);
		hboxv.setMaxWidth(900);
		hboxv.setStyle("-fx-background-color: white; -fx-border-color: black");
		Label lbcraftID = new Label("Aircraft ID"); 
		Label lbStatusID = new Label("Status"); 
		 lbcraftMake = new Label("AirCraft Make");
		 lbcraftModel = new Label("AirCraft Model");
		 lbcraftRang = new Label("AirCraft Range");
		 lbcraftRC = new Label("Range Clasification");
//		name.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		Address.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		City.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		State.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
		hboxv.getChildren().addAll(lbcraftID, lbStatusID, lbcraftMake, lbcraftModel, lbcraftRang, lbcraftRC);
		return hboxv;
	}

	/**
	 * This TextArea will display the output or result for the Aircraft information 
	 * it will allow users to view a summary of the Aircraft information. Users will not be able to 
	 * change the Aircraft information from this Aircraft view.
	 * @return
	 */
	private ScrollPane getTextAreaOne() {
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
				 while(i < viewAllAir.getAircraftID().size()) {
					 Label gridAirID = new Label (String.valueOf(viewAllAir.getAircraftID().get(i))); 
					 Label gridStatusID = new Label (String.valueOf(viewAllAir.getAircraftStatusID().get(i))); 
					 Label gridMake = new Label(String.valueOf(viewAllAir.getAircraftMake().get(i))); 
					 Label gridModel = new Label(String.valueOf(viewAllAir.getAircraftModel().get(i))); 
					 Label gridRange = new Label(String.valueOf(viewAllAir.getAircraftRange().get(i))); 
					 Label gridRangeClass = new Label(String.valueOf(viewAllAir.getAircraftRangeClass().get(i))); 
					 
					 
					 gpane.add(gridAirID,  0,  row);
					 gpane.add(gridStatusID, 1, row); 
					 gpane.add(gridMake,  2,  row);
					 gpane.add(gridModel,  3,  row);
					 gpane.add(gridRange,  4,  row);
					 gpane.add(gridRangeClass,  5,  row);
									 
					 
					 row++; 
					 i++; 
				 }
				
				box.setContent(gpane); 
				return box;
	}
	
	/**
	 * getViewSelected - the initial pane for the viewAircraft Page, 
	 * will house a feature to view a selected aircraft based on 
	 * aircraft ID
	 * @return
	 */
	private VBox getViewSelected(){
		DBViewAllAircraft all  = new DBViewAllAircraft(); 
		VBox centerBox = new VBox(); 
		 centerBox.setAlignment(Pos.TOP_CENTER);
		 centerBox.setMinHeight(300);
		 centerBox.setStyle("-fx-background-color: white");
		 
		// add title and subtitle instructions 
		    Text title = new Text("View Selected Aircraft"); 
		    Text instructions = new Text("Use the scroll bar to select an AircraftID, then click SEARCH. \n"
		    		+ "This will allow you to view all client information for selected plane.");
		    HBox selection = new HBox(); 
		    selection.setAlignment(Pos.CENTER);
		    ComboBox airSelect = new ComboBox(FXCollections.observableArrayList(all.getAircraftID())); 
		    airSelect.setVisibleRowCount(5);
		    Button airSearch = new Button("Search"); 
		    selection.getChildren().addAll(airSelect, airSearch); 
		
		    
		  //grid of information
		    GridPane grid = new GridPane(); 
		    grid.setAlignment(Pos.CENTER); 
		    Label lbID = new Label("Aircraft ID: "); 
		    Label lbStatusID = new Label("Status ID: "); 
		    Label lbMake = new Label("Make: "); 
		    Label lbModel = new Label("Model: "); 
		    Label lbRange = new Label("Range: "); 
		    Label lbRangeClass = new Label("Range Classification: "); 
		    Label lbPayload = new Label("Payload: "); 
		    Label lbLoadVol = new Label("Load Volume: "); 
		    
		    Text txtID = new Text(); 
		    Text txtStatusID = new Text(); 
		    Text txtMake = new Text(); 
		    Text txtModel = new Text(); 
		    Text txtRange = new Text(); 
		    Text txtRangeClass = new Text(); 
		    Text txtPayload = new Text(); 
		    Text txtLoadVol = new Text(); 
		    
		    grid.add(lbID, 0, 0);
		    grid.add(lbStatusID, 0, 1);
		    grid.add(lbMake, 0, 2);
		    grid.add(lbModel, 0, 3);
		    grid.add(lbRange,0,4); 
		    grid.add(lbRangeClass,0,5);
		    grid.add(lbPayload, 0, 6);
		    grid.add(lbLoadVol, 0, 7);
		    grid.add(txtID, 1, 0);
		    grid.add(txtStatusID, 1, 1);
		    grid.add(txtMake, 1, 2);
		    grid.add(txtModel, 1, 3);
		    grid.add(txtRange,1,4); 
		    grid.add(txtRangeClass,1,5);
		    grid.add(txtPayload, 1, 6);
		    grid.add(txtLoadVol, 1, 7);
		    
		    airSearch.setOnAction(e->{
		    	DBViewSelectAircraft view = new DBViewSelectAircraft(); 
		    	int entry = 0; 
		    	entry = Integer.parseInt(airSelect.getValue().toString());
		    	view.viewSelected(entry);
		    	
		    	txtID.setText(Integer.toString(view.getAircraftID()));
		    	txtStatusID.setText(Integer.toString(view.getStatusID()));
		    	txtMake.setText(view.getMake());
		    	txtModel.setText(view.getModel());
		    	txtRange.setText(view.getRange());
		    	txtRangeClass.setText(view.getRangeClass());
		    	txtPayload.setText(view.getPayload().toString());
		    	txtLoadVol.setText(view.getLoadVolume().toString());
		    });
		    
		    centerBox.getChildren().addAll(title,instructions, selection, grid); 
		    
		return centerBox; 
	}

} //End Subclass Aircraft

 
