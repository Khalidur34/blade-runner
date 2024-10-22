#include <Arduino.h>
#include "WiFiManager.h"
#include "PacketManager.h"
#include "MotionManager.h"
#include "CommandManager.h"

const char* ssid = "ENGG2K3K";
String remoteIP = "192.168.1.1"; //IP of CCP
unsigned int remotePort = 3020; //Port of CPP
unsigned int localPort = 5000; //ESP32 will be listening to this port

//motor pins
const int PWMA = 10;
const int AIN1 = 35;
const int AIN2 = 34;

//sensor pins
const int TRIG = 27;
const int ECHO = 28;
const int PHT = 7;

//led pins
const int DIN = 29;

WiFiManager wifiManager;
PacketManager packetManager;
MotionManager motionManager;
CommandManager commandManager;

void setup() {
    Serial.begin(115200);
    wifiManager.connect(ssid, "no_password");
    packetManager.setup(remoteIP, remotePort, localPort);
    motionManager.setup(PWMA, AIN1, AIN2);
    packetManager.begin();
    packetManager.init();
}

void loop() {
    
}