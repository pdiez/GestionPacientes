package view;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Message;
import model.User;
import util.Persistent;

public class MensajesController implements Initializable {
	
	private User user;
	
	@FXML
	private JFXListView<Label> lstChatUsers;
	
	@FXML
	ImageView imgUsuarios;
	
	@FXML
	JFXTextArea txtChat;
	
	@FXML
	JFXTextField txtMessage;
	
	@FXML
	JFXButton btnSend;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imgUsuarios.setImage(new Image(Main.class.getResourceAsStream("/assets/users.png")));
		
		for (User u : Persistent.getUserList()) {
			Label l = new Label(u.getName());
			l.setAccessibleText(u.getUsername());
			lstChatUsers.getItems().add(l);
		}
		
		txtMessage.setOnKeyPressed(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent keyEvent) {
		        if (keyEvent.getCode() == KeyCode.ENTER)  {
		        	addMensaje();
		        }
		    }
		});
	}
	
	@FXML
    private void handlelstClickAction(MouseEvent event) throws IOException {
		
		actualizaChat();
		
	}
	
	private void actualizaChat() {
		txtChat.clear();
		User to = Persistent.getUserByUsername(lstChatUsers.getSelectionModel().getSelectedItem().getAccessibleText());
		for (Message e : Persistent.getMessagesBetween(to, this.user)) {
			if (e.getFrom()==to.getId() && e.getTo()==this.user.getId()) {
				addChat("["+ e.getDate() +"] "+ to.getName() +" dijo: " + e.getText());
			} else {
				addChat("[" + e.getDate() + "] Dijiste: " + e.getText());
			} 
		}
		
	}

	private void addChat(String text) {
		String current = txtChat.getText() + "\n" + text;
		txtChat.setText(current);
		
	}
		
		
	@FXML
    private void handlebtnSendAction(ActionEvent event) throws IOException {
		addMensaje();
	}

	private void addMensaje() {
		Message m = new Message();
		
		m.setFrom(this.user.getId());
		m.setDate(new Date());
		User to = Persistent.getUserByUsername(lstChatUsers.getSelectionModel().getSelectedItem().getAccessibleText());
		m.setTo(to.getId());
		m.setText(txtMessage.getText());
		m.Guardar();
		txtMessage.clear();
		actualizaChat();
	}

}
