package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous (name = "FarExp", group = "Auto")
public class ExpFarAuto extends LinearOpMode {
    Hardware hw = new Hardware(); 
    
public void multitask (MyThread[] tasks) { 
	for (int i = 0; i <= tasks.length; i++) { 
		tasks[i].start();
	}
}

// an example calling hw.driveOneWheel(90, 0, .5) and hw.extend(1);

// this is called a lambda expression, just another syntax of writing a function
// by defining it this way i can express the arguments as an array of 3 ints, which
// is easier and marginally faster tha alternate methods


@Override public void runOpMode() {

   hw.init(hardwareMap);
   waitForStart();
   hw.clock.reset();
 //score pre-load takes about 8 sec
   hw.setClaw(0);

	runningTask<int[2], void> = (args) -> hw.driveConst(args[0], args[1]); 
	extendingTask<int, void> = (arg) -> hw.pwrLift(args[0], args[1]); 

	multitask( 
		{ new MyThread<> (runningTask, { 40, .25}), 
		  new MyThread<> (extendingTask, { hw.Hieght_of_bar + 10, 1 })
		}
	); 
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
