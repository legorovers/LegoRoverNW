package eass.nxt.legorover.wizardofoz;

import lejos.nxt.*;
// import lejos.nxt.comm.*;

import java.io.*;

import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.*;

/*
 * Codes from agent
 * 1. follow line
 * 2. forward
 * 3. stop
 * 4. left
 * 5. right
 * 6. backward
 * 7. back + distance
 * 8. right + angle
 * 9. left + angle
 * 10. forward + distance
 * 11. speed + rate
 * 12. rotatespeed + rate
 * 13. close
 */


public class Toto {	
	static LightSensor light = new LightSensor(SensorPort.S3);
	static UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S2);
	
	static RegulatedMotor motorLeft = Motor.C;
	static RegulatedMotor motorRight = Motor.A;
	
	static double wheelDiameter = 5;
	static double trackWidth = 10;
	
	static final int follow_line = 1;
	static final int forward = 2;
	static final int stop = 3;
	static final int left = 4;
	static final int right = 5;
	static final int backward = 6;
	static final int backwardN = 7;
	static final int rightN = 8;
	static final int leftN = 9;
	static final int forwardN = 10;
	static final int speedC = 11;
	static final int rspeedC = 12;
	static final int closed = 13;
	
	static final int blackWhiteThreshold = 30;
	
	// Setting various values based on set up values above.
	static float _leftTurnRatio = (float)(trackWidth / (wheelDiameter));
    static float _leftDegPerDistance = (float) (360 / (Math.PI * wheelDiameter));
    static float _rightTurnRatio = (float)(trackWidth / (wheelDiameter));
    static float _rightDegPerDistance = (float)(360 / (Math.PI * wheelDiameter));
    
    
    // Speeds and Accelerations
    static int _travelSpeed;
    static int _rotateSpeed;
    static int _acceleration;
    
    static boolean ended = false;
 

	// static DifferentialPilot pilot = new DifferentialPilot(5.5, 10.25, motorLeft, motorRight);
	
	public static void main(String args[]) throws Exception {
		setTravelSpeed(10);
		setRotateSpeed(45);
		setAcceleration(75);

		LCD.drawString("Getting Connection", 0, 2);

		// Connect to the laptop
		lejos.nxt.comm.NXTConnection connection = lejos.nxt.comm.Bluetooth.waitForConnection();

		DataOutputStream out = connection.openDataOutputStream();
		DataInputStream in = connection.openDataInputStream();

		// Set up sensors
		LCD.clear();
		light.setFloodlight(true);
		ultra.setMode(UltrasonicSensor.MODE_CONTINUOUS);
		
		// Follow line until ESCAPE is pressed
		LCD.drawString("Press ESCAPE", 0, 2);
		LCD.drawString("to stop ", 0, 3);
		
		// flag for tracking when robot is involved in an action.
		boolean acting = false;
		int command = 0;
		int arg = 0;
		int commandcompletecode = 0;
				
		while (! Button.ESCAPE.isDown() && ! ended){
			int lightvalue = light.readValue();
			int ultravalue = ultra.getDistance();
			
			if (in.available() > 0 || acting) {
				if (in.available() > 0) {
					command = in.readInt();
					LCD.drawInt(command, 0, 0);
					if (command > 6) {
						arg = in.readInt();
					}
				}
				
				
				if (command == follow_line) {
					acting = true;
					setRotateSpeed(_rotateSpeed);
					try {
						LCD.drawString("Light %: ", 0, 4);
						if (light.readValue() > blackWhiteThreshold){
							// On white, turn right
							LCD.drawString("Turn Right", 0, 5);
							motorLeft.forward();
							motorRight.stop();
						}
						else{
							// On black, turn left
							LCD.drawString("Turn Left", 0, 5);				
							motorRight.forward();
							motorLeft.stop();
						}
						LCD.drawInt(lightvalue, 3, 9, 4);
					} catch (Exception e) {
						
					}
					commandcompletecode = 0;
				} else if (command == forward) {
					LCD.drawString("Forward", 0, 5);
					setTravelSpeed(_travelSpeed);
					acting = false;
					fwd();
					commandcompletecode = 0;
				} else if (command == stop) {
					LCD.drawString("Stop", 0, 5);
					acting = false;
					stop();
					commandcompletecode = 0;
				} else if (command == right) {
					LCD.drawString("Right", 0, 5);
					setRotateSpeed(_rotateSpeed);
					acting = false;
					motorLeft.forward();
					motorRight.backward();
					commandcompletecode = 0;
				} else if (command == left) {
					LCD.drawString("Left", 0, 5);
					setRotateSpeed(_rotateSpeed);
					acting = false;
					motorRight.forward();
					motorLeft.backward();
					commandcompletecode = 0;
				} else if (command == backward) {
					LCD.drawString("Backward", 0, 5);
					setTravelSpeed(_travelSpeed);
					acting = false;
					motorLeft.backward();
					motorRight.backward();
					commandcompletecode = 0;
				} else if (command == forwardN) {
					LCD.drawString("Forward ", 0, 5);
					String s = " " + arg;
					LCD.drawString(s, 9, 5);
					setTravelSpeed(_travelSpeed);
					acting = true;
					if (arg > 0) {
						motorLeft.rotate((int) _leftDegPerDistance, true);
						motorRight.rotate((int) _rightDegPerDistance);
						arg--;
					} else {
						commandcompletecode = forwardN;
						acting = false;
					}
				} else if (command == backwardN) {
					LCD.drawString("Backward ", 0, 5);
					String s = " " + arg;
					LCD.drawString(s, 9, 5);
					setTravelSpeed(_travelSpeed);
					acting = true;
					if (arg > 0) {
						motorLeft.rotate(- (int) _leftDegPerDistance, true);
						motorRight.rotate(- (int) _rightDegPerDistance);
						arg--;
					} else {
						commandcompletecode = backwardN;
						acting = false;
					}
				} else if (command == rightN) {
					LCD.drawString("Right ", 0, 5);
					String s = " " + arg;
					LCD.drawString(s, 9, 5);
					setRotateSpeed(_rotateSpeed);
					acting = true;
					if (arg > 5) {
						float motorturnL = 5 * _leftTurnRatio;
						float motorturnR = 5 * _rightTurnRatio;
						motorLeft.rotate((int) motorturnL, true);
						motorRight.rotate(- (int) motorturnR);
						LCD.drawString("LM " + motorturnL, 0, 6);
						LCD.drawString("RM " + motorturnR, 5, 6);
						arg = arg - 5;
					} else {
						motorLeft.rotate(arg * (int) _leftTurnRatio, true);
						motorRight.rotate(arg * - (int) _rightTurnRatio);
						arg = 0;
						acting = false;
						commandcompletecode = rightN;
					}
				} else if (command == leftN) {
					LCD.drawString("Left ", 0, 5);
					String s = " " + arg;
					LCD.drawString(s, 9, 5);
					setRotateSpeed(_rotateSpeed);
					acting = true;
					if (arg > 5) {
						float motorturnL = 5 * _leftTurnRatio;
						float motorturnR = 5 * _rightTurnRatio;
						motorLeft.rotate(- (int) motorturnL, true);
						motorRight.rotate((int) motorturnR);
						LCD.drawString("LM " + motorturnL, 0, 6);
						LCD.drawString("RM " + motorturnR, 5, 6);
						arg = arg - 5;
					} else {
						motorLeft.rotate(arg * - (int) _leftTurnRatio, true);
						motorRight.rotate(arg * (int) _rightTurnRatio);
						arg = 0;
						acting = false;
						commandcompletecode = leftN;
					}
				} else if (command == speedC) {
					setTravelSpeed(arg);
					arg = 0;
				} else if (command == rspeedC) {
					setRotateSpeed(arg);
					arg = 0;
				} else if (command == closed) {
					ended = true;
				}
			}
			
			Thread.sleep(10);
			
			out.writeInt(lightvalue);
			out.flush();
			out.writeInt(ultravalue);
			out.flush();
			out.writeInt(commandcompletecode);
			out.flush();

		}
	}
	
	private static void fwd() {
		motorLeft.forward();
		motorRight.forward();
	}
	
	private static void stop()
	  {
		motorLeft.stop(true);
	    motorRight.stop(true);
	    waitComplete();                           
	   // setMotorAccel(_acceleration);  // restror acceleration value
	  }
	
	 /**
	   * wait for the current operation on both motors to complete
	   */
	  private static void waitComplete()
	  {
	    while(isMoving())
	    {
	      motorLeft.waitComplete();
	      motorRight.waitComplete();
	    }
	  }
	  
	  /**
	   * @return true if the NXT robot is moving.
	   **/
	  public static boolean isMoving()
	  {
	    return motorLeft.isMoving() || motorRight.isMoving();
	  }


	  private static void setSpeed(final int leftSpeed, final int rightSpeed)
	  {
	    motorLeft.setSpeed(leftSpeed);
	    motorRight.setSpeed(rightSpeed);
	  }

	  /**
	   * set travel speed in wheel diameter units per second
	   * @param travelSpeed : speed in distance (wheel diameter)units/sec
	   */
	   public static void setTravelSpeed(final double travelSpeed)
	   {
		   _travelSpeed = (int) travelSpeed;
		   setSpeed((int)Math.round(travelSpeed * _leftDegPerDistance), (int)Math.round(travelSpeed * _rightDegPerDistance));
	   }

	   public static double getTravelSpeed()
	   {
	     return _travelSpeed;
	   }

	   /**
	    * Sets the normal acceleration of the robot in distance/second/second  where
	    * distance is in the units of wheel diameter. The default value is 4 times the 
	    * maximum travel speed.  
	    * @param acceleration
	    */
	   public static void setAcceleration(int acceleration)
	   {
	    
	   _acceleration = acceleration;
	    setMotorAccel(_acceleration);
	   }
	  /**
	    * helper method for setAcceleration and quickStop
	    * @param acceleration 
	    */
	   private static void setMotorAccel(int acceleration)         
	   {
	        int motorAccel  = (int)Math.round(0.5 * acceleration * (_leftDegPerDistance + _rightDegPerDistance));
	        motorLeft.setAcceleration(motorAccel);
	        motorRight.setAcceleration(motorAccel); 
	   }

	   public static double getMaxTravelSpeed()
	   {
	     return Math.min(motorLeft.getMaxSpeed(), motorRight.getMaxSpeed()) / Math.max(_leftDegPerDistance, _rightDegPerDistance);
	   }

	   /**
	    * sets the rotation speed of the vehicle, degrees per second
	    * @param rotateSpeed
	    */
	   public static void setRotateSpeed(double rotateSpeed)
	   {
	     _rotateSpeed = (int)rotateSpeed;
	     setSpeed((int)Math.round(rotateSpeed * _leftTurnRatio), (int)Math.round(rotateSpeed * _rightTurnRatio));
	   }


	   public static double getRotateSpeed()
	   {
	     return _rotateSpeed;
	   }


	   public static float getMaxRotateSpeed()
	   {
	     return Math.min(motorLeft.getMaxSpeed(), motorRight.getMaxSpeed()) / Math.max(_leftTurnRatio, _rightTurnRatio);
	     // max degree/second divided by degree/unit = unit/second
	   }


	   public static double getRotateMaxSpeed()
	   {
	     return getMaxRotateSpeed();
	   }

}
