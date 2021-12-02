package org.firstinspires.ftc.teamcode;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.checkerframework.checker.index.qual.LTEqLengthOf;

public class AutoMethods {
    DcMotor l;
    DcMotor r;
    Servo pivot;
    MasterClass masterClass;
    DcMotor spin;
    DcMotor car1;
    DcMotor car2;

    public void ready(MasterClass master) {
        masterClass = master;

        l = masterClass.hardwareMap.dcMotor.get("l");
        r = masterClass.hardwareMap.dcMotor.get("r");
        l.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        r.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        l.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        r.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        l.setDirection(DcMotorSimple.Direction.REVERSE);

        pivot = masterClass.hardwareMap.servo.get("p");

        spin = masterClass.hardwareMap.dcMotor.get("s");
        spin.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spin.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spin.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        car1 = masterClass.hardwareMap.dcMotor.get("c1");
        car2 = masterClass.hardwareMap.dcMotor.get("c2");
        car1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        car2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        car1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        car2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        car1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        car2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void movePivot(double pos){
        pivot.setPosition(pos);
    }
    public void MoveInchEncoder(int ticks, double power){
        l.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        r.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        l.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        r.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        l.setDirection(DcMotorSimple.Direction.REVERSE);
        while (Math.abs(l.getCurrentPosition()) < ticks) {
            l.setPower(power);
            r.setPower(power);
            masterClass.telemetry.addData("l: ", Math.abs(l.getCurrentPosition()));
        }
        l.setPower(0);
        r.setPower(0);
    }
    public void spinCarousel(double power){
        car1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        car2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        car1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        car2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        car1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        car2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while(Math.abs(car2.getCurrentPosition()) < 1000){
            car1.setPower(power);
            car2.setPower(power);
            masterClass.telemetry.addData("car2: ", Math.abs(car2.getCurrentPosition()));
        }
        car1.setPower(0);
        car2.setPower(0);
    }
}
