package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import application.Main;

public class CSVUtil {
	
	private static final String FICHEROCSV = "sensorinput.csv";
	
	public static void leerCSV() {
        String line = "";
        String separador = ",";
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FICHEROCSV)))) {

            while ((line = br.readLine()) != null) {
                String[] sensor = line.split(separador);
                System.out.println( sensor[2].replace("\"","")  + " - Sensor ID " +  sensor[0].replace("\"","")  + " , valor ["+ sensor[1].replace("\"","")  + "] ");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
	public static String leerCSVbySensor(String sid) {
        String line = "";
        String separador = ",";
        String currentValue = "";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FICHEROCSV)))) {

            while ((line = br.readLine()) != null) {
                String[] sensor = line.split(separador);
                if (sensor[0].replace("\"","").equals(sid)) { 
                	currentValue = sensor[1].replace("\"","") ;
                }
                

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
		return currentValue;

	}


}
