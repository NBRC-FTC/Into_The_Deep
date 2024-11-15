package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Extension {
    DcMotor ExtensionDrive;
    int extensionPos;
    HardwareMap hardwareMap;
    static final int EXTENSION_MAX = 1775;
    static final int EXTENSION_MIN = 0;
    static final double EXTENSION_POWER = 0.60;
    static final int EXTENSION_MOVE = 6;
    static final int EXTENSION_SCORE_LOW = 750;
    static final int EXTENSION_SCORE_HIGH = 1775;
    static final int EXTENSION_PICKUP_POS = 750;
    static final int EXTENSION_FORTY = 1400;

    public enum LIMIT {
        FULL,
        FORTY,
        NONE
    }

    LIMIT limitValue;

    public Extension(HardwareMap hardwareMap) {

        this.hardwareMap = hardwareMap;
        ExtensionDrive = hardwareMap.get(DcMotor.class, "Extension");

        ExtensionDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extensionPos = 0;
        ExtensionDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    public void startPosition() {
        extensionPos = 0;
        moveExtension();
    }

    public void drivePosition() {
        extensionPos = 180;
        moveExtension();
    }

    public void pickupPosition() {
        extensionPos = EXTENSION_PICKUP_POS;
        moveExtension();
    }

    public void moveExtensionOut() {
        extensionPos = extensionPos + 10;
        moveExtension();
    }

    public void moveExtensionIn() {
        extensionPos = extensionPos - 10;
        moveExtension();
    }

    public void moveExtensionPosition(float gamepad) {

        //extensionPos = ExtensionDrive.getCurrentPosition() + (int)(-gamepad * EXTENSION_MOVE );
        extensionPos = extensionPos + (int) (-gamepad * EXTENSION_MOVE);
        moveExtension();
    }

    public void scoreLowPosition() {
        extensionPos = EXTENSION_SCORE_LOW;
        moveExtension();
    }

    public void scoreHighPosition() {
        extensionPos = EXTENSION_SCORE_HIGH;
        moveExtension();
    }

    public int getCurrentPosition() {
        return ExtensionDrive.getCurrentPosition();
    }

    public void setLimit(LIMIT limit) {
        limitValue = limit;
    }

    public String getLimit() {
        String output = "";
        switch (limitValue) {
            case FULL:
                output = "full";
            break;
            case NONE:
                output = "none";
            break;
            case FORTY:
                output = "forty";
            break;
        }
        return output;
    }

    private void moveExtension(){
        switch (limitValue){
            case FULL:
                extensionPos = EXTENSION_MIN;
                break;
            case NONE:
                extensionPos = extensionPos;
                break;
            case FORTY:
                if (extensionPos > EXTENSION_FORTY) {
                    extensionPos = EXTENSION_FORTY;
                }
                break;
        }

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