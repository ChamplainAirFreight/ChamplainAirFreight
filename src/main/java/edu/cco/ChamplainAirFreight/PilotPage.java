package edu.cco.ChamplainAirFreight;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import edu.cco.ChamplainAirFreight.Database.DBFinder;
import edu.cco.ChamplainAirFreight.Database.Client.DBAddClientAddress;
import edu.cco.ChamplainAirFreight.Database.Client.DBUpdateClientAddress;
import edu.cco.ChamplainAirFreight.Database.Pilot.DBAddPilot;
import edu.cco.ChamplainAirFreight.Database.Pilot.DBDeletePilot;
import edu.cco.ChamplainAirFreight.Database.Pilot.DBUpdatePilot;
import edu.cco.ChamplainAirFreight.Database.Pilot.DBViewAllPilot;
import edu.cco.ChamplainAirFreight.Database.Pilot.DBViewSelectPilot;
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
 * @Subclass PilotPage Description: GUI panes for pilot information
 */

//Begin Subclass PilotPage
public class PilotPage {

	//TextArea for the view - By Pierre
	static TextArea texReaOne = new TextArea();
	
	   //used for validation
    ValidateFields valid=new ValidateFields();
    
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
    
  //make buttons
    Button btnView = new Button("View");
    Button btnAdd = new Button("Add Pilot");
    Button btnEdit = new Button("Edit Pilot");
    Button btnDelete = new Button("Delete Pilot");
    Button btnEnter = new Button("Enter");
    Button btnCancel = new Button("Cancel");
    Button btnExit = new Button("Exit");

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
        buttonBox.setPadding(new Insets(20,0,0,0));
        buttonBox.setAlignment(Pos.CENTER);
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

        //add title, center, and buttons to pilots pane:
        box.setTop(titleBox);
        box.setCenter(getViewSelected()); //call a method to show db of pilots  
        box.setBottom(buttonBox);

        //add actionables to change the setCenter based on button responses:
        btnView.setOnAction(e -> {
        	box.setCenter(getViewLBs());
        });
        btnAdd.setOnAction(e -> {
            box.setCenter(addPane()); 
        });
        btnEdit.setOnAction(e -> {
        	 box.setCenter(editSelected()); 	
        });
        btnDelete.setOnAction(e -> {
        	box.setCenter(deletePilotPane());
        });
       btnExit.setOnAction(e -> {
            //clear whatever actions doing
            //return to just the viewPilots page
            box.setCenter(getViewSelected());
        });

        return box;
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
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//VIEW ALL
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * This TextArea will display the output or result for the pilot information 
	 * it will allow users to view a summary of the flight. Users will not be able to 
	 * change the pilot information from this flight view.
	 * calls the DBViewAllPilot class
	 * @return
	 */
	private ScrollPane getTextAreaOne() {
		DBViewAllPilot view = new DBViewAllPilot(); // call the view all class for pilot
		ScrollPane box = new ScrollPane(); 
		box.setFitToWidth(true);
		box.setStyle("-fx-background-color: white; -fx-border-color: black");
		
		GridPane gpane = new GridPane(); 
		gpane.setStyle("-fx-background-color: white; -fx-border-color: black");
		gpane.setPadding(new Insets(2,20,2,20));
		gpane.setAlignment(Pos.TOP_CENTER);
		gpane.setHgap(110);
		gpane.setVgap(5);
		
		int row = 1; 
		int i = 0; 
		while(i< view.getPilotID().size()) {
			Label gridFirstName = new Label(String.valueOf(view.getFirstName().get(i))); 
			Label gridLastName = new Label(String.valueOf(view.getLastName().get(i))); 
			Label gridDob = new Label(String.valueOf(view.getDateOfBirth().get(i))); 
			Label gridHire = new Label(String.valueOf(view.getDateOfHire().get(i))); 
			Label gridResign = new Label(String.valueOf(view.getDateLeftCAF().get(i))); 
			
			gpane.add(gridFirstName, 0, row);
			gpane.add(gridLastName, 1, row);
			gpane.add(gridDob, 2, row);
			gpane.add(gridHire, 3, row);
			gpane.add(gridResign, 4, row);
			
			row++; 
			i++; 
		}
		
		box.setContent(gpane); //add all the pilot info to the scrollpane
		return box; 
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//VIEW SELECTED
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * getViewSelected - the initial pane for the Pilot Page. Will hold a feature to view an individual 
	 * pilot with the DBViewSelectPilot class
	 * Kelly May
	 * 11/18/2020
	 */
	private VBox getViewSelected() {
		DBViewAllPilot all = new DBViewAllPilot(); // for filling the combo box
		
	    VBox centerBox = new VBox();
	    centerBox.setAlignment(Pos.TOP_CENTER);
	    centerBox.setMinHeight(300);
	    centerBox.setStyle("-fx-background-color: white");
	    
	    
	    // Add title and subtitle for instructions
	    Text title = new Text("View Selected Pilot"); 
	    Text instructions = new Text("Use the scroll bar to select a Pilot Name, then click SEARCH. \n"
	    		+ "This will allow you to view all pilot information for selected pilot."); 
	    
	    // add a combobox and fill with all client names
	    HBox selection = new HBox(); 
	    selection.setAlignment(Pos.CENTER);
	    //make arraylist with first and last names together
	    ArrayList<String> name = new ArrayList<>(); 
	    for (int i =0; i < all.getFirstName().size(); i++) {
	    	name.add(all.getFirstName().get(i) + " " + all.getLastName().get(i)); 
	    }
	    ComboBox<String> pilotSelect = new ComboBox(FXCollections.observableArrayList(name)); 
	    pilotSelect.setVisibleRowCount(5); 
	    
	    Button pilotSearch = new Button("Search"); 
	    selection.getChildren().addAll(pilotSelect, pilotSearch); 
	    
	    //grid of information: 
	    GridPane grid = new GridPane(); 
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(4);
	    grid.setVgap(4);
	    Label lbID = new Label("Pilot ID: "); 
		Text txtID = new Text(); 
		Label lbFirstName = new Label("First Name: "); 
		Text txtFirstName = new Text(); 
	    Label lblLastName = new Label("Last Name: "); 
	    Text txtLastName = new Text();  
	    Label lblDob = new Label("Date of Birth: "); 
	    Text txtDob = new Text();  
	    Label lblEmployeeNum = new Label("Employee Number: "); 
	    Text txtEmployeeNum = new Text();  
	    Label lblDateOfHire = new Label("Date of Hire: "); 
	    Text txtDateOfHire = new Text(); 
	    Label lblDateLeft = new Label("Date Left CAF: "); 
	    Text txtDateLeft = new Text();  
	     
	    
	    grid.add(lbID, 0,  0);
	    grid.add(txtID, 1,  0);
	    grid.add(lbFirstName, 0, 1);
	    grid.add(txtFirstName, 1,  1);
	    grid.add(lblLastName, 0,  2);
	    grid.add(txtLastName, 1,  2);
	    grid.add(lblDob, 0, 3);
	    grid.add(txtDob, 1, 3);
	    grid.add(lblEmployeeNum, 0, 4);
	    grid.add(txtEmployeeNum,1 ,4);
	    grid.add(lblDateOfHire, 0, 5);
	    grid.add(txtDateOfHire, 1, 5);
	    grid.add(lblDateLeft, 0, 6);
	    grid.add(txtDateLeft, 1, 6);
	   
	    
	   // fill text with selected flight information
	    pilotSearch.setOnAction(e->{
		   try {
			DBViewSelectPilot view = new DBViewSelectPilot(); //to view a select flight
			int index = name.indexOf(pilotSelect.getValue()); 
	    	int id = all.getPilotID().get(index); 
	    	view.viewSelected(id);

	    	txtID.setText(Integer.toString(view.getPilotID()));
	    	txtFirstName.setText(view.getPilotFirstName());
	    	txtLastName.setText(view.getPilotLastName());
	    	txtDob.setText(String.valueOf(view.getDateOfBirth()));
	    	txtEmployeeNum.setText(view.getEmployeeNum());
	    	txtDateOfHire.setText(String.valueOf(view.getDateOfHire()));
	    	txtDateLeft.setText(String.valueOf(view.getDateLeftCAF()));
	    	   	
	    	
		   } catch(Exception ex) {
			   pilotSelect.requestFocus(); 
		   }
		   
	    });
	    centerBox.getChildren().addAll(title, instructions, selection, grid);
	    
	    btnCancel.setOnAction(e->{
	    	pilotSelect.valueProperty().set(null);
	    	txtID.setText(""); 
	    	txtFirstName.setText("");
	    	txtLastName.setText("");
	    	txtDob.setText("");
	    	txtEmployeeNum.setText("");
	    	txtDateOfHire.setText("");
	    	txtDateLeft.setText("");
	    });
	    return centerBox; 
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//UPDATE
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * editSelected Used to select a pilot and import the textfields 
	 * Kelly May	  
	 * 11/18/2020
	 * Modified by Matt Ridgway for editing a pilot 
	 * 12/1/2020 Problems with DATE
	 */
	private VBox editSelected() {
		DBViewAllPilot all = new DBViewAllPilot(); // for filling the combo box with Pilots
		DBUpdatePilot update =new DBUpdatePilot();
		
	    VBox centerBox = new VBox();
	    centerBox.setAlignment(Pos.TOP_CENTER);
	    centerBox.setMinHeight(300);
	    centerBox.setStyle("-fx-background-color: white");
	    
	    
	    // Add title and subtitle for instructions
	    Text title = new Text("Edit Selected Pilot"); 
	    Text instructions = new Text("Select a Pilot to edit."); 
	    
	   
	    HBox selection = new HBox(); 
	    selection.setAlignment(Pos.CENTER);
	    //make arraylist with first and last names together
	    ArrayList<String> name = new ArrayList<>(); 
	    for (int i =0; i < all.getFirstName().size(); i++) {
	    	name.add(all.getFirstName().get(i) + " " + all.getLastName().get(i)); 
	    }
	    ComboBox<String> pilotSelect = new ComboBox(FXCollections.observableArrayList(name)); 
	    pilotSelect.setVisibleRowCount(5); 
	    
	    Button pilotSearch = new Button("Select Pilot"); 
	    selection.getChildren().addAll(pilotSelect, pilotSearch); 
	    
	    //grid of information: 
	    GridPane grid = new GridPane(); 
	    grid.setPadding(new Insets(4,4,4,4));
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(4);
	    grid.setVgap(4);
	    Label lbID = new Label("Pilot ID: "); 
		TextField txtID = new TextField(); 
		txtID.setEditable(false);// So this text field can't be edited
		Label lbFirstName = new Label("First Name: "); 
		TextField txtFirstName = new TextField(); 
	    Label lblLastName = new Label("Last Name: "); 
	    TextField txtLastName = new TextField();  
	    Label lblDob = new Label("Date of Birth: "); 
	    DatePicker txtDob = new DatePicker();  
	    Label lblEmployeeNum = new Label("Employee Number: "); 
	    TextField txtEmployeeNum = new TextField();  
	    Label lblDateOfHire = new Label("Date of Hire: "); 
	    DatePicker txtDateOfHire = new DatePicker(); 
	    Label lblDateLeft = new Label("Date Left CAF: "); 
	    DatePicker txtDateLeft = new DatePicker();  
	     
	    
	    grid.add(lbID, 0,  0);
	    grid.add(txtID, 1,  0);
	    grid.add(lbFirstName, 0, 1);
	    grid.add(txtFirstName, 1,  1);
	    grid.add(lblLastName, 0,  2);
	    grid.add(txtLastName, 1,  2);
	    grid.add(lblDob, 0, 3);
	    grid.add(txtDob, 1, 3);
	    grid.add(lblEmployeeNum, 0, 4);
	    grid.add(txtEmployeeNum,1 ,4);
	    grid.add(lblDateOfHire, 0, 5);
	    grid.add(txtDateOfHire, 1, 5);
	    grid.add(lblDateLeft, 0, 6);
	    grid.add(txtDateLeft, 1, 6);
	   
	    
	   // fill text with selected pilot information
	    pilotSearch.setOnAction(e->{
		   try {
			DBViewSelectPilot view = new DBViewSelectPilot(); //to view a select pilot
			int index = name.indexOf(pilotSelect.getValue()); 
	    	int id = all.getPilotID().get(index); 
	    	view.viewSelected(id);

	    	txtID.setText(Integer.toString(view.getPilotID()));
	    	txtFirstName.setText(view.getPilotFirstName());
	    	txtLastName.setText(view.getPilotLastName());
	    	txtDob.valueProperty().set(view.getDateOfBirth().toLocalDate());
	    	txtEmployeeNum.setText(view.getEmployeeNum());
	    	txtDateOfHire.valueProperty().set(view.getDateOfHire().toLocalDate()); //(String.valueOf(view.getDateOfHire()));
	    	txtDateLeft.valueProperty().set(view.getDateLeftCAF().toLocalDate());	    	   	
	    	
		   } catch(Exception ex) {
			   pilotSelect.requestFocus(); 
		   }
		
	    });
	    centerBox.getChildren().addAll(title, instructions, selection, grid);
	  btnEnter.setOnAction(e->{
			//strings for validation
  		String head="Pilot ID";
  		String cont="Not a int";
		int pilotID = valid.intChecker(txtID.getText(), head, cont); 
				 
		head="First Name";
  		cont="Not a string";
		String firstName;
			if(valid.isString(txtFirstName.getText().toString())) {
			firstName =txtFirstName.getText();	
			} else {
				valid.error.setError(head, cont);
				firstName ="";
			}
			
		head="Last Name";
	  	cont="Not a string";
		String lastName;
				if(valid.isString(txtLastName.getText().toString())) {
				lastName =txtLastName.getText();	
				} else {
					valid.error.setError(head, cont);
					lastName ="";
				}
				
		  String eNumber = txtEmployeeNum.getText();
		  
		  Date dob = Date.valueOf(txtDob.getValue());		
		  Date hireDate = Date.valueOf(txtDateOfHire.getValue());
		  		  
	if(pilotID==0) {
		txtID.clear();
	}else if(firstName=="") {
		txtFirstName.clear();
	}else if(lastName=="") {
		txtLastName.clear();
	}else if (valid.stringLength(eNumber)==0) {
		valid.error.setError("Employee Number", " Can't be Empty");
	}
	else if(valid.afterDate(dob, hireDate)) {
		valid.error.setError("DOB", "Can't be after Hire Date");
	}
	else {
		 try {
			  Date leftDate = Date.valueOf(txtDateLeft.getValue()); 
			  update.updatePilot(pilotID, firstName, lastName, dob, eNumber, hireDate, leftDate);
			  System.out.println("date left - " + leftDate);
			  //clear textFields
			   txtID.clear();
			   txtFirstName.clear();
			   txtLastName.clear();
			   txtDob.valueProperty().set(null);
			   txtEmployeeNum.clear();
			   txtDateOfHire.valueProperty().set(null);
			   txtDateLeft.valueProperty().set(null);	
			  } catch (NullPointerException ex) {
				  update.updatePilot(pilotID,  firstName,  lastName,  dob,  eNumber,  hireDate, null);
				  //clear textFields
				   txtID.clear();
				   txtFirstName.clear();
				   txtLastName.clear();
				   txtDob.valueProperty().set(null);
				   txtEmployeeNum.clear();
				   txtDateOfHire.valueProperty().set(null);
				   txtDateLeft.valueProperty().set(null);	
			  }
	}
		  		  
		 	  
	  });
	  
	  btnCancel.setOnAction(e->{
		  pilotSelect.valueProperty().set(null);
		  txtID.clear();
		   txtFirstName.clear();
		   txtLastName.clear();
		   txtDob.valueProperty().set(null);
		   txtEmployeeNum.clear();
		   txtDateOfHire.valueProperty().set(null);
		   txtDateLeft.valueProperty().set(null);	
	  });
	    
	    return centerBox; 
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//ADD
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * addPane - pane for adding a Pilot in the database. 
	 * Kelly May
	 */
	private VBox addPane() {
		VBox box = new VBox(); 
		box.setAlignment(Pos.CENTER); 
		box.setSpacing(10);
		box.setPadding(new Insets(2,20,2,20));
		box.setStyle("-fx-background-color: white");
		
		//title and instructions 
		Text title = new Text("Add a new Pilot"); 
		Text instructions = new Text("Enter valid information for a Pilot, and then press Enter"); 
		//labels
		Label lblFirstName = new Label ("First Name: "); 
		Label lblLastName = new Label ("Last Name: "); 
		Label lblDOB = new Label ("Date of Birth: "); 
		Label lblEmpNum = new Label("Employee Number: "); 
				
		//style labels
		Arrays.asList(lblFirstName, lblLastName, lblDOB, lblEmpNum).stream().map((b)->{
			b.setStyle(s.clientLB); 
			return b; 
		}); 
				
		//entry fields
		TextField txtFirstName = new TextField(); 
		TextField txtLastName = new TextField(); 
		DatePicker dpDOB = new DatePicker(); 
		TextField txtEmpNum = new TextField(); 
		
		//add input values into a gridpane
		GridPane grid = new GridPane(); 
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(4);
		grid.add(lblFirstName, 0, 0);
		grid.add(lblLastName, 0, 1);
		grid.add(lblDOB, 0, 2);
		grid.add(lblEmpNum, 0, 3);
				
		grid.add(txtFirstName, 1, 0);
		grid.add(txtLastName, 1, 1);
		grid.add(dpDOB, 1, 2);
		grid.add(txtEmpNum, 1, 3);
			
		box.getChildren().addAll(title,instructions,grid); 
		btnEnter.setOnAction(e->{
			//variables for SQL stored procedure
			String head="First Name";
	  		String cont="Not a String";
	  		String fName;
			if(valid.isString(txtFirstName.getText().toString())) {
				fName =txtFirstName.getText();	
			} else {
				valid.error.setError(head, cont);
				fName ="";
			}
			
		head="Last Name";
	  	cont="Not a string";
		String lName;
				if(valid.isString(txtLastName.getText().toString())) {
				lName =txtLastName.getText();	
				} else {
					valid.error.setError(head, cont);
					lName ="";
				}
		
			Date dob = Date.valueOf(dpDOB.getValue());
			String empNum = txtEmpNum.getText(); 
			Date dateHire = Date.valueOf(java.time.LocalDate.now()); //today's date auto added
			if(fName=="") {
				txtFirstName.clear();
			}else if(lName=="") {
				txtLastName.clear();
			}else if (valid.stringLength(empNum)==0) {
				valid.error.setError("Employee Number", " Can't be Empty");
			}
			else if(valid.afterDate(dob, dateHire)) {
				valid.error.setError("DOB", "Can't be after Hire Date");
			}else {
				//add pilot
				DBAddPilot addPilot = new DBAddPilot(fName, lName, dob, empNum, dateHire); 		
				//clear entry fields
				txtFirstName.clear(); 
				txtLastName.clear(); 
				dpDOB.valueProperty().set(null);
				txtEmpNum.clear(); 
				
			}		
		
		});
		
		btnCancel.setOnAction(e->{
			txtFirstName.clear(); 
			txtLastName.clear(); 
			dpDOB.valueProperty().set(null);
			txtEmpNum.clear(); 
		});
		    	
		return box; 
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//DELETE
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * deletePilotPane() - deletes a pilot from the db
	 * Kelly May
	 * 12/3/2020
	 * @return
	 */
	public VBox deletePilotPane() {
		DBViewAllPilot all = new DBViewAllPilot(); 
		
		VBox box = new VBox(); 
		box.setAlignment(Pos.CENTER); 
		box.setSpacing(10);
		box.setPadding(new Insets(2,20,2,20));
		box.setStyle("-fx-background-color: white");
		
		 ArrayList<String> name = new ArrayList<>(); 
		    for (int i =0; i < all.getFirstName().size(); i++) {
		    	name.add(all.getFirstName().get(i) + " " + all.getLastName().get(i)); 
		    }
		    
		//title and instructions 
		Text title = new Text("Delete a Pilot"); 
		Text instructions = new Text("Select a Pilot you wish to delete, and then press Enter"); 
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(4);
		
		Label lblPilotName = new Label("Pilot Name: "); 
		ComboBox<String> cbPilotName = new ComboBox(FXCollections.observableArrayList(name)); 
		grid.add(lblPilotName, 0, 0);
		grid.add(cbPilotName, 1, 0);
		
		box.getChildren().addAll(title, instructions, grid); 
		
		btnEnter.setOnAction(e->{
			int index = name.indexOf(cbPilotName.getValue());  //get pilot ID based on index of name
	    	int id = all.getPilotID().get(index);
	    	
	    	DBDeletePilot delete = new DBDeletePilot(); 
	    	delete.deletePilot(id); //delete pilot based on pilot ID
	    	
	    	cbPilotName.valueProperty().set(null);
	    	DBViewAllPilot viewAgain = new DBViewAllPilot(); 
	    	name.clear(); 
	    	for (int i =0; i < viewAgain.getFirstName().size(); i++) {
		    	name.add(all.getFirstName().get(i) + " " + viewAgain.getLastName().get(i)); 
		    }
	    	cbPilotName.setItems(FXCollections.observableArrayList(name));
	    	deletePilotPane(); //reopen deletePilotPane
		});
		
		btnCancel.setOnAction(e->{
			cbPilotName.valueProperty().set(null);
		});
		return box; 
		
	}
} //End Subclass PilotPage
