#include "MotionManager.h"
#include <Arduino.h>

//this class is for motor setup and motor control

void MotionManager::setup(int pwmaPin, int ain1Pin, int ain2Pin) {
    PWMA = pwmaPin;
    AIN1 = ain1Pin;
    AIN2 = ain2Pin;

    pinMode(PWMA, OUTPUT);
    pinMode(AIN1, OUTPUT);
    pinMode(AIN2, OUTPUT);

    digitalWrite(AIN1, LOW);
    digitalWrite(AIN2, LOW);

    ledcAttachPin(PWMA, 0);  // Attach `enA` pin to PWM channel 0
    ledcSetup(0, 5000, 8);
}

void MotionManager::forward(int speed) {
    Serial.println("I am here");
    digitalWrite(AIN1, HIGH);   // Motor forward
    digitalWrite(AIN2, LOW);
    ledcWrite(0, speed);    // PWM for x% speed
}

void MotionManager::backward(int speed) {
    digitalWrite(AIN1, LOW);    // Motor backward
    digitalWrite(AIN2, HIGH);
    ledcWrite(0, speed);    // PWM for x% speed
}

void MotionManager::stop() {
    digitalWrite(AIN1, LOW);    // Stop the motor
    digitalWrite(AIN2, LOW);
    ledcWrite(0, 0);        // Speed 0
}