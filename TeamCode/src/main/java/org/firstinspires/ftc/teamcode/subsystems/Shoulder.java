package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shoulder {
    DcMotor ShoulderDrive;
    int ShoulderPos;
    HardwareMap hardwareMap;
    boolean ignoreMinClass = false;

    static final int     SHOULDER_MAX             = 3850; //old value 6000;
    static final int     SHOULDER_MIN             = 0;
    static final double   SHOULDER_POWER          =0.85;   //was 0.85 before
    static final int     SHOULDER_MOVE_SLOW         = 12;
    static final int     SHOULDER_MOVE_FAST     = 30;
    static final int     SHOULDER_MOVE_SLOW_HIGH         = 3250;
    static final int     SHOULDER_MOVE_SLOW_LOW   = 1400;
    static final int     SHOULDER_DRIVE_POS        = 900;
    static final int     SHOULDER_PICKUP_POS        =550;
    public Shoulder(HardwareMap hardwareMap){

        this.hardwareMap = hardwareMap;
        ShoulderDrive = hardwareMap.get(DcMotor.class,"Shoulder");

        //ShoulderDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ShoulderDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        ShoulderPos = 0;
        ShoulderDrive.setDirection(DcMotor.Direction.REVERSE);
    }
    public void startPosition (){
        ShoulderPos = 0;
        moveShoulder();
    }
    public void drivePosition(){
        ShoulderPos = SHOULDER_DRIVE_POS;
        moveShoulder();
    }

    public void pickupPosition(){
        ShoulderPos = SHOULDER_PICKUP_POS;
        moveShoulder();
    }

    public void scoreLowPosition(){
        ShoulderPos = 2900;
        moveShoulder();
    }

    public void scoreHighPosition(){
        ShoulderPos = 3650;
        moveShoulder();
    }

    public void moveShoulderPosition(float gamepad){

        //ShoulderPos = ShoulderDrive.getCurrentPosition() + (int)(-gamepad * SHOULDER_MOVE);
        if (ShoulderPos < SHOULDER_MOVE_SLOW_LOW || ShoulderPos > SHOULDER_MOVE_SLOW_HIGH) {
            ShoulderPos = ShoulderPos + (int)(-gamepad * SHOULDER_MOVE_SLOW);
        //else if () {
            //ShoulderPos = ShoulderPos + (int) (-gamepad * SHOULDER_MOVE_SLOW);
        } else {
            ShoulderPos = ShoulderPos + (int)(-gamepad * SHOULDER_MOVE_FAST);
        }

        moveShoulder();
    }
    public void moveShoulderTo(int position) {
        ShoulderPos = position;
        moveShoulder();
    }
    public int getCurrentPosition(){
        return ShoulderDrive.getCurrentPosition();
    }

    public void zeroEncoder() {
        ShoulderDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    public void setIgnoreMin(boolean ignoreMin) {
        ignoreMinClass = ignoreMin;
    }
    private void moveShoulder(){
        if (ShoulderPos > SHOULDER_MAX) {
            ShoulderPos = SHOULDER_MAX;
        }
        if (ShoulderPos < SHOULDER_MIN && !ignoreMinClass) {
            ShoulderPos = SHOULDER_MIN;
        }
        ShoulderDrive.setTargetPosition(ShoulderPos);
        ShoulderDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ShoulderDrive.setPower(SHOULDER_POWER);
    }

}