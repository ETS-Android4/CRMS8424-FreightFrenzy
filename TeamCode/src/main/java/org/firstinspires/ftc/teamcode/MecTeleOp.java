package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="MecTeleOpOmni", group = "TeleOp")
public class MecTeleOp extends OpMode {
    DcMotor l;
    DcMotor r;
    DcMotor armLift;
    DcMotor spin;
    DcMotor car;

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
        armLift = hardwareMap.dcMotor.get("al");
        armLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        spin = hardwareMap.dcMotor.get("s");
        spin.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spin.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spin.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        car = hardwareMap.dcMotor.get("c");
        car.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        car.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        car.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {
        if(Math.abs(gamepad1.left_stick_y) > 0.1 || Math.abs(gamepad1.right_stick_x) > 0.1){
            double drive = gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            if(Math.abs(gamepad1.right_stick_x)>0.1){
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
        if(gamepad2.right_stick_y > 0.1){
            if(gamepad2.right_trigger > 0.1){
                armLift.setPower(0.5);
            }
            else{
                armLift.setPower(1);
            }
        }
        if(gamepad2.left_stick_y > 0.1){
            if(gamepad2.right_trigger > 0.1){
                spin.setPower(0.5);
            }
            else{
                spin.setPower(1);
            }
        }
        if(gamepad2.left_trigger > 0.1){
            car.setPower(1);
        }
    }
}
