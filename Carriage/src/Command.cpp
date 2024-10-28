#include "Command.h"
#define Threshold_Value
#define min_Distance
// this class contains the execution logic for each command
// commands execute required actions and return the reply message

String Command::execStopc() {
    motionManager.stop();
    ledControl.setColor(255, 0, 0);
    return "AK:STOPC";
}

String Command::execStopo() {
    motionManager.stop();
    ledControl.setColor(255, 165, 0);
    return "AK:STOPO";
}

String Command::execFFastc() {
    motionManager.forward(255);
    ledControl.setColor(0, 255, 0);
    return "AK:FFASTC";
}

String Command::execFSlowc() {
    motionManager.forward(150);
    ledControl.setColor(255, 255, 0);
    return "AK:FSLOWC";
}

String Command::execRSlowc() {
    motionManager.backward(150);
    ledControl.setColor(0, 0, 255);
    return "AK:RSLOWC";
}

// Add sensor functions to check if triggered
// bool Command::isPhototransistorTriggered() {
//     return photoTransistor.read() > Threshold_Value;
// }

// bool Command::isUltrasonicTriggered() {
//     return ultrasonic.getDistance() < min_Distance;
// }
