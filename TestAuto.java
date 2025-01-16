package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous (name = "DONT USE ME", group = "Auto")
public class TestAuto extends LinearOpMode {
    
    Hardware hw = new Hardware(); 
    
    @Override public void runOpMode() {
        hw.init(hardwareMap);
        waitForStart();
        hw.clock.reset();
    } 
}