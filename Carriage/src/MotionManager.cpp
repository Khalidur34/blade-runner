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
}

void MotionManager::forward(int speed) {
    constrain(speed, 0, 255);
    digitalWrite(AIN1, HIGH);   // Motor forward
    digitalWrite(AIN2, LOW);
    analogWrite(PWMA, speed);   // PWM for x% speed
}

void MotionManager::backward(int speed) {
    constrain(speed, 0, 255);
    digitalWrite(AIN1, LOW);    // Motor backward
    digitalWrite(AIN2, HIGH);
    analogWrite(PWMA, speed);   // PWM for x% speed
}

void MotionManager::stop() {
    digitalWrite(AIN1, LOW);    // Stop the motor
    digitalWrite(AIN2, LOW);
    analogWrite(PWMA, 0);     
}

void MotionManager::testMotor() {
    stop();

    // test fowards
    delay(2000);
    forward(200);
    delay(5000);
    stop();

    // test backwards
    delay(2000);
    backward(100);
    delay(5000);
    stop();

    // test backwards into forwards without stop
    delay(2000);
    forward(200);
    delay(5000);
    backward(100);
    delay(5000);

    stop();
}