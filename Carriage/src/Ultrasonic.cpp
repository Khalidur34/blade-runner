#include "Ultrasonic.h"

Ultrasonic::Ultrasonic(int trigPin, int echoPin, int threshold)
    : _trigPin(trigPin), _echoPin(echoPin), _threshold(threshold) {}

void Ultrasonic::setup() {
    pinMode(_trigPin, OUTPUT);
    pinMode(_echoPin, INPUT);
}

bool Ultrasonic::isTriggered() {
    int distance = getDistance();
    return (distance < _threshold); // Triggered if distance is below the threshold
}

int Ultrasonic::getDistance() {
    // Send a pulse
    digitalWrite(_trigPin, LOW);
    delayMicroseconds(2);
    digitalWrite(_trigPin, HIGH);
    delayMicroseconds(10);
    digitalWrite(_trigPin, LOW);

    // Read the echo pin
    long duration = pulseIn(_echoPin, HIGH);
    int distance = duration * 0.034 / 2; // Calculate distance in cm

    return distance;
}
