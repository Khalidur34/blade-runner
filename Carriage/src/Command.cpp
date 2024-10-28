#include "Command.h"

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
    motionManager.stop();
    motionManager.forward(255);
    ledControl.setColor(0, 255, 0);
    return "AK:FFASTC";
}

//execFSlowc() should slow down the carriage first then start to detect phototransistor,
//when close enough or detected stop() command should be called. 

String Command::execFSlowc() {
    motionManager.stop();
    motionManager.forward(100);
    ledControl.setColor(255, 255, 0);
    return "AK:FSLOWC";
}

//if alignment fails this function will be called and same function will be executed but in REVERSE

String Command::execRSlowc() {
    motionManager.stop();
    motionManager.backward(100);
    ledControl.setColor(0, 0, 255);
    return "AK:RSLOWC";
}