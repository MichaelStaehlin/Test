package login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

import client.Client;
import client.Player;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Willkommen extends Application implements Serializable {

	Stage window, window2;
	Scene scene1, scene2;
	Button create, exit;
	TextField IpAd, serverport, textname;
	
	private PrintWriter writer;
    private BufferedReader reader;
    private int id;
    private String name;
    ArrayList<Player> players;

	private GridPane createGridPane() {
		
				

		Text Ip = new Text("Ip-Adresse:");
		Text Port = new Text("Port:");
		Text playername = new Text("Spielername:");
		
		Font style = Font.font("Cambria", 15);

		textname = new TextField();
		textname.setPromptText("geben Sie hier dein Spielername ein");
		
		IpAd = new TextField();
		IpAd.setPromptText("geben Sie die IP-Adresse ein");
		
		serverport = new TextField();
		serverport.setPromptText("geben Sie die Port Nr. ein");
		
		playername.setFont(style);
		Ip.setFont(style);
		Port.setFont(style);
		
		Ip.setFill(Color.FUCHSIA);
		Port.setFill(Color.FUCHSIA);
		playername.setFill(Color.FUCHSIA);
		
		

		final ToggleGroup gender = new ToggleGroup();

		RadioButton male = new RadioButton("King");
		male.setToggleGroup(gender);
		male.setSelected(true);

		RadioButton female = new RadioButton("Queen");
		female.setToggleGroup(gender);

		Image image = new Image(getClass().getResourceAsStream("king.jpg"));
		ImageView imageview = new ImageView(image);
		imageview.setTranslateX(-50);

		male.setUserData("king");
		female.setUserData("queen");

		// https://docs.oracle.com/javafx/2/ui_controls/radio-button.htm

		gender.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (gender.getSelectedToggle() != null) {
					final Image image1 = new Image(getClass()
							.getResourceAsStream(gender.getSelectedToggle().getUserData().toString() + ".jpg"));
					imageview.setImage(image1);
				}
			}
		});

		create = new Button();
		create.setText("Spiel starten...");

		exit = new Button();
		exit.setText("Verlassen");

		GridPane layout1 = new GridPane();
	
				
		layout1.setPadding(new Insets(2));
		layout1.setHgap(10);
		layout1.setVgap(5);

		//ColumnConstraints column1 = new ColumnConstraints(100);
		//ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);

		//column2.setHgrow(Priority.ALWAYS);
		//layout1.getColumnConstraints().addAll(column1, column2);

		GridPane.setHalignment(playername, HPos.RIGHT);
		GridPane.setHalignment(Ip, HPos.RIGHT);
		GridPane.setHalignment(Port, HPos.RIGHT);
		
		GridPane.setHalignment(textname, HPos.LEFT);
		
		GridPane.setHalignment(IpAd, HPos.CENTER);
		GridPane.setHalignment(serverport, HPos.CENTER);

		GridPane.setHalignment(create, HPos.LEFT);
		GridPane.setHalignment(exit, HPos.RIGHT);

		GridPane.setHalignment(male, HPos.LEFT);
		GridPane.setHalignment(female, HPos.RIGHT);

		GridPane.setHalignment(imageview, HPos.CENTER);
		
		
		layout1.add(Ip, 0,0);
		layout1.add(IpAd, 1, 0);
		
		layout1.add(Port,0,1);
		layout1.add(serverport, 1, 1);

		layout1.add(playername, 0, 2);
		layout1.add(textname, 1, 2);

		layout1.add(male, 0, 4);
		layout1.add(female, 1, 4);

		layout1.add(create, 0, 5);
		layout1.add(exit, 1,5);

		layout1.add(imageview, 1, 4);
		
		
		

		return layout1;
	}

	private FlowPane createTopBanner() {

		FlowPane top = new FlowPane();
		
		top.setPrefHeight(40);
		
		Text gametext = new Text("Majesty");
		
		Font serif = Font.font("Game", 40);
		gametext.setFill(Color.GOLD);
		gametext.setFont(serif);
		
		//Setting the Stroke  
	    gametext.setStrokeWidth(1); 
	      
	    // Setting the stroke color
	    gametext.setStroke(Color.FUCHSIA);
	    
	    

		top.getChildren().addAll(gametext);

		return top;
	}

	private void closeProgram() {
		Boolean answer = ConfirmBox.display("Fenster schliessen?", "Wollen Sie das Fenster wirklich schliessen?");
		if (answer)
			window.close();
	}

	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		window.setWidth(400);
        window.setHeight(310);
		
		BorderPane root = new BorderPane();
		root.setId("root");
		root.getStylesheets().add(
                getClass().getResource("Willkommen.css").toExternalForm());
		
	
		
		GridPane layout = createGridPane();
		
		
		
		FlowPane topp = createTopBanner();
		

		root.setTop(topp);
		root.setCenter(layout);
		

		create.setOnAction(e -> {
			//CreatePlayer.display("Splash", "Splash");
			//IpAd.getText();
			Client client = new Client();
			String ipAddress = IpAd.getText();
			int port = Integer.parseInt(serverport.getText());
			String playerName = textname.getText();
			try {
				client.start(ipAddress, port, playerName);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		Stage stage = window2;

		VBox layout2 = new VBox(20);
		Label label2 = new Label("Spieler wurde erstellt");
		layout2.getChildren().add(label2);

		scene2 = new Scene(layout2, 100, 100);

		window.setTitle("Majesty by DMMS");
		window.setScene(scene2);
		window.show();

		exit.setOnAction(e -> closeProgram());

		primaryStage.setTitle("Majesty!");

		Scene scene = new Scene(root, 380, 150, Color.WHITE);
		primaryStage.setScene(scene);
		primaryStage.show();

		window.setOnCloseRequest(e -> {

			e.consume();
			closeProgram();

		});

	}

	public static void main(String[] args) {

		launch(args);

	}

}
