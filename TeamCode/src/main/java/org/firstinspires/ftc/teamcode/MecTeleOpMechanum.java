
package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="MecTeleOpMechanum", group = "TeleOp")
public class MecTeleOpMechanum extends OpMode {

    //driving motors
    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;


    //Intake
    DcMotor spinIntake;
    public int curTargetSpin = 0;
    public int curTargetSpinOut = 0;
    public boolean inOut;
    ElapsedTime elapsedTime = new ElapsedTime();
    //carousel
    //Servo cSpinner;
    DcMotor carousel;
    public int curTargetSpinCar = 0;
    //public double carouselPosition = 0.1;

    //Outtake
    public int liftHeight = 1;


    @Override
    public void init() {
        // initializing driving motors
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //Initializing Intake
        spinIntake = hardwareMap.dcMotor.get("spin");
        spinIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spinIntake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spinIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //Initializing Carousel
        carousel = hardwareMap.dcMotor.get("c");
        carousel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        carousel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        carousel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //cSpinner =  hardwareMap.servo.get("carousel");
        //cSpinner.setPosition(.1);
    }
    /*
    public void SpinA(boolean tele) {
        if (!tele) {
            while ((Math.abs(spinIntake.getCurrentPosition()) < curTargetSpin)) {
                spinIntake.setPower(-0.75);
            }
            curTargetSpin += 4000;
        }
        else {
            curTargetSpin += 2500;
        }
        spinIntake.setPower(0);
    }

    public void carousel(boolean b){
        if(b){
            if(carouselPosition == 0.1){
                carouselPosition = 0.6;
            }
            else{
                carouselPosition = 0.1;
            }
        }
        else{
            if(carouselPosition == 0.1){
                carouselPosition = -0.6;
            }
            else{
                carouselPosition = -0.1;
            }
        }
        cSpinner.setDirection(Servo.Direction.FORWARD);
        cSpinner.setPosition(carouselPosition);
    }
    */
    public void SpinB(boolean tele) {
        if (!tele) {
            while ((Math.abs(carousel.getCurrentPosition()) < curTargetSpinCar)) {
                carousel.setPower(1);

            }
            curTargetSpinCar += 4000;
        }
        else {
            curTargetSpinCar += 2500;
        }
        carousel.setPower(0);
    }
    public void SpinA(boolean tele) {
        if (!tele) {
            while ((Math.abs(carousel.getCurrentPosition()) < curTargetSpin)) {
                spinIntake.setPower(-.75);
            }
            curTargetSpin += 4000;
        }
        else {
            curTargetSpin += 2500;
        }
        carousel.setPower(0);
    }
    public void SpinX(boolean tele) {
        if (!tele) {
            while ((Math.abs(carousel.getCurrentPosition()) < curTargetSpinOut)) {
                spinIntake.setPower(.75);
            }
            curTargetSpinOut += 4000;
        }
        else {
            curTargetSpinOut += 2500;
        }
        carousel.setPower(0);
    }

    @Override
    public void loop() {
        telemetry.addData("lift level: ", liftHeight);
        if(Math.abs(gamepad1.left_stick_y) > 0.1 || Math.abs(gamepad1.left_stick_x) > 0.1|| Math.abs(gamepad1.right_stick_x) > 0.1){
            telemetry.addLine("Driving");
            double lx = gamepad1.left_stick_x * Math.abs(gamepad1.left_stick_x);
            double ly = gamepad1.left_stick_y * Math.abs(gamepad1.left_stick_y);
            double rx = gamepad1.right_stick_x * Math.abs(gamepad1.right_stick_x);
            double FLP = ly - lx - rx;
            double FRP = -ly - lx - rx;
            double BLP = ly + lx - rx;
            double BRP = -ly + lx - rx;

            double max = Math.max(Math.max(FLP, FRP), Math.max(BLP, BRP));

            if(max > 1) {
                FLP /= max;
                FRP /= max;
                BLP /= max;
                BRP /= max;
            }

            if(gamepad1.right_trigger > 0.1){
                fl.setPower(FLP * 0.175);
                br.setPower(BRP * 0.35);
                bl.setPower(BLP * 0.35);
                fr.setPower(FRP * 0.175);
            }
            else if(gamepad1.left_trigger > 0.1){
                fl.setPower(FLP);
                br.setPower(BRP);
                fr.setPower(FRP);
                bl.setPower(BLP);
            }
            else {
                fl.setPower(FLP*.5);
                br.setPower(BRP);
                fr.setPower(FRP*.75);
                bl.setPower(BLP);
            }

        }
        else{
            fl.setPower(0);
            bl.setPower(0);
            fr.setPower(0);
            br.setPower(0);
        }
        if(gamepad2.right_stick_y > 0.1){
            if(gamepad2.right_trigger > 0.1){
                spinIntake.setPower(0.5);
            }
            else {
                spinIntake.setPower(1);
            }
        }
        else if(gamepad2.right_stick_y < -0.1){
            if(gamepad2.right_trigger > 0.1){
                spinIntake.setPower(-0.5);
            }
            else{
                spinIntake.setPower(-1);
            }
        }
        else if(gamepad2.right_stick_y < 0.1 && gamepad2.right_stick_y > -0.1){
            spinIntake.setPower(0);
        }
        if(gamepad2.left_stick_y > 0.1){
            if(gamepad2.left_trigger > 0.1){
                carousel.setPower(0.5);
            }
            else {
                carousel.setPower(1);
            }
        }
        else if(gamepad2.left_stick_y < -0.1){
            if(gamepad2.left_trigger > 0.1){
                carousel.setPower(-0.5);
            }
            else{
                carousel.setPower(-1);
            }
        }
        else if(gamepad2.left_stick_y < 0.1 && gamepad2.left_stick_y > -0.1){
            carousel.setPower(0);
        }

        /*
        if(gamepad2.a){
            telemetry.addData("in or out", inOut);
            SpinA(false);
        }
        else if(gamepad2.x){
            SpinX(false);
        }
        */
        if(gamepad2.b){
            SpinB(false);
            //telemetry.addLine("If color is true red, else blue");
            //telemetry.addData("carouselSpinner:", cSpinner.getPosition());
            //carousel(color);

            /*
            if(color) {
                if (carouselPosition == 0.1) {
                    carouselPosition = 0.6;
                } else {
                    carouselPosition = 0.1;
                }
            }
            else{
                //carousel(false);
                if(carouselPosition == 0.1){
                    carouselPosition = -0.6;
                }
                else{
                    carouselPosition = -0.1;
                }
            }
            cSpinner.setPosition(carouselPosition);
            */
        }


        if(gamepad2.left_trigger > 0.1){
            if(liftHeight == 1){
                liftHeight = 2;
            }
            else if(liftHeight == 2){
                liftHeight = 3;
            }
            else{
                liftHeight = 1;
            }
            telemetry.addData("lift level: ", liftHeight);
        }
        telemetry.update();
    }
}
