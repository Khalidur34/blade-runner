#include <Arduino.h>
#include "WiFiManager.h"
#include "PacketManager.h"
#include "CommandManager.h"

const char* ssid = "ENGG2K3K";
String remoteIP = "10.20.30.141"; //IP of CCP
unsigned int remotePort = 5000; //Port of CPP
unsigned int localPort = 6000; //ESP32 will be listening to this port

WiFiManager wifiManager;
PacketManager packetManager;
CommandManager commandManager;

void setup() {
    Serial.begin(115200);
    wifiManager.connect(ssid, "no_password");
    packetManager.setup(remoteIP, remotePort, localPort);
    packetManager.init();
    commandManager.setup();
}

void loop() {
    String command = packetManager.receivePacket();
    if(command != "") {
        String response = commandManager.manage(command);
        packetManager.sendPacket(response);
    }
}