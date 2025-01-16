package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
                                                                                                                                                                                                                                                                                                                 
@TeleOp (name = "TURN THE LIGHTS DOWN ITS TOO BRIGHT", group = "Tele Op")
public class Main extends LinearOpMode {

    Hardware hw = new Hardware(); 
    
    @Override public void runOpMode () { 
        hw.init(hardwareMap);
        
        waitForStart(); 
        hw.clock.reset(); 
        
        boolean lastClawInput = false;
        double lastMonitorTime = -hw.CLAW_SUCCESSIVE_WINDOW;
        
        double leftpwr;
        double rightpwr; 
        while (opModeIsActive()) { 
            telemetry.addData("current Extension (cm): ", hw.liftExtension); 
            telemetry.update(); 
            
            leftpwr = -gamepad1.left_stick_y; 
            rightpwr = -gamepad1.right_stick_y; 
            
            boolean extended = false; 
            
            if (gamepad1.left_bumper){ 
                hw.drive( 1, -1, -1, 1); 
            } else if (gamepad1.right_bumper) { 
                hw.drive(-1, 1, 1, -1);
            } else {
                hw.drive(rightpwr, leftpwr, rightpwr, leftpwr);
            }
            
            if (gamepad2.dpad_up) {
                hw.pwrLift(1);
            } else if (gamepad2.dpad_down) { 
                hw.pwrLift(-1);
            } else {                                                                                                                                                                       
                hw.pwrLift(0);
            }

            if (gamepad2.left_trigger > 0){
                hw.setClaw(.6); 
            } else if (gamepad2.right_trigger > 0){
                hw.setClaw(0); 
            }
            
            if (gamepad2.left_bumper) { 
                hw.backClaw.setPosition(1);
            } else if (gamepad2.right_bumper) { 
                hw.backClaw.setPosition(0);
            }
            
            if (hw.clock.seconds() - lastMonitorTime < hw.CLAW_SUCCESSIVE_WINDOW) {
                if (gamepad2.x && !lastClawInput) {
                    lastMonitorTime = 0;
                    hw.backClaw.setPosition(0);
                    hw.extend(1);
                    hw.wrist.setPosition(1);
                }
            } else {
                if (gamepad2.x && !lastClawInput) {
                    lastMonitorTime = hw.clock.seconds();
                } else if (gamepad2.x) {
                    hw.extend(1);
                    hw.wrist.setPosition(1);
                } else if (lastMonitorTime > 0) {
                    hw.extend(1);
                    lastMonitorTime = 0;
                }
            }
            
            if (gamepad2.y) { 
                hw.extend(0); 
            } 
            
            if (-gamepad2.right_stick_y > 0) { 
                hw.wrist.setPosition(1); 
            } else if (-gamepad2.right_stick_y < 0) { 
                hw.wrist.setPosition(0);    
            }
            
            lastClawInput = gamepad2.x;
        }
    }
}
