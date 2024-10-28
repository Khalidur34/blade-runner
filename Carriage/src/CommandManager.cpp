#include "CommandManager.h"
#include <string>
#include <vector>
#include <sstream>

//this class is for filtering messages into the correct command handlers
const int MAX_PARTS = 4;
void CommandManager::setup() {
    status = "STOPC";
}

String CommandManager::manage(String message) {
    if(message.isEmpty())
        return "ERR: STRING EMPTY";

    String msgParts[MAX_PARTS];
    splitString(message, ':', msgParts, MAX_PARTS);
    
    if(msgParts[0] == "STRQ") {
        return "STAT:" + getStatus();
    }
    else if(msgParts[0] == "EXEC") {
        if(msgParts[1] == getStatus()){
            return "ERR: ALREADY IN STATE";
        }
        else if(msgParts[1] == "STOPC") { //BR stopped and the door is closed
            status = "STOPC";
            return command.execStopc();
        }
        else if(msgParts[1] == "STOPO") { //BR stopped and the door is opened
            status = "STOPO";
            return command.execStopo();
        }
        else if(msgParts[1] == "FSLOWC") { //BR moving in forward direction slowly and the door is closed.
            status = "FSLOWC";
            return command.execFSlowc();
        }
        else if(msgParts[1] == "FFASTC") { //BR moving in forward direction at fast speed and the door is closed
            status = "FFASTC";
            return command.execFFastc();
        }
        else if(msgParts[1] == "RSLOWC") { //BR moving slowly in reverse direction and the door is closed.
            status = "RSLOWC"; 
            return command.execRSlowc();
        }
    }
    return "ERR: COMMAND NOT FOUND";
    
}
String CommandManager::getStatus() {
    return status;
}

void CommandManager::splitString(const String &str, char delimiter, String output[], int maxParts) {
    int partIndex = 0;
    int start = 0;
    int end = str.indexOf(delimiter);

    // Loop through the string and split by delimiter
    while (end != -1 && partIndex < maxParts) {
        output[partIndex++] = str.substring(start, end);
        start = end + 1;  // Move past the delimiter
        end = str.indexOf(delimiter, start);
    }
    
    // Capture the last part (or the whole string if no delimiter was found)
    if (partIndex < maxParts) {
        output[partIndex++] = str.substring(start);
    }
}