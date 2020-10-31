package edu.cco.ChamplainAirFreight;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
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
 * @Subclass HelpPage Description: will hold GUI data for Help/FAQ page.
 */
//Begin Subclass HelpPage
public class HelpPage {
    // classes 

    Styles s = new Styles();

    //passed border pane from CAF. 
    BorderPane bPane = new BorderPane();

    // constructor pulls the border pane from CAF 
    HelpPage(BorderPane bp) {
        bPane = bp;
    }

    public VBox getPane() {
        VBox vclients = new VBox();
        vclients.getChildren().add(help());
        return vclients;
    }

    private BorderPane help() {
        BorderPane box = new BorderPane();
        //create a title pane for the top
        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);
        Text title = new Text("Help/FAQ");
        Text instruct = new Text("Need Help? Check out these Frequently Asked Questions:");
        //style text
        title.setFill(Color.DARKBLUE);
        title.setStrokeWidth(2);
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));

        instruct.setFill(Color.BLUE);
        instruct.setStrokeWidth(2);
        instruct.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
        //add titles to titlebox. 
        titleBox.getChildren().addAll(title, instruct);

        //add title and center to pane:
        box.setTop(titleBox);
        box.setCenter(faq());

        return box;
    }

    private ScrollPane faq() {
        ScrollPane chart = new ScrollPane();
        chart.setMinHeight(400);
        chart.setMaxHeight(400);

        //add text for frequently asked questions (could be pulled from a text file)
        return chart;
    }
} //End Subclass HelpPage
