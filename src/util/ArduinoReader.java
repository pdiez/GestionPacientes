package util;

import java.util.logging.Logger;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class ArduinoReader extends Thread {
	
	//Se crea una instancia de la librería PanamaHitek_Arduino
    private static PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    public String salida ="";
      
    private static final SerialPortEventListener listener = new SerialPortEventListener() {
    	public String salida;
    	@Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if (ino.isMessageAvailable()) {
                    //Se imprime el mensaje recibido en la consola
                    //System.out.println("la temperatura es: " + ino.printMessage());
                    salida = ino.printMessage();
                    //System.out.println(salida);
                    parseReading(salida);
                   
                }
            } catch (SerialPortException | ArduinoException ex) {
                Logger.getLogger(ArduinoReader.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    };

    	   		
    private static void parseReading(String s) {
    	 String[] data = s.split("#");
         if (data.length==3) {
             String tipo = data[0]; 
             int sensorId = Integer.parseInt(data[1]);
             int out = Integer.parseInt(data[2]);
             Persistent.saveSensorData(tipo, sensorId, out);
         }
    	
    }
    
    public void run() {
        try {
            ino.arduinoRX("COM3", 9600, listener);
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(ArduinoReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
}
