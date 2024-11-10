package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Wrist {

    HardwareMap hardwareMap;
    Servo wristServo;

    static final double     WRIST_MAX    = 0.75;
    static final double     WRIST_MIN    = 0.0;
    static final double     WRIST_PICKUP    = 0.2;
    static final double     WRIST_CHANGE    = 0.005;

    public Wrist(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        wristServo = hardwareMap.get(Servo.class, "wristServo");
    }

    public void moveToMin(){
        wristServo.setPosition(WRIST_MIN);
    }

    public void moveToMax(){
        wristServo.setPosition(WRIST_MAX);
    }

    public void  moveToPickUpPosition(){
        wristServo.setPosition(WRIST_PICKUP);
    }

    public void moveUp(){
        moveWrist(wristServo.getPosition() + WRIST_CHANGE);
    }

    public void moveDown(){
        moveWrist(wristServo.getPosition() - WRIST_CHANGE);
    }

    public double getCurrentPosition(){
        return wristServo.getPosition();
    }
    private void moveWrist(double wristPos){
        if (wristPos > WRIST_MAX) {
            wristPos = WRIST_MAX;
        }
        if (wristPos < WRIST_MIN) {
            wristPos = WRIST_MIN;
        }

        wristServo.setPosition(wristPos);

    }
}
