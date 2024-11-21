package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.concurrent.Callable;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


public class Hardware {

    public static final double IdlePwr_C = .05;
	 public static final double Hieght_of_railing = 0; 
	 public static final double Hieght_of_bar = 0;
	 public static final double Hieght_of_low_basket = 0
	 public static final double Hieght_of_high_basket = 0

    private static final double MAX_EXTENSION = 0; // TODO In cm
																	 
    
    public ElapsedTime clock = new ElapsedTime(); 
    private double currExtension = 0;
    private HardwareMap map = null; 

    private DcMotor liftL = null;
    private DcMotor liftR = null;
    
    private DcMotor actuator = null; 
    
    private DcMotor wheelFr = null;
    private DcMotor wheelFl = null;
    private DcMotor wheelBr = null;
    private DcMotor wheelBl = null;
    private Servo claw = null;

    
    public Hardware () {} 
    
    public void init (HardwareMap map) {
        liftL = map.get(DcMotor.class, "liftL");
        liftR = map.get(DcMotor.class, "liftR");
        
        actuator = map.get(DcMotor.class, "actuator"); 
        
        wheelFr = map.get(DcMotor.class, "wheelFr");
        wheelFl = map.get(DcMotor.class, "wheelFl");
        wheelBr = map.get(DcMotor.class, "wheelBr");
        wheelBl = map.get(DcMotor.class, "wheelBl");
        claw = map.get(Servo.class, "claw");
        // initiating the claw
        
        liftR.setDirection(DcMotor.Direction.REVERSE); 
        liftL.setDirection(DcMotor.Direction.FORWARD);
        // setting claw direction
        claw.setDirection(Servo.Direction.FORWARD);
        actuator.setDirection(DcMotor.Direction.FORWARD); 
        
        //wheel direction
        wheelFr.setDirection(DcMotor.Direction.REVERSE); 
        wheelFl.setDirection(DcMotor.Direction.REVERSE); 
        wheelBr.setDirection(DcMotor.Direction.FORWARD); 
        wheelBl.setDirection(DcMotor.Direction.REVERSE); 

    } 
    
    public void drive (double fr, double fl, double br, double bl) { 
        wheelFr.setPower(fr);
        wheelFl.setPower(fl);
        wheelBr.setPower(br);
        wheelBl.setPower(bl);
    }
 
	 // the maximum speed of the wheels is found to be 101.5 cm/s, as such 
	 // multiplying the maximum by the desired percent will produce the 
	 // correct speed 
    public void move (double distance, double speed) {
        double timef = clock.seconds() + (distance / (speed * 101.5)); 
       
        while (clock.seconds() < timef) { 
            drive(speed, speed, speed, speed); 
        }
    } 
    
    // positive pwr is rightwards, negative is leftwards
    public void straife (double distance, double pwr) { 
       double timef = clock.seconds() + (distance / (pwr * 101.5)); 
       
       while (clock.seconds() < timef) { 
           drive(pwr, -pwr, -pwr, pwr);
       } 
    } 
	 
	 // the max speed of the lift motors differ from those of the wheel
	 // because the load multiplied by gravity is always pushing oppositly;
	 // the max speed is TODO 
    public void lift (double pwr) { 
		 		/* if (currExtension >= MAX_EXTENSION && pwr > 0 || 
				    currExtension == 0 && pwr < 0) { 
					return; 
				} */ 

            liftR.setPower(pwr);
            liftL.setPower(pwr);
            // currExtension += pwr * maxSpeedofLift TODO in metric
    }
    
  /*  public void liftEnc (double position, double pwr) { 
       liftL.setMode(RUN_TO_POSITION);
       liftR.setMode(RUN_TO_POSITION);
       
       liftL.setPower(pwr); 
       liftR.setPower(pwr); 
       
       liftL.setTargetPosition(pos); 
       liftR.setTargetPosition(pos); 
       
    } 
    
     public void liftRel (double distance, double pwr) { 
      liftEnc(liftR.getCurrentPosition() + distance, pwr);
    } */
 
	 public void setClaw (int pos) { 
        claw.setPosition(pos); 
    }
    
    public void actuate (double pwr) { 
        actuator.setPower(pwr); 
    } 
        
};
