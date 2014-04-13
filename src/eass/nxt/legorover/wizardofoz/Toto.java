package eass.nxt.legorover.wizardofoz;

import lejos.nxt.*;
import lejos.nxt.comm.*;

import java.io.*;

import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.*;

public class Toto {
	static String left = "Turn left ";
	static String right= "Turn right";
	
	static LightSensor light = new LightSensor(SensorPort.S3);
	static UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S2);
	static TouchSensor touch = new TouchSensor(SensorPort.S4);
	
	static RegulatedMotor motorLeft = Motor.C;
	static RegulatedMotor motorRight = Motor.B;
	
	static final int forward = 1;
	static final int stop = 3;
	static final int flt = 4;
	static final int power = 80;	
	
	static DifferentialPilot pilot = new DifferentialPilot(5.5, 10.25, motorLeft, motorRight);
	
	public static void main(String args[]) throws Exception {
		final int blackWhiteThreshold = 45;

		LCD.drawString("Getting Connection", 0, 2);

		NXTConnection connection = Bluetooth.waitForConnection();
		
		// Show light percent until LEFT is pressed
		LCD.clear();
		LCD.drawString("Press LEFT", 0, 2);
		LCD.drawString("to start", 0, 3);
		while (! Button.LEFT.isDown()){
			LCD.drawInt(light.readValue(), 3, 9, 0);
		}
		DataOutputStream out = connection.openDataOutputStream();
		DataInputStream in = connection.openDataInputStream();
		// Follow line until ESCAPE is pressed
		LCD.drawString("Press ESCAPE", 0, 2);
		LCD.drawString("to stop ", 0, 3);
		light.setFloodlight(true);
		ultra.setMode(UltrasonicSensor.MODE_CONTINUOUS);
		
		boolean follow_line = false;
		
		while (! Button.ESCAPE.isDown()){
			int lightvalue = light.readValue();
			int ultravalue = ultra.getDistance();
			boolean touchvalue = touch.isPressed();
			
			if (in.available() > 0 || follow_line) {
				int command = 0;
				if (in.available() > 0) {
					command = in.readInt();
					LCD.drawInt(command, 0, 2);
				}
				
				
				if (command == 3) {
					follow_line = false;
					MotorPort.B.controlMotor(0, stop);
					MotorPort.C.controlMotor(0, stop);
				} else if (command == 2) {
					follow_line = false;
					MotorPort.B.controlMotor(power, forward);
					MotorPort.C.controlMotor(power, forward);
				} else if (command == 1 || follow_line) {
					follow_line = true;
					try {
						LCD.drawString("Light %: ", 0, 0);
						if (lightvalue > blackWhiteThreshold){
							// On white, turn right
							LCD.drawString(right, 0, 1);
							MotorPort.B.controlMotor(0,stop);
							MotorPort.C.controlMotor(power, forward);
						}
						else{
							// On black, turn left
							LCD.drawString(left, 0, 1);				
							MotorPort.B.controlMotor(power, forward);
							MotorPort.C.controlMotor(0,stop);
						}
						LCD.drawInt(lightvalue, 3, 9, 0);
					} catch (Exception e) {
						
					}
				} else {
					follow_line = false;
				}
			} else {
			}
			
			Thread.sleep(10);
			
			out.writeInt(lightvalue);
			out.flush();
			out.writeInt(ultravalue);
			out.flush();
			out.writeBoolean(touchvalue);
			out.flush();

		}
	}
	

}
