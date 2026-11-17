package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp // Registers this OpMode as a TeleOp.
public class FlywheelTunerTutorial extends OpMode {
    public DcMotorEx flywheelMotor;
    public DcMotor kickerMotor;
    public DcMotor intakeMotor;
    public double speed = 1000;

    @Override
    public void init() {
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "ccTurretMotor");
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        kickerMotor = hardwareMap.get(DcMotor.class, "ccKickerMotor");
        intakeMotor = hardwareMap.get(DcMotor.class, "ccIntakeMotor2");
        flywheelMotor.setDirection(DcMotor.Direction.REVERSE);
        kickerMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(76.5, 0, 0, 12.7030);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        telemetry.addLine("Init complete");
    }

    @Override
    public void loop() {

        if (gamepad1.a) {
            PIDFCoefficients pidfCoefficients = new PIDFCoefficients(76.5, 0, 0, 12.7030);
            flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
            flywheelMotor.setVelocity(1150);
        } else {
            PIDFCoefficients pidfCoefficients = new PIDFCoefficients(76.5, 0, 0, 12.7030);
            flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
            flywheelMotor.setVelocity(0);
        }

        if (gamepad1.b) {
            PIDFCoefficients pidfCoefficients = new PIDFCoefficients(76.5, 0, 0, 12.7030);
            flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
            flywheelMotor.setVelocity(1150);
            kickerMotor.setPower(1);
            intakeMotor.setPower(1);
        } else {
            PIDFCoefficients pidfCoefficients = new PIDFCoefficients(76.5, 0, 0, 12.7030);
            flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
            flywheelMotor.setVelocity(0);
            kickerMotor.setPower(0);
            intakeMotor.setPower(0);
        }

        if (gamepad1.x) {
            kickerMotor.setPower(1);

        } else {
            kickerMotor.setPower(0);
        }


        if (gamepad1.dpad_up) {

            intakeMotor.setPower(1);
        } else {
            intakeMotor.setPower(0);
        }



        telemetry.addData("Velocity", speed);
        telemetry.update();

    }
}
