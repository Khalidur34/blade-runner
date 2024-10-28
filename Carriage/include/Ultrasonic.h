#ifndef ULTRASONICSENSOR_H
#define ULTRASONICSENSOR_H

#include <Arduino.h>

class Ultrasonic {
public:
    Ultrasonic(int trigPin, int echoPin, int threshold);
    void setup();
    bool isTriggered();
    int getDistance();

private:
    int _trigPin;
    int _echoPin;
    int _threshold;
};

#endif // ULTRASONICSENSOR_H
