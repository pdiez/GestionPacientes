package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.User;

public class PerfilController implements Initializable {
	
	private User user;
	
	@FXML
	JFXTextField txtUsername;
	
	@FXML
	JFXTextField txtName;
	
	@FXML
	JFXPasswordField txtPassword;
	
	@FXML
	JFXTextField txtPhone;
	
	@FXML
	JFXTextArea txtNotas;
	
	@FXML
	JFXButton btnGuardar;
	
	
	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}




	@Override
	public void initialize(URL location, ResourceBundle resources) {

		txtUsername.setText(user.getUsername());
		txtName.setText(user.getName());
		txtPassword.setText(user.getPassword());
		txtPhone.setText(user.getPhone());
		txtNotas.setText(user.getNotes());
		
	}
	
	
	@FXML
    private void handlebtnGuardarAction(ActionEvent event) throws IOException {
		this.user.setName(txtName.getText());
		this.user.setPassword(txtPassword.getText());
		this.user.setPhone(txtPhone.getText());
		this.user.setNotes(txtNotas.getText());
		this.user.Guardar();
		
	}
	
}
