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
        
        centerBox.getChildren().addAll(getContactLBs() );

        /**
         * This send button will activate when user click send. Then the user's email will be send to
         * a designated email address
         */
        btnSend.setOnAction(e -> {
        	
    		System.out.println("Preparing to send your email");
    		final String calEmail = "champlainairfreight@gmail.com";
    		final String password = "Admin@100";
    		
    		Properties emailProperty = new Properties();
    		emailProperty.put("mail.smtp.auth", "true");
    		emailProperty.put("mail.smtp.starttls.enable", "true");
    		emailProperty.put("mail.smtp.host", "smtp.gmail.com");
    		emailProperty.put("mail.smtp.port", "587");
    		
    		Session session = Session.getInstance(emailProperty, new Authenticator() {
    		@Override
    			protected PasswordAuthentication getPasswordAuthentication() {
    				return new PasswordAuthentication(calEmail, password);
    			}
    		});
    		
    		Message mymessage = new MimeMessage(session);
    		String fName = String.valueOf(txfname.getText());
    		String lName = String.valueOf(txlname.getText());
    		String subjk = String.valueOf(txsubject.getText());
    		

    		try {
    			mymessage.setFrom(new InternetAddress(calEmail));
    			mymessage.addRecipient(Message.RecipientType.TO, new InternetAddress(calEmail));
    			mymessage.setSubject(subjk);
    			
    			Multipart parts = new MimeMultipart();	
    			//Body part
    			MimeBodyPart bodyText = new MimeBodyPart();
    			
    			bodyText.setText("This is a NEW TEST to see if the email is delevered");
    			parts.addBodyPart(bodyText);
    			mymessage.setContent(parts);
    			
    			Transport.send(mymessage);
    		} catch(Exception ex) {
    			ex.printStackTrace();
    		}

    		System.out.println("Your email was sent successfully");
    		
			 txfname.clear();
			 txlname.clear();
			 txsubject.clear();
			 texReaOne.clear();  

        });
       
        return box;
    }

	private ScrollPane viewContact() {
        ScrollPane chart = new ScrollPane();
        chart.setMinHeight(400);
        chart.setMaxHeight(400);
        //add text for Contact information (could be pulled from a text file)
        return chart;
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
        lbfname = new Label("First Name");
		 lblname = new Label("Last Name");
		 lbsubject = new Label("Subject");
    	
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
	private HBox getMessage() {
		HBox hboxt = new HBox();
	//	texReaOne = new TextArea();
		hboxt.setAlignment(Pos.CENTER);
		texReaOne.setStyle("-fx-border-color: black");
		texReaOne.setFont(new Font("Time New Roman", 10));
		texReaOne.setEditable(true);
		texReaOne.setWrapText(true);
		texReaOne.setPrefSize(900, 500);
		hboxt.getChildren().addAll(texReaOne);
		return hboxt;
	}

} //End Subclass ContactPage
