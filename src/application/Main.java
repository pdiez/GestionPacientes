package application;
	

import java.net.URL;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import util.ArduinoReader;
import view.LoginController;


public class Main extends Application {
	private ArduinoReader ar;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Control de Pacientes");
        URL url = getClass().getResource("/assets/doctor.png");
        
        primaryStage.getIcons().add(new Image(url.toExternalForm()));
        try {
        	ar = new ArduinoReader();
        	ar.start();
        	LoginController lc = new LoginController();
        	lc.show(primaryStage);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
            public void handle(WindowEvent event) {
            	ar.interrupt();
                Platform.exit();
                System.exit(0);
            }
        });
	}

	public static void main(String[] args) {
		launch(args);
	}

}
