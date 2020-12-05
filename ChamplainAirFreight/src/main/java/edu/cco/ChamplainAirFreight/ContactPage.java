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
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    
	// Client address labels - By Pierre
	static Label lbfname;
	static Label lblname;
	static Label lbcompany;
	static Label lbsubject;
	static Label lbmessage;

	//Text field - By Pierre
	static TextField txfname;
	static TextField txlname;
	static TextField txcompany;
	static TextField txsubject;
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
        centerBox.getChildren().addAll( getContactLBs());
  
        //create button HBox:
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setMinHeight(50);
        buttonBox.getChildren().addAll( getContactButtons());
        
        //add title and center to pane:
        box.setTop(titleBox);
        box.setCenter(centerBox);
        box.setBottom(buttonBox);
        
      //  centerBox.getChildren().addAll(getClientLBs());
       
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

        //make buttons
        Button btnCancel = new Button("Cancel");
        Button btnSubmit = new Button("Send");
        Button btnExit = new Button("Exit");
        
        //style buttons
        Arrays.asList(  btnCancel,btnSubmit, btnExit).forEach((b) -> {
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
        hboxcb.getChildren().addAll(btnCancel,btnSubmit, btnExit);   	
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
		 lbcompany = new Label("Company ");
		 lbsubject = new Label("Subject");
		 lbmessage  = new Label("Message");
    	
    	vboxi.getChildren().addAll(lbfname,lblname,lbcompany,lbsubject,lbmessage);
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
    	 txcompany = new TextField();
    	 txsubject = new TextField();
    	 txmessage = new TextField();
    	vboxi.getChildren().addAll(txfname,txlname ,txcompany, txsubject,txmessage, getTextAreaOne());
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
		texReaOne.setEditable(false);
		texReaOne.setWrapText(true);
		texReaOne.setPrefSize(900, 500);
		hboxt.getChildren().addAll(texReaOne);
		return hboxt;
	}
	private HBox getEmail(String recepient) {
		HBox hboxe = new HBox();
	//	String champlainairfreight = "pierresystem1@gmail.com";
		String calEmail = "champlainairfreight@gmail.com";
		String password = "Admin@100";
		
		Properties emailProperty = new Properties();
		emailProperty.put("mail.smtp.auth", "true");
		emailProperty.put("mail.smtp.starttls.enable", "true");
		emailProperty.put("mail.smtp.host", "smtp.gmail.com");
		emailProperty.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(emailProperty, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(calEmail, password);
			}
		});
		
		Message mymessage = calMessage(session, calEmail, recepient);
		//calMessage(session, calEmail, recepient);
		
		return hboxe;
	}
	private static Message calMessage(Session session, String calEmail, String recepient) {
		Message mymessage = new MimeMessage(session);
		try {
			mymessage.setFrom(new InternetAddress(calEmail));
			mymessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			//mymessage.setSubject(txsubject);
		} catch(Exception ex) {
			Logger.getLogger(ContactPage.class.getName()).log(Level.SEVERE, null, ex);
			//e.printStackTrace();
		}
		return null;
	}
//	private static calMessage(Session session, String calEmail) {
//		try {
//		Message mymessage = new MimeMessage(session);
//		mymessage.setFrom(new InternetAddress(calEmail));
//		}catch(Exception ex) {
//			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		return null;
//	}

} //End Subclass ContactPage
