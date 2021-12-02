package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class MasterClass extends LinearOpMode {
    AutoMethods autoMethods;
    StateMachineMechanum sm = null;
    public void Initialize(){
        autoMethods = new AutoMethods();
        sm = new StateMachineMechanum();

        autoMethods.ready(this);
    }
}
