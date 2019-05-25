package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import application.Main;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.User;
import util.Persistent;

public class LoginController implements Initializable {
	private static final String LOGINFXML = "LoginView.fxml";
	private Parent parent;
    private Scene scene;
    private Stage stage;
    
    @FXML
    private ImageView imgLogin;
    
    @FXML
    private Label lblLogin;
    
	@FXML
	private JFXTextField txtUser;
	@FXML
	private JFXPasswordField txtPassword;
	@FXML
	private JFXButton btnOk;
	
	public LoginController() {
		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(LOGINFXML));
		 fxmlLoader.setController(this);
	     try {
	      	 parent = (Parent) fxmlLoader.load();
	         scene = new Scene(parent);
	          
	         scene.getStylesheets().add(getClass().getResource("/assets/app.css").toExternalForm());
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	        
	        
	     
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtUser.setLabelFloat(true);
		txtPassword.setLabelFloat(true);
		btnOk.setDisable(true);
		
		btnOk.setDisable(false);
		btnOk.setFocusTraversable(true);
		//
		
		imgLogin.setImage(new Image(Main.class.getResourceAsStream("/assets/padlock1.png")));
		lblLogin.setText("Login");
		
		
		RequiredFieldValidator validUser = new RequiredFieldValidator();
		txtUser.getValidators().add(validUser);
		validUser.setMessage("Nombre de usuario obligatorio");
		validUser.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class)
	            .glyph(FontAwesomeIcon.WARNING)
	            .size("1em")
	            .styleClass("error")
	            .build());
		
		txtUser.focusedProperty().addListener((o, oldVal, newVal) -> {
          if (!newVal) {	
        		if(txtUser.validate()) btnOk.setDisable(false);
        		else btnOk.setDisable(true);
          }
		});
		
		txtUser.setOnKeyPressed(new EventHandler<KeyEvent>() {
			 @Override
			 public void handle(KeyEvent keyEvent) {
			     if (keyEvent.getCode() == KeyCode.ENTER)  {
			      	txtPassword.requestFocus();
			     }
			 }
		 });
	     txtPassword.setOnKeyPressed(new EventHandler<KeyEvent>() {
			 @Override
			 public void handle(KeyEvent keyEvent) {
			     if (keyEvent.getCode() == KeyCode.ENTER)  {
			    	 if (!btnOk.isDisable()) { doLogin(); }
			     }
			 }
		 });
		
	}
	
	public void show(Stage stage) {
		this.stage = stage;
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
	}
	
	@FXML
    private void handleOkAction(ActionEvent event) {
		
		doLogin();
	}

	private void doLogin() {
		
		boolean go = Persistent.validateCredentials(txtUser.getText(), txtPassword.getText());
		
		if(go) {
			imgLogin.setImage(new Image(Main.class.getResourceAsStream("/assets/padlock3.png")));
			lblLogin.setText("Ok!");
			MainController mc = new MainController();
			User user = Persistent.getUserByUsername(txtUser.getText());
			mc.show(stage, user);
			
			
		} else {
			imgLogin.setImage(new Image(Main.class.getResourceAsStream("/assets/padlock2.png")));
			lblLogin.setText("Error");
		}
		
		
	}
		
}
