package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.List;

public class StateMachineMechanum extends AutoMethodsMechanum {

    public String state;
    public ElapsedTime stateTime = new ElapsedTime();

    public void ChangeState(String nextState){
        if (state != nextState) {
            stateTime.reset();
            state = nextState;
        }
    }

}