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
        box.setCenter(viewFlights()); //call a method to show db of flights  
        box.setBottom(buttonBox);

        //add actionables to change the setCenter based on button responses:
        btnView.setOnAction(e -> {
            box.setCenter(getViewLBs());
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
            //return to just the viewFlights page
            box.setCenter(viewFlights());
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
		hboxv.setSpacing(150);
		hboxv.setPrefWidth(700);
		hboxv.setMaxWidth(900);
		hboxv.setStyle("-fx-background-color: white; -fx-border-color: black");
		Label lbstartairport = new Label("Start Airport");
		Label lbtxendairport = new Label("End Airport");
		Label lbtxflightstarttime = new Label("Flight Start Time");
		Label lbtxflightendtime = new Label("Flight End Time");
//		name.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		Address.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		City.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		State.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
		hboxv.getChildren().addAll(lbstartairport, lbtxendairport, lbtxflightstarttime, lbtxflightendtime);
		return hboxv;
	}

	/**
	 * This TextArea will display the output or result for the flight information 
	 * it will allow users to view a summary of the flight. Users will not be able to 
	 * change the flight information from this flight view.
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

} //End Subclass FlightsPage
