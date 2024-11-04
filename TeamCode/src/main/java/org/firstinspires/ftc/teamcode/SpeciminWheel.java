package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;

public class SpeciminWheel {
    HardwareMap hardwareMap;
    CRServo SpeciminWheelServo;

    public SpeciminWheel(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        SpeciminWheelServo = hardwareMap.crservo.get("grabservo");
    }

    public void CollectSpecimin() {
        SpeciminWheelServo.setPower(1);
    }

    public void ReleaseSpecimin() {
        SpeciminWheelServo.setPower(-1);
    }

    public void HoldSpecimin() {
        SpeciminWheelServo.setPower(0);
    }

}
