package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutoMode_Otos",preselectTeleOp = "TeleOpMode")  // @TeleOp(...) is the other common choice
// @Disabled
public class FTC_24007_Auto_Otos extends LinearOpMode {

    public enum START_POSITION{
        NEAR_PARK,
        FAR_PARK
    }

    public static START_POSITION startPosition;

    @Override
    public void runOpMode() {
        telemetry.setAutoClear(true);
        OtosDrive otosDrive = new OtosDrive(hardwareMap);

        //Key Pay inputs to selecting Starting Position of robot
        selectStartingPosition();
        telemetry.addData("Selected Starting Position", startPosition);

        while (opModeInInit()) {
            telemetry.addData("Selected Starting Position", startPosition);
            telemetry.update();
        }


        // Wait for the game to start (driver presses PLAY)

        waitForStart();

        switch (startPosition) {
            case FAR_PARK:
                otosDrive.moveRight(3,.5);
                sleep(500);
                otosDrive.moveForward(-48,.5);
                break;

            case NEAR_PARK:

                otosDrive.moveRight(3,.5);
                sleep(500);
                otosDrive.moveForward(-36,.5);
                break;

        }
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);  // Pause to display last telemetry message.
    }
    public void selectStartingPosition() {
        telemetry.setAutoClear(true);
        telemetry.clearAll();
        //******select start pose*****
        while(!isStopRequested()){
            //telemetry.addData("Initializing FTC Wires (ftcwires.org) Autonomous adopted for Team:",
            //       TEAM_NAME, " ", TEAM_NUMBER);
            //telemetry.addData("---------------------------------------","");
            //telemetry.addLine("This Auto program uses Open CV Vision Processor for Team Element detection");
            telemetry.addData("Select Starting Position using XY on Logitech on gamepad 1:","");
            telemetry.addData("    Near Park  ", "(X / ▢)");
            telemetry.addData("    Far Park ", "(Y / Δ)");
            if(gamepad1.x){
                startPosition = START_POSITION.NEAR_PARK;
                break;
            }
            if(gamepad1.y){
                startPosition = START_POSITION.FAR_PARK;
                break;
            }

            telemetry.update();
        }
        telemetry.clearAll();
    }
}
