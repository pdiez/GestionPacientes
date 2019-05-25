package application;
	

import java.net.URL;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.LoginController;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Control de Pacientes");
        URL url = getClass().getResource("/assets/doctor.png");
        
        primaryStage.getIcons().add(new Image(url.toExternalForm()));
        try {
        	LoginController lc = new LoginController();
        	lc.show(primaryStage);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	


}
