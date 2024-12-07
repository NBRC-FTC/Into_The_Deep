package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class SpeciminWheel {
    HardwareMap hardwareMap;
    CRServo SpeciminWheelServo;
    private ElapsedTime runtime = new ElapsedTime();

    public SpeciminWheel(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        SpeciminWheelServo = hardwareMap.crservo.get("SpeciminWheel");
    }

    public void collectSpecimin() {
        SpeciminWheelServo.setPower(-1);
    }

    public void releaseSpecimin() {
        SpeciminWheelServo.setPower(1);
    }

    public void holdSpecimin() {
        SpeciminWheelServo.setPower(0);
    }

    public void autoReleaseSpecimen() {
        runtime.reset();
        while(runtime.milliseconds() < 5000) {//can be shortened if short on time
            releaseSpecimin();
        }
        holdSpecimin();
    }
}
