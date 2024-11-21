package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "Left Red", group = "Auto")
public class NearNetRed extends LinearOpMode {
    // score specimens
    // park 
    Hardware hw = new Hardware(); 

    @Override public void runOpMode() {
        hw.init(hardwareMap);
        waitForStart();
        hw.clock.reset();
        
       // advance 50in forward, turn 90 degrees right, then advance ~15in
       // raise lift, advance a little more, then lower lift as to clip the 
       // specimen. pick up a sample and return to net and score  
    }
}

