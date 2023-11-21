package org.firstinspires.ftc.teamcode;
import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ControllerInput;

public class Drive {

    ControllerInput controller;
    Drive drive;

    public DcMotorEx RF; //motor dreapta fata
    public DcMotorEx RB; //motor dreapta spate
    public DcMotorEx LF; // motor stanga fata
    public DcMotorEx LB; // motor stanga spate

    private HardwareMap hardwareMap;

    public Drive(@NonNull HardwareMap hardwareMap){

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


    }

    public void setMotorPowers(double p1, double p2, double p3, double p4){
        LF.setPower(p1);
        RF.setPower(p2);
        LB.setPower(p3);
        RB.setPower(p4);
    }

    public void Movements(){

        double coEff = 0.7;

        double FW = controller.left_stick_y; //forward
        double SR = controller.left_stick_x; //strafe
        double TN = controller.right_stick_x; //turn

        double p1 = FW + SR + TN;
        double p2 = FW - SR - TN;
        double p3 = FW - SR + TN;
        double p4 = FW + SR - TN;

        drive.setMotorPowers(
                Range.clip(p1*coEff,-1,1),
                Range.clip(p2*coEff,-1,1),
                Range.clip(p3*coEff,-1,1),
                Range.clip(p4*coEff,-1,1)
        );


    }

}
