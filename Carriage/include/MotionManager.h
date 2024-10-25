#ifndef MOTIONMANAGER_H
#define MOTIONMANAGER_H

class MotionManager {
  public:
    void setup(int pwmaPin, int ain1Pin, int ain2Pin);
    void forward(int speed);
    void backward(int speed);
    void stop();
    void testMotor();

  private:
    int PWMA, AIN1, AIN2;  // Motor pins
};

#endif