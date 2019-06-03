package view;

import java.awt.List;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.IntegerValidator;
import com.sun.javafx.font.Glyph;

import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.Role;
import model.Sensor;
import model.SensorType;
import model.User;
import util.CSVUtil;
import util.Persistent;

public class SensoresController implements Initializable {
	ObservableList<SensorType> tipos = FXCollections.observableArrayList(Persistent.getSensorTypeList());
	ObservableList<User> users = FXCollections.observableArrayList(Persistent.getUserList());
	
	@FXML
	JFXComboBox cboTipoSensor;
	
	@FXML
	JFXComboBox cboUsuarioSensor;
	
	@FXML
	JFXButton btnAddSensor;
	
	@FXML
	JFXTextField txtSensorId;
	
	@FXML
	JFXTextField txtAlerta;
	
	@FXML
	TableView tblSensores;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cboTipoSensor.setConverter(new StringConverter<SensorType>() {
		    @Override
		    public String toString(SensorType object) {
		        return object.getType();
		    }

		    @Override
		    public SensorType fromString(String string) {
		        return null;
		    }
		});
		
		
		cboTipoSensor.setItems(tipos);
		
		cboUsuarioSensor.setConverter(new StringConverter<User>() {
		    @Override
		    public String toString(User object) {
		        return object.getName();
		    }

		    @Override
		    public User fromString(String string) {
		        return null;
		    }
		});
		
		
		cboUsuarioSensor.setItems(users);
		IntegerValidator iv = new IntegerValidator();
		iv.setMessage("Numerico");
		txtSensorId.getValidators().add(iv);
		txtSensorId.setOnKeyReleased(e ->
	    {
	        if (!txtSensorId.getText().equals(""))
	        	txtSensorId.validate();
	    });
		txtAlerta.getValidators().add(iv);
		txtAlerta.setOnKeyReleased(e ->
	    {
	        if (!txtAlerta.getText().equals(""))
	        	txtAlerta.validate();
	    });
		
		cargaTablaSensores();
	}
	
	private void cargaTablaSensores() {

		 
		TableColumn c1 = new TableColumn("ID");
        c1.setCellValueFactory(new PropertyValueFactory<Sensor,String>("id"));
		c1.prefWidthProperty().set(35.0);
		c1.getStyleClass().add("centrado");
		
        TableColumn c2 = new TableColumn("Usuario");
        c2.setCellValueFactory(new PropertyValueFactory<Sensor,String>("userName"));
        c2.prefWidthProperty().set(200.0);
        
    	TableColumn c3 = new TableColumn("Tipo");
        c3.setCellValueFactory(new PropertyValueFactory<Sensor,String>("sensorType"));
        c3.prefWidthProperty().set(150.0);
        
    	TableColumn c4 = new TableColumn("Valor Max.");
        c4.setCellValueFactory(new PropertyValueFactory<Sensor,Integer>("alertValue"));
        c4.prefWidthProperty().set(75.0);
        c4.getStyleClass().add("centrado");
        
        TableColumn c5 = new TableColumn("Valor Actual");
        c5.setCellValueFactory(new PropertyValueFactory<Sensor,Integer>("currentValue"));
        c5.prefWidthProperty().set(75.0);
        c5.getStyleClass().add("centrado");
        
        TableColumn c6 = new TableColumn("Estado");
        c6.setCellValueFactory(new PropertyValueFactory<>("ACT"));
        c6.prefWidthProperty().set(75.0);
        c6.getStyleClass().add("centrado");
        
        Callback<TableColumn<Sensor, String>, TableCell<Sensor, String>> cellFactory
        = //
        new Callback<TableColumn<Sensor, String>, TableCell<Sensor, String>>() {
		    @Override
		    public TableCell call(final TableColumn<Sensor, String> param) {
		        final TableCell<Sensor, String> cell = new TableCell<Sensor, String>() {
		        	
		        	final Button btn = new Button();
		
		            @Override
		            public void updateItem(String item, boolean empty) {
		                super.updateItem(item, empty);
		                if (empty) {
		                    setGraphic(null);
		                    setText(null);
		                } else {
		                	Sensor sn = getTableView().getItems().get(getIndex());
		                    btn.setOnAction(event -> {
		                        sn.cambiarEstado();
		                        updateTablaSensores();
		                    });
		                    FontAwesomeIcon g;
		                    String st = "";
		                    if (sn.getActive()==1) {
		                    	g = FontAwesomeIcon.CHECK;
		                    	st = "-fx-fill: green";
					    		
		                    } else {
		                    	g = FontAwesomeIcon.REMOVE;
		                    	st = "-fx-fill: red";
		                    }
		                    final GlyphIcon xIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
				    	            .glyph(g)
				    	            .size("1em")
				    	            .build();
		                    xIcon.setStyle(st);
				    		btn.setGraphic(xIcon);
		                    setGraphic(btn);
		                    setText(null);
		                }
		            }
		         };
		         return cell;
		    }
        };
        c6.setCellFactory(cellFactory);
		
        
        TableColumn c7 = new TableColumn("Borrar");
        c7.setCellValueFactory(new PropertyValueFactory<>("DEL"));
        c7.prefWidthProperty().set(75.0);
        c7.getStyleClass().add("centrado");
        
        Callback<TableColumn<Sensor, String>, TableCell<Sensor, String>> cellFactoryDel
        = //
        new Callback<TableColumn<Sensor, String>, TableCell<Sensor, String>>() {
		    @Override
		    public TableCell call(final TableColumn<Sensor, String> param) {
		        final TableCell<Sensor, String> cell = new TableCell<Sensor, String>() {
		        	
		        	final Button btn = new Button();
		
		            @Override
		            public void updateItem(String item, boolean empty) {
		                super.updateItem(item, empty);
		                if (empty) {
		                    setGraphic(null);
		                    setText(null);
		                } else {
		                	Sensor sn = getTableView().getItems().get(getIndex());
		                    btn.setOnAction(event -> {
		                        sn.Borrar();
		                        updateTablaSensores();
		                    });
		                    final GlyphIcon xIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
				    	            .glyph(FontAwesomeIcon.CLOSE)
				    	            .size("1em")
				    	            .build();
		                    xIcon.setStyle("-fx-fill: red");
				    		btn.setGraphic(xIcon);
		                    setGraphic(btn);
		                    setText(null);
		                }
		            }
		         };
		         return cell;
		    }
        };
        c7.setCellFactory(cellFactoryDel);
       
        tblSensores.getColumns().addAll(c1, c2, c3, c4 ,c5, c6, c7);
        
       updateTablaSensores();
	}
	
	private void updateTablaSensores() {

		ObservableList<Sensor> data = FXCollections.observableArrayList();
        ArrayList<Sensor> sensores = Persistent.getSensorList();
        if(!sensores.isEmpty()) {
			data.addAll(sensores);
			for (Sensor s : data) {
				s.actualizarDatos();
				
			}
			
        }
        tblSensores.setItems(data);
		
	}
	
	private void resetFormUsuarios() {
		txtSensorId.setText("");
		txtAlerta.setText("");
		cboUsuarioSensor.getSelectionModel().clearSelection();
		cboTipoSensor.getSelectionModel().clearSelection();
	}
	
	
	@FXML
    private void handlebtnAddSensorAction(ActionEvent event) throws IOException {
		if (txtSensorId.validate() && txtAlerta.validate()) {
			Sensor s = new Sensor();
			s.setId(Integer.parseInt(txtSensorId.getText()));
			User u = (User) cboUsuarioSensor.getSelectionModel().getSelectedItem();
			s.setUserId(u.getId());
			SensorType st = (SensorType) cboTipoSensor.getSelectionModel().getSelectedItem();
			s.setSensorTypeId(st.getId());
			s.setSensorType(st.getType());
			s.setActive(0);
			s.setAlertValue(Integer.parseInt(txtAlerta.getText()));
	
			
			s.Guardar();
			
			updateTablaSensores();
			resetFormUsuarios();
		}
		
	}

}
