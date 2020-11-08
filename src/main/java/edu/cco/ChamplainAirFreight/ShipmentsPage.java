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

	static TextField txShipmentVolume;
	static TextField txShipmentWeight;
	static TextField txShipmentStartDate;
	static TextField txShipmentEndDate;

	static TextArea texRea;
	
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
        Button btnAdd = new Button("Add Shipment");
        Button btnEdit = new Button("Edit Shipment");
        Button btnDelete = new Button("Delete Shipment");
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

        //add title, center, and buttons to shipment pane:
        box.setTop(titleBox);
        box.setCenter(centerBox); //call a method to show db of shipments  
        box.setBottom(buttonBox);

        //add actionables to change the setCenter based on button responses:
        btnView.setOnAction(e -> {
        	box.setCenter(getViewLBs());
        });
        btnAdd.setOnAction(e -> {              	 
        	 box.setCenter(getClientLBs() );
        });
        btnEdit.setOnAction(e -> {
        	 box.setCenter(getClientLBs() );
        });
        btnDelete.setOnAction(e -> {

        });
        btnEnter.setOnAction(e -> {

        });
        btnCancel.setOnAction(e -> {

        });
        btnExit.setOnAction(e -> {
            //clear whatever actions doing
            //return to just the viewShipments page
            box.setCenter(addShipments());
        });

        return box;
    }

    /**
     * viewShipments - generates a chart of the Shipment information, which can be
     * added to shipments() will have to call the database Shipments table
     *
     * @return
     */
    private ScrollPane addShipments() {
        ScrollPane chart = new ScrollPane();
        chart.setMinHeight(360);
        chart.setMaxHeight(360);

        return chart;
    }
  // 
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
		Label lbShipmentVolume = new Label("Shipment Volume");
		Label lbShipmentWeight = new Label("Shipment Weight");
		Label lbShipmentStartDate = new Label("Shipment Start Date");
		Label lbShipmentEndDate = new Label("Shipment End Date");
		Label lbShipmentNotes = new Label("Shipment Notes");
    	vboxi.getChildren().addAll(lbShipmentVolume, lbShipmentWeight, lbShipmentStartDate,
    			lbShipmentEndDate, lbShipmentNotes);
    	return vboxi;
    }
    /**
     * This function displays the text fields. User's will be able to enter information in the textfields
     * @return
     */
    private VBox getClientInfotx() {
    	VBox vboxi = new VBox();
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
        texRea.setPrefSize(600, 200);
    	vboxi.getChildren().addAll(txShipmentVolume, txShipmentWeight, txShipmentStartDate, 
    			txShipmentEndDate, texRea);
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
		hboxv.setPadding(new Insets(3, 20, 3, 20));
		hboxv.setSpacing(150);
		hboxv.setPrefWidth(700);
		hboxv.setMaxWidth(900);
		hboxv.setStyle("-fx-background-color: white; -fx-border-color: black");
		Label name = new Label("Volume");
		Label Address = new Label("Start Date");
		Label City = new Label("End Date");
		Label State = new Label("State");
//		name.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		Address.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		City.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		State.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
		hboxv.getChildren().addAll(name, Address, City, State);
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
} //End Subclass ShipmentsPage
