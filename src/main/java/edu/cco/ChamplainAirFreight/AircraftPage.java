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
	
/*	  = new Label("AirCraft Make");
	  = new Label("AirCraft Model");
	  = new Label("AirCraft Range");
	  = new Label("Range Clasification");*/
	
    //variables
    BorderPane bPane = new BorderPane();

    //classes
    Styles s = new Styles();

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
     //   Text title = new Text("Model");
        Text instruct = new Text("Aircraft:");
        //style text
//        title.setFill(Color.DARKBLUE);
//        title.setStrokeWidth(2);
//        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));

        instruct.setFill(Color.BLUE);
        instruct.setStrokeWidth(2);
        instruct.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
        //add titles to titlebox. 
        titleBox.getChildren().addAll(instruct);//title, 

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

        //add title, center, and buttons to Aircraft pane:
        box.setTop(titleBox);
        box.setCenter(craftView()); //call a method to show db of flights  
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
            box.setCenter(craftView());
        });

        return box;
    }

    /**
     * viewFlights - generates a chart of the Aircraft information, which can be
     * added to flights() will have to call the database Aircraft table
     *
     * @return
     */
    private ScrollPane craftView() {
        ScrollPane chart = new ScrollPane();
        chart.setMinHeight(360);
        chart.setMaxHeight(360);

        return chart;
    }
    /**
     * This is a function used to display the header section and the TextArea section for the result.
     * @return
     */
    private HBox getCraftViewLBs() {
    	HBox hboxlb = new HBox();
    	hboxlb.setMinHeight(300);
    	hboxlb.setStyle("-fx-background-color: white");
    	hboxlb.setAlignment(Pos.CENTER_LEFT);
    	hboxlb.getChildren().addAll(getCraftInfolb(),getCraftlInfotx());
    	return hboxlb;
    }   
    /*
     * This function will display the Aircraft information for the AircraftPage 
     */
    private VBox getCraftInfolb() {
    	VBox vboxi = new VBox();
    	vboxi.setSpacing(15);
    	vboxi.setPadding(new Insets(20,0,0,20));
    	lbcraftMake = new Label("AirCraft Make");
        lbcraftModel = new Label("AirCraft Model");
        lbcraftRang = new Label("AirCraft Range");
        lbcraftRC = new Label("Range Clasification");
        lbtxPayload = new Label("Payload");
        lbtxLoadVolume = new Label("Load Volume");
        //Adding Styles
        lbcraftMake.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        lbcraftModel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        lbcraftRang.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        lbcraftRC.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        lbtxPayload.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        lbtxLoadVolume.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        
        lbcraftMake.setStyle(s.LBTextColor);
        
        lbcraftMake.setStyle(s.LBTextColor);
        lbcraftModel.setStyle(s.LBTextColor);
        lbcraftRang.setStyle(s.LBTextColor);
        lbcraftRC.setStyle(s.LBTextColor);
        lbtxPayload.setStyle(s.LBTextColor);
        lbtxLoadVolume.setStyle(s.LBTextColor);
    	vboxi.getChildren().addAll(lbcraftMake, lbcraftModel, lbcraftRang, lbcraftRC,
    			lbtxPayload, lbtxLoadVolume);
    	return vboxi;
    }

    /*
     * This function will display the AircraftPage information for the AircraftPage page
     * 	//Text field -  Pierre
	static TextField ;
	static TextField ;
	static TextField ;
	static TextField ;
	static TextField  ;
	static TextField ;
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
		hboxv.setPadding(new Insets(3, 20, 3, 20));
		hboxv.setSpacing(150);
		hboxv.setPrefWidth(700);
		hboxv.setMaxWidth(900);
		hboxv.setStyle("-fx-background-color: white; -fx-border-color: black");
		 lbcraftMake = new Label("AirCraft Make");
		 lbcraftModel = new Label("AirCraft Model");
		 lbcraftRang = new Label("AirCraft Range");
		 lbcraftRC = new Label("Range Clasification");
//		name.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		Address.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		City.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
//		State.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 10));
		hboxv.getChildren().addAll(lbcraftMake, lbcraftModel, lbcraftRang, lbcraftRC);
		return hboxv;
	}

	/**
	 * This TextArea will display the output or result for the Aircraft information 
	 * it will allow users to view a summary of the Aircraft information. Users will not be able to 
	 * change the Aircraft information from this Aircraft view.
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

} //End Subclass Aircraft