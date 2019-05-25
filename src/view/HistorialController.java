package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import model.Sensor;
import model.User;
import util.CSVUtil;
import util.Persistent;

public class HistorialController implements Initializable {
	
	ObservableList<User> pacientes = FXCollections.observableArrayList(Persistent.getUserByRoleId(3));
	
	@FXML
	JFXComboBox cboPaciente;
	
	@FXML
	JFXTextArea txtNotas;
	
	@FXML
	TableView tblHistorial;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cboPaciente.setConverter(new StringConverter<User>() {
		    @Override
		    public String toString(User object) {
		        return object.getName();
		    }

		    @Override
		    public User fromString(String string) {
		        return null;
		    }
		});
		
		
		
		cboPaciente.setItems(pacientes);
		
		cargaSensores();
		
				
	}
	
	@FXML
    private void handlecboAction(ActionEvent event) throws IOException {
		User u = (User) cboPaciente.getSelectionModel().getSelectedItem();
		txtNotas.setText(u.getNotes());
		
		updateTablaSensoress(u);
		
		
	}

	private void cargaSensores() {
		
	
		TableColumn c1 = new TableColumn("ID");
        c1.setCellValueFactory(new PropertyValueFactory<Sensor,String>("id"));
		
    	TableColumn c2 = new TableColumn("Tipo");
        c2.setCellValueFactory(new PropertyValueFactory<Sensor,String>("sensorType"));
        
    	TableColumn c3 = new TableColumn("Valor Max.");
        c3.setCellValueFactory(new PropertyValueFactory<Sensor,Integer>("alertValue"));
        
        TableColumn c4 = new TableColumn("Valor Actual");
        c4.setCellValueFactory(new PropertyValueFactory<Sensor,Integer>("currentValue"));
       
        tblHistorial.getColumns().addAll(c1, c2, c3, c4);
		
	}
	
	private void updateTablaSensoress(User u) {

		ObservableList<Sensor> data = FXCollections.observableArrayList();
		data.addAll(new Sensor());//Persistent.getSensors().getSensorbyUser(u.getUsername()));
		for (Sensor s : data) {
			String cv = CSVUtil.leerCSVbySensor(s.getId());
			if (!cv.isEmpty()) s.setCurrentValue(Integer.parseInt(cv));
			
		}
		tblHistorial.setItems(data);
		
	}

}
