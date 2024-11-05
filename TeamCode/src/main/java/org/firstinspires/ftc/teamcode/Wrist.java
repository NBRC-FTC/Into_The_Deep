package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Wrist {

    HardwareMap hardwareMap;
    Servo grabServo;

    static final double     CLAW_OPEN    = 0.75;
    static final double     CLAW_CLOSED    = 0.60;

    public Wrist(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        wristServo = hardwareMap.get(Servo.class, "wristServo");
    }

    public void PickUpSample(){
        //claw0.setPosition(CLAW_CLOSED);
        wristServo.setPosition(CLAW_CLOSED);
    }

    public void RealeseSample(){
        wristServo.setPosition(CLAW_OPEN);
        //claw1.setPosition(CLAW_OPEN);
    }
}
