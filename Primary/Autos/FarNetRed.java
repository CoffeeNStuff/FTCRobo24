package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "Right Red", group = "Auto")
public class FarNetRed extends LinearOpMode {
    
    Hardware hw = new Hardware(); 
    
    @Override public void runOpMode() {
        hw.init(hardwareMap);
        waitForStart();
        hw.clock.reset();

		  // straife to left
		  // approach bar
		  // elevate lift
		  // apporach until flush
		  // descend lift
		  // raise lift
		  //
		  // back up
		  // retract lift
		  // back up a little 
		  //
		  // straife right
		  // back up till thing is in parking area
		  //
		  //go forwards and repeat last 2 steps for 2 other samples 
		  //
		  // park
    }
}
