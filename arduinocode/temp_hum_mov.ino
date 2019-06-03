//Script DH11

#include <SimpleDHT.h>

// for DHT11, 
//      VCC: 5V or 3V
//      GND: GND
//      DATA: 2
int pinDHT11 = 2;
int TsensorID = 1;
int HsensorID = 2;
int MsensorID = 3;

int ES = 0;

const int PIR = 7;  // pin 7 donde  va a la señal del sensor de movimiento
const int LED = 13;
int pir_lectura = 0;

SimpleDHT22 dht11(pinDHT11); //OJO AQUI K ES DONDE MODIFIQUE PARA MI SENSOR DE H/T

void setup() {
  Serial.begin(9600); // Configurar el puerto serie a 9600 para monitorizar
  pinMode(LED, OUTPUT); // Configurar LED como salida o OUTPUT
  pinMode(PIR, INPUT);  // Configurar pir como entrada o INPUT
  
  delay(500);//Delay to let system boot
  delay(1000);
}

void loop() {
  // Código principal donde ocurren en loop
  
  byte temperature = 0;
  byte humidity = 0;
  pir_lectura = digitalRead(PIR); //leer el pin del sensor de movimiento PIR
 
  if (dht11.read(pinDHT11, &temperature, &humidity, NULL)) {
    return;
  }
 if (pir_lectura == HIGH and (ES % 2)==0 ){ 
    digitalWrite(LED, HIGH); // prende LED
    Serial.print((String)"M#");
    Serial.print((int)MsensorID); 
    Serial.println("#IN");
    ES++;
    
  }
  else {
  if (pir_lectura == HIGH and (ES % 2)==1 ){
    digitalWrite(LED, HIGH); // prende LED
    Serial.print((String)"M#");
    Serial.print((int)MsensorID); 
    Serial.println("#OUT");
    ES++;
  }
   else {
      
    digitalWrite(LED, LOW); // apagar LED
    //Serial.println("No hay movimiento");
  }
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
