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
import ail.syntax.NumberTermImpl;
import lejos.nxt.LightSensor;

public class EASSLightSensor extends LightSensor implements EASSSensor {
	PrintStream out = System.out;

	public EASSLightSensor(NXTBrick brick, int i) {
		super(brick.getSensorPort(i));
	}

	public void addPercept(EASSNXTEnvironment env) {
		int lightvalue = readValue();
		out.println("Light Intensity is " + lightvalue);
		Literal light = new Literal("light");
		light.addTerm(new NumberTermImpl(lightvalue));
		env.addUniquePercept("light", light);
	}

	public void setPrintStream(PrintStream s) {out = s;};
}
