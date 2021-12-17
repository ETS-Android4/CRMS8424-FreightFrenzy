package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(group = "TeleOp", name = "TestForTest")
public class TestTestTeleOP extends OpMode{
    Servo pivot1;
    Servo pivot2;
    DcMotor l;
    DcMotor r;
    DcMotor spin;
    DcMotor car1;
    DcMotor car2;
    @Override
    public void init() {
        l = hardwareMap.dcMotor.get("l");
        r = hardwareMap.dcMotor.get("r");
        l.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        r.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        l.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        r.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        spin = hardwareMap.dcMotor.get("s");
        spin.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spin.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spin.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        car1 = hardwareMap.dcMotor.get("c1");
        car1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        car1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        car1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        car2 = hardwareMap.dcMotor.get("c2");
        car2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        car2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        car2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pivot1 = hardwareMap.servo.get("p1");
        pivot1.setPosition(0.5);
        pivot2 = hardwareMap.servo.get("p2");
        pivot1.setPosition(0.5);
    }

    @Override
    public void loop() {
        if(Math.abs(gamepad1.left_stick_y) > 0.1 || Math.abs(gamepad1.right_stick_x) > 0.1){
            double drive = gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double turnDrive = gamepad1.left_stick_y;
            if(Math.abs(gamepad1.right_stick_x)>0.1){
                r.setPower(-turn*.5);
                l.setPower(-turn*.5);
            }
            else if(Math.abs(gamepad1.right_stick_y) > 0.1){
                r.setPower(turnDrive*.5);
                l.setPower(-turnDrive*.5);
            }
            else if(gamepad1.right_trigger > .1 && Math.abs(gamepad1.right_stick_x)> 0.1){
                r.setPower(-turn*.25);
                l.setPower(-turn*.25);
            }

            else if(gamepad1.right_trigger > .1 && Math.abs(gamepad1.left_stick_y)> 0.1){
                r.setPower(drive*.375);
                l.setPower(-drive*.375);
            }
            else{
                r.setPower(drive*.75);
                l.setPower(-drive*.75);
            }

        }
        else{
            r.setPower(0);
            l.setPower(0);
        }
        if(Math.abs(gamepad2.right_stick_y) > 0.1){
            spin.setPower(gamepad2.right_stick_y);
        }
        else{
            spin.setPower(0);
        }

        if(gamepad2.left_trigger > 0.1){
            car1.setPower(1);
            car2.setPower(1);
        }
        else{
            car1.setPower(0);
            car2.setPower(0);
        }
        if(gamepad2.right_trigger > 0.1){
            car1.setPower(-1);
            car2.setPower(-1);
        }
        else{
            car1.setPower(0);
            car2.setPower(0);
        }
        if(gamepad2.left_stick_y > 0.1){
            double pivotPos = pivot1.getPosition();
            double pivotPos1 = pivot2.getPosition();
            if(pivotPos < 1) {
                pivot1.setPosition(pivotPos - 0.001);
                pivot2.setPosition(pivotPos1 - 0.001);
            }
        }
        else if(gamepad2.left_stick_y < -0.1){
            double pivotPos = pivot1.getPosition();
            double pivotPos1 = pivot2.getPosition();
            if(pivotPos > 0) {
                pivot1.setPosition(pivotPos + 0.001);
                pivot2.setPosition(pivotPos1 + 0.001);
            }
        }
    }
}
