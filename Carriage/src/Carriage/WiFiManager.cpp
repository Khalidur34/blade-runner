#include "WiFiManager.h"
#include <Arduino.h>

void WiFiManager::connect(const char* ssid, const char* password) {
    WiFi.begin(ssid);   
}