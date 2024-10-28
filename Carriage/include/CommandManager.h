#ifndef COMMANDMANAGER_H
#define COMMANDMANAGER_H
#include "Command.h"
#include <Arduino.h>

class CommandManager {
    public:
        String manage(String task);
        String getStatus();
        String changeStatus();

    private:
        Command command;
        String status;
        
};

#endif