package arya.phonebook.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/arya/phonebook/view/pages/Main.fxml"));
		Scene scene = new Scene(fxmlLoader.<BorderPane>load());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
