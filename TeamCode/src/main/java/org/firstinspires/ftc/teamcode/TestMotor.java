package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TestMotor extends LinearOpMode {

    DcMotor motor1;
    CRServo LW;
    CRServo RW;
    Servo servo1;
    DcMotor motor2;
    Servo servo2;
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
        servo2.setPosition(0);
        servo1.setPosition(0.5);

        waitForStart();
        int r =0;

        while (opModeIsActive() && !isStopRequested())
        {
            telemetry.addData("ticks", motor1.getCurrentPosition());
            telemetry.addData("ticks2", motor2.getCurrentPosition());

            telemetry.update();
            if (gamepad1.left_bumper)
            {
                motor1.setTargetPosition(-800);
                motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor1.setPower(0.5);
            }
            if (gamepad1.right_bumper){
                motor1.setTargetPosition(0);
                motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor1.setPower(0.3);
            }

            if(gamepad1.dpad_down){
                LW.setPower(1);
                RW.setPower(-1);
               // wait(5);
            }
            if(gamepad1.dpad_up){
                LW.setPower(-1);
                RW.setPower(1);
               // if(telemetry )

                }

            if(gamepad1.left_trigger>0.4){

                motor2.setTargetPosition(-421);
                motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor2.setPower(0.5);
                servo1.setPosition(0.5);
            }
            if(gamepad1.right_trigger>0.4)
            {
                motor2.setTargetPosition(900);
                motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor2.setPower(0.5);
                servo1.setPosition(0.9);
            }

            if(gamepad1.dpad_right)
            {  servo2.setPosition(0.7);

            }

        }
    }

}
