package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Test", group = "TeleOp")
public class TestTeleOP extends OpMode {
    DcMotor l;
    DcMotor r;
    Servo pivot;
    DcMotor spin;
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
    }

    @Override
    public void loop() {
        if(Math.abs(gamepad1.left_stick_y) > 0.1 || Math.abs(gamepad1.right_stick_x) > 0.1){
            double drive = gamepad1.left_stick_y;
            double turn = gamepad1.left_stick_x;
            if(Math.abs(gamepad1.left_stick_x)>0.1){
                r.setPower(turn);
                l.setPower(turn);
            }
            else if(gamepad1.right_trigger > .1 && Math.abs(gamepad1.right_stick_x)> 0.1){
                r.setPower(turn*.5);
                l.setPower(turn*.5);
            }
            else if(gamepad1.right_trigger > .1 && Math.abs(gamepad1.left_stick_y)> 0.1){
                r.setPower(drive*.5);
                l.setPower(-drive*.5);
            }
            else{
                r.setPower(drive);
                l.setPower(-drive);
            }

        }
        else{
            r.setPower(0);
            l.setPower(0);
        }
        if(gamepad1.a){
            pivot.setPosition(0);
        }
        if(gamepad1.b){
            pivot.setPosition(0.5);
        }
        if(gamepad1.x){
            pivot.setPosition(1);
        }
        if(Math.abs(gamepad1.right_stick_y) > 0.1){
            spin.setPower(gamepad1.right_stick_y);
        }
        else{
            spin.setPower(0);
        }
    }
}
