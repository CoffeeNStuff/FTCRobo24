package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "TotallyNormalName", group = "Tele Op")
public class Main extends LinearOpMode {

    Hardware hw = new Hardware(); 
    
    @Override public void runOpMode () { 
        hw.init(hardwareMap);
        
        waitForStart(); 
        hw.clock.reset(); 
        
        double leftpwr;
        double rightpwr; 
        while (opModeIsActive()) { 
           
            leftpwr = -gamepad1.left_stick_y; 
            rightpwr = -gamepad1.right_stick_y; 
            
            if (gamepad1.left_bumper){ 
                hw.drive(1, -1, -1, 1);
            } else if (gamepad1.right_bumper) { 
                hw.drive(-1, 1, 1, -1);
            } else { 
                hw.drive(rightpwr, leftpwr, rightpwr, leftpwr);
            }
            
            if (gamepad2.dpad_up) {
                hw.lift(1);
                sleep(50);
            } else if (gamepad2.dpad_down) { 
                hw.lift(-1); 
                sleep(50);
            } else {
                hw.lift(hw.IdlePwr_C); 
            }
            
            if (gamepad2.left_trigger > 0){
                hw.setClaw(1); 
            } else if (gamepad2.right_trigger > 0){
                hw.setClaw(0); 
            }

				if (gamepad2.a) { 
					hw.lift
    
        }
    }
}
