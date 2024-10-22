#include "WiFiManager.h"
#include <Arduino.h>

//this class is for managing wireless connectivity

void WiFiManager::connect(const char* ssid, const char* password) {
    WiFi.begin(ssid);

    while(WiFi.status() != WL_CONNECTED) {
        delay(1000);
        Serial.println("Connecting to WiFi...");
    }
    
    Serial.println("Connected to WiFi");
    Serial.print("ESP32 IP: ");
    Serial.println(WiFi.localIP());   
}