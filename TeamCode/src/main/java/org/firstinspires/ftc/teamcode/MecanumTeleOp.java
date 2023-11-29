package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp", group="Testing")
public class MecanumTeleOp extends OpMode {
    private RobotHardware robot;
    @Override
    public void init() {
        robot = new RobotHardware(hardwareMap);
    }

    boolean isButtonA = false;
    public double speedMult;
    @Override
    public void loop() {

        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotation = gamepad1.right_stick_x;
        /* double armRotation; */
        if (gamepad1.a) {
            if(!isButtonA) {
                robot.toggleSpeed();
                isButtonA = true;
            }
        } else {
            isButtonA = false;
        }
        robot.setMotorPowers(strafe, forward, rotation * 0.8);
        robot.arm(gamepad1.x, gamepad1.b);
        robot.armMotor(gamepad1.left_bumper, gamepad1.right_bumper, gamepad1.left_stick_button, gamepad1.right_stick_button);
        telemetry.addData("clawPos", robot.clawPos);
        telemetry.addData("armMotorPos", robot.armMotorPos);
        telemetry.addData("Encoder Revolutions", robot.armrevolutions);
        telemetry.addData("Encoder Angle (Degrees)", robot.armangle);
        telemetry.addData("Encoder Angle - Normalized (Degrees)", robot.armangleNormalized);
        telemetry.update();
    }
}