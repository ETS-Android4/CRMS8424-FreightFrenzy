package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public abstract class MasterClassMechanum extends LinearOpMode {
    AutoMethodsMechanum autoMethods;
    StateMachineMechanum sm = null;
    public void Initialize(){
        autoMethods = new AutoMethodsMechanum();
        sm = new StateMachineMechanum();

        autoMethods.ready(this);
    }
}
