#include "MotionManager.h"
#include <Arduino.h>

//this class is for motor setup and motor control

void MotionManager::setup() {
    pinMode(PWMA, OUTPUT);
    pinMode(AIN1, OUTPUT);
    pinMode(AIN2, OUTPUT);

    digitalWrite(AIN1, LOW);
    digitalWrite(AIN2, LOW);
}

void MotionManager::forward(int speed) {
    digitalWrite(AIN1, HIGH);   // Motor forward
    digitalWrite(AIN2, LOW);
    while(vel < speed) {
        currSpeed(vel);
        vel++;
    }
}

void MotionManager::backward(int speed) {
    digitalWrite(AIN1, LOW);    // Motor backward
    digitalWrite(AIN2, HIGH);
    while(vel < speed) {
        currSpeed(vel);
        vel++;
    }
}

void MotionManager::stop() {
    for(int i = vel; i >= 0 ; i-= 50) {
        currSpeed(i);
        vel = i;
    }
    digitalWrite(AIN1, LOW);    // Stop the motor
    digitalWrite(AIN2, LOW);  
    unsigned long stopTime = millis();
    while(millis() - stopTime < 2000);  
}

void MotionManager::currSpeed(int speed) {
    analogWrite(PWMA, speed);
    delay(1);
}

void MotionManager::testMotor() {
    // forward();
    // delay(2000);
    // stop();
    // backward();
    // delay(2000);
    // stop();
}