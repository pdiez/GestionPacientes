package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import application.Main;
import model.Message;
import model.Messages;
import model.Role;
import model.Roles;
import model.Sensor;
import model.Sensors;
import model.User;
import model.Users;

public class Persistent {
	
	private static final String USERDB = "users.xml";
	private static final String ROLEDB = "roles.xml";
	private static final String SENSORDB = "sensors.xml";
	private static final String CHATDB = "messages.xml";
	
	
	//
	
	public static Users getUsers () {
		
		Users u = new Users();
		
		try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            u = (Users) jaxbUnmarshaller.unmarshal(new File(USERDB));

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            

        } catch (JAXBException e) {
            e.printStackTrace();
        }
		
		return u;
		
	}
	
	public static Roles getRoles () {
		Roles r = new Roles();
		
		try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Roles.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            r = (Roles) jaxbUnmarshaller.unmarshal(new File(ROLEDB));

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            

        } catch (JAXBException e) {
            e.printStackTrace();
        }
		
		return r;
	}
	
	
	public static Sensors getSensors () {
		Sensors s = new Sensors();
		
		try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Sensors.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            s = (Sensors) jaxbUnmarshaller.unmarshal(new File(SENSORDB));

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            

        } catch (JAXBException e) {
            e.printStackTrace();
        }
		
		return s;
	}
	
	
	public static Messages getMessages() {
		Messages m = new Messages();
		
		try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Messages.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            m = (Messages) jaxbUnmarshaller.unmarshal(new File(CHATDB));

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return m;
		
	}
	
	public static User getUserById (String id) {
		
		Users u = getUsers();
		User user = new User();
		
		for (User usr : u.getUserList()) {
			if (usr.getUsername().equals(id)) {
				user = usr;
			}
			
		}
		
		return user;
	}

	
	public static Sensor getSensorById (String id) {
		
		Sensors s = getSensors();
		Sensor sensor = new Sensor();
		
		
		for (Sensor sen : s.getSensorList()) {
			if (sen.getId().equals(id)) {
				sensor = sen;
			}
			
		}
		
		return sensor;
	}
	
	
	public static void saveUser(User user) {
		Users us = Persistent.getUsers();
		boolean add = true;
		
		for(User u : us.getUserList()) {
			if (u.getUsername().equals(user.getUsername())) {
				u.setName(user.getName());
				u.setNotes(user.getNotes());
				u.setPassword(user.getPassword());
				u.setPhone(user.getPhone());
				u.setRole(user.getRole());
				add=false;
			}
		}
		if (add) { us.getUserList().add(user); }
		
		try {
			 	
				JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				
				jaxbMarshaller.marshal(us, new FileOutputStream(USERDB));

			      } 
		 catch (JAXBException e) {
				e.printStackTrace();
			      }
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
	}
	
	public static void delUser(User user) {
		Users us = Persistent.getUsers();
		User del = new User();
		
		for(User u : us.getUserList()) {
			if (u.getUsername().equals(user.getUsername())) {
				del = u;
			}
		}
		us.getUserList().remove(del);
		try {	
				JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				
				jaxbMarshaller.marshal(us, new FileOutputStream(USERDB));

			      } 
		 catch (JAXBException e) {
				e.printStackTrace();
			      }
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
	}
	
	public static void saveMessage(Message msg) {
		Messages m = Persistent.getMessages();

		m.getMessageList().add(msg); 
		
		try {
			 	
				JAXBContext jaxbContext = JAXBContext.newInstance(Messages.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(m, new FileOutputStream(CHATDB));

			      } 
		 catch (JAXBException e) {
				e.printStackTrace();
			      }
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
	}

}
