package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

//import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ControllerInput;
import org.firstinspires.ftc.teamcode.Drive;
//import org.firstinspires.ftc.teamcode.mechanisms.Claw;

public class teleop extends LinearOpMode {


    Telemetry telemetry;

    HardwareMap hardwareMap;
    Drive drive;
 //   Claw claw;

    ControllerInput controller;


    public double slow = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        controller = new ControllerInput(gamepad1);

    //    claw = new Claw(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            controller.update();


            if (controller.rightBumperOnce()){
                slow -= 0.1;
            }
            if (controller.leftBumperOnce()){
                slow += 0.1;
            }

            drive.Movements();


          /*  if (controller.BOnce()) {
                //B - INCHIDERE CLAW
                claw.closeClaw();
            }

            if (controller.AOnce()) {
                //A - DESCHIDERE CLAW
                claw.openClaw();
            }*/


        }
    }

}


