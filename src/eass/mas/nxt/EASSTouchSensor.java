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

import java.io.PrintStream;

import ail.syntax.Literal;
import lejos.nxt.TouchSensor;

public class EASSTouchSensor extends TouchSensor implements EASSSensor {
	
	public EASSTouchSensor(NXTBrick brick, int i) {
		super(brick.getSensorPort(i));
	}
	
	public void addPercept(EASSNXTEnvironment env) {
		boolean bumped = isPressed();
		if (bumped) {
			Literal bump = new Literal("bump");
			env.addUniquePercept("bump", bump);
		} else {
			Literal bump = new Literal("bump");
			env.removePercept(bump);
		}
	}

	public void setPrintStream(PrintStream s) {};
}