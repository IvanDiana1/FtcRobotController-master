package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.ControllerInput;
import org.firstinspires.ftc.teamcode.Drive;

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
    Servo servo2;

    public DcMotorEx RF; //motor dreapta fata
    public DcMotorEx RB; //motor dreapta spate
    public DcMotorEx LF; // motor stanga fata
    public DcMotorEx LB; // motor stanga spate
    @Override
    public void runOpMode(){

        motor1 = hardwareMap.get(DcMotor.class,"Lifter");
        LW = hardwareMap.get(CRServo.class, "LW");
        RW = hardwareMap.get(CRServo.class, "RW");
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        servo1 = hardwareMap.get(Servo.class, "Cutie");
        motor2 = hardwareMap.get(DcMotor.class, "W Gear");
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        servo2 = hardwareMap.get(Servo.class, "Avion");
        servo2.setPosition(1);
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

        waitForStart();
        int r =0;

        while (opModeIsActive() && !isStopRequested())
        {
            telemetry.addData("ticks", motor1.getCurrentPosition());
            telemetry.addData("ticks2", motor2.getCurrentPosition());

            telemetry.update();
            if (gamepad1.dpad_up)
            {
                motor1.setTargetPosition(-800);
                motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor1.setPower(0.5);
            }
            if (gamepad1.dpad_down)
            {
                motor1.setTargetPosition(0);
                motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor1.setPower(0.3);
            }

            while (gamepad2.left_trigger > 0.4)
            {
                LW.setPower(1);
                RW.setPower(-1);
            }
            while (gamepad2.right_trigger > 0.4)
            {
                LW.setPower(-1);
                RW.setPower(1);
            }

            if (gamepad1.left_trigger > 0.4)
            {

                motor2.setTargetPosition(-200);
                motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor2.setPower(0.5);
                servo1.setPosition(0.2);
            }

            if (gamepad1.right_trigger > 0.4)
            {
                motor2.setTargetPosition(1460);
                motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor2.setPower(0.5);
                servo1.setPosition(0.25);
            }

            if (gamepad1.dpad_right)
            {
                servo2.setPosition(0.5);
            }

            double FW = gamepad2.left_stick_y; //forward
            double SR = gamepad2.left_stick_x; //strafe
            double TN = gamepad2.right_stick_x; //turn

            double p1 = FW - SR - TN;
            double p2 = FW + SR + TN;
            double p3 = -FW- SR + TN;
            double p4 = -FW+ SR - TN;

            LF.setPower(p1);
            RF.setPower(p2);
            LB.setPower(p3);
            RB.setPower(p4);

        }
    }

}
