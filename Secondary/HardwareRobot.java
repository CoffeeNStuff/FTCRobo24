package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HardwareRobot {
    // Declare motors and servos
    public DcMotor wheelFr, wheelBr, wheelFl, wheelBl, liftR,liftL;
    public Servo claw;

    // Constructor
    public HardwareRobot() {
    }

    // Initialize the hardware
    public void init(HardwareMap hwMap) {
        // Define and initialize motors
        wheelBr =hwMap.get(DcMotor.class, "wheelBr");
        wheelBl = hwMap.get(DcMotor.class, "wheelBl");
        wheelFl= hwMap.get(DcMotor.class, "wheelFl");
        wheelFr = hwMap.get(DcMotor.class, "wheelFr");
         liftR = hwMap.get(DcMotor.class, "liftR");
         liftL = hwMap.get(DcMotor.class, "liftL");

        // Set motor directions
        wheelFl.setDirection(DcMotor.Direction.REVERSE);
         wheelBl.setDirection(DcMotor.Direction.REVERSE);
        wheelFr.setDirection(DcMotor.Direction.FORWARD);
        wheelBr.setDirection(DcMotor.Direction.FORWARD);
          liftR.setDirection(DcMotor.Direction.FORWARD); 
        liftL.setDirection(DcMotor.Direction.FORWARD);

        // Set motors to brake when power is zero
        wheelFl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        wheelFr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        wheelBl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        wheelBr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Initialize servo
        claw = hwMap.get(Servo.class, "claw");
    }

    // Method to set power to the motors for strafing left
    public void strafeLeft(double power) {
        wheelFl.setPower(-power);
        wheelFr.setPower(power);
        wheelBl.setPower(power);
        wheelBr.setPower(-power);
        
    }

    // Method to set power to the motors for strafing right
    public void strafeRight(double power) {
        wheelFl.setPower(power);
        wheelFr.setPower(power);
        wheelBl.setPower(-power);
        wheelBr.setPower(power);
    }

    // Method to stop all drivetrain motors
    public void stopMotors() {
        wheelFl.setPower(0);
        wheelFr.setPower(0);
        wheelBl.setPower(0);
        wheelBr.setPower(0);
    }
}