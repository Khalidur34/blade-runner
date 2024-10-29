#include "PhotoTransistor.h"


void PhotoTransistor::setup(int pinPHT, int threshold) {
    this->pinPHT = pinPHT;
    this->threshold = threshold;
    pinMode(pinPHT, INPUT);
}

bool PhotoTransistor::isTriggered() {
    int lightLevel = analogRead(pinPHT);
    return (lightLevel < threshold); // Triggered if light level is below the threshold
}
