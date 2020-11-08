package edu.cco.ChamplainAirFreight;

import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
 * @Subclass PilotPage Description: GUI panes for pilot information
 */
//Imports
//Begin Subclass PilotPage
public class PilotPage {

	//TextArea for the view - By Pierre
	static TextArea texReaOne = new TextArea();
    
	// Client address labels - By Pierre
	static Label lbfname;
	static Label lblname;
	static Label lbbirthdate;
	static Label lbdatehire;
	static Label lbdateresign;

	//Text field - By Pierre
	static TextField txfname;
	static TextField txlname;
	static TextField txbirthdate;
	static TextField txdatehire;
	static TextField txdateresign;
	
    // classes 
    Styles s = new Styles();

    //passed border pane from CAF. 
    BorderPane bPane = new BorderPane();

    /**
     * constructor pulls the border pane from CAF
     *
     * @param bp
     */
    PilotPage(BorderPane bp) {
        bPane = bp;
    }

    /**
     * getPane - this will call the pilots pane into the CAF main page.
     *
     * @return
     */
    public VBox getPane() {
        VBox vbox = new VBox();
        vbox.getChildren().add(pilots());
        return vbox;
    }

    /**
     * pilots - this is the main border pane for the pilots page
     *
     * @return
     */
    private BorderPane pilots() {
        BorderPane box = new BorderPane();

        //create a title pane for the top
        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);
        Text title = new Text("Pilots");
        Text instruct = new Text("View Pilot Information Below:");
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
        Button btnAdd = new Button("Add Pilot");
        Button btnEdit = new Button("Edit Pilot");
        Button btnDelete = new Button("Delete Pilot");
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

        //add title, center, and buttons to pilots pane:
        box.setTop(titleBox);
        box.setCenter(viewPilots()); //call a method to show db of pilots  
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
            //return to just the viewPilots page
            box.setCenter(viewPilots());
        });

        return box;
    }

    /**
     * will connect to the db to view pilot information
     *
     * @return
     */
    private ScrollPane viewPilots() {
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
     * This function will display the pilot information for the pilot page
     */
    private VBox getClientInfolb() {
    	VBox vboxi = new VBox();
        GridPane gPane = new GridPane();
		 lbfname = new Label("First Name");
		 lblname = new Label("Last Name");
		 lbbirthdate = new Label("Date of Birth");
		 lbdatehire = new Label("Date Hired");
		 lbdateresign  = new Label("Date Resigned");
    	
    	vboxi.getChildren().addAll(lbfname,lblname,lbbirthdate,lbdatehire,lbdateresign);
    	return vboxi;
    }

    /*
     * This function will display the pilot information for the pilot page
     */
    private VBox getClientInfotx() {
    	VBox vboxi = new VBox();
    	 txfname = new TextField();
    	 txlname = new TextField();
    	 txbirthdate = new TextField();
    	 txdatehire = new TextField();
    	 txdateresign = new TextField();
    	vboxi.getChildren().addAll(txfname,txlname ,txbirthdate,txdatehire,txdateresign );//, getClientInfotxZip());
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
     * This is a header section for the pilot view page. This header displays the names of the fields
     * @return
     */
	private HBox getViewLabel() {
		HBox hboxv = new HBox();
		hboxv.setAlignment(Pos.CENTER);
		hboxv.setPadding(new Insets(3, 20, 3, 20));
		hboxv.setSpacing(100);
		hboxv.setPrefWidth(700);
		hboxv.setMaxWidth(900);
		hboxv.setStyle("-fx-background-color: white; -fx-border-color: black");
		 lbfname = new Label("First Name");
		 lblname = new Label("Last Name");
		 lbbirthdate = new Label("Date of Birth");
		 lbdatehire = new Label("Date Hired");
		 lbdateresign  = new Label("Date Resigned");
   	
		 hboxv.getChildren().addAll(lbfname,lblname,lbbirthdate,lbdatehire,lbdateresign);
		return hboxv;
	}

	/**
	 * This TextArea will display the output or result for the pilot information 
	 * it will allow users to view a summary of the flight. Users will not be able to 
	 * change the pilot information from this flight view.
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
} //End Subclass PilotPage
