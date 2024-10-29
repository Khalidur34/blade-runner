#ifndef MOTIONMANAGER_H
#define MOTIONMANAGER_H
class MotionManager {
  public:
    void setup();
    void forward(int speed);
    void backward(int speed);
    void stop();
    void testMotor();
    void currSpeed(int speed);
  private:
    int PWMA = 14;
    int AIN1 = 27;
    int AIN2 = 26;
    int vel = 0;
};

#endif