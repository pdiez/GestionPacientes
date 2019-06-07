package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import model.Sensor;
import model.User;
import util.CSVUtil;
import util.Persistent;

public class HistorialController implements Initializable {
	
	ObservableList<User> pacientes = FXCollections.observableArrayList(Persistent.getUsersByRoleId(1));
	@FXML
	LineChart chrTemps; 
	
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
		data.addAll(Persistent.getSensorbyUser(u));
		for (Sensor s : data) {
			s.actualizarDatos();
			
		}
		tblHistorial.setItems(data);
	}
	
	@FXML
    private void handletblAction(MouseEvent event) throws IOException {
		if(tblHistorial.getItems().size()>0) {
			
			Sensor s = (Sensor) tblHistorial.getSelectionModel().getSelectedItem();
			XYChart.Series series = new XYChart.Series(); 
			
			int n = 0;
			for (Integer i : s.getLastTemps(50)) {
				series.getData().add(new XYChart.Data("nº"+n, i));
				n++;
			}
			chrTemps.getData().clear();
			chrTemps.getData().add(series);
			
		}

	}

}
