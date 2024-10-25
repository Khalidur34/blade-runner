#ifndef LedControl_H
#define LedControl_H
#include <Arduino.h>

class LedControl {
public:
    void setup();
    void changeColor(char *hex);
    void testLed();
    void turnOff();

private:
    uint32_t hexToColor(const char *hex);
};
#endif