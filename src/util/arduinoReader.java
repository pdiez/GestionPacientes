package util;

import java.util.logging.Logger;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class arduinoReader {

	//Se crea una instancia de la librería PanamaHitek_Arduino
    private static PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    public double valor= 2;
    public String salida ="";
    
    public double get_valor() {
    	
		return valor;
    	
    }
    
    private static final SerialPortEventListener listener = new SerialPortEventListener() {
    	public String salida;
    	@Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if (ino.isMessageAvailable()) {
                    //Se imprime el mensaje recibido en la consola
                    //System.out.println("la temperatura es: " + ino.printMessage());
                    salida = ino.printMessage();
                    System.out.println(salida);
                    
                   
                                    }
            } catch (SerialPortException | ArduinoException ex) {
                Logger.getLogger(arduinoReader.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    };

    	   		
    public String get_temperatura() {
    	return salida;
    }
    
    public static  void main(String[] args) {
        try {
            ino.arduinoRX("COM3", 9600, listener);
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(arduinoReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
}
