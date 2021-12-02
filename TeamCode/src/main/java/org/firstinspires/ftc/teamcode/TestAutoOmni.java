package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "TestAutoOmni", group = "Auto")
public class TestAutoOmni extends MasterClass{

    @Override
    public void runOpMode() throws InterruptedException {
        Initialize();
        waitForStart();
        telemetry.addData("pivot", autoMethods.pivot.getPosition());
        autoMethods.movePivot(10);
        telemetry.addData("pivot", autoMethods.pivot.getPosition());

    }
}
