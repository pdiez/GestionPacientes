package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.Main;
import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import util.Animate;

public class MainController implements Initializable {
	private static final String MAINFXML = "MainView.fxml";
	private static final String GESTIONFXML = "AdminView.fxml";
	private static final String HISTORIALFXML = "HistorialView.fxml";
	private static final String MENSAJESFXML = "MensajesView.fxml";
	private static final String SENSORESFXML = "SensoresView.fxml";
	private static final String ALERTASFXML = "AlertasView.fxml";
	private static final String PERFILFXML = "PerfilView.fxml";
	
	private Parent parent;
    private Scene scene;
    private Stage stage;
    private User user;
	
    @FXML
    private Label lblWelcome;
    
    @FXML
    private JFXButton btnHistorial;
    
    @FXML
    private JFXButton btnMensajes;
    
    @FXML
    private JFXButton btnAlertas;
    
    @FXML
    private JFXButton btnPerfil;
        
    @FXML
    private JFXButton btnSensores;
    
    @FXML
    private JFXButton btnAdmin;
    
    @FXML
    private AnchorPane contentPane;
    
    @FXML
    private SplitPane mainPane;
    
    
    @FXML
    private VBox vboxMenu;
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		final GlyphIcon usersIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
	            .glyph(FontAwesomeIcon.STICKY_NOTE)
	            .size("2em")
	            .build();
		usersIcon.setStyle("-fx-padding: 10");
		usersIcon.setStyle("-fx-fill: azure");
		btnHistorial.setGraphic(usersIcon);
		btnHistorial.getStyleClass().add("menubutton");
		
		final GlyphIcon msgIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
	            .glyph(FontAwesomeIcon.WECHAT)
	            .size("2em")
	            .build();
		msgIcon.setStyle("-fx-padding: 10");
		msgIcon.setStyle("-fx-fill: azure");
		btnMensajes.setGraphic(msgIcon);
		btnMensajes.getStyleClass().add("menubutton");
		
		final GlyphIcon alertIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
	            .glyph(FontAwesomeIcon.DASHBOARD)
	            .size("2em")
	            .build();
		alertIcon.setStyle("-fx-padding: 10");
		alertIcon.setStyle("-fx-fill: azure");
		btnAlertas.setGraphic(alertIcon);
		btnAlertas.getStyleClass().add("menubutton");
		
		final GlyphIcon sensoresIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
	            .glyph(FontAwesomeIcon.EYE)
	            .size("2em")
	            .build();
		sensoresIcon.setStyle("-fx-padding: 10");
		sensoresIcon.setStyle("-fx-fill: azure");
		btnSensores.setGraphic(sensoresIcon);
		btnSensores.getStyleClass().add("menubutton");
		
		final GlyphIcon adminIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
	            .glyph(FontAwesomeIcon.USERS)
	            .size("2em")
	            .build();
		adminIcon.setStyle("-fx-padding: 10");
		adminIcon.setStyle("-fx-fill: azure");
		btnAdmin.setGraphic(adminIcon);
		btnAdmin.getStyleClass().add("menubutton");
		
		final GlyphIcon perfilIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
	            .glyph(FontAwesomeIcon.GEAR)
	            .size("2em")
	            .build();
		perfilIcon.setStyle("-fx-padding: 10");
		perfilIcon.setStyle("-fx-fill: azure");
		btnPerfil.setGraphic(perfilIcon);
		btnPerfil.getStyleClass().add("menubutton");
	}
	
	private void menuRole() {
		btnHistorial.setDisable(true);
	    btnMensajes.setDisable(true);
	    btnAlertas.setDisable(true);
	    btnSensores.setDisable(true);
	    btnAdmin.setDisable(true);
	    btnPerfil.setDisable(true);
	    
	    if (user.getRole()>=1) {
	    	btnMensajes.setDisable(false);
	    	btnPerfil.setDisable(false);
	    }
	    if (user.getRole()>=2) {
	    	btnHistorial.setDisable(false);
	    	btnAlertas.setDisable(false);
	    	btnSensores.setDisable(false);
	    }
	    if (user.getRole()==3) btnAdmin.setDisable(false);
	    
		
	}

	public MainController () {
		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MAINFXML));
		 fxmlLoader.setController(this);
	        try {
	        	 parent = (Parent) fxmlLoader.load();
	             scene = new Scene(parent);
	             parent.requestFocus();
	             scene.getStylesheets().add(Main.class.getResource("/assets/app.css").toExternalForm());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public void show(Stage stage, User u) {
		this.stage = stage;
		this.user = u;
	
        stage.setTitle("Control de Pacientes");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        lblWelcome.setText("Bienvenido " + user.getName());
        menuRole();
      
	}
	
	@FXML
    private void handlebtnHistorialAction(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(HISTORIALFXML));
		contentPane.getChildren().remove(0);
		contentPane.getChildren().add(root);
		Animate.animateAnchorPane(contentPane);
		bordeActivo(btnHistorial);
		
	}
	

	@FXML
    private void handlebtnMensajesAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		
		MensajesController controller = new MensajesController();
	    controller.setUser(user);
	    loader.setController(controller);
		loader.setLocation(getClass().getResource(MENSAJESFXML));
	    Parent root = loader.load();
		contentPane.getChildren().remove(0);
		contentPane.getChildren().add(root);
		
		
		Animate.animateAnchorPane(contentPane);
		bordeActivo(btnMensajes);
		
	}
	
	@FXML
    private void handlebtnAlertasAction(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(ALERTASFXML));
		contentPane.getChildren().remove(0);
		contentPane.getChildren().add(root);
		Animate.animateAnchorPane(contentPane);
		bordeActivo(btnAlertas);
	}
		
	@FXML
    private void handlebtnSensoresAction(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(SENSORESFXML));
		contentPane.getChildren().remove(0);
		contentPane.getChildren().add(root);
		Animate.animateAnchorPane(contentPane);
		bordeActivo(btnSensores);
	}

	@FXML
    private void handlebtnAdminAction(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(GESTIONFXML));
		contentPane.getChildren().remove(0);
		contentPane.getChildren().add(root);
		Animate.animateAnchorPane(contentPane);
		bordeActivo(btnAdmin);
	}
	
	@FXML
    private void handlebtnPerfilAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		
		PerfilController controller = new PerfilController();
	    controller.setUser(user);
	    loader.setController(controller);
		loader.setLocation(getClass().getResource(PERFILFXML));
	    Parent root = loader.load();
		contentPane.getChildren().remove(0);
		contentPane.getChildren().add(root);
		
		
		Animate.animateAnchorPane(contentPane);
		bordeActivo(btnPerfil);
		
	}

	private void bordeActivo(JFXButton btn) {
		
		for (Node b : vboxMenu.getChildren()) {
			if (b==btn) {
				b.setStyle(null);
				b.getStyleClass().clear();
				b.getStyleClass().addAll("button", "jfx-button", "menubutton-selected");
			}else if (b.getStyleClass().contains("menubutton-selected")) {
				b.setStyle(null);
				b.getStyleClass().clear();
				b.getStyleClass().addAll("button", "jfx-button", "menubutton");
			}
		}
		
		
	}
	

}
