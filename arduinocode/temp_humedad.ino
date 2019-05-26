//Script DH11

#include <SimpleDHT.h>

// for DHT11, 
//      VCC: 5V or 3V
//      GND: GND
//      DATA: 2
int pinDHT11 = 2;
int TsensorID = 1;
int HsensorID = 2;
SimpleDHT11 dht11(pinDHT11);

void setup() {
  Serial.begin(9600);
  delay(500);//Delay to let system boot
  delay(1000);
}

void loop() {
  // read with raw sample data.
  byte temperature = 0;
  byte humidity = 0;
  if (dht11.read(pinDHT11, &temperature, &humidity, NULL)) {
    return;
  }
  
  Serial.print((String)"T#");
  Serial.print((int)TsensorID); 
  Serial.print('#');
  Serial.println((int)temperature); 
  delay(500);
  Serial.print((String)"H#");
  Serial.print((int)HsensorID); 
  Serial.print('#');
  Serial.println((int)humidity); 
 
  
  // DHT11 sampling rate is 1HZ.
  delay(1500);
}