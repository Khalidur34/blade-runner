#ifndef COMMAND_H
#define COMMAND_H
#include <Arduino.h>
#include "MotionManager.h"
//#include "MiscManager.h"

class Command {
    public:
        String execStopc();
        String execStopo();
        String execFSlowc();
        String execFFastc();
        String execRSlowc();
    private:
        MotionManager motionManager;
};

#endif