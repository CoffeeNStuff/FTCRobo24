 package org.firstinspires.ftc.teamcode;
 
 import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
 import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
 
 @Autonomous (name = "Near Net Blue", group = "Auto")
 public class NearAuto extends LinearOpMode {
  Hardware hw = new Hardware(); 
  
  private void score (double d) { // starting from aligned w/ sample
   hw.driveOld(50, .25);  // ending in front of basket
   hw.setClaw(0);
   hw.driveConst(50, -.5); 
   hw.rotate(-90, .5); 
   hw.pwrLift(hw.Hieght_of_basket, .5); 
   hw.driveOld(d, .5); 
   hw.driveOld(10, .125); 
   hw.setClaw(1); 
   hw.driveOld(-10, .125); 
   hw.pwrLift(0, 1); 
  }
  
  @Override public void runOpMode() {
   hw.init(hardwareMap);
   waitForStart();
   hw.clock.reset();
   
 // score first
   hw.straifeEnc(20, -.5);
   hw.driveOld(10, .5); 
   score(50); 
   
// score second 
   hw.driveConst(40, -.5);
   hw.rotate(90, .5); 
   score(30); 
   
// score third
   hw.rotate(90, .5); 
   score(0); 
// park
  hw.driveConst(60, -.5); 
  hw.rotate(90, .5); 
  hw.driveConst(100, .5); 
  hw.rotate(90, .5); 
  hw.pwrLift(hw.Hieght_of_bar, .5); 
  hw.driveOld(10, .125); 
  }
 }
