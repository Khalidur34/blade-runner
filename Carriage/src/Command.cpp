#include "Command.h"

String Command::execStopc() {
    motionManager.stop();
    return "AK:STOPC";
}

String Command::execStopo() {
    return "AK:STOPO";
}

String Command::execFSlowc() {
    return "AK:FSLOWC";
}

String Command::execFFastc() {
    return "AK:FFASTC";
}

String Command::execRSlowc() {
    return "AK:RSLOWC";
}