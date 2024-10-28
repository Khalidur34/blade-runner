#ifndef COMMANDMANAGER_H
#define COMMANDMANAGER_H
#include "Command.h"
#include <Arduino.h>

class CommandManager {
    public:
        void setup();
        String manage(String task);
        String getStatus();
        void splitString(const String &str, char delimiter, String output[], int maxParts);

    private:
        Command command;
        String status;
        
};

#endif