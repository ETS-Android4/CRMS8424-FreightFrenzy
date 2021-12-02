package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "TestServo", group = "Auto")
public class TestAuto extends LinearOpMode {
    Servo pivot;
    ElapsedTime elapsedTime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        pivot = hardwareMap.servo.get("p");
        waitForStart();
        pivot.setPosition(.3);

    }
}
