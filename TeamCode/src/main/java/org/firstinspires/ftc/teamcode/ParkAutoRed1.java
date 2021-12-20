package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(group = "Auto", name = "ParkAutoRed1")
public class ParkAutoRed1 extends LinearOpMode {
    DcMotor l;
    DcMotor r;
    @Override
    public void runOpMode() throws InterruptedException {
        l = hardwareMap.dcMotor.get("l");
        r = hardwareMap.dcMotor.get("r");
        l.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        r.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        l.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        r.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        l.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while(Math.abs(l.getCurrentPosition()) < 2000){
            l.setPower(.4);
            r.setPower(.3);
        }
        l.setPower(0);
        r.setPower(0);
    }
}
