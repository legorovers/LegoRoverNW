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
import ail.syntax.Term;
import ail.semantics.AILAgent;

import ajpf.util.AJPFLogger;

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
import java.util.Random;


/**
 * A simple environment for NXT Robots including sub-classes for two robot builds.  We need a more generic way of representing different robots
 * and robot configurations.
 * 
 * @author louiseadennis
 *
 */
public class TheLandOfOz extends EASSNXTEnvironment {
	int travelspeed = 10;
	int turningspeed = 15;
	int rulenumber = 8;
	boolean[] activerules = new boolean[rulenumber];
	String logname = "eass.nxt.legorover.wizardofoz.TheLandOfOz";
	int count_limit = 0;
	
	Random r = new Random();
	
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
		for (int i = 0; i < rulenumber; i++) {
			activerules[i] = false;
			int rn = i + 1;
			String rulename = "rule" + rn;
			addRule("obstaclerule", rulename, agent);
			addRuleGuard(rulename, agent, "none");
			addRuleAction(rulename, 1, agent, "do_nothing");
			addRuleAction(rulename, 2, agent, "do_nothing");
			addRuleAction(rulename, 3, agent, "do_nothing");
		}
		
		return robot;
	}
	
	private void addRuleAction(String rulename, int actionnumber, String agent, String action) {
		String actionname = "act" + actionnumber;
		Literal rule = new Literal("rule");
		rule.addTerm(new Literal(rulename));
		rule.addTerm(new Literal(actionname));
		Literal a = new Literal("action");
		a.addTerm(new Literal(action));
		rule.addTerm(a);
		addSharedBelief(agent, rule);
	}

	private void removeRuleAction(String rulename, int actionnumber, String agent) {
		String actionname = "act" + actionnumber;
		Literal rule = new Literal("rule");
		rule.addTerm(new Literal(rulename));
		rule.addTerm(new Literal(actionname));
		Literal a = new Literal("action");
		a.addTerm(new VarTerm("A"));
		rule.addTerm(a);
		removeUnifiesShared(agent, rule);
	}

	private void addRuleAddGoal(String rulename, int actionnumber, String agent, String goal) {
		String actionname = "act" + actionnumber;
		Literal rule = new Literal("rule");
		rule.addTerm(new Literal(rulename));
		rule.addTerm(new Literal(actionname));
		Literal a = new Literal("new_goal");
		a.addTerm(new Literal(goal));
		rule.addTerm(a);
		addSharedBelief(agent, rule);
	}

	private void addRuleGuard(String rulename, String agent, String guard) {
		Literal g = new Literal("guard");
		g.addTerm(new Literal(rulename));
		g.addTerm(new Literal(guard));
		System.err.println(g);
		addSharedBelief(agent, g);
	}
	
	private void addRuleGuard(String rulename, String agent, Literal guard) {
		Literal g = new Literal("guard");
		g.addTerm(new Literal(rulename));
		g.addTerm(guard);
		System.err.println(g);
		addSharedBelief(agent, g);
	}

	private void removeRuleGuard(String rulename, String agent) {
		Literal g = new Literal("guard");
		g.addTerm(new Literal(rulename));
		g.addTerm(new VarTerm("A"));
		removeUnifiesShared(agent, g);
	}

	private void addRule(String rulepredicate, String rulename, String agent) {
		Literal rule = new Literal(rulepredicate);
		rule.addTerm(new Literal(rulename));
		addSharedBelief(agent, rule);
	}
	private void removeRule(String rulepredicate, String rulename, String agent) {
		Literal rule = new Literal(rulepredicate);
		rule.addTerm(new Literal(rulename));
		removeSharedBelief(agent, rule);
	}

	
	public void eachrun() {
		for (LegoRobot r: robots.values()) {
			try {
				int lightvalue = r.getBrick().getDataInputStream().readInt();
				// System.err.println("lightvalue is " + lightvalue);
				((Karen) r).setLightValue(lightvalue);
				
				int distancevalue = r.getBrick().getDataInputStream().readInt();
				// System.err.println("distancevalue is " + distancevalue);
				((Karen) r).setDistanceValue(distancevalue);
				
				int commandcompletecode = r.getBrick().getDataInputStream().readInt();
				Literal ccc = new Literal("completed");
				if (commandcompletecode == 7) {
					ccc.addTerm(new Literal("backwardN"));
				} else if (commandcompletecode == 8) {
					ccc.addTerm(new Literal("rightN"));
				} else if (commandcompletecode == 9) {
					ccc.addTerm(new Literal("leftN"));
				} else if (commandcompletecode == 10) {
					ccc.addTerm(new Literal("forwardN"));
					// System.err.println(ccc);
					// System.err.println(agentmap.get("noor"));
				} else {
					ccc.addTerm(new Literal("some_action"));
				}
				
				addUniquePercept("noor", ccc);
				
						
			} catch (Exception e) {
				
			}
		}
		
		if (count_limit < 1) {
			addPercept(new Literal("ping"));
		} else {
			removePercept(new Literal("ping"));
			count_limit--;
		}
		
		super.eachrun();
	}
	
	
	public void close() {
		for (LegoRobot r: robots.values()) {
			((Karen) r).close();
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see eass.mas.nxt.EASSNXTEnvironment#executeAction(java.lang.String, ail.syntax.Action)
	 */
	public Unifier executeAction(String agName, Action act) throws AILexception {
		   Unifier u = new Unifier();
		   String rname = rationalName(agName);
		   Karen robot = (Karen) getRobot(rname);
		   AJPFLogger.info(logname, act.toString());	
		   			     
		   // Movement Actions sent to robot.
		   if (act.getFunctor().equals("stop")) {
			   removeSharedBelief(rname, new Literal("following_line"));
			   robot.stop();
		   } else if (act.getFunctor().equals("forward")) {
		   		removeSharedBelief(rname, new Literal("following_line"));
		   		robot.forward();
		   	} else if (act.getFunctor().equals("backward")) {
		   		removeSharedBelief(rname, new Literal("following_line"));
		   		robot.backward();
		   	} else if (act.getFunctor().equals("right")) {
		   		removeSharedBelief(rname, new Literal("following_line"));
		   		robot.right();
		   	} else if (act.getFunctor().equals("left")) {
		   		removeSharedBelief(rname, new Literal("following_line"));
		   		robot.left();
		   	} else if (act.getFunctor().equals("right90") || act.getFunctor().equals("right_90")) {
		   		removeSharedBelief(rname, new Literal("following_line"));
		   		robot.right(90);
		   		count_limit = 900;
		   	} else if (act.getFunctor().equals("left90") || act.getFunctor().equals("left_90")) {
		   		removeSharedBelief(rname, new Literal("following_line"));
		   		robot.left(90);
		   		count_limit = 900;
		   	} else if (act.getFunctor().equals("backward10") || act.getFunctor().equals("backward_10")) {
		   		removeSharedBelief(rname, new Literal("following_line"));
		   		robot.backward(10);
		   		count_limit = 100;
		   	} else if (act.getFunctor().equals("forward10") || act.getFunctor().equals("forward_10")) {
		   		removeSharedBelief(rname, new Literal("following_line"));
		   		robot.forward(10);
		   		count_limit = 100;
		   	} else if (act.getFunctor().equals("random_turn")) {
		   		removeSharedBelief(rname, new Literal("following_line"));
		   		int angle = r.nextInt(360);
		   		robot.right(angle);	
		   		count_limit = 10*angle;
		   	} else if (act.getFunctor().equals("random_forward")) {
		   		removeSharedBelief(rname, new Literal("following_line"));
		   		int distance = r.nextInt(30);
		   		robot.forward(distance);
		   		count_limit = 10*distance;
		   	} else if (act.getFunctor().equals("follow_line")) {
		   		addSharedBelief(rname, new Literal("following_line"));
		   		robot.follow_line();
		   	}
		   
		   	// Settings Actions sent to robot
		   	else if (act.getFunctor().equals("speed")) {
		   		robot.setSpeed((int) ((NumberTerm) act.getTerm(0)).solve());
		   	} else if (act.getFunctor().equals("rspeed")) {
		   		robot.setRotateSpeed((int) ((NumberTerm) act.getTerm(0)).solve());
		   	}
		   
		   	// Sensor Thresholds
		   	else if (act.getFunctor().equals("obstacle_distance")) {
		   		Literal distance_threshold = new Literal("change_distance");
		   		distance_threshold.addTerm(act.getTerm(0));
		   		String abstraction_name = "abstraction_" + rname;
		   		addPercept(abstraction_name, distance_threshold);
		   	} else if (act.getFunctor().equals("water_lower")) {
		   		Literal distance_threshold = new Literal("change_wlintensity");
		   		distance_threshold.addTerm(act.getTerm(0));
		   		String abstraction_name = "abstraction_" + rname;
		   		addPercept(abstraction_name, distance_threshold);
		   	} else if (act.getFunctor().equals("water_upper")) {
		   		Literal distance_threshold = new Literal("change_wuintensity");
		   		distance_threshold.addTerm(act.getTerm(0));
		   		String abstraction_name = "abstraction_" + rname;
		   		addPercept(abstraction_name, distance_threshold);
		   	} else if (act.getFunctor().equals("river_bed_lower")) {
		   		Literal distance_threshold = new Literal("change_blintensity");
		   		distance_threshold.addTerm(act.getTerm(0));
		   		String abstraction_name = "abstraction_" + rname;
		   		addPercept(abstraction_name, distance_threshold);
		   	} else if (act.getFunctor().equals("river_bed_upper")) {
		   		Literal distance_threshold = new Literal("change_buintensity");
		   		distance_threshold.addTerm(act.getTerm(0));
		   		String abstraction_name = "abstraction_" + rname;
		   		addPercept(abstraction_name, distance_threshold);
		   	} 
		   
		   // Setting Goals
		   	else if (act.getFunctor().equals("new_goal")) {
		   		Literal ng = new Literal("new_goal");
		   		ng.addTerm(new VarTerm("N"));
		   		removeUnifiesShared(rname, ng);
		   		Literal ng2 = new Literal("new_goal");
		   		ng2.addTerm(act.getTerm(0));
		   		addSharedBelief(rname, ng2);
		   	}
		   
		   // Actions that alter the rules
		   	else if (act.getFunctor().startsWith("rule")) {
		   		int rulenum = Integer.parseInt(act.getFunctor().substring(4));
		   		int ruleindex = rulenum - 1;
		   		Literal active = new Literal("active");
		   		active.addTerm(new Literal(act.getFunctor()));
		   		if (!activerules[ruleindex]) {
		   			addSharedBelief(rname, active);
		   			activerules[ruleindex] = true;
		   		} else {
		   			removeSharedBelief(rname, active);
		   			activerules[ruleindex] = false;
		   		}
		   	}
		   
		   else if (act.getFunctor().substring(2).equals("event")) {
			   Term ruletype = act.getTerm(0);
	   			String rulenumber = act.getFunctor().substring(1,2);
	   			String rulename = "rule" + rulenumber;
	   			removeRule("obstaclerule", rulename, rname);
	   			removeRule("waterrule", rulename, rname);
	   			removeRule("riverrule", rulename, rname);
	   			removeRule("obstaclegoalrule", rulename, rname);
	   			removeRule("watergoalrule", rulename, rname);
	   			removeRule("riverrule", rulename, rname);
			   if (ruletype.getFunctor().equals("new_goal")) {
		   			String rulepredicate = ((Literal) ruletype).getTerm(0).getFunctor() + "goalrule";
		   			addRule(rulepredicate, rulename, rname);				   
			   } else  {
		   			String rulepredicate = ruletype.getFunctor() + "rule";
		   			addRule(rulepredicate, rulename, rname);
			   } 
		   } 
		   
		   else if (act.getFunctor().substring(2).equals("guard")) {
			   Literal ruletype = (Literal) act.getTerm(0);
	   			String rulenumber = act.getFunctor().substring(1,2);
	   			String rulename = "rule" + rulenumber;
	   			removeRuleGuard(rulename, rname);
	   			addRuleGuard(rulename, rname, ruletype); 
		   } 

		   else if (act.getFunctor().substring(2).startsWith("action")) {
	   			String rulenumber = act.getFunctor().substring(1,2);
	   			String rulename = "rule" + rulenumber;
	   			int actionnumber = Integer.parseInt(act.getFunctor().substring(8,9));
	   			
	   			removeRuleAction(rulename, actionnumber, rname);
	   			Literal actiontype = (Literal) act.getTerm(0);
	   			if (actiontype.getFunctor().equals("new_goal_deed")) {
	   				addRuleAddGoal(rulename, actionnumber, rname, actiontype.getTerm(0).getFunctor());
	   			} else {
	   				addRuleAction(rulename, actionnumber, rname, actiontype.getFunctor());
	   			}
		   	} 
		   
		   
		   else if (act.getFunctor().equals("do_nothing")) {
			   count_limit = 100;
		   } else if (act.getFunctor().equals("interrupted")) {
			   addSharedBelief(agName, new Literal("interrupted"));
		   }
		   
		   
		   
		   
		   else {
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
		DataOutputStream out;
		KarenLightSensor lSensor;
		KarenUltrasonicSensor uSensor;
		
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
								
			    lSensor = new KarenLightSensor(brick, 3);
				setSensor(3, lSensor);
				lSensor.setPrintStream(lPrintStream); 
								
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
			System.out.println("Set Speed " + speed);
			try {
				getBrick().getDataOutputStream().writeInt(11);
				getBrick().getDataOutputStream().flush();
				getBrick().getDataOutputStream().writeInt(speed);
				getBrick().getDataOutputStream().flush();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
		}
		
		/**
		 * Setter for the robot's rotation speed.
		 * @param speed
		 */
		public void setRotateSpeed(int speed) {
			// System.out.println("Set Rotate Speed " + speed);
			try {
				getBrick().getDataOutputStream().writeInt(12);
				getBrick().getDataOutputStream().flush();
				getBrick().getDataOutputStream().writeInt(speed);
				getBrick().getDataOutputStream().flush();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
		}
		
		public void follow_line() {
			// System.out.println("Following Line");
			try {
				getBrick().getDataOutputStream().writeInt(1);
				getBrick().getDataOutputStream().flush();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
		}

		public void forward() {
			// System.out.println("Forward");
			try {
				getBrick().getDataOutputStream().writeInt(2);
				getBrick().getDataOutputStream().flush();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
		}
		
		public void stop() {
			// System.out.println("Stop");
			try {
				getBrick().getDataOutputStream().writeInt(3);
				getBrick().getDataOutputStream().flush();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
		}
		
		public void left() {
			// System.out.println("Left");
			try {
				getBrick().getDataOutputStream().writeInt(4);
				getBrick().getDataOutputStream().flush();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
			
		}

		public void right() {
			// System.out.println("Right");
			try {
				getBrick().getDataOutputStream().writeInt(5);
				getBrick().getDataOutputStream().flush();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
			
		}

		public void backward() {
			// System.out.println("Backward");
			try {
				getBrick().getDataOutputStream().writeInt(6);
				getBrick().getDataOutputStream().flush();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
			
		}

	
		public void backward(int distance) {
			// System.out.println("Reverse " + distance);
			try {
				getBrick().getDataOutputStream().writeInt(7);
				getBrick().getDataOutputStream().flush();
				getBrick().getDataOutputStream().writeInt(distance);
				getBrick().getDataOutputStream().flush();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
			
		}

		public void right(int angle) {
			System.out.println("Right " + angle);
			try {
				getBrick().getDataOutputStream().writeInt(8);
				getBrick().getDataOutputStream().flush();
				getBrick().getDataOutputStream().writeInt(angle);
				getBrick().getDataOutputStream().flush();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
			
		}

		public void left(int angle) {
			System.out.println("Left " + angle);
			try {
				getBrick().getDataOutputStream().writeInt(9);
				getBrick().getDataOutputStream().flush();
				getBrick().getDataOutputStream().writeInt(angle);
				getBrick().getDataOutputStream().flush();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
			
		}
		
		public void forward(int distance) {
			//  System.out.println("Forward " + distance);
			try {
				getBrick().getDataOutputStream().writeInt(10);
				getBrick().getDataOutputStream().flush();
				getBrick().getDataOutputStream().writeInt(distance);
				getBrick().getDataOutputStream().flush();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			}
			
		}

		public void close() {
			System.err.println("closing");
			try {
				getBrick().getDataOutputStream().writeInt(13);
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

}
