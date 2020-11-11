package edu.cco.ChamplainAirFreight;

import java.util.Arrays;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
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
        Button btnView = new Button("View Pilot");
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
            box.setCenter(viewPilots());
        });
        btnAdd.setOnAction(e -> {
        	String fName="Freddy";
        	String lName="Right";  
        	Date dob =new Date(1990/10/21);            
        	String number ="345TGHT";
        	Date hire = new Date(2011/11/11);        	
        	
        	DBAddPilot addP=new DBAddPilot(fName,lName,dob,number,hire);
        	addP.pFirstName=fName;
        	addP.pLastName=lName;
        	addP.pDOB=dob;
        	addP.pHireDate=hire;
        	
        	
            //box.setCenter(addPilot()); 
        });
        btnEdit.setOnAction(e -> {

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

} //End Subclass PilotPage
