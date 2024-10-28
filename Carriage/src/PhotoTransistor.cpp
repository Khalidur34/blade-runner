#include "PhotoTransistor.h"

PhotoTransistor::PhotoTransistor(int pin, int threshold)
    : _pin(pin), _threshold(threshold) {}

void PhotoTransistor::setup() {
    pinMode(_pin, INPUT);
}

bool PhotoTransistor::isTriggered() {
    int lightLevel = analogRead(_pin);
    return (lightLevel < _threshold); // Triggered if light level is below the threshold
}
