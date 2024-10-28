#include <Arduino.h>
#include "WiFiManager.h"
#include "PacketManager.h"
#include "MotionManager.h"
#include "CommandManager.h"
#include "LedControl.h"

const char* ssid = "ENGG2K3K";
String remoteIP = "10.20.30.141"; //IP of CCP
unsigned int remotePort = 5000; //Port of CPP
unsigned int localPort = 6000; //ESP32 will be listening to this port

// motor pins
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
CommandManager commandManager;
PhotoTransistor photoTransistor;
MotionManager motionManager;
LedControl ledControl;

void setup() {
    Serial.begin(115200);
    //wifiManager.connect(ssid, "no_password");
    //packetManager.setup(remoteIP, remotePort, localPort);
    motionManager.setup(PWMA, AIN1, AIN2);
    //motionManager.testMotor();
}

void loop() {

}