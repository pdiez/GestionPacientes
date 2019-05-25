package view;

import java.awt.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Sensor;
import util.CSVUtil;
import util.Persistent;

public class SensoresController implements Initializable {

	
	@FXML
	TableView tblSensores;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//CSVUtil.leerCSV();
		
		 
		TableColumn c1 = new TableColumn("ID");
        c1.setCellValueFactory(new PropertyValueFactory<Sensor,String>("id"));
		
        TableColumn c2 = new TableColumn("Usuario");
        c2.setCellValueFactory(new PropertyValueFactory<Sensor,String>("user"));
        
    	TableColumn c3 = new TableColumn("Tipo");
        c3.setCellValueFactory(new PropertyValueFactory<Sensor,String>("sensorType"));
        
    	TableColumn c4 = new TableColumn("Valor Max.");
        c4.setCellValueFactory(new PropertyValueFactory<Sensor,Integer>("alertValue"));
        
        TableColumn c5 = new TableColumn("Valor Actual");
        c5.setCellValueFactory(new PropertyValueFactory<Sensor,Integer>("currentValue"));
       
        tblSensores.getColumns().addAll(c1, c2, c3, c4 ,c5);
        
        ObservableList<Sensor> data = FXCollections.observableArrayList();
        ArrayList<Sensor> sensores = null; //Persistent.getSensors();
        if(!sensores.isEmpty()) {
			data.addAll(sensores);
			for (Sensor s : data) {
				String cv = CSVUtil.leerCSVbySensor(s.getId());
				if (!cv.isEmpty()) s.setCurrentValue(Integer.parseInt(cv));
				
			}
			
        }
        tblSensores.setItems(data);
	}

}
