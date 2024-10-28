#ifndef LedControl_H
#define LedControl_H
#include <Arduino.h>
#include "PhotoTransistor.h"
#include "UltrasonicSensor.h"

class LedControl {
public:
    void setup();
    void setColor(int red, int green, int blue);
    void testLed();
    void checkSensors();

private:
    PhotoTransistor photoTransistor;
    Ultrasonic ultrasonicSensor;     
};
#endif