#ifndef PHOTOTRANSISTOR_H
#define PHOTOTRANSISTOR_H

#include <Arduino.h>

class PhotoTransistor {
public:
    void setup(int pinPHT, int threshold);
    bool isTriggered();

private:
    int pinPHT;
    int threshold;
};

#endif // PHOTOTRANSISTOR_H
