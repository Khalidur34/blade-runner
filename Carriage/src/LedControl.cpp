#include <Adafruit_NeoPixel.h>
#include "LedControl.h"

// Pin where NeoPixel is connected
#define LED_PIN 12 // Use the GPIO pin you've wired to DIN of NeoPixel

// Number of NeoPixels
#define NUM_PIXELS 4 // Adjust for the number of pixels on your stick

Adafruit_NeoPixel strip(NUM_PIXELS, LED_PIN, NEO_GRB + NEO_KHZ800);

void LedControl::setup() {
    strip.begin();
    strip.show(); // Initialize all pixels to 'off'
}

void LedControl::setColor(int red, int green, int blue) {
    uint32_t color = strip.Color(red, green, blue);
    for (int i = 0; i < strip.numPixels(); i++) {
        strip.setPixelColor(i, color);  // Set color for each LED
    }
    strip.show();  // Update the strip to display the new color
}

void LedControl::testLed() {
  setColor(255, 0, 0);  // Red
  delay(1000);
  setColor(0, 255, 0);  // Green
  delay(1000);
  setColor(0, 0, 255);  // Blue
  delay(1000);
}