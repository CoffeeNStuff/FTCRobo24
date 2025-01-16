package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "Far", group = "Auto")
public class FarAuto extends LinearOpMode {
    Hardware hw = new Hardware(); 
    
    // starting from 20 cm behind the sample placed on
    // teeth of the intersecting maps in the human area
    private void scoreSample () { 
        hw.pwrLift(hw.Hieght_of_railing, .5); 
        hw.setClaw(1);
        hw.driveOld(20, .25); 
        hw.doNothing(.5); 
        hw.setClaw(0); 
        hw.pwrLift(hw.Hieght_of_railing + 15, .5);
        hw.straifeEnc(130, .75);

        hw.driveOld(20, -.5);
    
        hw.rotate(180, .5);
        
        hw.pwrLift(hw.Max_extension - 5, .75);
        hw.driveConst(50, .25);
        hw.driveOld(10, .125);
        hw.pwrLift(hw.Hieght_of_bar - 15, -.75);
        hw.setClaw(1);
        hw.pwrLift(0, -.5);
        
        hw.straifeEnc(145, .75);     
        hw.rotate(180, .5);
        hw.pwrLift(hw.Hieght_of_railing, .5); 
        hw.driveConst(40, .75);
    }
    
    @Override public void runOpMode() {
        hw.init(hardwareMap);
        waitForStart();
        hw.clock.reset();
        //hw.extend(0);
        
// score pre-load takes about 8 sec
        hw.setClaw(0);
        hw.pwrLift(hw.Hieght_of_bar + 10, 1);
        hw.driveConst(40, .25);       
        hw.driveOld(25, .125);
        hw.pwrLift(hw.Hieght_of_bar, -1);
        hw.setClaw(1);
        hw.pwrLift(0, -.5);
// score first thingy on the tape
        hw.driveOld(10, -.5);
        hw.straifeEnc(80, .5);
        
        hw.driveConst(65, .5); 
        hw.extend(1); 
        hw.straifeEnc(30, .25);
        hw.driveConst(100, -.5);

// score two samples
        hw.driveConst(20, .25); 
        hw.rotate(180, .75); 
        hw.driveOld(20, .25); 
        
        scoreSample();
        
//provisional 
        hw.pwrLift(hw.Hieght_of_railing, .5); 
        hw.setClaw(1);
        hw.driveOld(20, .25); 
        hw.doNothing(.5); 
        hw.setClaw(0); 
        hw.pwrLift(hw.Hieght_of_railing + 15, .5);
        hw.straifeEnc(130, .75);

        hw.driveOld(20, -.5);
    
        hw.rotate(180, .5);
        
        hw.pwrLift(hw.Max_extension - 5, .75);
        hw.driveConst(50, .25);
        hw.driveOld(10, .125);
        hw.pwrLift(hw.Hieght_of_bar - 15, -.75);
        hw.setClaw(1);
        hw.pwrLift(0, -.5);
        
        hw.driveConst(40, 1);
        hw.straifeEnc(145, 1);     
        // end of provisional
// push rest in 
        hw.driveConst(85, .25); 
        hw.straifeEnc(35, .25);
        hw.driveConst(95, -.5);
        
        hw.driveConst(95, .25); 
        hw.straifeEnc(45, .25);
        hw.driveConst(105, -.5); 
        
        hw.straifeEnc(-80, .5); 
        hw.driveConst(20, .5); 
        hw.rotate(180, .5); 

// score last two
        scoreSample();
        scoreSample();
    }
}
