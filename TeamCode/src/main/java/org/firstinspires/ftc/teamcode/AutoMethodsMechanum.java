package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class AutoMethodsMechanum {
    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    //Servo cSpinner;
    DcMotor carousel;
    DcMotor spinner;
    public int curTargetSpin = 4000;
    public int curTargetSpinCar = 4000;
    MasterClassMechanum masterClass = null;
    MecTeleOpMechanum mecTeleOp = null;
    public ElapsedTime time = new ElapsedTime();
    Orientation angles;
    public BNO055IMU imu;
    double curDiff = 0;
    ElapsedTime turnTime = new ElapsedTime();

    public void MoveInch(double speed){
        fl.setPower(speed);
        bl.setPower(speed);
        fr.setPower(-speed);
        br.setPower(-speed);
    }

    /*
    public void spinCarousel(double position){
        cSpinner.setPosition(position);
    }
    */
    public void spinOut(boolean tele, double power){
        if (!tele) {
            while ((Math.abs(spinner.getCurrentPosition()) < curTargetSpin)) {
                spinner.setPower(power);
            }
        }
        carousel.setPower(0);
    }
    public void carousel(boolean tele, double power){
        if (!tele) {
            while ((Math.abs(carousel.getCurrentPosition()) < curTargetSpinCar)) {
                carousel.setPower(power);
            }
        }
        //carousel.setPower(power);
    }
    public void MoveInchEncoder(double speed, double ticks) {
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double ticksDis = ticks;
        while (Math.abs(bl.getCurrentPosition()) < ticks) {

            //  ticksDis -= bl.getCurrentPosition();
            //  speed = 1 - (1 / Math.sqrt(ticksDis));
            if (speed > 1) {
                speed = 1;
            }
            bl.setPower(speed);
            fl.setPower(speed);
            br.setPower(speed);
            fr.setPower(speed);
            masterClass.telemetry.addData("bl", bl.getCurrentPosition());
            masterClass.telemetry.update();


        }
        bl.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        fr.setPower(0);

    }
    public void MoveInchEncoderSpeedy(double speed, double ticks) {
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double ticksDis = ticks;
        while (Math.abs(bl.getCurrentPosition()) < ticks) {

            //  ticksDis -= bl.getCurrentPosition();
            //  speed = 1 - (1 / Math.sqrt(ticksDis));
            if (speed > 1) {
                speed = 1;
            }
            fr.setPower(-speed*0.7);
            bl.setPower(speed);
            fl.setPower(speed*0.7);
            br.setPower(-speed);
            masterClass.telemetry.addData("bl", bl.getCurrentPosition());
            masterClass.telemetry.update();


        }
        bl.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        fr.setPower(0);

    }

    public void GyroStable(double speed, double ticks) {
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double ticksDis = ticks;
    }

    public void StrafeRight(double speed,double ticks ){
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while(Math.abs(bl.getCurrentPosition()) < ticks){

            //ticksDis -= bl.getCurrentPosition();
            //speed = 1-(1/Math.sqrt(ticksDis));
            if(speed > 1){
                speed = 1;
            }
            bl.setPower(-speed);
            fl.setPower((speed)*0.7);
            br.setPower(speed);
            fr.setPower((-speed)*0.7);
            //masterClass.telemetry.addData("bl", bl.getCurrentPosition());
        }
        bl.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        fr.setPower(0);
    }

    public void ready(MasterClassMechanum master){
        masterClass = master;

        fl = masterClass.hardwareMap.dcMotor.get("fl");
        fr = masterClass.hardwareMap.dcMotor.get("fr");
        bl = masterClass.hardwareMap.dcMotor.get("bl");
        br = masterClass.hardwareMap.dcMotor.get("br");

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
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
        fl.setDirection(DcMotorSimple.Direction.REVERSE);


        //cSpinner =  masterClass.hardwareMap.servo.get("carousel");
        //cSpinner.setPosition(.1);
        carousel = masterClass.hardwareMap.dcMotor.get("c");
        carousel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        carousel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        carousel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        spinner = masterClass.hardwareMap.dcMotor.get("spin");
        spinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spinner.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spinner.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";

        imu = masterClass.hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

    }

    public double getGyroYaw() {
        updateGyroValues();
        return angles.firstAngle;
    }

    public void updateGyroValues() {
        angles = imu.getAngularOrientation();
    }

    public void turnPD(int tarDegree, double speed)
    {
        double startDiff = tarDegree - getGyroYaw();
        double p = 0;
        curDiff = startDiff;
        double plusMin = 0;
        while(Math.abs(curDiff) > 1)
        {
            curDiff = tarDegree - getGyroYaw();
            double trueDiff = tarDegree - getGyroYaw();
            int calc = Math.abs((int)(tarDegree - curDiff));
            p = Math.abs(curDiff)/Math.abs(startDiff);
            p = p * Math.signum(trueDiff);
            plusMin = .1 * Math.signum(trueDiff);
            bl.setPower(speed * p + plusMin);
            fl.setPower(speed * p + plusMin);
            br.setPower(speed * p + plusMin);
            fr.setPower(speed * p + plusMin);
            masterClass.telemetry.addData("getGyrowyaw", getGyroYaw());
            masterClass.telemetry.addData("curdiff", curDiff);
            masterClass.telemetry.addData("startd", startDiff);
            masterClass.telemetry.update();
        }
        bl.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        fr.setPower(0);
    }
    public void turnPDSpeedy(int tarDegree, double speed)
    {
        double startDiff = tarDegree - getGyroYaw();
        double p = 0;
        curDiff = startDiff;
        double plusMin = 0;
        while(Math.abs(curDiff) > 1)
        {
            curDiff = tarDegree - getGyroYaw();
            double trueDiff = tarDegree - getGyroYaw();
            int calc = Math.abs((int)(tarDegree - curDiff));
            p = Math.abs(curDiff)/Math.abs(startDiff);
            p = p * Math.signum(trueDiff);
            plusMin = .1 * Math.signum(trueDiff);
            bl.setPower((speed * p + plusMin)*0.5);
            fl.setPower(speed * p + plusMin);
            br.setPower((speed * p + plusMin)*0.5);
            fr.setPower(speed * p + plusMin);
            masterClass.telemetry.addData("getGyrowyaw", getGyroYaw());
            masterClass.telemetry.addData("curdiff", curDiff);
            masterClass.telemetry.addData("startd", startDiff);
            masterClass.telemetry.update();
        }
        bl.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        fr.setPower(0);
    }

    public void turnPDT(int tarDegree, double speed, double timeout)
    {
        double startDiff = tarDegree - getGyroYaw();
        double p = 0;
        curDiff = startDiff;
        double plusMin = 0;
        turnTime.reset();
        while(Math.abs(curDiff) > 1 && turnTime.milliseconds() < timeout)
        {
            curDiff = tarDegree - getGyroYaw();
            double trueDiff = tarDegree - getGyroYaw();
            int calc = Math.abs((int)(tarDegree - curDiff));
            p = Math.abs(curDiff)/Math.abs(startDiff);
            p = p * Math.signum(trueDiff);
            plusMin = .1 * Math.signum(trueDiff);
            bl.setPower(speed * p + plusMin);
            fl.setPower(speed * p + plusMin);
            br.setPower(speed * p + plusMin);
            fr.setPower(speed * p + plusMin);
            masterClass.telemetry.addData("getGyrowyaw", getGyroYaw());
            masterClass.telemetry.addData("curdiff", curDiff);
            masterClass.telemetry.addData("startd", startDiff);
            masterClass.telemetry.update();
        }
        bl.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        fr.setPower(0);
    }

    public void Strafe(double speed, double orientation, double val){// positive speed is right
        double gyro = (getGyroYaw() - orientation) * val;
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // positive is right

        if(speed > 1){
            speed = 1;
        }
        bl.setPower(speed);
        fl.setPower(-speed - gyro);
        br.setPower(speed);
        fr.setPower(-speed - gyro);

    }




}
