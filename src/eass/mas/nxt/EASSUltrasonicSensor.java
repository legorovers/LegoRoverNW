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

import ail.syntax.Literal;
import ail.syntax.NumberTermImpl;

import java.io.PrintStream;

public class EASSUltrasonicSensor extends UltrasonicSensor implements EASSSensor {
	PrintStream out = System.out;
	
	public EASSUltrasonicSensor(NXTBrick brick, int i) {
		super(brick.getSensorPort(i), brick.getNXTCommand());
	//	continuous();
	}
	
	public void addPercept(EASSNXTEnvironment env) {
		ping();
		int distancevalue = getDistance();
		out.println("distance is " + distancevalue);
		Literal distance = new Literal("distance");
		distance.addTerm(new NumberTermImpl(distancevalue));
		env.addUniquePercept("distance", distance);
	}
	
	public void setPrintStream(PrintStream s) {
		out = s;
	}
}