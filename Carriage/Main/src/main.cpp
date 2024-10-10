#include <Arduino.h>
#include <WiFi.h>
#include <WebServer.h>

WiFiUDP udp;
WiFiManager wifiManager;
PacketManager packetManager;
CommandManager commandManager;

const char* ssid = "ENGG2K3K";
unsigned int localUdpPort = 3020;  // Port used for communication
char incomingPacket[255];  // Buffer for incoming packet
String status = "STOPC";

//motor pins
const int PWMA = 14;
const int AIN1 = 27;
const int AIN2 = 26;

//sensor pins
const int TRIG = 27;
const int ECHO = 28;
const int PHT = 7;

//led pins
const int DIN = 29;

class WiFiManager {
  public:
  void connect() {
    WiFi.begin(ssid);
    while(WiFi.status() != WL_CONNECTED) {
      delay(1000);
      Serial.println("Connecting to WiFi...");
    }
    Serial.println("Connected to WiFi");
    Serial.print("ESP32 IP: ");
    Serial.println(WiFi.localIP());
  }

  IPAddress getIP() {
    return WiFi.localIP();
  }
};

class PacketManager {
  public:
  void begin() {
    udp.begin(localUdpPort);
    Serial.printf("Listening on port %d\n", localUdpPort);
  }

  String receivePacket() {
    int packetSize = udp.parsePacket();
    if (packetSize) {
      int len = udp.read(incomingPacket, 255);
      if (len > 0) {
        incomingPacket[len] = 0;
      }
      return String(incomingPacket);
    }
  }

  void sendPacket(IPAddress ip, int port, String message) {
      udp.beginPacket(ip, port);
      char buffer[message.length() + 1];
      message.toCharArray(buffer, message.length() + 1);
      udp.write((uint8_t*)buffer, message.length());
      udp.endPacket();
  }

};

class CommandManager {
  Command command;
  
  public:

  String manage(String task) {
    if(task == "STRQ") {
      return status;
    }
    else if(task == "EXEC:STOPC") { //BR stopped and the door is closed
      status = "STAT:STOPC";
      return command.execStopc();
    }
    else if(task == "EXEC:STOPO") { //BR stopped and the door is opened
      status = "STAT:STOPO";
      return command.execStopo();
    }
    else if(task == "EXEC:FSLOWC") { //BR moving in forward direction slowly and the door is closed.
      status = "STAT:FSLOWC";
      return command.execFSlowc();
    }
    else if(task == "EXEC:FFASTC") { //BR moving in forward direction at fast speed and the door is closed
      status = "STAT:FFASTC";
      return command.execFFastc();
    }
    else if(task == "EXEC:RSLOWC") { //BR moving slowly in reverse direction and the door is closed.
      status = "STAT:RSLOWC"; 
      return command.execRSlowc();
    }
  }
  
};

class Command {
  MotionManager motionManager;
  MiscManager miscManager;

  public:
  
  String execStopc() {
    motionManager.stop();
    miscManager.doorControl(false);
    return "AK:STOPC";
  }
  String execStopo(){
    motionManager.stop();
    miscManager.doorControl(true);
    return "AK:STOPO";
  }
  String execFSlowc() {
    miscManager.doorControl(false);
    motionManager.forward(150);
    while(!miscManager.sensorStatus()){
      
    }
    motionManager.stop();
    return "AK:FSLOWC";
  }
  String execFFastc() {
    motionManager.forward(255);
    miscManager.doorControl(false);
    return "AK:FFASTC";
  }
  String execRSlowc() {
    motionManager.backward(150);
    miscManager.doorControl(false);
    while(!miscManager.sensorStatus()){
      
    }
    motionManager.stop();
    return "AK:RSLOWC";
  }  
};

class MotionManager {

  public:

  void forward(int speed) {
    digitalWrite(AIN1, HIGH);   // Motor forward
    digitalWrite(AIN2, LOW);
    analogWrite(PWMA, speed);     // PWM for x% speed
  }

  void backward(int speed) {
    digitalWrite(AIN1, LOW);    // Motor backward
    digitalWrite(AIN2, HIGH);
    analogWrite(PWMA, speed);     // PWM for x% speed
  }

  void stop() {
    digitalWrite(AIN1, LOW);    // Stop the motor
    digitalWrite(AIN2, LOW);
    analogWrite(PWMA, 0);       // Speed 0
  }

};

class MiscManager {
  public:
  void doorControl(boolean status) {
    if(status == true) {
      Serial.printf("Door open");
    }
    else{
      Serial.printf("Door closed");
    }
  }
  void ledControl(boolean status, int color) {

  }
  bool sensorStatus() {
    int sensorValue = analogRead(PHT);
    Serial.printf("Phototransistor reading: %d\n", sensorValue);
    return sensorValue > 250;
  }

};

void setup() {
  Serial.begin(115200);
  pinMode(PWMA, OUTPUT);
  pinMode(AIN1, OUTPUT);
  pinMode(AIN2, OUTPUT);
  pinMode(PHT, INPUT);
  wifiManager.connect();
  packetManager.begin();
}

void loop() {
  String command = packetManager.receivePacket();
  if(command != "") {
    Serial.printf("Received command: '%s'\n", command.c_str());
    String response = commandManager.manage(command);
    Serial.printf("Sending response: '%s'\n", response.c_str());
    packetManager.sendPacket(udp.remoteIP(), udp.remotePort(), response);
  }
}