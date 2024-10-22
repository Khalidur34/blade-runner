#include "MotionManager.h"
#include <Arduino.h>

void MotionManager::setup(int pwmaPin, int ain1Pin, int ain2Pin) {
    PWMA = pwmaPin;
    AIN1 = ain1Pin;
    AIN2 = ain2Pin;

    pinMode(PWMA, OUTPUT);
    pinMode(AIN1, OUTPUT);
    pinMode(AIN2, OUTPUT);
}

void MotionManager::forward(int speed) {
    digitalWrite(AIN1, HIGH);   // Motor forward
    digitalWrite(AIN2, LOW);
    analogWrite(PWMA, speed);   // PWM for x% speed
}

void MotionManager::backward(int speed) {
    digitalWrite(AIN1, LOW);    // Motor backward
    digitalWrite(AIN2, HIGH);
    analogWrite(PWMA, speed);   // PWM for x% speed
}

void MotionManager::stop() {
    digitalWrite(AIN1, LOW);    // Stop the motor
    digitalWrite(AIN2, LOW);
    analogWrite(PWMA, 0);       // Speed 0
}