package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous (name = "FarExp", group = "Auto")
public class ExpFarAuto extends LinearOpMode {
    Hardware hw = new Hardware(); 
    
@Override public void runOpMode() {
   hw.init(hardwareMap);
   waitForStart();
   hw.clock.reset();
 //score pre-load takes about 8 sec
   hw.setClaw(0);
   hw.pwrLift(hw.Hieght_of_bar + 10, 1);
   hw.driveConst(40, .25);       
   hw.driveOld(25, .125);
   hw.pwrLift(hw.Hieght_of_bar, -1);
   hw.setClaw(.6);
   hw.pwrLift(0, -.5);
// grab pre-clipped sample 
   hw.driveOneSide(90, 0, -0.5);
   hw.driveOld(50, .5);
   hw.driveOneSide(115, .5, 0);
   hw.driveOld(10, .25);
   
   hw.extend(1);
   hw.setClaw(.6);
   hw.pwrLift(hw.Hieght_of_railing + 5, .5); 
   hw.setClaw(0);

   hw.driveOneSide(50, 0, -.5); // 90 cm at .125 is near perfect, still
                                // over shot by about 5 cm
   hw.driveOneSide(40, 1, 0);
   hw.driveOld(35, .5);
   hw.driveOneSide(115, .5, 0);

   hw.pwrLift(hw.Hieght_of_bar + 10, 1);
   hw.driveOld(15, .125);
   hw.pwrLift(hw.Hieght_of_bar -5, -1);
   hw.setClaw(.6);
   

} // runOpMode() 
} // class