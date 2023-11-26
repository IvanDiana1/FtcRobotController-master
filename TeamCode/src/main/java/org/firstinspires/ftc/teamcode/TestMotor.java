package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TestMotor extends LinearOpMode {

    DcMotor motor1;

    Drive drive;
    //   Claw claw;

    ControllerInput controller;
    CRServo LW;
    CRServo RW;
    Servo servo1;
    DcMotor motor2;
    Servo Crs_avion;

    public DcMotorEx RF; //motor dreapta fata
    public DcMotorEx RB; //motor dreapta spate
    public DcMotorEx LF; // motor stanga fata
    public DcMotorEx LB; // motor stanga spate

    public static final double DRIVETRAIN_MULTIPLIER = 0.5;

    @Override
    public void runOpMode(){

        motor1 = hardwareMap.get(DcMotor.class,"Lifter");
        LW = hardwareMap.get(CRServo.class, "LW");
        RW = hardwareMap.get(CRServo.class, "RW");
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        servo1 = hardwareMap.get(Servo.class, "Cutie");
        motor2 = hardwareMap.get(DcMotor.class, "W Gear");
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Crs_avion = hardwareMap.get(Servo.class, "Avion");
        Crs_avion.setPosition(1);
        servo1.setPosition(0.15);
        RF = hardwareMap.get(DcMotorEx.class, "RF");
        RB = hardwareMap.get(DcMotorEx.class, "RB");
        LF = hardwareMap.get(DcMotorEx.class, "LF");
        LB = hardwareMap.get(DcMotorEx.class, "LB");

        RF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LF.setDirection(DcMotorSimple.Direction.FORWARD);
        LB.setDirection(DcMotorSimple.Direction.REVERSE);
        RF.setDirection(DcMotorSimple.Direction.REVERSE);
        RB.setDirection(DcMotorSimple.Direction.FORWARD);

        int pos_lift = 0;

        waitForStart();

        while (opModeIsActive() && !isStopRequested())
        {
            telemetry.addData("ticks", motor1.getCurrentPosition());
            telemetry.addData("ticks2", motor2.getCurrentPosition());

            telemetry.update();
            if(gamepad1.dpad_up )
            {

                pos_lift= 2500;
                motor1.setTargetPosition(pos_lift);
                motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor1.setPower(0.5);
            } else  if (gamepad1.dpad_down )
            {
                pos_lift= 0;
                motor1.setTargetPosition(pos_lift);
                motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor1.setPower(0.3);
            }


            if (gamepad2.left_trigger > 0.4)
            {
                LW.setPower(1);
                RW.setPower(-1);
            }
            else if (gamepad2.right_trigger > 0.4)
            {
                LW.setPower(-1);
                RW.setPower(1);
            }
            else {
                LW.setPower(0);
                RW.setPower(0);
            }

            if (gamepad1.left_trigger > 0.4)
            {

                motor2.setTargetPosition(0);
                motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor2.setPower(0.3);
                servo1.setPosition(0.2);
            }

            if (gamepad1.right_trigger > 0.4)
            {
                motor2.setTargetPosition(1460);
                motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor2.setPower(0.3);
                servo1.setPosition(0.5);


            }
            if(gamepad1.square){
                motor2.setTargetPosition(700);
                motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor2.setPower(0.3);
                servo1.setPosition(0.4);
                motor1.setTargetPosition(4000);
                motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor1.setPower(0.5);
            }


            if (gamepad1.dpad_right)
            {
                Crs_avion.setPosition(0.5);
            }

            double FW = gamepad2.left_stick_y; //forward
            double SR = gamepad2.left_stick_x; //strafe
            double TN = gamepad2.right_stick_x; //turn

            double p1 = FW - SR - TN;
            double p2 = FW + SR + TN;
            double p3 = -FW- SR + TN;
            double p4 = -FW+ SR - TN;

            LF.setPower(p1 * DRIVETRAIN_MULTIPLIER);
            RF.setPower(p2 * DRIVETRAIN_MULTIPLIER);
            LB.setPower(p3 * DRIVETRAIN_MULTIPLIER);
            RB.setPower(p4 * DRIVETRAIN_MULTIPLIER);

        }
    }

}
