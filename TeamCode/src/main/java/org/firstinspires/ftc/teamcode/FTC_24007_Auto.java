package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Extension;
import org.firstinspires.ftc.teamcode.subsystems.Mecanum;
import org.firstinspires.ftc.teamcode.subsystems.Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SpeciminWheel;
import org.firstinspires.ftc.teamcode.subsystems.Wrist;

@Autonomous(name="AutoMode",preselectTeleOp = "TeleOpMode")  // @TeleOp(...) is the other common choice
// @Disabled
public class FTC_24007_Auto extends LinearOpMode {

    public enum START_POSITION{
        NEAR_PARK,
        FAR_PARK,
        NEAR_SCORE
    }

    public static START_POSITION startPosition;

    @Override
    public void runOpMode() {
        telemetry.setAutoClear(true);
        Mecanum mecanum = new Mecanum(hardwareMap);

        Shoulder shoulder = new Shoulder(hardwareMap);
        SpeciminWheel speciminWheel = new SpeciminWheel(hardwareMap);
        Extension extension = new Extension(hardwareMap);
        Wrist wrist = new Wrist(hardwareMap);
        shoulder.zeroEncoder();
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
                mecanum.moveRight(3,.5);
                sleep(500);
                mecanum.moveForward(-48,.5);
                break;

            case NEAR_PARK:

                mecanum.moveRight(3,.5);
                sleep(500);
                mecanum.moveForward(-36,.5);
                break;

            case NEAR_SCORE:

                mecanum.moveRight(23,.5);
                sleep(500);
                shoulder.scoreHighPosition();
                sleep(1000);
                extension.setLimit((Extension.LIMIT.NONE));
                extension.scoreHighPosition();
                sleep(1000);
                wrist.scoreHighPosition();
                mecanum.turnLeft(12,.25);
                mecanum.moveRight(14,0.25);
                mecanum.moveForward(26,.25);
                sleep(500);
                speciminWheel.releaseSpecimin();
                sleep(1000);
                speciminWheel.holdSpecimin();
                sleep(1000);
                mecanum.moveForward(-10,.5);
                wrist.moveToMin();
                extension.startPosition();
                sleep(1000);
                shoulder.startPosition();
                mecanum.moveForward(-16,.5);
                mecanum.moveRight(-14,0.5);
                mecanum.turnLeft(-12,.5);
                mecanum.moveForward(-69,1);
                mecanum.moveRight(-23,.75);
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
