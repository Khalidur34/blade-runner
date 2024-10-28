#ifndef PHOTOTRANSISTOR_H
#define PHOTOTRANSISTOR_H

#include <Arduino.h>

class PhotoTransistor {
public:
    PhotoTransistor(int pin, int threshold);
    void setup();
    bool isTriggered();

private:
    int _pin;
    int _threshold;
};

#endif // PHOTOTRANSISTOR_H
