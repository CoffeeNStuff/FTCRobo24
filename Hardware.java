package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;


public class Hardware {
    
    public static final double CLAW_SUCCESSIVE_WINDOW = 0.25; //test this jonah bruh

    private static final double Max_motor_speed = 140; // cm/s,  
    private static final double Robot_mass = 10.8;  // Kg; 
    private static final double Robot_weight = Robot_mass * 9.8; // N = kg m/s
    private static final double Friction_coefficient = -.169; 
    private static final double F_f = Robot_weight * Friction_coefficient; // N
    private static final double a_f = Friction_coefficient * 9.8; // m/s

    private static final double Max_straife_speed = 95; // cm/s
    private static final double Ticks_per_cm = 383.6 / (10 * Math.PI);
    public final double Max_extension = 70; // cm
    public final double Max_lift_speed = 45; // cm/s, this is accounting for F_f
    public final double Lift_idle = .1; // percent of max speed
    
    public final int Turn_circumfrence = (int) (Math.PI * 70);
    public double liftExtension = 0; // cm 
    private double liftDuration = 0; // ms 
    private double currLiftSpeed = 0; // percent of max speed

    private double currStraifeVelocity = 0;
    
     public static final double Hieght_of_railing = 20; // cm 
     public static final double Hieght_of_bar = 55; // ^
     public static final double Hieght_of_basket = 57; // ^


    public ElapsedTime clock = new ElapsedTime(); 
    private HardwareMap map = null; 

    private DcMotor liftL = null;
    private DcMotor liftR = null;
    public Servo claw = null;
    public Servo wrist = null;
    public Servo backClaw = null;
    public Servo arm = null;

    private DcMotor wheelFr = null;
    private DcMotor wheelFl = null;
    private DcMotor wheelBr = null;
    private DcMotor wheelBl = null;

    
    public Hardware () {} 
    
    public void doNothing (double seconds) { 
        double timei = clock.seconds(); 
        drive(0, 0, 0, 0); 
        lift(Lift_idle); 
        wheelFr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wheelBr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wheelBr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wheelBr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        wheelFr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelFl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelBr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelBl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        if (seconds > 0) { 
            while (clock.seconds() < timei + seconds) {}; 
        }
    }
    
    public void init (HardwareMap map) {
        liftL = map.get(DcMotor.class, "liftL");
        liftR = map.get(DcMotor.class, "liftR");
        
        wheelFr = map.get(DcMotor.class, "wheelFr");
        wheelFl = map.get(DcMotor.class, "wheelFl");
        wheelBr = map.get(DcMotor.class, "wheelBr");
        wheelBl = map.get(DcMotor.class, "wheelBl");
        
        claw = map.get(Servo.class, "claw");
        wrist = map.get(Servo.class, "wrist");
        backClaw = map.get(Servo.class, "backClaw");

        arm = map.get(Servo.class, "arm");

        
        liftR.setDirection(DcMotor.Direction.REVERSE); 
        liftL.setDirection(DcMotor.Direction.FORWARD);
        // setting claw direction
        
        //wheel direction
        wheelFr.setDirection(DcMotor.Direction.FORWARD); 
        wheelFl.setDirection(DcMotor.Direction.REVERSE); 
        wheelBr.setDirection(DcMotor.Direction.FORWARD); 
        wheelBl.setDirection(DcMotor.Direction.REVERSE); 
        
        backClaw.setDirection(Servo.Direction.REVERSE);
     
        wheelFr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wheelBr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wheelBr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wheelBr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
         wheelFr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         wheelFl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         wheelBr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         wheelBl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
             
    } 
    
    public void setClaw (double pos) { 
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

        public void straife  (double distance, double v) { 
            final double t_running = clock.seconds() + Math.abs(distance / (v * Max_straife_speed)); 
            while (clock.seconds() < t_running) { 
                drive(-v, v, v, -v);
            } 
         } 
        public void driveOld (double distance, double v) { 
            // because t_final = t_initial + distance / v
            final double t_running = clock.seconds() + Math.abs(distance / (v * Max_motor_speed)); 
            
            while (clock.seconds() < t_running) { 
                drive(v, v, v, v);
            } 
            
            drive(0, 0, 0, 0);
         } 
         
         public void driveOneSide (double distance, double vl, double vr) { 
            // because t_final = t_initial + distance / v
            final double t_running = clock.seconds() + Math.abs(distance / 
                                                    ((vr == 0 ? vl : vr) * Max_motor_speed)); 
            
            while (clock.seconds() <= t_running) { 
                drive(vr, vl, vr, vl);
            } 
            
            drive(0, 0, 0, 0);
         }
         public void driveConst (double distance, double v) { 
            // because t_final = t_initial + distance / v
            final double t_running = clock.seconds() 
                                     + distance / (Math.abs(v) * Max_motor_speed); 
            
            while (clock.seconds() < t_running) { 
                drive(v, v, v, v);
            } 
            
            while (clock.seconds() < t_running + .25) { 
                drive (-v, -v, -v, -v); 
            } 
            
            drive(0, 0, 0, 0); 
         } 

         // minimizes time and produces the same effect as driveConst without the possible
         // slipage because of abrupt change in speed. applies power of motors, allows 
         // friction to lower speed, and then applies an opposite force
         // the deceleraion phase's duration is controled by the 't' variable
         public void driveP (double distance, double v, double t_dec) { 
            // because delta(d) = vt + a (t^2)/2
            final double d_dec = (v * Max_motor_speed) * t_dec // vt
                                 + (F_f / Robot_mass)          // + a
                                 * (t_dec * t_dec / 2);        // * t^2/2
            // because t_final = t_initial + distance / v
            final double t_running = clock.seconds() 
                                     + (Math.abs(distance) - d_dec) 
                                     / (v * Max_motor_speed); 
            // because v_final = v_initial + acceleration * time and F = ma => a = F / m
            final double v_f = (v * Max_motor_speed // incorrect somehow
                               + a_f * t_dec)
                               / Max_motor_speed;
            
            while (clock.seconds() < t_running) { 
                drive(v, v, v, v);
            } 
            
            // do nothing to decelerate
            while (clock.seconds() < t_running + t_dec) {
                drive(0, 0, 0, 0);
            } 
            
            double t_opp = clock.seconds() + 1; 
            while (clock.seconds() < t_opp) { 
                drive (-v_f, -v_f, -v_f, -v_f); 
            } 
            
            drive(0, 0, 0, 0);
         } 
 
    public void lift (double pwr) { 
                liftR.setPower(pwr);
                liftL.setPower(pwr);
    }

    public void pwrLift (double pwr) { 
        if (liftExtension >= Max_extension && !(pwr < 0)) { 
            liftDuration = clock.seconds();
            currLiftSpeed = 0;
            lift(Lift_idle);
            return;
        } else if (liftExtension <= 0 && !(pwr >= 0) ) {
            liftDuration = clock.seconds();
            currLiftSpeed = 0;
            return;
        }
        
        liftExtension += (clock.milliseconds() - liftDuration) 
                         * (currLiftSpeed * Max_lift_speed) / 1000;
        liftDuration = clock.milliseconds(); 

        currLiftSpeed = pwr;
        if (liftExtension == 0 && pwr == 0) { 
            lift(0);
         } else { 
            lift(pwr == 0 ? Lift_idle : pwr);
        }
     } 
     
    public void extend(double pos) { 
        arm.setPosition(pos); 
    } 
    
    public void rotate(int degree, double pwr) {
            int targetPos = (int) ((degree * Ticks_per_cm * Turn_circumfrence) / 360);
            double timei = clock.seconds();
            
             wheelFr.setTargetPosition(wheelFr.getCurrentPosition() - targetPos); 
             wheelFl.setTargetPosition(wheelFl.getCurrentPosition() + targetPos); 
             wheelBr.setTargetPosition(wheelBr.getCurrentPosition() - targetPos);
             wheelBl.setTargetPosition(wheelBl.getCurrentPosition() + targetPos); 
             
             wheelFr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             wheelFl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             wheelBr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             wheelBl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             
            while (wheelFl.isBusy() & wheelBl.isBusy() & wheelFr.isBusy() & wheelBr.isBusy()) { 
                drive(pwr, pwr, pwr, pwr); 
            } 
            
            doNothing(0);
            
            
     }

         // test with motors on BRAKE mode, if not enough
         // implement a counter force at time_f
         public void pwrLift (double absPos, double pwr) { 
            double d = Math.abs(absPos - liftExtension); 

            double time_f = clock.milliseconds() + (d / (Math.abs(pwr) * Max_lift_speed * .001));
            while (clock.milliseconds() <= time_f) { 
                lift(pwr);
            }
            
            lift(Lift_idle);
            liftExtension = absPos;
         }
         
         public void straifeEnc (int d, double pwr) { 
             int targetPos = (int) (d * Ticks_per_cm);
            
             wheelFr.setTargetPosition(wheelFr.getCurrentPosition() - targetPos); 
             wheelFl.setTargetPosition(wheelFl.getCurrentPosition() + targetPos); 
             wheelBr.setTargetPosition(wheelBr.getCurrentPosition() + targetPos);
             wheelBl.setTargetPosition(wheelBl.getCurrentPosition() - targetPos); 
             
             wheelFr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             wheelFl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             wheelBr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             wheelBl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             
            while (wheelFl.isBusy() & wheelBl.isBusy() & wheelFr.isBusy() & wheelBr.isBusy()) { 
                drive(pwr, pwr, pwr, pwr); 
            }  

            
            doNothing(0);
            
 
         }
         
         public void straifeEnc (double pwr) { 
             if (pwr == 0) { 
                doNothing(0); 
                return; 
             } if (pwr == currStraifeVelocity) { 
                drive(1, 1, 1, 1); 
             } else { 
                 currStraifeVelocity = pwr; 
                 int d = (int) (370 * Ticks_per_cm * pwr); 
                 wheelFr.setTargetPosition(wheelFr.getCurrentPosition() - d); 
                 wheelFl.setTargetPosition(wheelFl.getCurrentPosition() + d); 
                 wheelBr.setTargetPosition(wheelBr.getCurrentPosition() + d);
                 wheelBl.setTargetPosition(wheelBl.getCurrentPosition() - d); 
                 
                 wheelFr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                 wheelFl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                 wheelBr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                 wheelBl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
                drive(pwr, pwr, pwr, pwr);
             } 
         }
}
