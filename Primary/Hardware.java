package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.concurrent.Callable;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


public class Hardware {
	
/* How to find each constant
 * 1. weight) 	weight robot, this is the weight
 * 2. mass) 	divide by 9.8, this is the mass
 * 3. v for d)	measure displacment and time
 *    		 	to finds V. MIND this cannot be used as
 *    		 	max speed bc friction is not subtracted
 * 4. F_n) 		for the same distance,d, drive and cut
 *    			power from the motors, call teh length 
 *    			progressed afterwards is d_1, 
 *					d_1 = p - F_f
 *				  		 = mv - (weight * Static_coefficient)
 * 5. max speed) subtract F_f from v of d 
 */
	 private static final double Max_motor_speed = 0; // cm/s,  
	 private static final double Robot_mass = 0;  // Kg
	 private static final double Robot_weight = Robot_mass * 9.8; // N
	 private static final double Static_coeffient = 0; 


    private static final double Max_extension = 0; // Cm
    private static final double Max_lift_speed = 0; // cm/s
	 private double final Lift_idle = .15; // percent of max speed
    private double liftExtension = 0; // cm 
    private double liftDuration = 0; // s 
	 private double currLiftSpeed = 0; // percent of max speed

	 private static final double Hieght_of_railing = 0; // cm 
	 private static final double Hieght_of_bar = 0; // ^
	 private static final double Hieght_of_low_basket = 0; // ^
	 private static final double Hieght_of_high_basket = 0; // ^

    public ElapsedTime clock = new ElapsedTime(); 
    private HardwareMap map = null; 

    private DcMotor liftL = null;
    private DcMotor liftR = null;
    
    
    private DcMotor wheelFr = null;
    private DcMotor wheelFl = null;
    private DcMotor wheelBr = null;
    private DcMotor wheelBl = null;

    
    public Hardware () {} 
    
    public void init (HardwareMap map) {
        liftL = map.get(DcMotor.class, "liftL");
        liftR = map.get(DcMotor.class, "liftR");
        
        wheelFr = map.get(DcMotor.class, "wheelFr");
        wheelFl = map.get(DcMotor.class, "wheelFl");
        wheelBr = map.get(DcMotor.class, "wheelBr");
        wheelBl = map.get(DcMotor.class, "wheelBl");
        
        liftR.setDirection(DcMotor.Direction.REVERSE); 
        liftL.setDirection(DcMotor.Direction.FORWARD);
        // setting claw direction
        
        //wheel direction
        wheelFr.setDirection(DcMotor.Direction.REVERSE); 
        wheelFl.setDirection(DcMotor.Direction.REVERSE); 
        wheelBr.setDirection(DcMotor.Direction.FORWARD); 
        wheelBl.setDirection(DcMotor.Direction.REVERSE); 

    } 
    
    // positive pwr is rightwards, negative is leftwards
    public void straife (double distance, double pwr) { 
       double timef = clock.seconds() + (distance / (pwr * 101.5)); 
       
       while (clock.seconds() < timef) { 
           drive(pwr, -pwr, -pwr, pwr);
       } 
    } 
	 
	 public void setClaw (int pos) { 
        claw.setPosition(pos); 
    }

    public void drive (double fr, double fl, double br, double bl) { 
        wheelFr.setPower(fr);
        wheelFl.setPower(fl);
        wheelBr.setPower(br);
        wheelBl.setPower(bl);
    }
	
	// Q: if the wheels are set to float will the extra rotations of the wheels
	// do to P be non-negligible, if so set to brake mode and ensure the
	// friction coefficient is according
	
	 public void driveConst (double distance, double speed) { 
			double time_i = clock.seconds(); 
			double time_f = time_i + distance / speed * Max_speed; 
			double p_f = Robot_mass * (speed * Max_speed; 

			while (clock.seconds() < time_f) { 
				drive(speed); 
			} 

			// completes to complete halt to combat momentum by opposite force
			while (clock.seconds() < time_f + .5) {
				drive(-2p_f / Max_speed); 
			}
	 } 

	 // minimizes time and produces the same effect as driveConst without the possible
	 // slipage because of abrupt change in speed. applies power of motors, allows 
	 // friction to lower speed, and then applies an opposite force
	 // the deceleraion phase's duration is controled by the 't' variable
	 public void driveP (double distance, double speed, double t_dec) { 
			double time_i = clock.seconds(); 
			double time_f = timei + distance / (speed * Max_speed); 
			double time_dec = time_f + t_dec; 
			// P at a given time equals: 
			// (mass * speed) Normal Force * force from friction * time friction is applied
			double p_f = Robot_mass * (speed * Max_speed) 
							 - t_dec(Robot_weight * Friction_Coefficient); 

			while (clock.seconds() < time_dec) { 
				drive(speed); 
			} 

			while (clock.seconds() < time_dec) {};  
			// completes to complete halt to combat momentum by opposite force
			while (clock.seconds() < time_dec + .5) {
				drive(-2p_f / Max_speed); 
			}
	 } 
 
    public void lift (double pwr) { 
		liftR.setPower(pwr);
		liftL.setPower(pwr);
    }

	 // TOCHECK ensure lift weight AND idle pwr are BOTH subtracted
	 public void pwrLift (double pwr) { 
		if (liftExtension == Max_extension && pwr > 0 ||
			 liftExtension == 0 && pwr < 0) {
			return;
		}
		
		 if (pwr != currLiftPwr) { 
			liftExtension += (clock.seconds() - liftDuration) * (currLiftPwr * Max_speed - Lift_idle);
			liftDuration = clock.seconds(); 	


			/* if we want to not idle at no height: 
			 * if (pwr == 0) { 
			 * 	currPwr = liftExtension == 0 && pwr == 0 ? 0 : pwr + Lift_idle; 
			 * } else { 
			 * 	currPwr = pwr; 
			 * } 
			 */ 
		}

		lift(currLiftPwr);
	 } 

	 // test with motors on BRAKE mode, if not enough
	 // implement a counter force at time_f
	 public void pwrLift (double distance, double pwr) { 
		 time_i = clock.seconds(); 
		 time_f = time_i + d / (Lift_max_speed(pwr  - Lift_Idle));

		 while (clock.seconds() < timef) { 
			lift(pwr);
		 }
	 } 

};
