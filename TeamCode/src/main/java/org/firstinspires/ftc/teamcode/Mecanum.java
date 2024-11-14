package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Mecanum {
    HardwareMap hardwareMap;

    private DcMotor front_left  = null;
    private DcMotor front_right = null;
    private DcMotor back_left   = null;
    private DcMotor back_right  = null;

    static final double     SLOW_SPEED             = 0.1;
    static final double     NORMAL_SPEED             = 0.6;
    static final double     FAST_SPEED             = 1;

    public enum SPEED{
        SLOW,
        NORMAL,
        FAST
    }

    public Mecanum(HardwareMap hardwareMap){

        this.hardwareMap = hardwareMap;
        front_left   = hardwareMap.get(DcMotor.class, "front_left");
        front_right  = hardwareMap.get(DcMotor.class, "front_right");
        back_left    = hardwareMap.get(DcMotor.class, "back_left");
        back_right   = hardwareMap.get(DcMotor.class, "back_right");

        front_left.setDirection(DcMotor.Direction.FORWARD);
        front_right.setDirection(DcMotor.Direction.REVERSE);
        back_left.setDirection(DcMotor.Direction.FORWARD);
        back_right.setDirection(DcMotor.Direction.REVERSE);

        // Mecanum drive is controlled with three axes: drive (front-and-back),
        // strafe (left-and-right), and twist (rotating the whole chassis).
        ;
    }

    public void driveMecanum(double drive,double strafe,double twist, SPEED speed) {
        double speedMultiplier;

        switch(speed) {
            case SLOW:
                speedMultiplier = SLOW_SPEED;
                break;
            case  FAST:
                speedMultiplier = FAST_SPEED;
                break;
            default:
                speedMultiplier = NORMAL_SPEED;
        }

        // You may need to multiply some of these by -1 to invert direction of
        // the motor.  This is not an issue with the calculations themselves.
        double[] speeds = {
                (drive + strafe + twist) * speedMultiplier,
                (drive - strafe - twist) * speedMultiplier,
                (drive - strafe + twist) * speedMultiplier,
                (drive + strafe - twist) * speedMultiplier
        };
        // Because we are adding vectors and motors only take values between
        // [-1,1] we may need to normalize them.

        // Loop through all values in the speeds[] array and find the greatest
        // *magnitude*.  Not the greatest velocity.
        double max = Math.abs(speeds[0]);
        for (int i = 0; i < speeds.length; i++) {
            if (max < Math.abs(speeds[i])) max = Math.abs(speeds[i]);
        }

        // If and only if the maximum is outside of the range we want it to be,
        // normalize all the other speeds based on the given speed value.
        if (max > 1) {
            for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
        }

        // apply the calculated values to the motors.
        front_left.setPower(speeds[0]);
        front_right.setPower(speeds[1]);
        back_left.setPower(speeds[2]);
        back_right.setPower(speeds[3]);
    }
}
