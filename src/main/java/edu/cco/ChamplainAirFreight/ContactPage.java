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
 * @Subclass ContactPage Description: holds GUI for the contact-us page
 */
//Begin Subclass ContactPage
public class ContactPage {
	
    //variables
    BorderPane bPane = new BorderPane();
    
	//TextArea for the view - By Pierre
	static TextArea texReaOne = new TextArea();
    
	// Client address labels - By Pierre
	static Label lbfname;
	static Label lblname;
	static Label lbcompany;
	static Label lbphone;
	static Label lbmessage;

	//Text field - By Pierre
	static TextField txfname;
	static TextField txlname;
	static TextField txcompany;
	static TextField txphone;
	static TextField txmessage;

    //classes
    Styles s = new Styles();

    /**
     * Constructor - pulls the border pane from CAF (main page)
     *
     * @param bp
     */
    ContactPage(BorderPane bp) {
        bPane = bp;
    }

    /**
     * getPane - this will call the Contact pane into the CAF main page.
     *
     * @return
     */
    public VBox getPane() {
        VBox vbox = new VBox();
        vbox.getChildren().add(contacts());
        return vbox;
    }

    /**
     * contacts - main GUI portion for contacts page 
     * @return 
     */
    private BorderPane contacts() {
        BorderPane box = new BorderPane();

        //create a title pane for the top
        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);
        Text title = new Text("Contact Us");
        Text instruct = new Text("Please see below for ways to get in "
                + "touch with Champlain Air Freight:");
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
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setMinHeight(300);
        centerBox.setStyle("-fx-background-color: white");
        centerBox.getChildren().addAll(getContactInfo());
  
        //create button HBox:
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setMinHeight(50);
        buttonBox.getChildren().addAll(getContactNumb() );
        
        //add title and center to pane:
        box.setTop(titleBox);
        box.setCenter(centerBox);
        box.setBottom(buttonBox);
        
      //  centerBox.getChildren().addAll(getClientLBs());
       
        return box;
    }
/**
 * This fucntion displays the size of the box
 * @return
 */
    private ScrollPane viewContact() {
        ScrollPane chart = new ScrollPane();
        chart.setMinHeight(400);
        chart.setMaxHeight(400);
        //add text for Contact information (could be pulled from a text file)
        return chart;
    }

	  /**By - Pierre
	   * This function will display the contact information for the technical service team
	   * @return
	   */
	    private VBox getContactInfo() {
	    	VBox vboxlb = new VBox();
	    	Label contTex = new Label("The Champlain Air-Freight can be reached 27/7");
	    	Label cInfo1 = new Label("For any dificulty or if you are having the following issue Please call "
	    			+ "out technical team phone number");
	    	Label cInfo2 = new Label("1 - Program not launching");
	    	Label cInfo3 = new Label("2 - The program keeps restarting");
	    	Label cInfo4 = new Label("3 - The program is freezing with a popup error");
	    	Label cInfo5 = new Label("4 - Need to install the program on a new computer");
	    	Label cInfo6 = new Label("5 - The you are receiving an error message when trying to enter, search or save data");
	    	contTex.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 40));
	    	cInfo1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	    	cInfo2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	    	cInfo3.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	    	cInfo4.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	    	cInfo5.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	    	cInfo6.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	    	
	    	vboxlb.setMinHeight(300);
	    	vboxlb.setAlignment(Pos.CENTER_LEFT);
	    	vboxlb.getChildren().addAll(contTex, cInfo2, cInfo3, cInfo4, cInfo5, cInfo6);
	    	return vboxlb;
	    }   
	    /**
	     * This function displays the contact number 
	     * @return
	     */
	    private VBox getContactNumb() {
	    	VBox vboxlb = new VBox();
	    	Label conNumb = new Label("TOLL FREE - 1800-AIR-FREI");
	    	Label conNumb1 = new Label("PHONE NUMBER: 1800-234-3333"); 
	    	conNumb.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
	    	conNumb1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	    	vboxlb.setMinHeight(100);
	    	vboxlb.setAlignment(Pos.CENTER_LEFT);
	    	vboxlb.getChildren().addAll(conNumb, conNumb1);
	    	return vboxlb;
	    }  

} //End Subclass ContactPage
