package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shoulder {
    DcMotor ShoulderDrive;
    int ShoulderPos;
    HardwareMap hardwareMap;

    static final int     SHOULDER_MAX             = 6000;
    static final int     SHOULDER_MIN             = 0;
    static final double   SHOULDER_POWER          =0.85;
    static final int     SHOULDER_MOVE          =200;

    public Shoulder(HardwareMap hardwareMap){

        this.hardwareMap = hardwareMap;
        ShoulderDrive = hardwareMap.get(DcMotor.class,"Shoulder");

        ShoulderDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ShoulderPos = 0;
        ShoulderDrive.setDirection(DcMotor.Direction.REVERSE);
    }
    public void startPosition (){
        ShoulderPos = 0;
        moveShoulder();
    }
    public void drivePosition(){
        ShoulderPos = 180;
        moveShoulder();
    }

    public void extendPosition(){
        ShoulderPos = 180;
        moveShoulder();
    }

    public void scoreLowPosition(){
        ShoulderPos = 2900;
        moveShoulder();
    }

    public void scoreHighPosition(){
        ShoulderPos = 3600;
        moveShoulder();
    }

    public void moveShoulderPosition(float gamepad){

        ShoulderPos = ShoulderDrive.getCurrentPosition() + (int)(-gamepad * SHOULDER_MOVE);
        //ShoulderPos = ShoulderPos + (int)(-gamepad * SHOULDER_MOVE);
        moveShoulder();
    }

    public int getCurrentPosition(){
        return ShoulderDrive.getCurrentPosition();
    }

    private void moveShoulder(){
        if (ShoulderPos > SHOULDER_MAX) {
            ShoulderPos = SHOULDER_MAX;
        }
        if (ShoulderPos < SHOULDER_MIN) {
            ShoulderPos = SHOULDER_MIN;
        }
        ShoulderDrive.setTargetPosition(ShoulderPos);
        ShoulderDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ShoulderDrive.setPower(SHOULDER_POWER);
    }

}