package edu.cco.ChamplainAirFreight;

import java.util.ArrayList;
import java.util.Arrays;
import edu.cco.ChamplainAirFreight.Database.DBFinder;
import edu.cco.ChamplainAirFreight.Database.Client.DBAddClient;
import edu.cco.ChamplainAirFreight.Database.Client.DBAddClientAddress;
import edu.cco.ChamplainAirFreight.Database.Client.DBDeleteClient;
import edu.cco.ChamplainAirFreight.Database.Client.DBDeleteClientAddress;
import edu.cco.ChamplainAirFreight.Database.Client.DBUpdateClient;
import edu.cco.ChamplainAirFreight.Database.Client.DBUpdateClientAddress;
import edu.cco.ChamplainAirFreight.Database.Client.DBViewAllClient;
import edu.cco.ChamplainAirFreight.Database.Client.DBViewSelectClient;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
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
 * 
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
    
  //make buttons
    Button btnView = new Button("View");
    Button btnAdd = new Button("Add Client");
    Button btnEdit = new Button("Edit Client");
    Button btnDelete = new Button("Delete Client");
    Button btnEnter = new Button("Enter");
    Button btnCancel = new Button("Cancel");
    Button btnExit = new Button("Exit");

    

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
        btnExit.setMinHeight(0);

        //add buttons to button HBox
        buttonBox.getChildren().addAll(btnView, btnAdd, btnEdit, btnDelete, btnEnter,
                btnCancel, btnExit);

        //add title, center, and buttons to clients pane:
        box.setTop(titleBox);
        box.setCenter(getViewSelected()); //call a method to show db of clients  
        box.setBottom(buttonBox);

        //add actionables to change the setCenter based on button responses:
        btnView.setOnAction(e -> {
        	 box.setCenter(getViewLBs());//(getViewLBs()); 
        //	getClientInfolb(), getClientInfotx()
        });
        btnAdd.setOnAction(e -> {
            //box.setCenter(getClientLBs()); 
        	box.setCenter(addPane());
           
         });
        btnEdit.setOnAction(e -> {
        	//box.setCenter(getClientLBs());   	
        	box.setCenter(addPaneUpdate());
        
        });
        btnDelete.setOnAction(e -> {
        	box.setCenter(deletePane()); 
        });
        btnEnter.setOnAction(e -> {
        	
        });
        btnCancel.setOnAction(e -> {

        });
        btnExit.setOnAction(e -> {
            //clear whatever actions doing
            //return to just the viewClient page
            box.setCenter(getViewSelected());
        });

        return box;
    } 
 
    //getClientInfortxZip, getClientInfotx, getClientInfolb, getClientLBs all incorporated into addPane
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
		hboxv.getChildren().addAll(name, Address, City, State,Zip);
		return hboxv;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * This TextArea will display the output or result for the View All shipment information 
	 * it will allow users to view a summary of the shipments. Users will not be able to 
	 * change the shipment information from this shipment view.
	 * @return
	 */
	private ScrollPane getTextAreaOne() {
		DBViewAllClient viewAllClient = new DBViewAllClient();  //view all client information 
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
		 int i =0; 
		 while(i < viewAllClient.getName().size()) {
			 Label gridName = new Label (String.valueOf(viewAllClient.getName().get(i))); 
			 Label gridAdress = new Label (String.valueOf(viewAllClient.getAddress1().get(i)) + " " +
			 String.valueOf(viewAllClient.getAddress2().get(i))); 
			 Label gridCity = new Label(String.valueOf(viewAllClient.getCity().get(i))); 
			 Label gridState = new Label(String.valueOf(viewAllClient.getState().get(i))); 
			 Label gridZip = new Label(String.valueOf(viewAllClient.getZip().get(i))); 
			 
			 gpane.add(gridName,  0,  row);
			 gpane.add(gridAdress, 1, row); 
			 gpane.add(gridCity,  2,  row);
			 gpane.add(gridState,  3,  row);
			 gpane.add(gridZip,  4,  row);
			 
			 row++; 
			 i++; 
		 }
		
		 box.setContent(gpane);
		return box;
	}
	
	/**
	 * getViewSelected - the initial pane for the ViewClients page. Will hold a feature 
	 * to view an individual client with the DBViewSelectClient class 
	 * @return
	 */
	private VBox getViewSelected() {
	DBViewAllClient all = new DBViewAllClient(); // for filling the combo box
		
    VBox centerBox = new VBox();
    centerBox.setAlignment(Pos.TOP_CENTER);
    centerBox.setMinHeight(300);
    centerBox.setStyle("-fx-background-color: white");
    
    // add title and subtitle instructions 
    Text title = new Text("View Selected Client"); 
    Text instructions = new Text("Use the scroll bar to select a client, then click SEARCH. \n"
    		+ "This will allow you to view all client information for selected client."); 
    
    // add a combobox and fill with all client names
    HBox selection = new HBox(); 
    selection.setAlignment(Pos.CENTER);
    ComboBox clientSelect = new ComboBox(FXCollections.observableArrayList(all.getName())); 
    clientSelect.setVisibleRowCount(5); 
    Button clientSearch = new Button("Search"); 
    selection.getChildren().addAll(clientSelect, clientSearch); 
    
    //grid of information: 
    GridPane grid = new GridPane(); 
    grid.setAlignment(Pos.CENTER);
    Label lbName = new Label("Client Name: "); 
    grid.add(lbName, 0, 0);
    Label lbType = new Label ("Client Type: "); 
    grid.add(lbType, 0, 1);
    Label lbPhone = new Label("Client Phone: "); 
    grid.add(lbPhone, 0, 2);
    Label lbAddress = new Label("Address: "); 
    grid.add(lbAddress, 0, 3);
    Label lbCity = new Label("City: "); 
    grid.add(lbCity, 0, 4);
    Label lbState = new Label("State: "); 
    grid.add(lbState, 0, 5);
    Label lbZip = new Label("Zip: "); 
    grid.add(lbZip,  0,  6);
    
    Text txtSelectName = new Text(); 
	grid.add(txtSelectName, 1, 0);
	Text txtSelectType = new Text(); 
	grid.add(txtSelectType, 1, 1);
	Text txtSelectPhone = new Text(); 
	grid.add(txtSelectPhone, 1, 2);
	Text txtSelectAddress = new Text(); 
	grid.add(txtSelectAddress, 1, 3);
	Text txtSelectCity = new Text(); 
	grid.add(txtSelectCity, 1, 4);
	Text txtSelectState = new Text(); 
	grid.add(txtSelectState, 1, 5);
	Text txtSelectZip = new Text();
	grid.add(txtSelectZip, 1,  6); 
	
	 
   clientSearch.setOnAction(e->{
	   try {
		DBViewSelectClient view = new DBViewSelectClient(); //to view a select Client
	   	String entry = clientSelect.getValue().toString();
    	int id = all.getName().indexOf(entry); //get the index of arraylist where = entry 
    	view.viewSelected(all.getID().get(id));

    	txtSelectName.setText(view.getClientName());
    	txtSelectType.setText(view.getClientType());
    	txtSelectPhone.setText(view.getPhone());
    	txtSelectAddress.setText(view.getAddress1()+ " " + view.getAddress2());
    	txtSelectCity.setText(view.getCity());
    	txtSelectState.setText(view.getState());
    	txtSelectZip.setText(view.getZip());
    	
	   } catch(Exception ex) {
		   clientSelect.requestFocus(); 
	   }
	   
    });
    centerBox.getChildren().addAll(title, instructions, selection, grid);
    
    return centerBox; 
	}
	
	/**
	 * addPane - pane for adding a client in the database. 
	 */
	private VBox addPane() {
		VBox box = new VBox(); 
		box.setAlignment(Pos.CENTER); 
		box.setSpacing(10);
		box.setPadding(new Insets(23,30,0,20));
		//add client classes
		DBAddClient add = new DBAddClient();
		//title and instructions 
		Text title = new Text("Add a new Client"); 
		Text instructions = new Text("Enter valid information for a client, and then press Enter"); 
		//labels
		Label lblName = new Label ("Client Name: "); 
		Label lblType = new Label ("Client Type ID: "); 
		Label lblPhone = new Label ("Client Phone Number: "); 
		Label lblAdd1 = new Label("Address 1: "); 
		Label lblAdd2 = new Label("Address 2: "); 
		Label lblCity = new Label("City: "); 
		Label lblState = new Label("State: "); 
		Label lblZip = new Label("Zip Code: "); 
		//style labels
		Arrays.asList(lblName, lblType, lblPhone, lblAdd1, lblAdd2, lblCity, lblState, lblZip).stream().map((b)->{
			b.setStyle(s.LBTextColor); 
			return b; 
		}); 
				
		//entry fields
		TextField txtName = new TextField(); 
		Spinner<Integer> spType = new Spinner<Integer>(1,10,1); //min, max, start
		TextField txtPhone = new TextField(); 
		TextField txtAdd1 = new TextField(); 
		TextField txtAdd2 = new TextField(); 
		TextField txtCity = new TextField(); 
		ComboBox<String> cbState = new ComboBox();
    	cbState.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
				"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
				"NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
				"VA", "WA", "WV", "WI", "WY");
    	TextField txtZip = new TextField(); 
    	
    	//add input values into a gridpane
    	GridPane grid = new GridPane(); 
    	grid.setAlignment(Pos.CENTER);
    	grid.setHgap(10);
    	grid.setVgap(4);
    	grid.add(lblName, 0, 0);
    	grid.add(lblType, 0, 1);
    	grid.add(lblPhone, 0, 2);
    	grid.add(lblAdd1, 0, 3);
    	grid.add(lblAdd2, 0, 4);
    	grid.add(lblCity, 0, 5);
    	grid.add(lblState, 0, 6);
    	grid.add(lblZip, 0, 7);
    	
    	grid.add(txtName, 1, 0);
    	grid.add(spType, 1, 1);
    	grid.add(txtPhone, 1, 2);
    	grid.add(txtAdd1, 1, 3);
    	grid.add(txtAdd2, 1, 4);
    	grid.add(txtCity, 1, 5);
    	grid.add(cbState, 1, 6);
    	grid.add(txtZip, 1, 7);
    	
    	box.getChildren().addAll(title,instructions,grid); 
    	btnEnter.setOnAction(e->{
    		//variables for SQL stored procedure
    		String name = txtName.getText(); 
    		int type = spType.getValue(); 
    		String phone = txtPhone.getText(); 
    		//add client
    		add.insertSQL(name, type, phone);
    		
    		//find new clientID for adding address1
    		DBFinder find = new DBFinder(); 
    		int id = find.findClientID(name, type, phone); 
    		System.out.println(id); 
    		
    		String add1 = txtAdd1.getText(); 
    		String add2 = txtAdd2.getText(); 
    		String city = txtCity.getText(); 
    		String state = cbState.getValue(); 
    		int zip = Integer.parseInt(txtZip.getText());
    		DBAddClientAddress ca = new DBAddClientAddress(add1, add2, city, state, zip, id);
    		
    		//clear entry fields
    		txtName.clear(); 
    		txtPhone.clear(); 
    		txtAdd1.clear(); 
    		txtAdd2.clear();
    		txtCity.clear();
    		cbState.valueProperty().set(null);
    		txtZip.clear(); 
    	});
    	    	
		return box; 
	}
	/**
 * @Author Name: Matt Ridgway
 * @Assignment Name: caf
 * @Date: Nov 28, 2020
 *  
 * 
 * addPaneUpdate - pane for updating a client in the database 
 */
	private VBox addPaneUpdate() {
		VBox box = new VBox(); 
		box.setAlignment(Pos.CENTER); 
		box.setSpacing(10);
		box.setPadding(new Insets(23,30,0,20));
		//update client classes
		DBViewAllClient viewAll = new DBViewAllClient(); //used to get arraylist
		DBViewSelectClient select = new DBViewSelectClient(); 
		DBUpdateClient updateClient = new DBUpdateClient();
	
		
		//call the get client id from viewAll 
		ComboBox<Integer> cbClientID = new ComboBox(FXCollections.observableArrayList(viewAll.getID()));
		Button btSelectClient = new Button("Select Client"); 
		
		
		//title and instructions 
		Text title = new Text("Update Client"); 
		Text instructions = new Text("Enter Valid Client information and press Enter."); 
		//labels
		Label lblClientID =new Label("Client ID: ");
		Label lblName = new Label ("Client Name: "); 
		Label lblType = new Label ("Client Type ID: "); 
		Label lblPhone = new Label ("Client Phone Number: "); 
	
		Label lblAdd1 = new Label("Address 1: "); 
		Label lblAdd2 = new Label("Address 2: "); 
		Label lblCity = new Label("City: "); 
		Label lblState = new Label("State: "); 
		Label lblZip = new Label("Zip Code: "); 
		//style labels
		Arrays.asList(lblClientID,lblName, lblType, lblPhone, lblAdd1, lblAdd2, lblCity, lblState, lblZip).stream().map((b)->{
			b.setStyle(s.LBTextColor); 
			return b; 
		}); 
				
		//entry fields
		
		TextField txtName = new TextField(); 	
		TextField txtType = new TextField(); 
		TextField txtPhone = new TextField(); 
		TextField txtAdd1 = new TextField(); 
		TextField txtAdd2 = new TextField(); 
		TextField txtCity = new TextField(); 
		ComboBox<String> cbState = new ComboBox();
    	cbState.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
				"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
				"NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
				"VA", "WA", "WV", "WI", "WY");
    	TextField txtZip = new TextField(); 
    	    	
    	//add input values into a gridpane
    	GridPane grid = new GridPane(); 
    	grid.setAlignment(Pos.CENTER);
    	grid.setHgap(11);
    	grid.setVgap(5);
    	
    	grid.add(lblClientID, 0,0);    	
    	grid.add(lblName, 0, 1);
    	grid.add(lblType, 0, 2);
    	grid.add(lblPhone, 0, 3);
    	grid.add(lblAdd1, 0, 4);
    	grid.add(lblAdd2, 0, 5);
    	grid.add(lblCity, 0, 6);
    	grid.add(lblState, 0, 7);
    	grid.add(lblZip, 0, 8);
    	
    	grid.add(cbClientID, 1,0); //add combobox and select button
    	grid.add(btSelectClient, 2,0);
    	grid.add(txtName, 1, 1);
    	grid.add(txtType, 1, 2);
    	grid.add(txtPhone, 1, 3);
    	grid.add(txtAdd1, 1, 4);
    	grid.add(txtAdd2, 1, 5);
    	grid.add(txtCity, 1, 6);
    	grid.add(cbState, 1, 7);
    	grid.add(txtZip, 1, 8);
    	    	
    	box.getChildren().addAll(title,instructions,grid); 
    	
    	//set the other textfields to whatever clientID is entered
    	btSelectClient.setOnAction(e->{
    		select.viewSelected(cbClientID.getValue());
    		    		
    		txtName.setText(select.getClientName());
    		txtType.setText(select.getClientType()); 
    		txtPhone.setText(select.getPhone());
    		txtAdd1.setText(select.getAddress1());
    		txtAdd2.setText(select.getAddress2());
    		txtCity.setText(select.getCity());
    		cbState.setValue(select.getState());
    		txtZip.setText(select.getZip()); 
    	});
    	
    	
    	btnEnter.setOnAction(e->{
    		//variables for SQL stored procedure
    		int clientID = cbClientID.getValue().intValue();
    	   
    		String name = txtName.getText(); 
    		int type = Integer.parseInt(txtType.getText());     		
    		String phone = txtPhone.getText(); 
    		
    		String add1 = txtAdd1.getText(); 
    		String add2 = txtAdd2.getText(); 
    		String city = txtCity.getText(); 
    		String state = cbState.getValue(); 
    		int zip = Integer.parseInt(txtZip.getText());
    		//update client Class
    		updateClient.updateC(clientID, name, type, phone);			
    		 
    		//get index of clientID  
    		//problem here
    		select.viewSelected(clientID);
    		int clientAddressID =select.getAddressID();	
    		if(clientAddressID == 0) {
    			DBAddClientAddress addAddress = new DBAddClientAddress(add1, add2, city, state, zip, clientID); 
    		}
    		else {
    		System.out.println("address ID = " + clientAddressID); 
    		    		
    		DBUpdateClientAddress updateClientAddress = new DBUpdateClientAddress();
    		//update client address Class
    		updateClientAddress.updateClientA(clientAddressID, clientID, add1, add2, city, state, zip);
    		}
    		//clear text fields    		
    		cbClientID.valueProperty().set(null);
    		txtName.clear(); 
    		txtPhone.clear(); 
    		txtAdd1.clear(); 
    		txtAdd2.clear();
    		txtCity.clear();
    		cbState.valueProperty().set(null);
    		txtZip.clear(); 
    	});
    	    	
		return box; 
	}

	/**
	 * deletePane - method for deleting a client using the DBDeleteClient class
	 * @return
	 */
	private VBox deletePane() {
		DBViewAllClient view = new DBViewAllClient(); //for arraylist of clients
		VBox box = new VBox(); 
		box.setAlignment(Pos.CENTER); 
		box.setSpacing(10);
		box.setPadding(new Insets(23,30,0,20));
		box.setMinHeight(300);
		box.setStyle("-fx-background-color: white");
		//add client classes
		DBAddClient add = new DBAddClient();
		//title and instructions 
		Text title = new Text("Delete a Client"); 
		Text instructions = new Text("Select a client, then click DELETE"); 
		
		ComboBox cbClients = new ComboBox(FXCollections.observableArrayList(view.getName())); 
		Button btDelete = new Button("DELETE"); 
		GridPane gpane = new GridPane(); 
		gpane.setAlignment(Pos.CENTER);
		gpane.add(cbClients, 0,0);
		gpane.add(btDelete, 1, 0);
		
		box.getChildren().addAll(title,instructions,gpane); 
		btDelete.setOnAction(e->{
			DBDeleteClient delete = new DBDeleteClient(); 
			DBDeleteClientAddress deleteAdd = new DBDeleteClientAddress(); 
			//get client ID based on name
			int index = view.getName().indexOf(cbClients.getValue()); 
			int id = view.getID().get(index); 
			delete.deleteClient(id);
			// deleteAdd.deleteClientAddress(id); 
			// need to delete client address by clientaddressid, not clientid
			
			//clear combobox
			cbClients.valueProperty().set(null);
		});
		
		return box; 
		
	}
  
} //End Subclass ClientsPage
