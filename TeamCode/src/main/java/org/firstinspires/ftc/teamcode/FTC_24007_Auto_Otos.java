package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.OtosDrive;

@Autonomous(name="AutoMode_Otos",preselectTeleOp = "TeleOpMode")  // @TeleOp(...) is the other common choice
// @Disabled
public class FTC_24007_Auto_Otos extends LinearOpMode {

    public enum START_POSITION{
        NEAR_PARK,
        FAR_PARK,
        NEAR_SCORE
    }

    public static START_POSITION startPosition;

    @Override
    public void runOpMode() {
        telemetry.setAutoClear(true);
        OtosDrive otosDrive = new OtosDrive(hardwareMap);
        otosDrive.configureOtos();
        telemetry.addData("Configured OTOS", startPosition);
        telemetry.update();

        sleep(5000);

        //Key Pay inputs to selecting Starting Position of robot
        selectStartingPosition();
        telemetry.addData("Selected Starting Position", startPosition);
        telemetry.update();

        while (opModeInInit()) {
            telemetry.addData("Selected Starting Position", startPosition);
            // Display current robot position/heading
            telemetry.addData("Current X coordinate", otosDrive.myPosition().x);
            telemetry.addData("Current Y coordinate", otosDrive.myPosition().y);
            telemetry.addData("Current Heading angle", otosDrive.myPosition().h);
            telemetry.update();
        }

        // Wait for the game to start (driver presses PLAY)

        waitForStart();

        switch (startPosition) {
            case FAR_PARK:
                SparkFunOTOS.Pose2D currentPosition = new SparkFunOTOS.Pose2D(.0, 0, 0); // should be -3.75 & -7.5 and 90
                //otosDrive.setPosition(currentPosition);
                telemetry.addData("Current X coordinate", otosDrive.myPosition().x);
                telemetry.addData("Current Y coordinate", otosDrive.myPosition().y);
                telemetry.addData("Current Heading angle", otosDrive.myPosition().h);
                telemetry.update();
                sleep(5000);
                otosDrive.otosDrive(10,10,90,10);
                telemetry.addData("Current X coordinate", otosDrive.myPosition().x);
                telemetry.addData("Current Y coordinate", otosDrive.myPosition().y);
                telemetry.addData("Current Heading angle", otosDrive.myPosition().h);
                telemetry.update();
                sleep(500);
                break;

            case NEAR_PARK:

                //otosDrive.otosDrive(10,10,90,10);
                otosDrive.moveRobot(.5,0,0);
                sleep(5000);
                break;

            case NEAR_SCORE:

                telemetry.addData("Current X coordinate", otosDrive.myPosition().x);
                telemetry.addData("Current Y coordinate", otosDrive.myPosition().y);
                telemetry.addData("Current Heading angle", otosDrive.myPosition().h);
                telemetry.update();
                sleep(5000);

                otosDrive.otosDrive(0,23,0, 10);
                sleep(500);
                otosDrive.otosDrive(5,23,-45, 10);
                telemetry.addLine("Ari cooked");
                sleep(5000);
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
            telemetry.addData("Select Starting Position using XYAB on Logitech on gamepad 1:","");
            telemetry.addData("    Near Park  ", "(X)");
            telemetry.addData("    Far Park ", "(Y)");
            telemetry.addData("    Near Score ", "(A)");

            if(gamepad1.x){
                startPosition = START_POSITION.NEAR_PARK;
                break;
            }
            if(gamepad1.y){
                startPosition = START_POSITION.FAR_PARK;
                break;
            }
            if(gamepad1.a){
                startPosition = START_POSITION.NEAR_SCORE;
                break;
            }

            telemetry.update();
        }
        telemetry.clearAll();
    }
}
