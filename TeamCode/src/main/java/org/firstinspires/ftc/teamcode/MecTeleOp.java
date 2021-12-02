package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="MecTeleOpOmni", group = "TeleOp")
public class MecTeleOp extends OpMode {
    DcMotor l;
    DcMotor r;
    Servo pivot;
    DcMotor spin;
    DcMotor car1;
    DcMotor car2;
    ElapsedTime pivotPos = new ElapsedTime();
    public int lH;
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
        pivot = hardwareMap.servo.get("p");
        spin = hardwareMap.dcMotor.get("s");
        spin.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spin.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spin.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        car1 = hardwareMap.dcMotor.get("c1");
        car2 = hardwareMap.dcMotor.get("c2");
        car1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        car2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        car1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        car2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        car1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        car2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void PivotA(int h){
        pivotPos.reset();
        if(h == 2){
            while(pivotPos.milliseconds() <= 0) {
                pivot.setPosition(0);
                if(pivotPos.milliseconds() == .5){
                    pivot.setPosition(0.5);
                }
            }
        }
        else if(h == 3){
            while(pivotPos.milliseconds() <= 0) {
                pivot.setPosition(0);
                if(pivotPos.milliseconds() == 1){
                    pivot.setPosition(0.5);
                }
            }
        }
        else if(h == 4){
            while(pivotPos.milliseconds() <= 0) {
                pivot.setPosition(0);
                if(pivotPos.milliseconds() == 1.5){
                    pivot.setPosition(0.5);
                }
            }
        }
    }
    public void PivotB(int h){
        pivotPos.reset();
        if(h == 2){
            while(pivotPos.milliseconds() <= 1) {
                pivot.setPosition(1);
                if(pivotPos.milliseconds() == .5){
                    pivot.setPosition(0.5);
                }
            }
        }
        else if(h == 4){
            while(pivotPos.milliseconds() <= 1) {
                pivot.setPosition(0);
                if(pivotPos.milliseconds() == .5){
                    pivot.setPosition(0.5);
                }
            }
        }
        else if(h == 1){
            while(pivotPos.milliseconds() <= 1) {
                pivot.setPosition(1);
                if(pivotPos.milliseconds() == 1){
                    pivot.setPosition(0.5);
                }
            }
        }
    }

    @Override
    public void loop() {
        if(Math.abs(gamepad1.left_stick_y) > 0.1 || Math.abs(gamepad1.right_stick_x) > 0.1){
            double drive = gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double turnDrive = gamepad1.left_stick_y;
            if(Math.abs(gamepad1.right_stick_x)>0.1){
                r.setPower(turn);
                l.setPower(turn);
            }
            else if(Math.abs(gamepad1.right_stick_y) > 0.1){
                r.setPower(turnDrive);
                l.setPower(-turnDrive);
            }
            else if(gamepad1.right_trigger > .1 && Math.abs(gamepad1.right_stick_x)> 0.1){
                r.setPower(turn*.5);
                l.setPower(turn*.5);
            }

            else if(gamepad1.right_trigger > .1 && Math.abs(gamepad1.left_stick_y)> 0.1){
                r.setPower(drive*.25);
                l.setPower(-drive*.25);
            }
            else{
                r.setPower(drive*.5);
                l.setPower(-drive*.5);
            }

        }
        else{
            r.setPower(0);
            l.setPower(0);
        }
        if(gamepad2.y) {

            pivotPos.reset();
            if(lH == 3){
                while(pivotPos.milliseconds() <= 1) {
                    pivot.setPosition(1);
                    if(pivotPos.milliseconds() == .5){
                        pivot.setPosition(0.5);
                    }
                }
            }
            else if(lH == 2){
                while(pivotPos.milliseconds() <= 1) {
                    pivot.setPosition(1);
                    if(pivotPos.milliseconds() == 1){
                        pivot.setPosition(0.5);
                    }
                }
            }
            else if(lH == 1){
                while(pivotPos.milliseconds() <= 2) {
                    pivot.setPosition(1);
                    if(pivotPos.milliseconds() == 1.5){
                        pivot.setPosition(0.5);
                    }
                }
            }

            lH=4;
        }
        if(gamepad2.a){

            pivotPos.reset();
            if(lH == 2){
                while(pivotPos.milliseconds() <= 0) {
                    pivot.setPosition(0);
                    if(pivotPos.milliseconds() == .5){
                        pivot.setPosition(0.5);
                    }
                }
            }
            else if(lH == 3){
                while(pivotPos.milliseconds() <= 0) {
                    pivot.setPosition(0);
                    if(pivotPos.milliseconds() == 1){
                        pivot.setPosition(0.5);
                    }
                }
            }
            else if(lH == 4){
                while(pivotPos.milliseconds() <= 0) {
                    pivot.setPosition(0);
                    if(pivotPos.milliseconds() == 1.5){
                        pivot.setPosition(0.5);
                    }
                }
            }
            lH=1;
        }
        else if(gamepad2.b){

            pivotPos.reset();
            if(lH == 2){
                while(pivotPos.milliseconds() <= 1) {
                    pivot.setPosition(1);
                    if(pivotPos.milliseconds() == .5){
                        pivot.setPosition(0.5);
                    }
                }
            }
            else if(lH == 4){
                while(pivotPos.milliseconds() <= 1) {
                    pivot.setPosition(0);
                    if(pivotPos.milliseconds() == .5){
                        pivot.setPosition(0.5);
                    }
                }
            }
            else if(lH == 1){
                while(pivotPos.milliseconds() <= 1) {
                    pivot.setPosition(1);
                    if(pivotPos.milliseconds() == 1){
                        pivot.setPosition(0.5);
                    }
                }
            }
            lH=3;
        }

        else if(gamepad2.x){
            pivotPos.reset();
            if(lH == 3){
                while(pivotPos.milliseconds() <= 1) {
                    pivot.setPosition(0);
                    if(pivotPos.milliseconds() == .5){
                        pivot.setPosition(0.5);
                    }
                }
            }
            else if(lH == 1){
                while(pivotPos.milliseconds() <= 1) {
                    pivot.setPosition(1);
                    if(pivotPos.milliseconds() == .5){
                        pivot.setPosition(0.5);
                    }
                }
            }
            else if(lH == 4){
                while(pivotPos.milliseconds() <= 2) {
                    pivot.setPosition(0);
                    if(pivotPos.milliseconds() == 1){
                        pivot.setPosition(0.5);
                    }
                }
            }
            lH=2;
        }

        if(Math.abs(gamepad2.right_stick_y) > 0.1){
            spin.setPower(1);
        }
        else{
            spin.setPower(0);
        }
        if(Math.abs(gamepad2.left_stick_y) > 0.1){
            car1.setPower(1);
            car2.setPower(1);
        }
        else{
            car1.setPower(0);
            car2.setPower(0);
        }
    }
}
