package view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXListView;

import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable; 
import javafx.scene.control.Label;
import javafx.util.Duration;
import model.Sensor;
import model.User;
import util.CSVUtil;
import util.Persistent;

public class AlertasController implements Initializable {
	
	@FXML
	private JFXListView<Label> lstAlertas;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		lstAlertas.setExpanded(true);
		pintaAlertas();
		
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev -> {
			pintaAlertas();
	    }));
	    timeline.setCycleCount(Animation.INDEFINITE);
	    timeline.play();
	   
		
	}
	
	private void pintaAlertas() {
		lstAlertas.getItems().clear();
		List<Sensor> sensores = Persistent.getSensors().getSensorList();
		for (Sensor s : sensores) {
			String cv = CSVUtil.leerCSVbySensor(s.getId());
			if (!cv.isEmpty()) s.setCurrentValue(Integer.parseInt(cv));
			if (s.getCurrentValue() >= s.getAlertValue()) {
				addAlerta(s);
			}
		}
	}
	
	private void addAlerta(Sensor s) {
		
		final GlyphIcon aIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
	            .glyph(FontAwesomeIcon.WARNING)
	            .size("1em")
	            .build();
		if (s.getUser()!=null) {
			User u = Persistent.getUserById(s.getUser());
			
			String t = "ATENCIÓN!! " + u.getName() + " Sensor: " + s.getId() + ". Valor " + s.getCurrentValue();
			Label l = new Label(t, aIcon);
			aIcon.setStyle("-fx-padding: 10");
			aIcon.setStyle("-fx-background-color: azure");
			lstAlertas.getItems().add(l);
		}
		
	}

}
