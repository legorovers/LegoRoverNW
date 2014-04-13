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

package eass.mas.nxt;

import ail.util.AILexception;
import ail.util.AILConfig;
import ail.syntax.Unifier;
import ail.syntax.Action;
import ail.syntax.Literal;
import ail.syntax.Predicate;
import ail.syntax.VarTerm;

import java.util.HashMap;

import eass.mas.DefaultEASSEnvironment;

/**
 * Default environment class for EASS project.  Sets up socket servers and generic actions.
 * @author louiseadennis
 *
 */
public class EASSNXTEnvironment extends DefaultEASSEnvironment {
	boolean connectingtobricks = true;
	String holdingaddress;
	HashMap<String, String> namesandaddresses = new HashMap<String, String>();;
	public HashMap<String, LegoRobot> robots = new HashMap<String, LegoRobot>();

	/**
	 * Constructor 
	 *
	 */
	public EASSNXTEnvironment() {
		// NB.  NOT connected to matlab
		super();
		notConnectedToMatLab();
	}
	
	public void setAddress(String name, String address) {
		namesandaddresses.put(name, address);
	}
	
	public String getAddress(String name) {
		return namesandaddresses.get(name);
	}
	
	public void  close(String rname) {
		LegoRobot r = getRobot(rname);
		r.close();
	}
	
	/**
	 * Add and abstraction engine.
	 * @param s
	 */
	public void addAbstractionEngine(String s, String foragent) {
		super.addAbstractionEngine(s, foragent);
		setAddress(foragent, holdingaddress);
		LegoRobot robot = createRobot(foragent);
		robots.put(foragent, robot);
	}
	
	public void addRobot(String name, LegoRobot r) {
		robots.put(name, r);
	}
	
	public LegoRobot createRobot(String agent) {
		BasicRobot robot = new BasicRobot(agent, getAddress(agent));
		return robot;
	}
		
	
	/**
	 * Overridable function.
	 *
	 */
	public void eachrun() {
		for (LegoRobot r: robots.values()) {
			r.addPercepts(this);
		}
		/// NEEDS TO HANDLE NEXT predicates incoming
	}
		
	public void noconnection_run(String agname, Action act) {
		// SOMERTHING FOR NXT
	}
	
	public Literal noconnection_calc(Predicate predicate, VarTerm val, Unifier u) {
		//SOMETHING FOR NEXT
		return new Literal("result");
	}

	public LegoRobot getRobot(String name) {
		return robots.get(name);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ail.others.DefaultEnvironment#executeAction(java.lang.String, ail.syntax.Action)
	 */
	public Unifier executeAction(String agName, Action act) throws AILexception {
	   
		Unifier u = new Unifier();
		if (act.getFunctor().equals("shutdown")) {
			stopRunning();
		} else {
			u = super.executeAction(agName, act);
		}
		
		return u;
	  }
	
	/*
	 * (non-Javadoc)
	 * @see eass.mas.DefaultEASSEnvironment#configure(ail.util.AILConfig)
	 */
	public void configure(AILConfig config) {
		super.configure(config);
		if (config.containsKey("nxt.robotAddress")) {
			holdingaddress = config.getProperty("nxt.robotAddress");
		}

	}
	  
	

}


