package login;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	
	static boolean answer;
	
	public static boolean display(String title, String msg) {
		
	
	
	Stage window = new Stage();
	
	window.initModality(Modality.APPLICATION_MODAL);
	window.setTitle(title);
	window.setMinWidth(250);
	
	Label label1 = new Label();
	label1.setText(msg);
	label1.setFont(Font.font("Amble CN", FontWeight.BOLD,17));
	
	
	
	Button yesButton = new Button();
	yesButton.setText("Ja");
	
	yesButton.setOnAction(e -> {
		answer = true;
		window.close();		
	});
	
	Button noButton = new Button();
	noButton.setText("Nein");
	
	noButton.setOnAction(e -> {
		answer = false;
		window.close();		
	});
	
	VBox layout = new VBox(10);
	
	layout.getChildren().addAll(label1, yesButton,noButton);
	
	layout.setAlignment(Pos.CENTER);
	
	Scene scene1 = new Scene(layout,400,120);
	window.setScene(scene1);
	window.showAndWait();
	
	return answer;
	
	}
	

}
