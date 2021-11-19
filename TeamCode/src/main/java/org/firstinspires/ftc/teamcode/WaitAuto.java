package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "waitAuto", group = "Auto")
public class WaitAuto extends  MasterClassMechanum{
    @Override
    public void runOpMode() throws InterruptedException {
        Initialize();

        waitForStart();

        while (!isStopRequested() && opModeIsActive()){
            sleep(30000);
        }
    }
}
