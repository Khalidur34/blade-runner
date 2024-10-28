#ifndef LedControl_H
#define LedControl_H
#include <Arduino.h>

class LedControl {
public:
    void setup();
    void setColor(int red, int green, int blue);
    void testLed();
};
#endif