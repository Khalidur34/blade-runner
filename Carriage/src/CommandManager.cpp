#include "CommandManager.h"
#include <string>
#include <vector>
#include <sstream>

//this class is for filtering messages into the correct command handlers
const int MAX_PARTS = 4;

String CommandManager::manage(String message) {
    if(message.isEmpty())
        return "ERR";

    String msgParts[MAX_PARTS];
    int numParts = splitString(message, ':', msgParts, MAX_PARTS);

    if(msgParts[0] == "STRQ") {
        return "STAT:" + getStatus();
    }
    else if(msgParts[0] = "EXEC") {
        // ALREADY IN STATE
        if(msgParts[1] == getStatus()){
            return "ERR";
        }
        else if(msgParts[1] == "STOPC") { //BR stopped and the door is closed
            status = "AK:STOPC";
            return command.execStopc();
        }
        else if(msgParts[1] == "STOPO") { //BR stopped and the door is opened
            status = "AK:STOPO";
            return command.execStopo();
        }
        else if(msgParts[1] == "FSLOWC") { //BR moving in forward direction slowly and the door is closed.
            status = "AK:FSLOWC";
            return command.execFSlowc();
        }
        else if(msgParts[1] == "FFASTC") { //BR moving in forward direction at fast speed and the door is closed
            status = "AK:FFASTC";
            return command.execFFastc();
        }
        else if(msgParts[1] == "RSLOWC") { //BR moving slowly in reverse direction and the door is closed.
            status = "AK:RSLOWC"; 
            return command.execRSlowc();
        }
    }
   
    return "ERR";
    
}
String CommandManager::getStatus() {
    return status;
}


int splitString(const String &str, char delimiter, String output[], int maxParts) {
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

    return partIndex; // Return the number of parts split
}