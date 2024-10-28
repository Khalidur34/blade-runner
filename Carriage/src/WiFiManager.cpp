#include "WiFiManager.h"
#include <Arduino.h>

//this class is for managing wireless connectivity

void WiFiManager::connect(const char* ssid, const char* password) {
    WiFi.begin("ENGG2K3K");

    while(WiFi.status() != WL_CONNECTED) {
        delay(1000);
        Serial.printf("Connecting to WiFi...");
        Serial.println();
    }
    
    Serial.printf("Connected to WiFi");
    Serial.println();
    Serial.printf("ESP32 IP: ");
    Serial.print(WiFi.localIP());   
}