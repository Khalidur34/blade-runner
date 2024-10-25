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
    motionManager.forward(200);
    ledControl.changeColor("#00FF00");
    return "AK:FFASTC";
}

String Command::execFSlowc() {
    motionManager.forward(100);
    ledControl.changeColor("#FFFF00");
    return "AK:FSLOWC";
}

String Command::execRSlowc() {
    motionManager.backward(100);
    ledControl.changeColor("#0000FF");
    return "AK:RSLOWC";
}