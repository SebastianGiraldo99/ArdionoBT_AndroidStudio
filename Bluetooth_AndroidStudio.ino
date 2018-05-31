#include <SoftwareSerial.h>

#include <Servo.h> 

Servo ServoA;
char rxChar; //Caracter recibido por bluetooth
int ang =  0; //Ángulo inicial 0 grados
const int ledPIN =13;
SoftwareSerial BT(0,1);


void setup(){
  ServoA.attach(3,600,2400); //Servomotor: PIN3 & AP(min)=600 AP(max)=2400
  Serial.begin(9600);
  BT.begin(9600);
  ServoA.write(ang);
  pinMode(ledPIN , OUTPUT);  //definir pin como salida
}

void loop(){
   
   if(BT.available()){ // "Si se detecta una entrada"
      rxChar = BT.read();
    }
    
    if (rxChar == 'C'){ 
      ang = 0; //Se gira a 0° para abrir la cerradura
      Serial.print("CERRADA"); //Se envia el valor al celular
      digitalWrite(ledPIN , LOW); 
      rxChar = 'B';
      }
    else if (rxChar == 'A'){
      ang = 180; //Se gira a 180° para cerrar la cerradura
      Serial.print("ABIERTA");
      digitalWrite(ledPIN , HIGH);
      rxChar = 'B';
      }
      
    ServoA.write(ang); //Se ingresa el valor de "ang" en el Servo
    delay(100);
  
  
  
 }
