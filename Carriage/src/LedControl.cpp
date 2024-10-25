#include <Adafruit_NeoPixel.h>
#include "LedControl.h"

// Pin where NeoPixel is connected
#define LED_PIN 5 // Use the GPIO pin you've wired to DIN of NeoPixel

// Number of NeoPixels
#define NUM_PIXELS 8 // Adjust for the number of pixels on your stick

Adafruit_NeoPixel strip(NUM_PIXELS, LED_PIN, NEO_GRB + NEO_KHZ800);

void LedControl::setup() {
    strip.begin();
    strip.show(); // Initialize all pixels to 'off'
}

uint32_t LedControl::hexToColor(const char *hex){
    long number = strtol(hex + 1, NULL, 16); // Convert hex to long
    uint8_t r = (number >> 16) & 0xFF;       // Extract red component
    uint8_t g = (number >> 8) & 0xFF;        // Extract green component
    uint8_t b = number & 0xFF;               // Extract blue component
    return strip.Color(g, r, b);             // Return in G-R-B order
}

// Fill the strip with a color, one pixel at a time
void LedControl::changeColor(char *hex){
    uint32_t color = hexToColor(hex);
    for (int i = 0; i < strip.numPixels(); i++)
    {
        strip.setPixelColor(i, color);
        strip.show();
    }
}

void LedControl::turnOff() {
    changeColor("#000000");
}

void LedControl::testLed() {
    changeColor("#FF0000"); 
    delay(2000);           
    changeColor("#00FF00"); 
    delay(2000);           
    changeColor("#0000FF"); 
    delay(2000);   
    turnOff();
}