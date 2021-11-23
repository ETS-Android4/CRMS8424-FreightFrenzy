package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

public class TestAutoMechanum extends MasterClassMechanum{
    @Override
    public void runOpMode() throws InterruptedException {
        Initialize();
        waitForStart();
        autoMethods.br.setPower(.45);
        autoMethods.fr.setPower(-.9);
        autoMethods.bl.setPower(-.54);
        autoMethods.fl.setPower(.9);
        sleep(2000);
        autoMethods.bl.setPower(0);
        autoMethods.br.setPower(0);
        autoMethods.fl.setPower(0);
        autoMethods.fr.setPower(0);
    }
}

