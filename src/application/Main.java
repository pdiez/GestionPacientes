package application;
	

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.BBDD;
import view.LoginController;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		writeXML();
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
	
	private void writeXML() {
		
		try {
			String cwd = new File("").getAbsolutePath();
			File f = new File(cwd+"/users.xml");
			if (!f.exists()) {
				Files.copy(getClass().getResourceAsStream("/assets/users.xml"), Paths.get(cwd+"/users.xml"), StandardCopyOption.REPLACE_EXISTING);
				Files.copy(getClass().getResourceAsStream("/assets/messages.xml"), Paths.get(cwd+"/messages.xml"), StandardCopyOption.REPLACE_EXISTING);
				Files.copy(getClass().getResourceAsStream("/assets/sensors.xml"), Paths.get(cwd+"/sensors.xml"), StandardCopyOption.REPLACE_EXISTING);
				Files.copy(getClass().getResourceAsStream("/assets/roles.xml"), Paths.get(cwd+"/roles.xml"), StandardCopyOption.REPLACE_EXISTING);
				Files.copy(getClass().getResourceAsStream("/assets/sensorinput.csv"), Paths.get(cwd+"/sensorinput.csv"), StandardCopyOption.REPLACE_EXISTING);
				Files.copy(getClass().getResourceAsStream("/assets/a.bat"), Paths.get(cwd+"/a.bat"), StandardCopyOption.REPLACE_EXISTING);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	


}
