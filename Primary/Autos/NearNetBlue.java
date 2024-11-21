package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "Near Net Blue", group = "Auto")
public class NearNetBlue extends LinearOpMode {
    // score specimens
    // park 
    Hardware hw = new Hardware(); 

    @Autonomous (name = "Near Blue", group = "Auto")
    @Override public void runOpMode() {
        hw.init(hardwareMap);
        waitForStart();
        hw.clock.reset();
		  // straife to Right
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
		  // straife Left
		  // back up till thing is in parking area
		  //
		  //go forwards and repeat last 2 steps for 2 other samples 
		  //
		  // park
    }
}
