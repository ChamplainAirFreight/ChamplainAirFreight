package edu.cco.ChamplainAirFreight;

import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
 * @Author Name: Andrew Dockan
 * @Assignment Name: LoginPage
 * @Date: Nov 21, 2020
 * @Subclass LoginPage Description: GUI pane for initial login 
 */

public class LoginPage {
	
	Styles s = new Styles();
	
	//labels for login fields
	static Label lbUsername = new Label("User Name: ");
	static Label lbPassword = new Label("Password: ");
	
	//login fields
	static TextField tfUsername = new TextField();
	static TextField tfPassword = new TextField();
	
	//login buttons
	Button btnLogin = new Button("Login");
	Button btnForgotPassword = new Button("Forgot Password");
	Button btnExit = new Button("Exit");
	
	//passed borderpane from CAF
	BorderPane bPane = new BorderPane();
	
	//constructor takes borderpane from CAF
	LoginPage(BorderPane bp){
		bPane = bp;
	}
	
	// getPane - what gets called in caf to view loginPage data 
    // @return 
	public VBox getPane() {
		VBox vlogin = new VBox();
		vlogin.getChildren().addAll(login());
        return vlogin;
	}
	
	private BorderPane login() {
		BorderPane box = new BorderPane();
		
		VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);
        Text title = new Text("Login");
        Text instruct = new Text("Please Enter Your Login Credentials Below:");
        //style text
        title.setFill(Color.DARKBLUE);
        title.setStrokeWidth(2);
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));

        instruct.setFill(Color.BLUE);
        instruct.setStrokeWidth(2);
        instruct.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
        //add titles to titlebox. 
        titleBox.getChildren().addAll(title, instruct);

      //add input values into a gridpane
    	GridPane grid = new GridPane(); 
    	grid.setAlignment(Pos.CENTER);
    	grid.setHgap(10);
    	grid.setVgap(4);
    	grid.add(lbUsername, 0, 0);
    	grid.add(lbPassword, 0, 1);    	
    	grid.add(tfUsername, 1, 0);
    	grid.add(tfPassword, 1, 1);
        
        //create button HBox:
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20,0,0,0));
        buttonBox.setSpacing(20);

      //style buttons
        Arrays.asList(btnLogin, btnForgotPassword, btnExit).forEach((b) -> {
            b.setStyle(s.entryButtons);
            b.setMinHeight(30);
            b.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
        });
        //Exit button style
        btnExit.setStyle(s.redEntryButtons);
        btnExit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
        btnExit.setMinHeight(0);

        //add buttons to button HBox
        buttonBox.getChildren().addAll(btnLogin, btnForgotPassword, btnExit);

        //add title, center, and buttons to clients pane:
        box.setTop(titleBox);
        box.setCenter(grid); //call a method to show db of clients  
        box.setBottom(buttonBox);

        //add actionables to change the setCenter based on button responses:
        btnLogin.setOnAction(e -> {
        	
        });
        
        btnForgotPassword.setOnAction(e -> {
          
           
         });
        
        btnExit.setOnAction(e -> {
            
        });
		
		return box;
	}
	
}
