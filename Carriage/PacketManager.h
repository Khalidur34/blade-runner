#ifndef PACKETMANAGER_H
#define PACKETMANAGER_H

#include <WiFi.h>
#include <WiFiUdp.h>
#include <Arduino.h>


class PacketManager {
    public:
        void setup(String remoteIP, unsigned int remotePort, unsigned int localPort);
        void begin();
        void init();
        void sendPacket(String message);
        String receivePacket(); 
    private:
        WiFiUDP udp;
        char incomingPacket[255];
        unsigned int localPort;
        IPAddress remoteIP;
        unsigned int remotePort;
};

#endif