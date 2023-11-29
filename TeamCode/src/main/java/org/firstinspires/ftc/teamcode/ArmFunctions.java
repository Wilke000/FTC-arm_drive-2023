package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class ArmFunctions {
    private DcMotor leftFront, leftRear, rightFront, rightRear;
    private DcMotorEx armMotor;
    private Servo armRotServo, clawServo;
    public ArmFunctions(HardwareMap hardwareMap) {
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armRotServo = hardwareMap.get(Servo.class, "armRotServo");
        clawServo = hardwareMap.get(Servo.class, "clawServo");
    }
    double speedMult = 1;
    double clawPos;
    int armMotorPos;
    double armangleNormalized;
    double armangle;
    double armrevolutions;
    public void toggleSpeed() {
        if (speedMult == 1) {
            speedMult = 0.62;
        }
        else {
            speedMult = 1;
        }
    }
    public void arm(boolean gamepad1x, boolean gamepad1b) {
        if (gamepad1x) {
            armRotServo.setPosition(0 /* 0 degrees */);
        } else if (gamepad1b) {
            armRotServo.setPosition(1 /* 180 degrees */);
        }
    }
    public void armMotor(boolean gamepadLBUMP, boolean gamepadRBUMP, boolean gamepadLSBUTTON, boolean gamepadRSBUTTON) {
        /*if (gamepadLBUMP) {
            armMotor.setTargetPosition(-10);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armMotor.setPower(0.1);
        } else if (gamepadRBUMP) {
            armMotor.setTargetPosition(100);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armMotor.setPower(0.1);
        }*/
        armMotorPos = armMotor.getCurrentPosition();
        double CPR = 2786;

        // Get the current position of the motor
        int position = armMotor.getCurrentPosition();
        armrevolutions = position/CPR;

        armangle = armrevolutions * 360;
        armangleNormalized = armangle % 360;
        /*if (gamepadLBUMP) {
            clawServo.setPosition(0);
        } else if (gamepadRBUMP) {
            clawServo.setPosition(180);
        }
        clawPos = clawServo.getPosition();*/
    }

}