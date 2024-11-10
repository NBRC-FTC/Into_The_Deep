package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Extension {
    DcMotor ExtensionDrive;
    int extensionPos;
    HardwareMap hardwareMap;
    static final int     EXTENSION_MAX             = 1775;
    static final int     EXTENSION_MIN             = 0;
    static final double     EXTENSION_POWER          =0.85;
    static final int     EXTENSION_MOVE          =90;

    public Extension(HardwareMap hardwareMap){

        this.hardwareMap = hardwareMap;
        ExtensionDrive = hardwareMap.get(DcMotor.class,"Extension");

        ExtensionDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extensionPos = 0;
        ExtensionDrive.setDirection(DcMotor.Direction.REVERSE);
    }
    public void startPosition (){
        extensionPos = 0;
        moveExtension();
    }
    public void drivePosition(){
        extensionPos = 180;
        moveExtension();
    }

    public void extendPosition(){
        extensionPos = 180;
        moveExtension();
    }

    public void scoreLowPosition(){
        extensionPos = 180;
        moveExtension();
    }

    public void scoreHighPosition(){
        extensionPos = 180;
        moveExtension();
    }
    public void moveExtensionOut(){
        extensionPos = extensionPos + 10;
        moveExtension();
    }
    public void moveExtensionIn(){
        extensionPos = extensionPos - 10;
        moveExtension();
    }

    public void moveExtensionPosition(double gamepad){

        extensionPos = ExtensionDrive.getCurrentPosition() + (int)(-gamepad * EXTENSION_MOVE );
        moveExtension();
    }

    public int getCurrentPosition(){return ExtensionDrive.getCurrentPosition();
    }

    private void moveExtension(){
        if (extensionPos > EXTENSION_MAX) {
            extensionPos = EXTENSION_MAX;
        }
        if (extensionPos < EXTENSION_MIN) {
            extensionPos = EXTENSION_MIN;
        }
        ExtensionDrive.setTargetPosition(extensionPos);
        ExtensionDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ExtensionDrive.setPower(EXTENSION_POWER);
    }

}