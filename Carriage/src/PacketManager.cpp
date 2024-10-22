#include "PacketManager.h"

//this class is for exchanging commands

void PacketManager::setup(String remoteIP, unsigned int remotePort, unsigned int localPort) {
    IPAddress ip;
    ip.fromString(remoteIP);
    this->remoteIP = ip;
    this->remotePort = remotePort;
    this->localPort = localPort;
}

void PacketManager::begin() {
    udp.begin(localPort);
    Serial.printf("ready to send and receive commands");
}

void PacketManager::init() {
    sendPacket("BRIN"); //send init message
    String command;
    Serial.printf("waiting for BRIN");
    while(command != "CCIN") { //will wait indefinitely until AKIN in received
        command = receivePacket();
    }
    Serial.printf("BRIN received");
    Serial.printf("init complete");
}

void PacketManager::sendPacket(String message) {
    udp.beginPacket(remoteIP, remotePort);
    udp.write((uint8_t*)message.c_str(), message.length());
    udp.endPacket();
}

String PacketManager::receivePacket() {
    int packetSize = udp.parsePacket();
    if (packetSize) {
        int len = udp.read(incomingPacket, 255);
        if (len > 0) {
            incomingPacket[len] = 0;
        }
        return String(incomingPacket);
    }
    return "";
}
