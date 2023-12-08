package org.firstinspires.ftc.teamcode;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class ArmFunctions {
    private DcMotorEx armMotor;
    private Servo armRotServo, clawServo, planeLauncher;
    public ArmFunctions(HardwareMap hardwareMap) {
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armRotServo = hardwareMap.get(Servo.class, "armRotServo");
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        planeLauncher = hardwareMap.get(Servo.class, "planeLauncher");
    }
    double clawPos;
    double clawRotPos;
    int armMotorPos;
    double PLpos;
    public void setup(boolean gp1start) {
        if (gp1start) {
            armRotServo.setPosition(0.45);
        }
    }
    public void planeLaunch(boolean GP2LB, boolean GP2RB) {
        PLpos = planeLauncher.getPosition();
        if (GP2LB == GP2RB) {
            planeLauncher.setPosition(0 /* fix later */);
        }
    }
    public void clawRot(boolean gamepad1x, boolean gamepad1b) {
        if (gamepad1x) {
            armRotServo.setPosition(0.45 /* 0 degrees */); // down
        } else if (gamepad1b) {
            armRotServo.setPosition(0.8 /* 180 degrees */); // up
        }
        clawRotPos = armRotServo.getPosition();
    }
    public void armMotor(boolean GP2RS, boolean GP2LS) {
        if (GP2RS) {
            armMotor.setTargetPosition(0);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armMotor.setPower(0.5);
            armRotServo.setPosition(0.45); // down
        } else if (GP2LS) {
            armMotor.setTargetPosition(1150);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armMotor.setPower(0.5);
            armRotServo.setPosition(0.8); // up
        }
        armMotorPos = armMotor.getCurrentPosition();
    }
    public void claw(boolean gamepad2x, boolean gamepad2b) {
        clawPos = clawServo.getPosition();
        if (gamepad2x) {
            clawServo.setPosition(0.25); // open
        } else if (gamepad2b) {
            clawServo.setPosition(0.5); // close
        }
        clawPos = clawServo.getPosition();
    }
}