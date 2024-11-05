package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;

public class SpeciminWheel {
    HardwareMap hardwareMap;
    CRServo SpeciminWheelServo;

    public SpeciminWheel(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        SpeciminWheelServo = hardwareMap.crservo.get("SpeciminWheel");
    }

    public void collectSpecimin() {
        SpeciminWheelServo.setPower(1);
    }

    public void releaseSpecimin() {
        SpeciminWheelServo.setPower(-1);
    }

    public void holdSpecimin() {
        SpeciminWheelServo.setPower(0);
    }

}
