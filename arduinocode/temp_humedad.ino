#include <SimpleDHT.h>
//necesita instalar la libreria SimpleDHT en herramientas->librerias
// for DHT11, 
//      VCC: 5V or 3V
//      GND: GND
//      DATA: 2
int pinDHT11 = 2;
int sensorID = 1;
SimpleDHT11 dht11;

void setup() {
  Serial.begin(9600);
}

void loop() {
  // read with raw sample data.
  byte temperature = 0;
  byte humidity = 0;
  byte data[40] = {0};
  if (dht11.read(pinDHT11, &temperature, &humidity, data)) {
    return;
  }
  for (int i = 0; i < 40; i++) {
    
  }
  Serial.print((int)sensorID); 
  Serial.print("#");
  Serial.print((int)temperature); 
  Serial.print("#");
  Serial.println((int)humidity); 
 
  
  // DHT11 sampling rate is 1HZ.
  delay(1000);
}