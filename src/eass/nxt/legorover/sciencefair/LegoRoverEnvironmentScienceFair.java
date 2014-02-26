// ----------------------------------------------------------------------------
// Copyright (C) 2013 Louise A. Dennis, and  Michael Fisher 
//
// This file is part of the Lego Rover Library.
// 
// The Lego Rover Library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 3 of the License, or (at your option) any later version.
// 
// The Lego Rover Library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with the EASS Library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
// 
// To contact the authors:
// http://www.csc.liv.ac.uk/~lad
//
//----------------------------------------------------------------------------
package eass.nxt.legorover.sciencefair;

import ail.util.AILexception;
import ail.mas.NActionScheduler;
import ail.syntax.Unifier;
import ail.syntax.Action;
import ail.syntax.Literal;
import ail.syntax.VarTerm;
import ail.syntax.NumberTerm;
import ail.semantics.AILAgent;

import eass.mas.nxt.BasicRobot;
import eass.mas.nxt.EASSNXTEnvironment;
import eass.mas.nxt.EASSTouchSensor;
import eass.mas.nxt.EASSUltrasonicSensor; 
import eass.mas.nxt.EASSSoundSensor;
import eass.mas.nxt.EASSLightSensor;
import eass.mas.nxt.EASSSensor;
import eass.mas.nxt.LegoRobot;
import eass.mas.nxt.NXTBrick;
import eass.mas.nxt.RemoteMotor;

import lejos.robotics.navigation.DifferentialPilot;

import java.io.PrintStream;


/**
 * A simple environment for NXT Robots including sub-classes for two robot builds.  We need a more generic way of representing different robots
 * and robot configurations.
 * 
 * @author louiseadennis
 *
 */
public class LegoRoverEnvironmentScienceFair extends EASSNXTEnvironment {
	boolean rule1 = false;
	int travelspeed = 10;
	int turningspeed = 15;
	static Literal activer1 = new Literal("active");
	static {activer1.addTerm(new Literal("rule1"));};
	
	static Literal obstacler1 = new Literal("obstaclerule");
	static {obstacler1.addTerm(new Literal("rule1"));};
	
	/**
	 * Construct the Environment.
	 */
	public LegoRoverEnvironmentScienceFair() {
		super();
		NActionScheduler s = new NActionScheduler(20);
		s.addJobber(this);
		setScheduler(s);
		addPerceptListener(s);
	}
	
	/**
	 * Create the relevant object for the robot.
	 */
	public LegoRobot createRobot(String agent) {
		LegoRobot robot;
		robot = new ScienceFairRobot(agent, getAddress(agent));
		addSharedBelief(agent, obstacler1);
		return robot;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see eass.mas.nxt.EASSNXTEnvironment#executeAction(java.lang.String, ail.syntax.Action)
	 */
	public Unifier executeAction(String agName, Action act) throws AILexception {
		   Unifier u = new Unifier();
		   String rname = rationalName(agName);
		   LegoRobot robot = getRobot(rname);
		   System.err.println(act);
		   			     
		   	if (act.getFunctor().equals("forward")) {
		   		if (robot.hasPilot()) {
		   			robot.getPilot().forward();
		   		}
		   	} else if (act.getFunctor().equals("backward")) {
		   		if (robot.hasPilot()) {
		   			robot.getPilot().backward();
		   		}
		   	} else if (act.getFunctor().equals("stop")) {
		   		if (robot.hasPilot()) {
		   			robot.getPilot().stop();
		   		}
		   	} else if (act.getFunctor().equals("right")) {
		   		if (robot.hasPilot()) {
		   			robot.getPilot().rotateRight();
		   		}
		   	} else if (act.getFunctor().equals("left")) {
		   		if (robot.hasPilot()) {
		   			robot.getPilot().rotateLeft();
		   		}
		   	} else if (act.getFunctor().equals("right90")) {
		   		if (robot.hasPilot()) {
		   			robot.getPilot().rotate(-90);
		   		}
		   	} else if (act.getFunctor().equals("left90")) {
		   		if (robot.hasPilot()) {
		   			robot.getPilot().rotate(90);
		   		}
		   	} else if (act.getFunctor().equals("backward10")) {
		   		if (robot.hasPilot()) {
		   			robot.getPilot().travel(-10);
		   		}
		   	} else if (act.getFunctor().equals("forward10")) {
		   		if (robot.hasPilot()) {
		   			robot.getPilot().travel(10);
		   		}
		   	} else if (act.getFunctor().equals("rule1")) {
		   		if (!rule1) {
		   			addSharedBelief(rname, activer1);
		   			rule1 = true;
		   		} else {
		   			removeSharedBelief(rname, activer1);
		   			rule1 = false;
		   		}
		   	} else if (act.getFunctor().equals("r1action1")) {
		   		Literal oldr1act = new Literal("rule");
		   		oldr1act.addTerm(new Literal("rule1"));
		   		oldr1act.addTerm(new Literal("act1"));
		   		oldr1act.addTerm(new VarTerm("A"));
		   		removeUnifiesShared(rname, oldr1act);
		   		Literal r1act = new Literal("rule");
		   		r1act.addTerm(new Literal("rule1"));
		   		r1act.addTerm(new Literal("act1"));
		   		r1act.addTerm(act.getTerm(0));
		   		addSharedBelief(rname, r1act);
		   	} else if (act.getFunctor().equals("r1action2")) {
		   		Literal oldr1act = new Literal("rule");
		   		oldr1act.addTerm(new Literal("rule1"));
		   		oldr1act.addTerm(new Literal("act2"));
		   		oldr1act.addTerm(new VarTerm("A"));
		   		removeUnifiesShared(rname, oldr1act);
		   		Literal r1act = new Literal("rule");
		   		r1act.addTerm(new Literal("rule1"));
		   		r1act.addTerm(new Literal("act2"));
		   		r1act.addTerm(act.getTerm(0));
		   		addSharedBelief(rname, r1act);
		   	} else if (act.getFunctor().equals("wait")) {
				try {
	    			Thread.sleep(500);
	    		} catch (InterruptedException ie) {
	    			System.out.println("catching interrupt");
	    		}
				addSharedBelief(rname, new Literal("waited"));
		   	} else if (act.getFunctor().equals("obstacle_distance")) {
		   		Literal distance_threshold = new Literal("change_distance");
		   		distance_threshold.addTerm(act.getTerm(0));
		   		String abstraction_name = "abstraction_" + rname;
		   		addPercept(abstraction_name, distance_threshold);
		   	} else {
		   		u = super.executeAction(agName, act);
		   	}
		   	
		   	return u;
	}
	
	/**
	 * Actions are called by an agent called abstract_rname, but the actual robot's name is just rname.  This method does
	 * the necessary string manipulations.
	 * @param name
	 * @return
	 */
	public String rationalName(String name) {
		int index = 12;
		if (name.length() > index) {
			return name.substring(index);
		} else {
			return name;
		}
	}
	
	/**
	 * Setting the print stream if the robot has an Ultrasonic sensor.  This means output from the sensor can be piped to the
	 * interface.
	 * @param rname
	 * @param s
	 */
	public void setUltraPrintStream(String rname, PrintStream s) {
		LegoRobot robot = getRobot(rname);
		((ScienceFairRobot) robot).setUPrintStream(s);
	}
	
	/**
	 * A class for Noor type robots (Robots with one ultrasonic sensor) and two motors.
	 * @author louiseadennis
	 *
	 */
	public class ScienceFairRobot extends BasicRobot {
		DifferentialPilot pilot;
		PrintStream uPrintStream = System.out;
		
		/**
		 * Set up the configuration of the robot.
		 * @param name
		 * @param address
		 */
		public ScienceFairRobot(String name, String address) {
			super(name, address);
			
			if (isConnected()) {
				NXTBrick brick = getBrick();
				EASSUltrasonicSensor uSensor = new EASSUltrasonicSensor(brick, 2);
				setSensor(2, uSensor);
				uSensor.setPrintStream(uPrintStream); 
				
				
				RemoteMotor motorLeft = brick.getMotorC();
				RemoteMotor motorRight = brick.getMotorA();
				pilot = new  DifferentialPilot(5.5, 10.25, motorLeft, motorRight);
				pilot.setTravelSpeed(10);
				pilot.setRotateSpeed(15);
				setPilot(pilot);
			}
		}
		
		/**
		 * Grab the print stream from the ultrasonic sensor.
		 * @param s
		 */
		public void setUPrintStream(PrintStream s) {
			uPrintStream = s;
			EASSSensor uSensor = getSensor(2);
			if (uSensor != null) {
				uSensor.setPrintStream(s);
			}
		}
		
	}


}
