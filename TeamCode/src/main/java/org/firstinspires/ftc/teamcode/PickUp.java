package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class PickUp {

    HardwareMap hardwareMap;
    Servo grabServo;

    static final double     CLAW_OPEN    = 0.75;
    static final double     CLAW_CLOSED    = 0.60;

    public PickUp (HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        grabServo = hardwareMap.get(Servo.class, "grabServo");
    }

    public void PickUpSample(){
        //claw0.setPosition(CLAW_CLOSED);
        grabServo.setPosition(CLAW_CLOSED);
    }

    public void RealeseSample(){
        grabServo.setPosition(CLAW_OPEN);
        //claw1.setPosition(CLAW_OPEN);
    }
}
