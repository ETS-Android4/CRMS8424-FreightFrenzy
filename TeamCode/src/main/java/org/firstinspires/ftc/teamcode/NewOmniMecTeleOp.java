package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(group = "NewMecTeleOp")
public class NewOmniMecTeleOp extends OpMode {
    DcMotor l;
    DcMotor r;
    DcMotor spin;
    DcMotor car1;
    DcMotor car2;
    Servo pivot1;
    Servo pivot2;
    Double pick = 0.1;
    Double low = 0.2;
    Double mid = 0.3;
    Double high = 0.4;
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
        pivot2 = hardwareMap.servo.get("p2");
        pivot1.setPosition(0.1);
        pivot2.setPosition(0.1);
    }
    public void pivotUp(){
        if(pivot1.getPosition() == pick){
            pivot1.setPosition(low);
            pivot2.setPosition(low);
        }
        else if(pivot1.getPosition() == low){
            pivot1.setPosition(mid);
            pivot2.setPosition(mid);
        }
        else {
            pivot1.setPosition(high);
            pivot2.setPosition(high);
        }
    }
    public void stopPivot(){
        pivot1.setPosition(pivot1.getPosition());
        pivot2.setPosition(pivot2.getPosition());
    }
    public void pivotDown(){
        if(pivot1.getPosition() == high){
            pivot1.setPosition(mid);
            pivot2.setPosition(mid);
        }
        else if(pivot1.getPosition() == mid){
            pivot1.setPosition(low);
            pivot2.setPosition(low);
        }
        else {
            pivot1.setPosition(pick);
            pivot2.setPosition(pick);
        }
    }
    public void spinIntake(double power){
        spin.setPower(power);
    }
    public void stopIntake(){
        spin.setPower(0);
    }
    public void carousel(boolean color){
        if(color){
            car1.setPower(1);
            car2.setPower(1);
        }
        else {
            car1.setPower(-1);
            car2.setPower(-1);
        }
    }
    public void stopCarousel(){
        car1.setPower(0);
        car2.setPower(0);
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
                r.setPower(-drive*.375);
                l.setPower(drive*.375);
            }
            else{
                r.setPower(-drive*.75);
                l.setPower(drive*.75);
            }

        }
        else{
            r.setPower(0);
            l.setPower(0);
        }
        if(gamepad2.left_stick_y > 0.1){
            if(pivot1.getPosition() == pick){
                pivot1.setPosition(low);
                pivot2.setPosition(low);
            }
            else if(pivot1.getPosition() == low){
                pivot1.setPosition(mid);
                pivot2.setPosition(mid);
            }
            else {
                pivot1.setPosition(high);
                pivot2.setPosition(high);
            }
            //pivotUp();
        }
        else{
            pivot1.setPosition(pivot1.getPosition());
            pivot2.setPosition(pivot2.getPosition());
            //stopPivot();
        }
        if(gamepad2.left_stick_y < -0.1){
            if(pivot1.getPosition() == high){
                pivot1.setPosition(mid);
                pivot2.setPosition(mid);
            }
            else if(pivot1.getPosition() == mid){
                pivot1.setPosition(low);
                pivot2.setPosition(low);
            }
            else {
                pivot1.setPosition(pick);
                pivot2.setPosition(pick);
            }
            //pivotDown();
        }
        else{
            pivot1.setPosition(pivot1.getPosition());
            pivot2.setPosition(pivot2.getPosition());
            //stopPivot();
        }
        if(Math.abs(gamepad2.right_stick_y) > 0.1){
            spin.setPower(gamepad2.right_stick_y);
            //spinIntake(gamepad2.right_stick_y);
        }
        else{
            spin.setPower(0);
            //stopIntake();
        }

        if(gamepad2.left_trigger > 0.1){
            car1.setPower(1);
            car2.setPower(1);
            //carousel(true);
        }
        else{
            car1.setPower(0);
            car2.setPower(0);
            //stopCarousel();
        }
        if(gamepad2.right_trigger > 0.1){
            car1.setPower(-1);
            car2.setPower(-1);
            //carousel(false);
        }
        else{
            car1.setPower(0);
            car2.setPower(0);
            //stopCarousel();
        }
    }
}
