/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/*
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@TeleOp(name="TeleOpMode")

public class FTC_24007_TeleOp_102724 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Shoulder shoulder = new Shoulder(hardwareMap);
        SpeciminWheel speciminWheel = new SpeciminWheel(hardwareMap);
        Extension extension = new Extension(hardwareMap);
        Mecanum mecanum = new Mecanum(hardwareMap);
        Wrist wrist = new Wrist(hardwareMap);

        Mecanum.SPEED speed;

        // Wait for the game to start (driver presses START)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            shoulder.moveShoulderPosition(gamepad2.right_stick_y);

            extension.moveExtensionPosition(gamepad2.left_stick_y);

            if (gamepad2.a){
                shoulder.drivePosition();
            }

            if (gamepad2.x){
                shoulder.pickupPosition();
                if (shoulder.getCurrentPosition()>525) {
                    extension.pickupPosition();
                    wrist.moveToPickUpPosition();
                }
            }


            if (gamepad2.b){
                shoulder.scoreLowPosition();
                if (shoulder.getCurrentPosition()>525){
                    extension.scoreLowPosition();
                    wrist.moveToPickUpPosition();
                }
            }

            if (gamepad2.y){
                shoulder.scoreHighPosition();
                if (shoulder.getCurrentPosition()>525) {
                    extension.scoreHighPosition();
                    wrist.scoreHighPosition();
                }
            }

            if (gamepad2.right_bumper){
                speciminWheel.releaseSpecimin();
            } else if (gamepad2.left_bumper) {
                speciminWheel.collectSpecimin();
            }
            else {speciminWheel.holdSpecimin();}

            if (gamepad1.right_bumper){
                speed = Mecanum.SPEED.FAST;
            } else if (gamepad1.left_bumper) {
                speed = Mecanum.SPEED.SLOW;
            }
            else {
                speed = Mecanum.SPEED.NORMAL;
            }

            mecanum.driveMecanum( -gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, speed );

            if (gamepad2.dpad_up){
                wrist.moveUp();
            } else if (gamepad2.dpad_down) {
                wrist.moveDown();
            } else if (gamepad2.dpad_left) {
                wrist.moveToPickUpPosition();
            }

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Positions", "Shoulder Position: " + shoulder.getCurrentPosition());
            telemetry.addData("Positions", "Extend Position: " + extension.getCurrentPosition());
            telemetry.addData("Positions","Wrist Position: " + wrist.getCurrentPosition());
            telemetry.addData("Game Pad 1", "Left stick y:" + gamepad1.left_stick_y);
            telemetry.addData("Game Pad 1", "Left stick x:" + gamepad1.left_stick_x);
            telemetry.addData("Game Pad 1", "Right stick x:" + gamepad1.right_stick_x);
            telemetry.addData("Game Pad 2", "Right stick y:" + gamepad2.right_stick_y);


            telemetry.update();
        }
    }
}