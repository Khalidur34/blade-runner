#ifndef COMMAND_H
#define COMMAND_H
#include <Arduino.h>
#include "MotionManager.h"
#include "LedControl.h"
#include "PhotoTransistor.h"

class Command {
    public:
        void setup();
        String execStopc();
        String execStopo();
        String execFSlowc();
        String execFFastc();
        String execRSlowc();
    private:
        MotionManager motionManager;
        LedControl ledControl;
        PhotoTransistor photoTransistor;
};

#endif