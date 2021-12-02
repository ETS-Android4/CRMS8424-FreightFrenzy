package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red", group = "Auto")
public class RedAuto extends MasterClass{
    @Override
    public void runOpMode() throws InterruptedException {
        Initialize();
        waitForStart();
        autoMethods.MoveInchEncoder(1000,-.4);
        autoMethods.movePivot(0.5);
        autoMethods.spinCarousel(1);
        autoMethods.MoveInchEncoder(2000,.4);
    }
}
