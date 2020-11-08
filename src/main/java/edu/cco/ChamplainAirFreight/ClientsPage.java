package edu.cco.ChamplainAirFreight;

import java.util.Arrays;

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
 * @Date: Oct 28, 2020
 * @Subclass ClientsPage Description: GUI panes for client information 
 */
//Imports
//Begin Subclass ClientsPage
public class ClientsPage {
	static TextArea texReaOne = new TextArea();

    // classes 
    Styles s = new Styles();
    
	// Client address labels
	static Label lbName = new Label("Client Name");
	static Label lbAddress = new Label("Address");
	static Label lbCity = new Label("City");
	static Label lbState = new Label("State");
	static ComboBox<String> cbState;
	static Label lbZip = new Label("Zip");

	static TextField txName;
	static TextField txAddress;
	static TextField txCity;
	static TextField txState;
	static TextField txZip;

    //passed border pane from CAF. 
    BorderPane bPane = new BorderPane();
    

    /**
     * constructor pulls the border pane from CAF
     */
    ClientsPage(BorderPane bp) {
        bPane = bp;
		
    }

    /**
     * getPane - what gets called in caf to view clientsPage data 
     * @return 
     */
    public VBox getPane() {
        VBox vclients = new VBox();
        vclients.getChildren().addAll(clients());
        return vclients;
    }

    /**
     * clients - this is the main border pane for the clients page. 
     * @return 
     */
    private BorderPane clients() {
        BorderPane box = new BorderPane();
        
        //create a title pane for the top
        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);
        Text title = new Text("Clients");
        Text instruct = new Text("View Client Information Below:");
        //style text
        title.setFill(Color.DARKBLUE);
        title.setStrokeWidth(2);
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));

        instruct.setFill(Color.BLUE);
        instruct.setStrokeWidth(2);
        instruct.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
        //add titles to titlebox. 
        titleBox.getChildren().addAll(title, instruct);

        //creating center box to add client information - Pierre
        HBox centerBox = new HBox();
        centerBox.setAlignment(Pos.CENTER_LEFT);
        centerBox.setMinHeight(300);
        centerBox.setStyle("-fx-background-color: white");
        centerBox.getChildren().addAll();
        
        //create button HBox:
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(20);

        //make buttons
        Button btnView = new Button("View");
        Button btnAdd = new Button("Add Client");
        Button btnEdit = new Button("Edit Client");
        Button btnDelete = new Button("Delete Client");
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
        btnExit.setMinHeight(0);

        //add buttons to button HBox
        buttonBox.getChildren().addAll(btnView, btnAdd, btnEdit, btnDelete, btnEnter,
                btnCancel, btnExit);

        //add title, center, and buttons to clients pane:
        box.setTop(titleBox);
        box.setCenter(centerBox); //call a method to show db of clients  
        box.setBottom(buttonBox);

        //add actionables to change the setCenter based on button responses:
        btnView.setOnAction(e -> {
        	 box.setCenter(getViewLBs());//(getViewLBs()); 
        //	getClientInfolb(), getClientInfotx()
        });
        btnAdd.setOnAction(e -> {
            box.setCenter(getClientLBs()); 
        });
        btnEdit.setOnAction(e -> {
        	box.setCenter(getClientLBs()); 
        });
        btnDelete.setOnAction(e -> {

        });
        btnEnter.setOnAction(e -> {

        });
        btnCancel.setOnAction(e -> {

        });
        btnExit.setOnAction(e -> {
            //clear whatever actions doing
            //return to just the viewClient page
            box.setCenter(addClient());
        });

        return box;
    }

    /**
     * will connect to db to view client information 
     * @return 
     */
    private ScrollPane addClient() {
        ScrollPane chart = new ScrollPane();
        chart.setMinHeight(560);
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
     * This function will display the client information for the client page
     */
    private VBox getClientInfolb() {
    	VBox vboxi = new VBox();
        GridPane gPane = new GridPane();
        Styles s = new Styles(); //styles class for colors/fonts 
        lbName.setStyle(s.clientLB);
        lbAddress.setStyle(s.clientLB);
        lbCity.setStyle(s.clientLB);
        lbZip.setStyle(s.clientLB);
        lbName = new Label("Client Name");
    	lbAddress = new Label("Address");
    	lbCity = new Label("City");
    	
    	vboxi.getChildren().addAll(lbName, lbAddress, lbCity, lbState);
    	return vboxi;
    }
    /*
     * This function will display the client information for the client page
     */
    private VBox getClientInfotx() {
    	VBox vboxi = new VBox();
    	TextField txName = new TextField();
    	TextField txAddress = new TextField();
    	TextField txCity = new TextField();
    	vboxi.getChildren().addAll(txName, txAddress, txCity, getClientInfotxZip());
    	return vboxi;
    }   
    /*
     * This function will display the State and zip code information for the client page
     */
    private HBox getClientInfotxZip() {
    	HBox hboxi = new HBox();
    	lbZip = new Label("Zip");
    	ComboBox<String> cbState = new ComboBox();
    	txZip = new TextField();
		cbState.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
				"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
				"NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
				"VA", "WA", "WV", "WI", "WY");
    	hboxi.getChildren().addAll(cbState, lbZip,txZip);
    	return hboxi;
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
		hboxv.setPadding(new Insets(3, 20, 3, 20));
		hboxv.setSpacing(150);
		hboxv.setPrefWidth(700);
		hboxv.setMaxWidth(900);
		hboxv.setStyle("-fx-background-color: white; -fx-border-color: black");
		Label name = new Label("Client Name");
		Label Address = new Label("Address");
		Label City = new Label("City");
		Label State = new Label("State");
		Label Zip = new Label("Zip Code");
//		name.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		Address.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		City.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		State.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
		hboxv.getChildren().addAll(name, Address, City, State,Zip);
		return hboxv;
	}
	
	/**
	 * This TextArea will display the output or result for the shipment information 
	 * it will allow users to view a summary of the shipments. Users will not be able to 
	 * change the shipment information from this shipment view.
	 * @return
	 */
	private HBox getTextAreaOne() {
		HBox hboxt = new HBox();
		texReaOne = new TextArea();
		hboxt.setAlignment(Pos.CENTER);
		texReaOne.setStyle("-fx-border-color: black");
		texReaOne.setFont(new Font("Time New Roman", 10));
		texReaOne.setEditable(false);
		texReaOne.setWrapText(true);
		texReaOne.setPrefSize(900, 500);
		hboxt.getChildren().addAll(texReaOne);
		return hboxt;
	}
  
} //End Subclass ClientsPage
