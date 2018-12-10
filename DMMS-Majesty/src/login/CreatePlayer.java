package login;



import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreatePlayer {

	public static void display(String title, String msg) {
		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label1 = new Label();
		label1.setText(msg);
		
		
		Button closeButton = new Button();
		closeButton.setText("OK");
		closeButton.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(closeButton,label1);
		
		Scene scene1 = new Scene(layout,100,100);
		window.setScene(scene1);
		window.showAndWait();
		
			
	}

}
