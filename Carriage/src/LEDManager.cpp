#include <Adafruit_NeoPixel.h>

// Pin where NeoPixel is connected
#define LED_PIN 5 // Use the GPIO pin you've wired to DIN of NeoPixel

// Number of NeoPixels
#define NUM_PIXELS 8 // Adjust for the number of pixels on your stick

// Create the NeoPixel object
Adafruit_NeoPixel strip(NUM_PIXELS, LED_PIN, NEO_GRB + NEO_KHZ800);

void setup()
{
    // Initialize NeoPixel strip
    strip.begin();
    strip.show(); // Initialize all pixels to 'off'
}

uint32_t hexToColor(const char *hex)
{
    long number = strtol(hex + 1, NULL, 16); // Convert hex to long
    uint8_t r = (number >> 16) & 0xFF;       // Extract red component
    uint8_t g = (number >> 8) & 0xFF;        // Extract green component
    uint8_t b = number & 0xFF;               // Extract blue component
    return strip.Color(g, r, b);             // Return in G-R-B order
}

// Fill the strip with a color, one pixel at a time
void changeColor(char *hex)
{
    for (int i = 0; i < strip.numPixels(); i++)
    {
        strip.setPixelColor(i, hexToColor(hex));
        strip.show();
    }
}

// Display a rainbow pattern
void rainbow(int wait)
{
    for (long firstPixelHue = 0; firstPixelHue < 5 * 65536; firstPixelHue += 256)
    {
        for (int i = 0; i < strip.numPixels(); i++)
        {
            int pixelHue = firstPixelHue + (i * 65536L / strip.numPixels());
            strip.setPixelColor(i, strip.gamma32(strip.ColorHSV(pixelHue)));
        }
        strip.show();
        delay(wait);
    }
}