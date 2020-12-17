package edu.cco.ChamplainAirFreight;

import java.util.Arrays;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.thoughtworks.qdox.model.JavaModel;
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
       
    //used for validation
    ValidateFields valid = new ValidateFields();
    
	//TextArea for the view - By Pierre
	static TextArea texReaOne = new TextArea();
	
    //make buttons
    static Button btnCancel = new Button("Cancel");
    static Button btnSend = new Button("Send");
    static Button btnExit = new Button("Exit");
    
	// Client address labels - By Pierre
	static Label lbfname;
	static Label lblname;
	static Label lbsubject;

	//Text field - By Pierre
	static TextField txfname;
	static TextField txlname;
	static TextField txsubject;
	
	static String fName;
	static String lName;
	static String subjk;
	static String passText;
	static String passText2;

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
    
//    private HomePage HomePage(BorderPane bPane2) {
//		// TODO Auto-generated method stu	
//		return null;
//	}

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
      // centerBox.getChildren().addAll(getEmail());
  
        //create button HBox:
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setMinHeight(50);
        buttonBox.getChildren().addAll(getContactButtons());
        
        //add title and center to pane:
        box.setTop(titleBox);
        box.setCenter(centerBox);
        box.setBottom(buttonBox);
        
        centerBox.getChildren().addAll(getContactLBs());

        /**
         * This send button will activate when user click send. Then the user's email will be send to
         * a designated email address
         */
        btnSend.setOnAction(e -> {

        	System.out.println("Preparing to send your email");
    		
    		//Authentication to be able to access the email account top send the email 
    		final String calEmail = "champlainairfreight@gmail.com";
    		final String password = "Admin@100";
    		
    		//Properties of the API conponents
    		Properties emailProperty = new Properties();
    		emailProperty.put("mail.smtp.auth", "true");
    		emailProperty.put("mail.smtp.starttls.enable", "true");
    		emailProperty.put("mail.smtp.host", "smtp.gmail.com");
    		emailProperty.put("mail.smtp.port", "587");
    		       
    		//Session to allow the email to be sent
    		Session session = Session.getInstance(emailProperty, new Authenticator() {
    		@Override
    			protected PasswordAuthentication getPasswordAuthentication() {
    				return new PasswordAuthentication(calEmail, password);
    			}
    		});
	
    		//Validating email variables
    		try {
	        	//Veriables to pass into the the body message
	       		fName = txfname.getText().toString();
	       		lName = txfname.getText().toString();
	       		subjk = String.valueOf(txsubject.getText());
	       		
	       		//Message format
	       		passText = texReaOne.getText();
	       		texReaOne.setText("This is a request for Support \n\n"+ "Requester First Name: " + txfname.getText() + 
	       				"\nRequester Last Name: " + txlname.getText() + "\nMessage Details \n\n" + passText );    
	       		
       			//Passing the textfield value to the TextArea	
        		passText2 = texReaOne.getText();	     

        		//Instantiated a message
        		Message mymessage = new MimeMessage(session);
    			mymessage.setFrom(new InternetAddress(calEmail));
    			mymessage.addRecipient(Message.RecipientType.TO, new InternetAddress(calEmail));
    			mymessage.setSubject(subjk);

    			//Instantiated a Multipart
    			Multipart parts = new MimeMultipart();	
    			
    			//Instantiated a MimeBodyPart
    			MimeBodyPart bodyText = new MimeBodyPart();	
    			
    			bodyText.setText(fName);
    			bodyText.setText(lName);
    			bodyText.setText(passText);
    			bodyText.setText(passText2);
      			
    			parts.addBodyPart(bodyText);
    			mymessage.setContent(parts);

    			//If statement to validate veriables
           		if(txfname.getText().isEmpty()) {
       				valid.noSenderFirstName();
           		}else if(txlname.getText().isEmpty()) {
     				valid.noSenderLastName();
           		}else if(subjk.isEmpty()){
           			valid.noSubject();
           		}else if(passText.isEmpty()) {
    				valid.noMessage();
    			}else if(passText2.isEmpty()) {
    				valid.noMessage();
    			}else if(texReaOne.getText().isEmpty()) {
   					valid.noMessage();
           		}else {
        			//Transport to allow the email to be sent
        			Transport.send(mymessage);
            		System.out.println("Your email was sent successfully");
            		
            		//Display a confirmation page
       	            box.setCenter(getConfirmation());
       	            centerBox.getChildren().addAll(contacts());
       			}
           		
    		} catch(Exception ex) {
    			ex.printStackTrace();
    			valid.noSenderFirstName();
    			valid.noSenderLastName();
    			valid.noSubject();
    			valid.noMessage();
    		}
    		// set focus
    		 txfname.requestFocus();
    		 // clearing the text fields -- took this off so that users wont lose info -- kelly
     		 //txfname.clear();
      		 //txlname.clear();
      		 //txsubject.clear();
      		 //texReaOne.clear(); 
        });
       	    
        return box;
    }

	 /**
     * The function below is to display the bottom section button that allows a user to cancel
     * submit or exit the contact page
     * @return
     */
    private HBox getContactButtons() {
    	HBox hboxcb = new HBox();

        
        //style buttons
        Arrays.asList(  btnCancel,btnSend, btnExit).forEach((b) -> {
                    b.setStyle(s.entryButtons);
                    b.setMinHeight(30);
                    b.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
                });
        //Exit button style
        btnExit.setStyle(s.redEntryButtons);
        btnExit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
        btnExit.setMinHeight(30);
    	hboxcb.setSpacing(20);
    	hboxcb.setAlignment(Pos.CENTER);
    	
        //add buttons to button HBox
        hboxcb.getChildren().addAll(btnCancel,btnSend, btnExit);   
     

    	return hboxcb;
    }
    /** By - Pierre
     * This is a function used to display the header section and the TextArea section for the result.
     * @return
     */
    private HBox getContactLBs() {
    	HBox hboxlb = new HBox();
    	hboxlb.setMinHeight(300);
    	hboxlb.setStyle("-fx-background-color: white");
    	hboxlb.setAlignment(Pos.CENTER_LEFT);
    	hboxlb.getChildren().addAll(getContactInfolb(),getContactInfotx());
    	
    	return hboxlb;
    }   
    /**By - Pierre
     * This function will display the pilot information for the pilot page
     * @return
     */
    private VBox getContactInfolb() {
    	VBox vboxi = new VBox();
    	vboxi.setSpacing(10);
    	vboxi.setAlignment(Pos.TOP_RIGHT); 
        lbfname = new Label("First Name");
		 lblname = new Label("Last Name");
		 lbsubject = new Label("Subject");
    	lbfname.setStyle(s.contTextCol);
    	lblname.setStyle(s.contTextCol);
    	lbsubject.setStyle(s.contTextCol);
    	vboxi.getChildren().addAll(lbfname,lblname,lbsubject);
    	return vboxi;
    }
  /**By - Pierre
   * This function will display the pilot information for the pilot page
   * @return
   */
    private VBox getContactInfotx() {
    	VBox vboxi = new VBox();
    	 txfname = new TextField();
    	 txlname = new TextField();
    	 txsubject = new TextField();
    	 txfname.setPrefColumnCount(4);
    	 txlname.setPrefColumnCount(4);
    	 txsubject.setPrefColumnCount(4);
    	vboxi.getChildren().addAll(txfname,txlname, txsubject, getTextAreaOne());
    	return vboxi;
    } 
	/**By - Pierre
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
		texReaOne.setEditable(true);
		texReaOne.setWrapText(true);
		texReaOne.setPrefSize(900, 500);
		hboxt.getChildren().addAll(texReaOne);
		return hboxt;
	}

    private VBox getConfirmation() {
    	VBox vboxlb = new VBox();
    	vboxlb.setAlignment(Pos.CENTER_LEFT);
    	vboxlb.setSpacing(20);
    	vboxlb.setPadding(new Insets(0,0,0,30));
    	Label contTex = new Label("This is a confirmation that we have received your email request.");
    	Label cInfo1 = new Label("We are currently reviewing your request and we will reach out to you as soon as possible.");
    	Label cInfo2 = new Label("If you are having the following issues please close and re-launch the program");
    	Label cInfo3 = new Label("1 - Program freezes after your computer lost internet connection");
    	Label cInfo4 = new Label("2 - The program is freezing with a connection error");
    	Label cInfo5 = new Label("3 - Need to enter a record but are unable open or access the page");
    	Label cInfo6 = new Label("4 - The you are receiving an error message when trying to enter, search or save data");
    	contTex.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
    	cInfo1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
    	cInfo2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
    	cInfo3.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
    	cInfo4.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
    	cInfo5.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
    	cInfo6.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
    	vboxlb.setPrefSize(900, 500);
    	vboxlb.getChildren().addAll(contTex, cInfo2, cInfo3, cInfo4, cInfo5, cInfo6);
    	return vboxlb;
    }

} //End Subclass ContactPage


