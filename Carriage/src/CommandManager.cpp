#include "CommandManager.h"

String CommandManager::manage(String task) {
    if(task == "STRQ") {
        return status;
    }
    else if(task == "EXEC:STOPC") { //BR stopped and the door is closed
        status = "STAT:STOPC";
        return command.execStopc();
    }
    else if(task == "EXEC:STOPO") { //BR stopped and the door is opened
        status = "STAT:STOPO";
        return command.execStopo();
    }
    else if(task == "EXEC:FSLOWC") { //BR moving in forward direction slowly and the door is closed.
        status = "STAT:FSLOWC";
        return command.execFSlowc();
    }
    else if(task == "EXEC:FFASTC") { //BR moving in forward direction at fast speed and the door is closed
        status = "STAT:FFASTC";
        return command.execFFastc();
    }
    else if(task == "EXEC:RSLOWC") { //BR moving slowly in reverse direction and the door is closed.
        status = "STAT:RSLOWC"; 
        return command.execRSlowc();
    }
    
}
String CommandManager::getStatus() {
    return status;
}