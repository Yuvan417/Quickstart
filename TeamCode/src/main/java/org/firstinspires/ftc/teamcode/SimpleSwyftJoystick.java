package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Robot-centric mecanum teleop with BRAKE mode on every drive motor.
 *
 * Controls:
 *   Left stick  → forward/back + strafe
 *   Right stick X → rotate
 *
 * Hardware map names (change these to match your Robot Configuration):
 *   frontLeft, backLeft, frontRight, backRight
 */
@TeleOp(name = "Mecanum TeleOp (Brake)", group = "TeleOp")
public class SimpleSwyftJoystick extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // ===== Hardware Map =====
        DcMotor frontLeft  = hardwareMap.get(DcMotor.class, "ccFrontLeft");
        DcMotor backLeft   = hardwareMap.get(DcMotor.class, "ccBackLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "ccFrontRight");
        DcMotor backRight  = hardwareMap.get(DcMotor.class, "ccBackRight");
        DcMotor intake = hardwareMap.get(DcMotor.class, "ccIntake");
        DcMotor nectar = hardwareMap.get(DcMotor.class, "ccNectarMotor");
        DcMotor pollen = hardwareMap.get(DcMotor.class, "ccPollenMotor");

        // ===== Directions =====
        // Reverse the right side so positive power drives forward.
        // If the robot drives backward when you push the stick forward,
        // reverse the LEFT side instead.
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
//        nectar.setDirection(DcMotorSimple.Direction.FORWARD);
//       pollen.setDirection(DcMotorSimple.Direction.FORWARD);


        // ===== BRAKE mode on every drive motor =====
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Run without encoders (standard for pure teleop)
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        nectar.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        pollen.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Initialized – BRAKE mode active");
        telemetry.update();

        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {

            // ===== Gamepad Input =====
            double y  = -gamepad1.left_stick_y;          // Forward / back (Y is inverted)
            double x  =  gamepad1.left_stick_x * 1.1;    // Strafe (1.1 helps imperfect rollers)
            double rx =  gamepad1.right_stick_x;         // Rotate

            // ===== Normalized mecanum math =====
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0);

            double frontLeftPower  = (y + x + rx) / denominator;
            double backLeftPower   = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower  = (y + x - rx) / denominator;

            // ===== Apply power =====
            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);

            // Optional live telemetry
            telemetry.addData("FrontLeft", "%.2f", frontLeftPower);
            telemetry.addData("BackLeft", "%.2f", backLeftPower);
            telemetry.addData("FrontRight", "%.2f", frontRightPower);
            telemetry.addData("BackRight", "%.2f", backRightPower);
            telemetry.update();

            if (gamepad1.a) {
                intake.setPower(1);
            } else {
                intake.setPower(0);
            }

            if (gamepad1.dpad_up) {
                frontLeft.setPower(0.1);
                frontRight.setPower(0.1);
                backLeft.setPower(0.1);
                backRight.setPower(0.1);
            } else {
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }

            if (gamepad1.dpad_right) {
                frontLeft.setPower(0.2);
                frontRight.setPower(0.2);
                backLeft.setPower(0.2);
                backRight.setPower(0.2);
            } else {
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }

            if (gamepad1.dpad_down) {
                frontLeft.setPower(0.3);
                frontRight.setPower(0.3);
                backLeft.setPower(0.3);
                backRight.setPower(0.3);
            } else {
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }

            if (gamepad1.dpad_left) {
                frontLeft.setPower(0.5);
                frontRight.setPower(0.5);
                backLeft.setPower(0.5);
                backRight.setPower(0.5);
            } else {
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }

//            if (gamepad2.a) {
//                pollen.setPower(0.2);
//            } else {
//                pollen.setPower(0);
//            }
//
//            if (gamepad2.b) {
//                pollen.setPower(0.4);
//            } else {
//                pollen.setPower(0);
//            }
//
//            if (gamepad2.x) {
//                pollen.setPower(0.3);
//            } else {
//                pollen.setPower(0);
//            }
//
//            if (gamepad2.y) {
//                pollen.setPower(0.5);
//            } else {
//                pollen.setPower(0);
//            }
//
//            if (gamepad2.dpad_up) {
//                nectar.setPower(0.2);
//            } else {
//                nectar.setPower(0);
//            }
//
//            if (gamepad2.dpad_left) {
//                nectar.setPower(0.4);
//            } else {
//                nectar.setPower(0);
//            }
//
//            if (gamepad2.dpad_right) {
//                nectar.setPower(0.3);
//            } else {
//                nectar.setPower(0);
//            }
//
//            if (gamepad2.dpad_down) {
//                nectar.setPower(0.5);
//            } else {
//                nectar.setPower(0);
//            }


        }
    }
}
