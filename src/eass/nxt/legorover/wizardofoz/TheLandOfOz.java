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
package eass.nxt.legorover.wizardofoz;

import ail.util.AILexception;
import ail.mas.NActionScheduler;
import ail.syntax.NumberTermImpl;
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

import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;

import java.io.PrintStream;
import java.io.DataOutputStream;


/**
 * A simple environment for NXT Robots including sub-classes for two robot builds.  We need a more generic way of representing different robots
 * and robot configurations.
 * 
 * @author louiseadennis
 *
 */
public class TheLandOfOz extends EASSNXTEnvironment {
	boolean rule1 = false;
	boolean rule2 = false;
	boolean rule3 = false;
	int travelspeed = 10;
	int turningspeed = 15;
	static Literal activer1 = new Literal("active");
	static {activer1.addTerm(new Literal("rule1"));};
	static Literal activer2 = new Literal("active");
	static {activer2.addTerm(new Literal("rule2"));};
	static Literal activer3 = new Literal("active");
	static {activer3.addTerm(new Literal("rule3"));};
	
	static Literal obstacler1 = new Literal("obstaclerule");
	static {obstacler1.addTerm(new Literal("rule1"));};
	static Literal bumpr1 = new Literal("bumprule");
	static {bumpr1.addTerm(new Literal("rule1"));};
	static Literal soundr1 = new Literal("soundrule");
	static {soundr1.addTerm(new Literal("rule1"));};
	static Literal mineralr1 = new Literal("mineralrule");
	static {mineralr1.addTerm(new Literal("rule1"));};
	static Literal obstacler2 = new Literal("obstaclerule");
	static {obstacler2.addTerm(new Literal("rule2"));};
	static Literal bumpr2 = new Literal("bumprule");
	static {bumpr2.addTerm(new Literal("rule2"));};
	static Literal soundr2 = new Literal("soundrule");
	static {soundr2.addTerm(new Literal("rule2"));};
	static Literal mineralr2 = new Literal("mineralrule");
	static {mineralr2.addTerm(new Literal("rule2"));};
	static Literal obstacler3 = new Literal("obstaclerule");
	static {obstacler3.addTerm(new Literal("rule3"));};
	static Literal bumpr3 = new Literal("bumprule");
	static {bumpr3.addTerm(new Literal("rule3"));};
	static Literal soundr3 = new Literal("soundrule");
	static {soundr3.addTerm(new Literal("rule3"));};
	static Literal mineralr3 = new Literal("mineralrule");
	static {mineralr3.addTerm(new Literal("rule3"));};
	
	/**
	 * Construct the Environment.
	 */
	public TheLandOfOz() {
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
		LegoRobot robot = new Karen(agent, getAddress(agent));
		addSharedBelief(agent, obstacler1);
		addSharedBelief(agent, obstacler2);
		addSharedBelief(agent, obstacler3);
		return robot;
	}
	
	public void eachrun() {
		for (LegoRobot r: robots.values()) {
			try {
				int lightvalue = r.getBrick().getDataInputStream().readInt();
				System.err.println("lightvalue is " + lightvalue);
				((Karen) r).setLightValue(lightvalue);
				
				int distancevalue = r.getBrick().getDataInputStream().readInt();
				System.err.println("distancevalue is " + distancevalue);
				((Karen) r).setDistanceValue(distancevalue);
						
				boolean bumped = r.getBrick().getDataInputStream().readBoolean();
				System.err.println("bumped is " + bumped);
				((Karen) r).setBumped(bumped);
				r.addPercepts(this);

			} catch (Exception e) {
				
			}
		}
		
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
		   /* System.err.println(agentmap.get("noor")); */
		   
		   			     
		   	if (act.getFunctor().equals("forward")) {
		   		( (Karen) robot).forward();
		   	} else if (act.getFunctor().equals("stop")) {
		   		( (Karen) robot).stop();
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
		   	} else if (act.getFunctor().equals("follow_line")) {
		   		((Karen) robot).follow_line();
		   	} else if (act.getFunctor().equals("r1event")) {
		   		if (act.getTerm(0).getFunctor().equals("obstacle")) {
		   			removeSharedBelief(rname, bumpr1);
		   			removeSharedBelief(rname, soundr1);
		   			removeSharedBelief(rname, mineralr1);
		   			addSharedBelief(rname, obstacler1);
		   		} else if (act.getTerm(0).getFunctor().equals("obstacle_at_back")) {
		   			removeSharedBelief(rname, obstacler1);
		   			removeSharedBelief(rname, soundr1);
		   			removeSharedBelief(rname, mineralr1);
		   			addSharedBelief(rname, bumpr1);
		   		} else if (act.getTerm(0).getFunctor().equals("sound")) {
		   			removeSharedBelief(rname, bumpr1);
		   			removeSharedBelief(rname, obstacler1);
		   			removeSharedBelief(rname, mineralr1);
		   			addSharedBelief(rname, bumpr1);
		   		} else if (act.getTerm(0).getFunctor().equals("mineral")) {
		   			removeSharedBelief(rname, bumpr1);
		   			removeSharedBelief(rname, obstacler1);
		   			removeSharedBelief(rname, soundr1);
		   			addSharedBelief(rname, mineralr1);
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
		   	} else if (act.getFunctor().equals("rule2")) {
		   		if (!rule2) {
		   			addSharedBelief(rname, activer2);
		   			rule2 = true;
		   		} else {
		   			removeSharedBelief(rname, activer2);
		   			rule2 = false;
		   		}
		   	} else if (act.getFunctor().equals("r2event")) {
		   		if (act.getTerm(0).getFunctor().equals("obstacle")) {
		   			removeSharedBelief(rname, bumpr2);
		   			removeSharedBelief(rname, soundr2);
		   			removeSharedBelief(rname, mineralr2);
		   			addSharedBelief(rname, obstacler2);
		   		} else if (act.getTerm(0).getFunctor().equals("obstacle_at_back")) {
		   			removeSharedBelief(rname, obstacler2);
		   			removeSharedBelief(rname, soundr2);
		   			removeSharedBelief(rname, mineralr2);
		   			addSharedBelief(rname, bumpr2);
		   		} else if (act.getTerm(0).getFunctor().equals("sound")) {
		   			removeSharedBelief(rname, bumpr2);
		   			removeSharedBelief(rname, obstacler2);
		   			removeSharedBelief(rname, mineralr2);
		   			addSharedBelief(rname, bumpr2);
		   		} else if (act.getTerm(0).getFunctor().equals("mineral")) {
		   			removeSharedBelief(rname, bumpr2);
		   			removeSharedBelief(rname, obstacler2);
		   			removeSharedBelief(rname, soundr2);
		   			addSharedBelief(rname, mineralr2);
		   		}
		   	} else if (act.getFunctor().equals("r2action1")) {
		   		Literal oldr1act = new Literal("rule");
		   		oldr1act.addTerm(new Literal("rule2"));
		   		oldr1act.addTerm(new Literal("act1"));
		   		oldr1act.addTerm(new VarTerm("A"));
		   		removeUnifiesShared(rname, oldr1act);
		   		Literal r1act = new Literal("rule");
		   		r1act.addTerm(new Literal("rule2"));
		   		r1act.addTerm(new Literal("act1"));
		   		r1act.addTerm(act.getTerm(0));
		   		addSharedBelief(rname, r1act);
		   	} else if (act.getFunctor().equals("r2action2")) {
		   		Literal oldr1act = new Literal("rule");
		   		oldr1act.addTerm(new Literal("rule2"));
		   		oldr1act.addTerm(new Literal("act2"));
		   		oldr1act.addTerm(new VarTerm("A"));
		   		removeUnifiesShared(rname, oldr1act);
		   		Literal r1act = new Literal("rule");
		   		r1act.addTerm(new Literal("rule2"));
		   		r1act.addTerm(new Literal("act2"));
		   		r1act.addTerm(act.getTerm(0));
		   		addSharedBelief(rname, r1act);
		   	} else if (act.getFunctor().equals("rule3")) {
		   		if (!rule3) {
		   			addSharedBelief(rname, activer3);
		   			rule3 = true;
		   		} else {
		   			removeSharedBelief(rname, activer3);
		   			rule3 = false;
		   		}
		   	} else if (act.getFunctor().equals("r3event")) {
		   		if (act.getTerm(0).getFunctor().equals("obstacle")) {
		   			removeSharedBelief(rname, bumpr3);
		   			removeSharedBelief(rname, soundr3);
		   			removeSharedBelief(rname, mineralr3);
		   			addSharedBelief(rname, obstacler3);
		   		} else if (act.getTerm(0).getFunctor().equals("obstacle_at_back")) {
		   			removeSharedBelief(rname, obstacler3);
		   			removeSharedBelief(rname, soundr3);
		   			removeSharedBelief(rname, mineralr3);
		   			addSharedBelief(rname, bumpr3);
		   		} else if (act.getTerm(0).getFunctor().equals("sound")) {
		   			removeSharedBelief(rname, bumpr3);
		   			removeSharedBelief(rname, obstacler3);
		   			removeSharedBelief(rname, mineralr3);
		   			addSharedBelief(rname, bumpr3);
		   		} else if (act.getTerm(0).getFunctor().equals("mineral")) {
		   			removeSharedBelief(rname, bumpr3);
		   			removeSharedBelief(rname, obstacler3);
		   			removeSharedBelief(rname, soundr3);
		   			addSharedBelief(rname, mineralr3);
		   		}
		   	} else if (act.getFunctor().equals("r3action1")) {
		   		Literal oldr1act = new Literal("rule");
		   		oldr1act.addTerm(new Literal("rule13"));
		   		oldr1act.addTerm(new Literal("act1"));
		   		oldr1act.addTerm(new VarTerm("A"));
		   		removeUnifiesShared(rname, oldr1act);
		   		Literal r1act = new Literal("rule");
		   		r1act.addTerm(new Literal("rule3"));
		   		r1act.addTerm(new Literal("act1"));
		   		r1act.addTerm(act.getTerm(0));
		   		addSharedBelief(rname, r1act);
		   	} else if (act.getFunctor().equals("r3action2")) {
		   		Literal oldr1act = new Literal("rule");
		   		oldr1act.addTerm(new Literal("rule"));
		   		oldr1act.addTerm(new Literal("act2"));
		   		oldr1act.addTerm(new VarTerm("A"));
		   		removeUnifiesShared(rname, oldr1act);
		   		Literal r1act = new Literal("rule");
		   		r1act.addTerm(new Literal("rule3"));
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
		   	}/*else if (act.getFunctor().equals("speed")) {
		   		if (robot instanceof Noor) {
		   			((Noor) robot).setSpeed((int) ((NumberTerm) act.getTerm(0)).solve());
		   		}
		   		if (robot instanceof Claudia) {
		   			((Claudia) robot).setSpeed((int) ((NumberTerm) act.getTerm(0)).solve());
		   		}
		   	}  else if (act.getFunctor().equals("rspeed")) {
		   		if (robot instanceof Noor) {
		   			((Noor) robot).setRotateSpeed((int) ((NumberTerm) act.getTerm(0)).solve());
		   		}
		   		if (robot instanceof Claudia) {
		   			((Claudia) robot).setRotateSpeed((int) ((NumberTerm) act.getTerm(0)).solve());
		   		}
		   	} else if (act.getFunctor().equals("obstacle_distance")) {
		   		if (robot instanceof Noor) {
		   			Literal distance_threshold = new Literal("change_distance");
		   			distance_threshold.addTerm(act.getTerm(0));
		   			String abstraction_name = "abstraction_" + rname;
		   			addPercept(abstraction_name, distance_threshold);
		   		}
		   	} else if (act.getFunctor().equals("sound_volume")) {
		   		if (robot instanceof Noor) {
		   			Literal sound_threshold = new Literal("change_volume");
		   			sound_threshold.addTerm(act.getTerm(0));
		   			String abstraction_name = "abstraction_" + rname;
		   			addPercept(abstraction_name, sound_threshold);
		   		}
		   	} else if (act.getFunctor().equals("light_intensity")) {
		   		if (robot instanceof Noor) {
		   			Literal light_threshold = new Literal("change_intensity");
		   			light_threshold.addTerm(act.getTerm(0));
		   			String abstraction_name = "abstraction_" + rname;
		   			addPercept(abstraction_name, light_threshold);
		   		}
		   	} */ else {
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
		if (robot instanceof Karen) {
			((Karen) robot).setUPrintStream(s);
		}
	} 
	
	/**
	 * Setting the print stream if the robot has a Sound sensor.  This means output from the sensor can be piped to the
	 * interface.
	 * @param rname
	 * @param s
	 */
/*	public void setSoundPrintStream(String rname, PrintStream s) {
		LegoRobot robot = getRobot(rname);
		if (robot instanceof Noor) {
			((Noor) robot).setSPrintStream(s);
		}
	} */
	/**
	 * Setting the print stream if the robot has a Light sensor.  This means output from the sensor can be piped to the
	 * interface.
	 * @param rname
	 * @param s
	 */
	public void setLightPrintStream(String rname, PrintStream s) {
		LegoRobot robot = getRobot(rname);
		if (robot instanceof Karen) {
			((Karen) robot).setLPrintStream(s);
		}
	} 
	/**
	 * A class for Noor type robots (Robots with one ultrasonic sensor) and two motors.
	 * @author louiseadennis
	 *
	 */
	public class Karen extends BasicRobot {
		DifferentialPilot pilot;
		PrintStream uPrintStream = System.out;
		PrintStream lPrintStream = System.out;
		PrintStream sPrintStream = System.out;
		DataOutputStream out;
		KarenLightSensor lSensor;
		KarenUltrasonicSensor uSensor;
		KarenTouchSensor tSensor;
		
		/**
		 * Set up the configuration of the robot.
		 * @param name
		 * @param address
		 */
		public Karen(String name, String address) {
			super(name, address);
			
			if (isConnected()) {
				NXTBrick brick = getBrick();
				uSensor = new KarenUltrasonicSensor(brick, 2);
				setSensor(2, uSensor);
				uSensor.setPrintStream(uPrintStream);
				
			/*	EASSSoundSensor sSensor = new EASSSoundSensor(brick, 3);
				setSensor(3, sSensor);
				sSensor.setPrintStream(sPrintStream); */
				
			    lSensor = new KarenLightSensor(brick, 3);
				setSensor(3, lSensor);
				lSensor.setPrintStream(lPrintStream); 
				
				tSensor = new KarenTouchSensor(brick, 4);
				setSensor(4, tSensor);
				
				/* RemoteMotor motorLeft = brick.getMotorC();
				RemoteMotor motorRight = brick.getMotorA();
				pilot = new  DifferentialPilot(5.5, 10.25, motorLeft, motorRight);
				pilot.setTravelSpeed(10);
				pilot.setRotateSpeed(15);
				setPilot(pilot); */
			}
			
		}
		
		public void setLightValue(int lv) {
			lSensor.setLightValue(lv);
		}
		
		public void setDistanceValue(int dv) {
			uSensor.setDistanceValue(dv);
		}
		
		public void setBumped(boolean b) {
			tSensor.setBumped(b);
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
		
		/**
		 * Grab the print stream from the sound sensor.
		 * @param s
		 */
		public void setSPrintStream(PrintStream s) {
			sPrintStream = s;
			EASSSensor sSensor = getSensor(3);
			if (sSensor != null) {
				sSensor.setPrintStream(s);
			}
		}
		/**
		 * Grab the print stream from the light sensor.
		 * @param s
		 */
		public void setLPrintStream(PrintStream s) {
			lPrintStream = s;
			EASSSensor lSensor = getSensor(3);
			if (lSensor != null) {
				lSensor.setPrintStream(s);
			}
		}
		/**
		 * Setter for the robot's travel speed.
		 * @param speed
		 */
		public void setSpeed(int speed) {
			pilot.setTravelSpeed(speed);
		}
		
		/**
		 * Setter for the robot's rotation speed.
		 * @param speed
		 */
		public void setRotateSpeed(int speed) {
			pilot.setRotateSpeed(speed);
		}
		
		public void follow_line() {
			System.out.println("Following Line");
			try {
				getBrick().getDataOutputStream().writeInt(1);
				getBrick().getDataOutputStream().flush();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
		}

		public void forward() {
			System.out.println("Forward");
			try {
				getBrick().getDataOutputStream().writeInt(2);
				getBrick().getDataOutputStream().flush();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
		}
		
		public void stop() {
			System.out.println("Forward");
			try {
				getBrick().getDataOutputStream().writeInt(3);
				getBrick().getDataOutputStream().flush();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
		}

}

	final class KarenLightSensor implements EASSSensor {
		PrintStream out = System.out;
		int lightvalue = 0;

		public KarenLightSensor(NXTBrick brick, int i) {
		}

		public void addPercept(EASSNXTEnvironment env) {
			out.println("Light Intensity is " + lightvalue);
			Literal light = new Literal("light");
			light.addTerm(new NumberTermImpl(lightvalue));
			env.addUniquePercept("light", light);
		}

		public void setPrintStream(PrintStream s) {out = s;};
		
		public void setLightValue(int lv) {
			lightvalue = lv;
		}
	}

	final class KarenUltrasonicSensor implements EASSSensor {
		PrintStream out = System.out;
		int distancevalue = 0;

		public KarenUltrasonicSensor(NXTBrick brick, int i) {
		}

		public void addPercept(EASSNXTEnvironment env) {
			out.println("distance is " + distancevalue);
			Literal distance = new Literal("distance");
			distance.addTerm(new NumberTermImpl(distancevalue));
			env.addUniquePercept("distance", distance);
		}

		public void setPrintStream(PrintStream s) {out = s;};
		
		public void setDistanceValue(int lv) {
			distancevalue = lv;
		}
	}

	final class KarenTouchSensor implements EASSSensor {
		PrintStream out = System.out;
		boolean bumped = false;

		public KarenTouchSensor(NXTBrick brick, int i) {
		}

		public void addPercept(EASSNXTEnvironment env) {
			if (bumped) {
				Literal bump = new Literal("bump");
				env.addUniquePercept("bump", bump);
			} else {
				Literal bump = new Literal("bump");
				env.removePercept(bump);
			}
		}

		public void setPrintStream(PrintStream s) {out = s;};
		
		public void setBumped(boolean b) {
			bumped = b;
		}
	}

	/**
	 * A class for Claudia type robots (Robots with one bump sensor) and two motors.
	 * @author louiseadennis
	 *
	 */
	public class Claudia extends BasicRobot {
		DifferentialPilot pilot;
		
		/**
		 * Set up the configuration for the robot.
		 * @param name
		 * @param address
		 */
		public Claudia(String name, String address) {
			super(name, address);
			
			if (isConnected()) {
				NXTBrick brick = getBrick();
				EASSTouchSensor tSensor = new EASSTouchSensor(brick, 1);
				setSensor(1, tSensor);
				RemoteMotor claudia_motorLeft = brick.getMotorC();
				RemoteMotor claudia_motorRight = brick.getMotorA();
				pilot = new  DifferentialPilot(5, 11, claudia_motorLeft, claudia_motorRight);
				pilot.setTravelSpeed(10);
				pilot.setRotateSpeed(15);
				setPilot(pilot);
			}
		}
		
		/**
		 * Set the travel speed.
		 * @param speed
		 */
		public void setSpeed(int speed) {
			pilot.setTravelSpeed(speed);
		}

		/**
		 * Set the rotation speed.
		 * @param speed
		 */
		public void setRotateSpeed(int speed) {
			pilot.setRotateSpeed(speed);
		}
	}

}
