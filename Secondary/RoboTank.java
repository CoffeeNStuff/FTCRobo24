package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Tank Mode TeleOp", group = "TeleOp")


public class RoboTank extends LinearOpMode{
    // Declare the hardware class

    public HardwareRobot robot = new HardwareRobot();
// CLaw
    public Servo claw;

    // Declare motor variables

    private DcMotor wheelBr, wheelBl, wheelFr, wheelFl;
    private DcMotor liftR, liftL;
         
 // Encoder Limits for the linear slide
 private static final int SLIDEMAX = 3000;
 private static final int SLIDEMIN = 0;

 
// Variables to control claw position

    private double clawOpenPosition = 0.7; // Adjust for open position

    private double clawClosePosition = 1.0; // Adjust for close position

    private boolean clawOpen = false; // Track if claw is open or closed

         ElapsedTime holdTimer = new ElapsedTime();// timer for holding power
         boolean holding = false;//flag to track holding state
         


    @Override
    public void runOpMode() {
    // Initial claw
     // Initialize servo
        claw = hardwareMap.get(Servo.class, "claw");
     // Initialize Drive Train hardware
       wheelBr = hardwareMap.get(DcMotor.class, "wheelBr");
       wheelBl = hardwareMap.get(DcMotor.class, "wheelBl");
       wheelFr = hardwareMap.get(DcMotor.class, "wheelFr");
        wheelFl = hardwareMap.get(DcMotor.class, "wheelFl");

// initialize linear drive hardware
     liftR = hardwareMap.get(DcMotor.class,"liftR");
     liftL = hardwareMap.get(DcMotor.class,"liftL");
          // Set motors to use encoders

   liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    liftR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
   liftL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



    // Set zero power behavior

    liftR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    liftL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    
    //reset Timer
    holdTimer.reset();
  
  // Reverse the direction of necessary motors (depends on robot configuration)

        wheelFl.setDirection(DcMotor.Direction.REVERSE);
        wheelBl.setDirection(DcMotor.Direction.REVERSE);
       // wheelFr.setDirection(DcMotor.Direction.REVERSE);



        telemetry.addData("Status", "Initialized");

        telemetry.update();



        // Wait for the game to start

        waitForStart();



        while (opModeIsActive()) {

            // Get joystick values for tank drive

            double leftPower = -gamepad1.left_stick_y; // Y-axis is inverted

            double rightPower = -gamepad1.right_stick_y;



            // Check for bumper input to strafe

            if (gamepad1.left_bumper) {

                // Strafe left

                wheelFl.setPower(-1);

                wheelBl.setPower(1);

                wheelFr.setPower(-1);

                wheelBr.setPower(-1);

            } else if (gamepad1.right_bumper) {

                // Strafe right

                wheelFl.setPower(1);

                wheelBl.setPower(-1);

                wheelFr.setPower(1);
                wheelBr.setPower(1);

            } else {

                // Standard tank drive

                wheelFl.setPower(leftPower);

                wheelBl.setPower(leftPower);

                wheelFr.setPower(-rightPower);

                wheelBr.setPower(rightPower);

            }


        // Toggle claw open/close with gamepad2's A button

        if (gamepad2.a ) {//&& !clawOpen

            claw.setPosition(0.5);

            clawOpen = true;

        } else if (gamepad2.b) {

            claw.setPosition(.95);

            //clawOpen = false;

        }
        else if (gamepad2.y) {

            claw.setPosition(0.95);

            clawOpen = false;

        }

     int currentPosition = liftR.getCurrentPosition(); // Assuming both motors move the same distance
    int liftRPosition = liftR.getCurrentPosition();
    int liftLPosition = liftL.getCurrentPosition();
    double targetPosition;
    double holdPower = -0.05;


    if (gamepad2.dpad_up && currentPosition < 3000) {

        // Move up if below the top limit

        liftR.setPower(-0.25);
        liftL.setPower(-0.25);
        //holding mode
        targetPosition = liftR.getCurrentPosition();
        holding = true;
        holdTimer.reset();

    } else if (gamepad2.dpad_down) {

        // Move down if above the bottom limit

        liftR.setPower(.25);
        liftL.setPower(.25);
        holding = false; //exit the hold state
// holding position
    } else if (holding && holdTimer.seconds()<=10) 
    {
    liftR.setPower(holdPower);
    liftL.setPower(holdPower);
   } else {

        // Stop the lift if no D-pad button is pressed or limit is reached

        liftR.setPower(0);
        liftL.setPower(0);
        holding = false; //exit holding state after 10 seconds

    }


        // Telemetry for debugging

//telemetry.addData("FL Power", flPower);

//        telemetry.addData("FR Power", frPower);

//        telemetry.addData("BL Power", blPower);

 //       telemetry.addData("BR Power", brPower);

  //      telemetry.addData("Claw Position", clawOpen ? "Open" : "Closed");
        
        

        telemetry.update();

  

            // Telemetry for debugging

            telemetry.addData("Left Power", leftPower);

            telemetry.addData("Right Power", rightPower);

            telemetry.addData("Strafe", gamepad1.left_bumper ? "Left" : (gamepad1.right_bumper ? "Right" : "None"));
            telemetry.addData("tank drive","left %.2f, Right: %.2f", leftPower,rightPower);
            telemetry.addData("LiftR position", liftRPosition);
            telemetry.addData("LiftL position", liftLPosition);
            telemetry.addData("hold true or false", holding);

            telemetry.update();

        }

    }
}
//as of 11/15

