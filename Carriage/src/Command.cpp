#include "Command.h"

// this class contains the execution logic for each command
// commands execute required actions and return the reply message

String Command::execStopc() {
    motionManager.stop();
    ledControl.changeColor("#FF0000");
    return "AK:STOPC";
}

String Command::execStopo() {
    motionManager.stop();
    ledControl.changeColor("#FFA500");
    return "AK:STOPO";
}

String Command::execFFastc() {
    motionManager.stop();
    motionManager.forward(255);
    return "AK:FFASTC";
}

//execFSlowc() should slow down the carriage first then start to detect phototransistor,
//when close enough or detected stop() command should be called. 

String Command::execFSlowc() {
    motionManager.stop();
    motionManager.forward(100);
    ledControl.changeColor("#FFFF00");
    return "AK:FSLOWC";
}

//if alignment fails this function will be called and same function will be executed but in REVERSE

String Command::execRSlowc() {
    motionManager.stop();
    motionManager.backward(100);
    ledControl.changeColor("#0000FF");
    return "AK:RSLOWC";
}