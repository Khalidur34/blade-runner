#include <Arduino.h>
#include "WiFiManager.h"
#include "PacketManager.h"
#include "MotionManager.h"
#include "CommandManager.h"

const char* ssid = "ENGG2K3K";
String remoteIP = "10.20.30.141"; //IP of CCP
unsigned int remotePort = 5000; //Port of CPP
unsigned int localPort = 6000; //ESP32 will be listening to this port

//motor pins
int PWMA = 14;
int AIN1 = 27;
int AIN2 = 26;

//sensor pins
//const int TRIG = 27;
//const int ECHO = 28;
//const int PHT = 7;

//led pins
//const int DIN = 29;

WiFiManager wifiManager;
PacketManager packetManager;
MotionManager motionManager;
CommandManager commandManager;

void setup() {
    Serial.begin(115200);
    wifiManager.connect(ssid, "no_password");
    //packetManager.setup(remoteIP, remotePort, localPort);
    motionManager.setup(PWMA, AIN1, AIN2);
    //packetManager.begin();
    //packetManager.init();
    /*String command = commandManager.manage("EXEC:FSLOWC");
    Serial.println(command);
    //packetManager.sendPacket(command);
    command = commandManager.manage("EXEC:FFASTC");
    Serial.println(command);
    command = commandManager.manage("EXEC:RSLOWC");
    Serial.println(command);
    command = commandManager.manage("EXEC:STOPC");*/
}

void loop() {
    //String command = commandManager.manage("EXEC:FSLOWC");
    //Serial.println(command);
    //delay(10000);
    //packetManager.sendPacket(command);
    //command = commandManager.manage("EXEC:FFASTC");
    //String message = packetManager.receivePacket();
    motionManager.forward(150);
    delay(10000);
    motionManager.stop();
}