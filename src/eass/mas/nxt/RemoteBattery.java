// ----------------------------------------------------------------------------
//
// This file is part of the Lego Rover Library.  But is identical to
// the implementation of RemoteBattery that comes with leJOS.  Needed
// here because of tweaks to NXTCommand.
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
//
//----------------------------------------------------------------------------

package eass.mas.nxt;

import java.io.*;

import lejos.nxt.remote.NXTProtocol;

/*
 * WARNING: THIS CLASS IS SHARED BETWEEN THE classes AND pccomms PROJECTS.
 * DO NOT EDIT THE VERSION IN pccomms AS IT WILL BE OVERWRITTEN WHEN THE PROJECT IS BUILT.
 */

/**
 * Battery readings from a remote NXT.
 */
public class RemoteBattery implements NXTProtocol {
	
	private NXTCommand nxtCommand;
	
	public RemoteBattery(NXTCommand nxtCommand) {
		this.nxtCommand = nxtCommand;
	}
		
	/**
	 * The NXT uses 6 batteries of 1500 mV each.
	 * @return Battery voltage in mV. ~9000 = full.
	 */
	public int getVoltageMilliVolt() {
		/*
	     * calculation from LEGO firmware
	     */
		try {
			return nxtCommand.getBatteryLevel();
		} catch (IOException ioe) {
			return 0;
		}
	}

	/**
	 * The NXT uses 6 batteries of 1.5 V each.
	 * @return Battery voltage in Volt. ~9V = full.
	 */
	public float getVoltage() {
	   return (float)(getVoltageMilliVolt() * 0.001);
	}
}

