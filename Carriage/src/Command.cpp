#include "Command.h"

//this class contains the execution logic for each and every command

String Command::execStopc() {
    motionManager.stop();
    return "AK:STOPC";
}

String Command::execStopo() {
    motionManager.stop();
    return "AK:STOPO";
}

String Command::execFSlowc() {
    motionManager.forward(150);
    return "AK:FSLOWC";
}

String Command::execFFastc() {
    for(int i = 0 ; i < 200; i = i + 50) {
        motionManager.forward(i);
    }
    
    return "AK:FFASTC";
}

String Command::execRSlowc() {
    for(int i = 0 ; i < 150; i = i + 50) {
        motionManager.forward(i);
    }
    return "AK:RSLOWC";
}