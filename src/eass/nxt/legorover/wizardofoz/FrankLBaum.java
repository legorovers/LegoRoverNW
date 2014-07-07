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

import javax.swing.*;   
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;

import eass.mas.nxt.EASSNXTEnvironment;
import ail.syntax.Action;
import ail.syntax.Literal;
import ail.syntax.Predicate;
import ail.syntax.Goal;
import ail.syntax.Intention;
import ail.syntax.NumberTermImpl;
import ail.syntax.Event;
import ail.util.AILConfig;
import ail.mas.AIL;
import ail.mas.MAS;
import ajpf.MCAPLcontroller;
import ail.semantics.AILAgent;

/**
 * This sets up a user interface for the remote operation of Lego Rovers.  It is intended primarily for use with school children.
 * Further information can be found at: http://cgi.csc.liv.ac.uk/~lad/legorovers/
 * @author louiseadennis
 *
 */
public class FrankLBaum extends JPanel implements ActionListener, WindowListener, PropertyChangeListener {
		private static final long serialVersionUID = 1L;
		
		// The various buttons and boxes used by the interface.
	    protected JButton fbutton, rbutton, lbutton, sbutton, delaybutton,
	    		r90button, l90button, bbutton, line, reverse;
	    protected JCheckBox r1button, r2button, r3button, r4button, r5button, r6button, r7button, r8button;
	    protected JComboBox delaybox;
	    protected JFormattedTextField speedbox, rspeedbox, distancebox, wllightbox, wulightbox, bllightbox, bulightbox;
	    protected TextAreaOutputStream uvalues, lvalues;
	    protected JLabel showbeliefs, showgoals;

	    protected NumberFormat numberFormat;
    	Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

	    //
	    // 500 = 0.5s = Satellite Call UK-Australia
	    // 1000 = 1s
	    // 13000 = 1.3s = Earth-Moon delay
	    // 180000 = 3 min = Earth-Mars delay
	    protected String[] delays = {"0s","0.5s","1s","1.3s","180s"};
	    protected String[] bevents = {"BELIEF obstacle", "BELIEF water", "BELIEF river bed"};
	    protected String[] gevents = {"GOAL water", "GOAL river bed", "GOAL obstacle"};
	    protected String[] guards = {"Anything", "BELIEVE obstacle", "BELIEVE water", "BELIEVE river bed", "DON'T BELIEVE obstacle", "DON'T BELIEVE water", "DON'T BELIEVE river bed"};
	    protected String[] actions = {"do nothing", "stop", "forward 1", "forward 3", "backward 10", "right 90", 
	    		"left 90", "forward", "backward", "right", "left", "follow line", "random turn", "ACHIEVE GOAL river bed", "ACHIEVE GOAL water"};
	    protected ArrayList<String> teleop_commands = new ArrayList<String>();
	    
	    // The delay before instructions reach the robot, the environment and the default robot name.
	    protected int delay = 0;
	    protected static EASSNXTEnvironment env;
	    protected static String rName = "noor";
	    
	    // Wrapping the environment in a thread so events are handled faster.
	    protected EnvironmentThread envThread = new EnvironmentThread();
	    
	    // Initial Values
	    protected int ispeed=10;
	    protected int irspeed=45;
	    protected int idthreshold=50;
	    protected int iwlthreshold=35;
	    protected int iwuthreshold=45;
	    protected int iblthreshold=0;
	    protected int ibuthreshold=30;
	    
	    protected boolean ispeedinit = false;
	    protected boolean irspeedinit = false;
	    protected boolean idtinit = false;
	    protected boolean iwlinit = false;
	    protected boolean iwuinit = false;
	    protected boolean iblinit = false;
	    protected boolean ibuinit = false;
	    
	    protected HashMap<String, Integer> thresholds = new HashMap<String, Integer>();
	    protected HashMap<String, Boolean> inits = new HashMap<String, Boolean>();
	    
	    private void initThresholds() {
	    	thresholds.put("speed", ispeed);
	    	thresholds.put("rspeed", irspeed);
	    	thresholds.put("obstacle_distance", idthreshold);
	    	thresholds.put("water_lower", iwlthreshold);
	    	thresholds.put("water_upper", iwuthreshold);
	    	thresholds.put("river_bed_lower", iblthreshold);
	    	thresholds.put("river_bed_upper", ibuthreshold);
	    }
	    
	    private void initInits() {
	    	inits.put("speed", ispeedinit);
	    	inits.put("rspeed", irspeedinit);
	    	inits.put("obstacle_distance", idtinit);
	    	inits.put("water_lower", iwlinit);
	    	inits.put("water_upper", iwuinit);
	    	inits.put("river_bed_lower", iblinit);
	    	inits.put("river_bed_upper", ibuinit);
	    }
	
	    /**
	     * Constructs the User Interface.
	     * @param layout
	     */
	    public FrankLBaum(GridBagLayout layout) {
	    	initThresholds();
	    	initInits();
			
	    	setLayout(layout);
	    	GridBagConstraints c = new GridBagConstraints();
	    	c.fill = GridBagConstraints.HORIZONTAL;
	    	
	    	// A JPanel for Settings
	    	createSettingsPanel();
	    		    	
	    	// A JPanel for Controls
	    	createControlsPanel();

	        // A JPanel for Rules
	    	JPanel rules = new JPanel();
	    	rules.setBorder(BorderFactory.createTitledBorder(loweredetched, "Rules"));
	    	rules.setLayout(new GridBagLayout());
	    	c.gridx = 0;
	    	c.gridy = 2;
	        c.gridwidth = 1;
	    	add(rules, c);

	    	createRule(c, rules, r1button);
	    	createRule(c, rules, r2button);
	    	createRule(c, rules, r3button);
	    	createRule(c, rules, r4button);
	    	createRule(c, rules, r5button);
	    	// createRule(c, rules, r6button);
	    	// createRule(c, rules, r7button);
	    	// createRule(c, rules, r8button);
	    	
	    	// A JPanel for Goals
	    	JPanel goals = new JPanel();
	    	goals.setBorder(BorderFactory.createTitledBorder(loweredetched, "Robot Goal"));
	    	goals.setLayout(new GridBagLayout());
	    	
	    	c.gridx = 0;
	    	c.gridy = 3;
	    	c.gridwidth = 1;
	    	add(goals, c);
	    	
	    	JLabel goaltext = new JLabel("Set Robot's Goal to:");
	    	c.gridx = 0;
	    	c.gridy = 0;
	    	goals.add(goaltext, c);
	    	String [] gs = {"none", "water", "river bed", "obstacle"};
	    	JComboBox goalbox = new JComboBox(gs);
	    	goalbox.setActionCommand("new_goal");
	    	goalbox.addActionListener(this);
	    	c.gridx = 1;
	    	goals.add(goalbox, c);
	    	
	    	// A JPanel for the Agent state
	    	JPanel agentstate = new JPanel();
	    	agentstate.setLayout(new GridBagLayout());
	    	c.gridx = 2;
	    	goals.add(agentstate, c);
	    	
	    	displayAgentState(agentstate);

	        
	    	// A JPanel for Sensor Input
	    	JPanel sensordata = new JPanel();
	    	sensordata.setBorder(BorderFactory.createTitledBorder(loweredetched, "Sensors"));
	    	sensordata.setLayout(new GridBagLayout());
	    	c.gridx = 1;
	    	c.gridy = 0;
	        c.gridwidth = 1;
	        c.gridheight = GridBagConstraints.REMAINDER;
	        add(sensordata, c);
	    	
	        // Panel for Ultrasonic sensor
	    	JPanel ultra = new JPanel();
	    	ultra.setBorder(BorderFactory.createTitledBorder(loweredetched, "The Ultrasonic Sensor"));
	    	ultra.setLayout(new GridBagLayout());
	    	c.gridx = 0;
	    	c.gridy = 0;
	    	c.gridwidth = 1;
	    	c.gridheight = 1;
	    	sensordata.add(ultra, c);
		    	
	    	// Distance Setting
	    	JLabel utext = new JLabel("Obstacle Distance:");
	    	ultra.add(utext);
	    	NumberFormat f2 = NumberFormat.getIntegerInstance(); 
	    	f2.setMaximumIntegerDigits(3);
	    	distancebox = new JFormattedTextField(f2);
	    	distancebox.setValue(thresholds.get("obstacle_distance"));
	    	distancebox.setColumns(3);
	    	distancebox.addPropertyChangeListener(this);
	    	ultra.add(distancebox);
		    	
	    	// Ultrasound Values
	    	JLabel vtext = new JLabel("Ultrasonic Sensor Values:");
	    	c.gridy = 1;
	    	c.gridwidth = 2;
	    	ultra.add(vtext, c);

	    	JTextArea textArea = new JTextArea(10, 30);
	    	uvalues = new TextAreaOutputStream(textArea, "Ultrasound Sensor Value");
	    	((TheLandOfOz) env).setUltraPrintStream(rName, new PrintStream(uvalues));
	    	JPanel output = new JPanel();
	    	output.add(new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
	    	c.gridx = 0;
	    	c.gridy = 2;
	    	c.gridwidth = 2;
	    	ultra.add(output, c);
		    	
	    	//  Light
	    	JPanel light = new JPanel();
	    	light.setBorder(BorderFactory.createTitledBorder(loweredetched, "The Light Sensor"));
	    	light.setLayout(new GridBagLayout());
	    	c.gridx = 0;
	    	c.gridy = 20;
	    	c.gridwidth = 1;
	    	sensordata.add(light, c);
		    	
	    	// Light Setting
	    	JLabel ltext = new JLabel("Light Intensity for Water (Lower):");
	    	c.gridx = 0;
	    	c.gridy = 0;
	    	c.gridwidth = 1;
	    	light.add(ltext, c);

	    	NumberFormat f4 = NumberFormat.getIntegerInstance(); 
	    	f4.setMaximumIntegerDigits(3);
	    	wllightbox = new JFormattedTextField(f4);
	    	wllightbox.setValue(thresholds.get("water_lower"));
	    	wllightbox.setColumns(3);
	    	wllightbox.addPropertyChangeListener(this);
	    	c.gridx = 1;
	    	light.add(wllightbox, c);
	    	
	    	JLabel lutext = new JLabel("(Upper):");
	    	c.gridx = 2;
	    	light.add(lutext, c);
	    	
	    	wulightbox = new JFormattedTextField(f4);
	    	wulightbox.setValue(thresholds.get("water_upper"));
	    	wulightbox.setColumns(3);
	    	wulightbox.addPropertyChangeListener(this);
	    	c.gridx = 3;
	    	light.add(wulightbox, c);

	    	JLabel l2text = new JLabel("Light Intensity for River Bed (Lower):");
	    	c.gridy = 1;
	    	c.gridx = 0;
	    	light.add(l2text, c);
	    	
	    	bllightbox = new JFormattedTextField(f4);
	    	bllightbox.setValue(thresholds.get("river_bed_lower"));
	    	bllightbox.setColumns(3);
	    	bllightbox.addPropertyChangeListener(this);
	    	c.gridx = 1;
	    	light.add(bllightbox, c);

	    	JLabel butext = new JLabel("(Upper):");
	    	c.gridx = 2;
	    	light.add(butext, c);

	    	bulightbox = new JFormattedTextField(f4);
	    	bulightbox.setValue(thresholds.get("river_bed_upper"));
	    	bulightbox.setColumns(3);
	    	bulightbox.addPropertyChangeListener(this);
	    	c.gridx = 3;
	    	light.add(bulightbox, c);

	    	// Light Values
	    	JLabel lvtext = new JLabel("Light Sensor Values:");
	    	c.gridx = 0;
	    	c.gridy = 2;
	    	c.gridwidth = 4;
	    	light.add(lvtext, c);

	    	JTextArea ltextArea = new JTextArea(10, 30);
	    	lvalues = new TextAreaOutputStream(ltextArea, "Light Sensor Value");
	    	((TheLandOfOz) env).setLightPrintStream(rName, new PrintStream(lvalues));
	    	JPanel loutput = new JPanel();
	    	loutput.add(new JScrollPane(ltextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
	    	c.gridx = 0;
	    	c.gridy = 3;
	    	c.gridwidth = 4;
	    	light.add(loutput, c); 
	    	
	    	
	    	envThread.start();


	    }
	    
	    
	    private void displayAgentState(JPanel agentstate) {
	    	GridBagConstraints c = new GridBagConstraints();
	    	c.fill = GridBagConstraints.HORIZONTAL;
	    	JLabel beliefs = new JLabel("Robot Beliefs: ");
	    	c.gridx = 0;
	    	c.gridy = 0;
	    	agentstate.add(beliefs, c);
	    	showbeliefs = new JLabel(" ");
	    	c.gridx = 1;
	    	agentstate.add(showbeliefs, c);
	    	
	    	JLabel goallabel = new JLabel("Robot Goals:");
	    	c.gridx = 0;
	    	c.gridy = 3;
	    	agentstate.add(goallabel, c);
	    	showgoals = new JLabel(" ");
	    	c.gridx = 1;
	    	agentstate.add(showgoals, c);
	    	Timer timer = new Timer(1000, new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent arg0) {
	                updateAgentState();
	            }
	        });
	        timer.start(); 
	    }
	    
	    private void createControlsPanel() {
	    	GridBagConstraints c = new GridBagConstraints();
	    	c.fill = GridBagConstraints.HORIZONTAL;
	    	JPanel controls = new JPanel();
	    	controls.setBorder(BorderFactory.createTitledBorder(loweredetched, "Controls"));
	    	controls.setLayout(new GridBagLayout());
	    	c.gridx = 0;
	    	c.gridy = 1;
	    	add(controls, c);

	    	// Forward
	    	fbutton = new JButton("Forward");
	        fbutton.setMnemonic(KeyEvent.VK_F);
	        fbutton.setActionCommand("forward");
	        teleop_commands.add(fbutton.getActionCommand());
	        fbutton.addActionListener(this);
	        fbutton.setToolTipText("Click to move Forward");
	        c.gridx = 0;
	        c.gridy = 0;
	        c.gridwidth = 1;
	        controls.add(fbutton, c);
	    	
	        // Right
	        rbutton = new JButton("Right");
	        rbutton.setMnemonic(KeyEvent.VK_R);
	        rbutton.setActionCommand("right");
	        teleop_commands.add(rbutton.getActionCommand());
	        rbutton.addActionListener(this);
	        rbutton.setToolTipText("Click to turn Right");
	        c.gridx = 1;
	        c.gridy = 0;
	        controls.add(rbutton, c);

	        // Left
	        lbutton = new JButton("Left");
	        lbutton.setMnemonic(KeyEvent.VK_L);
	        lbutton.setActionCommand("left");
	        teleop_commands.add(lbutton.getActionCommand());
	        lbutton.addActionListener(this);
	        lbutton.setToolTipText("Click to turn Left");
	        c.gridx = 2;
	        c.gridy = 0;
	        controls.add(lbutton, c);
	        
	        // Reverse
	        reverse = new JButton("Reverse");
	        reverse.setActionCommand("backward");
	        teleop_commands.add(reverse.getActionCommand());
	        reverse.addActionListener(this);
	        reverse.setToolTipText("Click to Reverse");
	        c.gridx = 3;
	        c.gridy = 0;
	        controls.add(reverse, c);

	        // Stop
	        sbutton = new JButton("Stop");
	        sbutton.setMnemonic(KeyEvent.VK_S);
	        sbutton.setActionCommand("stop");
	        teleop_commands.add(sbutton.getActionCommand());
	        sbutton.addActionListener(this);
	        sbutton.setToolTipText("Click to Stop");
	        c.gridx = 4;
	        c.gridy = 0;
	        controls.add(sbutton, c);
	        
	        // Reverse 10
	        bbutton = new JButton("Reverse (10 cm)");
	        bbutton.setMnemonic(KeyEvent.VK_S);
	        bbutton.setActionCommand("backward10");
	        teleop_commands.add(bbutton.getActionCommand());
	        bbutton.addActionListener(this);
	        bbutton.setToolTipText("Click to Got Backwards 10");
	        c.gridx = 0;
	        c.gridy = 1;
	        controls.add(bbutton, c);

	        // Right 90
	        r90button = new JButton("Right (90 degrees)");
	        r90button.setActionCommand("right90");
	        teleop_commands.add(r90button.getActionCommand());
	        r90button.addActionListener(this);
	        r90button.setToolTipText("Click to turn Right 90 degrees");
	        c.gridx = 1;
	        c.gridy = 1;
	        controls.add(r90button, c);

	        // Left 90
	        l90button = new JButton("Left (90 degrees)");
	        l90button.setActionCommand("left90");
	        teleop_commands.add(l90button.getActionCommand());
	        l90button.addActionListener(this);
	        l90button.setToolTipText("Click to turn Left 90 degrees");
	        c.gridx = 2;
	        c.gridy = 1;
	        controls.add(l90button, c);
	        
	        // Follow Line
	        line = new JButton("Follow River Bed");
	        line.setActionCommand("follow_line");
	        teleop_commands.add(line.getActionCommand());
	        line.addActionListener(this);
	        line.setToolTipText("Click to Follow a Dry River Bed");
	        c.gridx = 3;
	        c.gridy = 1;
	        controls.add(line, c);

	    }
	    
	    private void createSettingsPanel() {
	    	JPanel settings = new JPanel();
	    	settings.setBorder(BorderFactory.createTitledBorder(loweredetched, "Settings"));
	    	GridBagConstraints c = new GridBagConstraints();
	    	c.gridx = 0;
	    	c.gridy = 0;
	        c.gridwidth = 1;
	    	c.fill = GridBagConstraints.HORIZONTAL;
	    	add(settings, c);
	    	
	    	// ---------- Components of the Settings box
	    	
	    	// Delay
	    	JLabel dtext = new JLabel("Delay:");
	    	settings.add(dtext);
	    	delaybox = new JComboBox(delays);
	    	delaybox.setActionCommand("delay");
	    	delaybox.addActionListener(this);
	    	settings.add(delaybox);
	    	
	    	// Speed
	    	JLabel stext = new JLabel("Forward Speed:");
	    	settings.add(stext);
	    	NumberFormat f = NumberFormat.getIntegerInstance(); 
	    	f.setMaximumIntegerDigits(3);
	    	speedbox = new JFormattedTextField(f);
	    	speedbox.setValue(thresholds.get("speed"));
	    	speedbox.setColumns(3);
	    	speedbox.addPropertyChangeListener(this);
	    	settings.add(speedbox);
	    	
	    	// Speed
	    	JLabel ttext = new JLabel("Turning Speed:");
	    	settings.add(ttext);
	    	NumberFormat f1 = NumberFormat.getIntegerInstance(); 
	    	f1.setMaximumIntegerDigits(3);
	    	rspeedbox = new JFormattedTextField(f1);
	    	rspeedbox.setValue(thresholds.get("rspeed"));
	    	rspeedbox.setColumns(3);
	    	rspeedbox.addPropertyChangeListener(this);
	    	settings.add(rspeedbox);
	    }
	    
	    
	    int rulenumber = 1;
	    private void createRule(GridBagConstraints c, JPanel rules, JCheckBox button) {
	    	button = new JCheckBox("rule"+ rulenumber +":");
	        button.setActionCommand("rule" + rulenumber);
	        button.addActionListener(this);
	        button.setToolTipText("Activate rule " + rulenumber);
	        
	        int ypos = (rulenumber - 1)*2;
	        
	        c.gridx = 0;
	        c.gridy = ypos;
	        c.gridwidth = 1;
	        rules.add(button, c);
	        JLabel r1 = new JLabel("If New "); 
	        c.gridx = 1;
	        c.gridy = ypos;
	        rules.add(r1, c);

	        String[] events = new String[bevents.length + gevents.length];
	        int eventsi = 0;
	        for (String s: bevents) {
	        	events[eventsi] = s;
	        	eventsi++;
	        }
	        for (String s: gevents) {
	        	events[eventsi] = s;
	        	eventsi++;
	        }
	        
	        JComboBox eventbox = new JComboBox(events);
	    	eventbox.setActionCommand("r"+rulenumber+"event");
	    	eventbox.addActionListener(this);
	        c.gridx = 2;
	        c.gridy = ypos;
	        rules.add(eventbox, c);	        
	        
	        /* JLabel and = new JLabel(" And ");
	        c.gridx = 3;
	        c.gridy = ypos;
	        rules.add(and, c);

	        JComboBox guardbox = new JComboBox(guards);
	        guardbox.setActionCommand("r"+rulenumber+"guard");
	        guardbox.addActionListener(this);
	        c.gridx = 4;
	        c.gridy = ypos;
	        rules.add(guardbox, c);
	        */

	        
	        JLabel then = new JLabel("Then");
	        c.gridx = 1;
	        c.gridy = ypos + 1;
	        rules.add(then, c);

	        JComboBox action1box = new JComboBox(actions);
	    	action1box.setActionCommand("r"+rulenumber+"action1");
	    	action1box.addActionListener(this);
	        c.gridx = 2;
	        rules.add(action1box, c);	        
	        JComboBox action2box = new JComboBox(actions);
	    	action2box.setActionCommand("r"+rulenumber+"action2");
	    	action2box.addActionListener(this);
	        c.gridx = 3;
	        rules.add(action2box, c);	 
	        JComboBox action3box = new JComboBox(actions);
	    	action3box.setActionCommand("r"+rulenumber+"action3");
	    	action3box.addActionListener(this);
	        c.gridx = 4;
	        rules.add(action3box, c);	 
	        rulenumber++;
	    }

	    /**
	     * Create the GUI and show it.  For thread safety,
	     * this method should be invoked from the
	     * event-dispatching thread.
	     */
	    private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("Mars Robot");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        FrankLBaum contentPane = new FrankLBaum(new GridBagLayout());
	        frame.addWindowListener(contentPane);
	        frame.setContentPane(contentPane);
	 
	 
	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }
	 
	    /**
	     * Start up the whole application
	     * @param args
	     */
	    public static void main(String[] args) {
	    	AILConfig config;
	    	// By default the robot is Noor, but if a different name is supplied then this is used.
	    	if (args.length > 0) {
	    		rName = args[0];
	    	}
	    	
	    	String path = "/src/eass/nxt/legorover/wizardofoz/";
	    	if (args.length > 1) {
	    		path = args[1];
	    	}
	    	
	    	if (rName.equals("claudia")) {
		    	config = new AILConfig(path + "Claudia.ail");
	    	} else {
	    		config = new AILConfig(path + "dorothy.ail");
	    	}
			AIL.configureLogging(config);
		
			MAS mas = AIL.AILSetup(config);
			env = (EASSNXTEnvironment) mas.getEnv();
			
			//Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }}
	        );

	        // Lastly we construct a controller.
			MCAPLcontroller mccontrol = new MCAPLcontroller(mas, "", 1);
			// Start the system.
			mccontrol.begin(); 
			mas.finalize();
			
	    }
	
	    
	    /*
	     * (non-Javadoc)
	     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	     */
		public void actionPerformed(ActionEvent e) {
			
			// Things are busy ignore latest robot action.
	    	if (envThread.busy()) {
	    		System.err.println("Actions already queued");
	    		
	    		if (teleop_commands.contains(e.getActionCommand())) {
	    			return;
	    		}

	    		System.err.println("Queuing action anyway");
	    	}
	    	
	   		    	
	    	// This is a settings change
	    	if (e.getActionCommand().equals("delay")) {
	    		JComboBox cb = (JComboBox)e.getSource();
	    		String delaystring = (String) cb.getSelectedItem();
	    		if (delaystring.startsWith("0s")) {
	    			delay = 0;
	    		} else if (delaystring.startsWith("0.5")) {
	    			delay = 500;
	    		} else if (delaystring.startsWith("1s")) {
	    			delay = 1000;
	    		} else if (delaystring.startsWith("1.3")) {
	    			delay = 1300;
	    		} else {
	    			delay = 180000;
	    		}
	    		return;
	    	} 
	    	
	    	
	    	// Everything else will get sent to the robot
	    	Action act = new Action(e.getActionCommand());

	    	if (ruleComponent(e.getActionCommand()) || e.getActionCommand().equals("new_goal")) {
	    		JComboBox cb = (JComboBox)e.getSource();
	    		act.addTerm(planComponent((String)cb.getSelectedItem()));
	    	} 
	    	
	    	if (teleop_commands.contains(e.getActionCommand())) {
	    		envThread.latestAction(new Action("interrupted"));
	    	}
	    
	    	envThread.latestAction(act);	    		    	

		}
		
		// Converting interface elements into literals for action.
		public Predicate planComponent(String boxentry) {
	    	boxentry = boxentry.replace(' ', '_');
		    
		    if (boxentry.startsWith("BELIEF_")) {
		    	return new Literal(boxentry.substring(7));
		    }
		    
		    if (boxentry.startsWith("GOAL_")) {
		    	Literal g = new Literal("new_goal");
		    	g.addTerm(new Literal(boxentry.substring(5)));
		    	return g;
		    }
		    
		    if (boxentry.equals("Anything")) {
		    	return Predicate.PTrue;
		    }
		    
		    if (boxentry.startsWith("BELIEVE_")) {
		    	return new Literal(boxentry.substring(8));
		    }
		    
		    if (boxentry.startsWith("DON")) {
		    	Literal not = new Literal("not");
		    	not.addTerm(new Literal(boxentry.substring(14)));
		    	return not;
		    }
		    
		    if (boxentry.startsWith("ACHIEVE")) {
		    	Literal g = new Literal("new_goal_deed");
		    	g.addTerm(new Literal(boxentry.substring(13)));
		    	return g;
		    }
		    		    
		    return new Literal(boxentry);
		}
		
		public boolean ruleComponent(String s) {
			String comp = s.substring(2);
			if (comp.equals("event") || comp.equals("guard") || 
					comp.equals("action1") || comp.equals("action2") || comp.equals("action3")) {
				return true;
			}
			
			return false;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent e) {
			Object source = e.getSource();
			
			if (source == speedbox) {
				reactToThresholdChange(speedbox, "speed");
			} else if (source == rspeedbox) {
				reactToThresholdChange(rspeedbox, "rspeed");
			} else if (source == distancebox) {
				reactToThresholdChange(distancebox, "obstacle_distance");
			} else if (source == wllightbox) {
				reactToThresholdChange(wllightbox, "water_lower");
			} else if (source == wulightbox) {
				reactToThresholdChange(wulightbox, "water_upper");
			} else if (source == bllightbox) {
				reactToThresholdChange(bllightbox, "river_bed_lower");
			} else if (source == bulightbox) {
				reactToThresholdChange(bulightbox, "river_bed_upper");
			}
						
		}
		
		/**
		 * The code for reacting to a user change in one of the box values
		 * @param source
		 * @param threshold
		 * @param init
		 * @param actionname
		 * @return
		 */
		private int reactToThresholdChange(JFormattedTextField source, String actionname) {
			int new_value = ((Number) source.getValue()).intValue();
			
			int threshold = thresholds.get(actionname);
			boolean init = inits.get(actionname);
			
			if (! (new Integer(new_value)).equals(new Integer(threshold)) || !init) {
				System.err.println("Changing!!! " + threshold + " " + new_value + " " + init);
				thresholds.put(actionname, new_value);
				inits.put(actionname, true);
				
				Action act = new Action(actionname);
				act.addTerm(new NumberTermImpl(new_value));
				try {
					env.executeAction(rName, act);
				} catch (Exception ex) {
					System.err.println(ex.getMessage());
				}
			}
			
			return threshold;
			
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
		 */
	    public void windowActivated(WindowEvent e) {};
	    /*
	     * (non-Javadoc)
	     * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	     */
	    public void windowClosed(WindowEvent e) {
	    	envThread.stopRunning();
	    	System.out.println("closing "+rName);
	    	env.close(rName);
	    }
	    /*
	     * (non-Javadoc)
	     * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	     */
	    public void windowClosing(WindowEvent e) {
	    	System.out.println("closing "+rName);
	    	env.close(rName);
	    };
	    /*
	     * (non-Javadoc)
	     * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
	     */
	    public void windowDeactivated(WindowEvent e) {};
	    /*
	     * (non-Javadoc)
	     * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	     */
	    public void windowDeiconified(WindowEvent e) {};
	    /*
	     * (non-Javadoc)
	     * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	     */
	    public void windowIconified(WindowEvent e) {};
	    /*
	     * (non-Javadoc)
	     * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	     */
	    public void windowOpened(WindowEvent e) {};
	    
    	public void updateAgentState() {
    		try {
	    	AILAgent noor = env.agentmap.get("noor");
	    	ArrayList<Literal> nbbs = noor.getBB().getAll();
	    	String sbtext = " ";
	    	int slength = 0;
	    	for (Literal l : nbbs) {
	    		if (l.toString().equals("obstacle")) {
    				slength++;
	    			if (slength > 1) {
	    				sbtext += ", ";
	    			}
	    			sbtext +="obstacle";
	    			
	    		}
	    		
	    		if (l.toString().equals("water")) {
    				slength++;
	    			if (slength > 1) {
	    				sbtext += ", ";
	    			}
	    			sbtext +="water";
	    			
	    		}

	    		if (l.toString().equals("river_bed")) {
    				slength++;
	    			if (slength > 1) {
	    				sbtext += ", ";
	    			}
	    			sbtext +="river bed";
	    			
	    		}
	    	}
	    	showbeliefs.setText(sbtext);
	    	
	    	ArrayList<Intention> isref = noor.getIntentions();
	    	ArrayList<Intention> is = new ArrayList<Intention>();
	    	is.addAll(isref);
	    	if (noor.getIntention() != null) {
	    		is.add(noor.getIntention());
	    	}
	    	String sgtext = " ";
	    	slength = 0;
	    	ArrayList<Goal> ngs = new ArrayList<Goal>();
	    	for (Intention i: is) {
	    		ArrayList<Event> events = i.events();
	    		for (Event e: events) {
	    			if (e.getCategory() == Event.AILGoal) {
	    				if (!ngs.contains(e.getGoal())) {
	    					ngs.add(e.getGoal());
	    				}
	    			}
	    		}
	    	}
	    	for (Goal g: ngs) {
	    		Literal l = g.getLiteral();
	    		// System.err.println(l);
	    		if (l.toString().equals("obstacle")) {
    				slength++;
	    			if (slength > 1) {
	    				sgtext += ", ";
	    			}
	    			sgtext +="obstacle";
	    			
	    		}
	    		
	    		if (l.toString().equals("water")) {
    				slength++;
	    			if (slength > 1) {
	    				sgtext += ", ";
	    			}
	    			sgtext +="water";
	    			
	    		}

	    		if (l.toString().equals("river_bed")) {
    				slength++;
	    			if (slength > 1) {
	    				sgtext += ", ";
	    			}
	    			sgtext +="river bed";
	    			
	    		}
	    	}
	    	showgoals.setText(sgtext);
    		} catch (Exception e) {
    			System.err.println("couldn't get agent: " + e.getMessage());
    		}

		}
	    
	    /**
	     * A text area to display the contents of an output stream.
	     * @author lad
	     *
	     */
	    public class TextAreaOutputStream extends OutputStream {

	    	   private final JTextArea textArea;
	    	   private final StringBuilder sb = new StringBuilder();
	    	   private String title;

	    	   public TextAreaOutputStream(final JTextArea textArea, String title) {
	    	      this.textArea = textArea;
	    	      this.title = title;
	    	      sb.append(title + "> ");
	    	   }

	    	   /*
	    	    * (non-Javadoc)
	    	    * @see java.io.OutputStream#flush()
	    	    */
	    	   public void flush() {
	    	   }

	    	   /*
	    	    * (non-Javadoc)
	    	    * @see java.io.OutputStream#close()
	    	    */
	    	   public void close() {
	    	   }

	    	   /*
	    	    * (non-Javadoc)
	    	    * @see java.io.OutputStream#write(int)
	    	    */
	    	   public void write(int b) throws IOException {

	    	      if (b == '\r')
	    	         return;

	    	      if (b == '\n') {
	    	         final String text = sb.toString() + "\n";
	    	         SwingUtilities.invokeLater(new Runnable() {
	    	            public void run() {
	    	               textArea.append(text);
	    	            }
	    	         });
	    	         sb.setLength(0);
	    	         sb.append(title + "> ");
	    	         return;
	    	      }

	    	      sb.append((char) b);
	    	           
	    	   }
	    }
	    
	    /**
	     * We encapsulate the environment in a thread because small children like rapidly pressing buttons and the
	     * environments executeAction method takes too  long if they do this.
	     * @author louiseadennis
	     *
	     */
	    public class EnvironmentThread extends Thread {
	    	// This stores the most recent action.  We assume children have no interest in queueing actions.
	    	Queue<Action> actions = new LinkedList<Action>();
	    	// A flag to control the while loop in the run method.
	    	boolean isrunning = true;
	    	
	    	/**
	    	 * Set up the latest actions ready to run next time the thread loops.  Notify the thread in case it is waiting.
	    	 * @param name
	    	 * @param act
	    	 */
	    	public void latestAction(Action act) {
	    		synchronized(this) {
	    			actions.add(act);
	    			notify();
	    		}
	    	}
	    	
	    	public boolean busy() {
	    		return (!actions.isEmpty());
	    	}
	    	
	    	/*
	    	 * (non-Javadoc)
	    	 * @see java.lang.Thread#run()
	    	 */
	    	public void run() {
	    		while (isrunning) {
	    			synchronized(this) {
	    				// If there is no recent action we wait for one to arrive.
	    				if (actions.isEmpty()) {
	    					try {
	    						wait();
	    					} catch (InterruptedException ie) {
	    						System.err.println("catching an interrupt");
	    					}
	    				}
	    			}
	    			
	    			// At this point we should definitely have an action so it is safe to execute the most recent.
	    			executeAction();
	    			
	    	}
	    	

	    		
	    	}
	    	
	    	/**
	    	 * Execute the most recently supplied action, with a delay if necessary.
	    	 */
	    	public void executeAction() {
		    	try {
		    		Action act = actions.poll();
		    		// OK we've got the action so set it to null ready for the next one to arrive.
		    		try {
		    			System.err.println("delaying " + delay);
		    			Thread.sleep(delay);
		    		} catch (InterruptedException ie) {
		    			System.out.println("catching interrupt");
		    		}
		    		env.executeAction(rName, act);
		    	} catch (Exception ex){
		    		System.err.println(ex.getMessage());
		    	}
		    	
	    		
	    	}
	    	
	    	/**
	    	 * Stop running.
	    	 */
	    	public void stopRunning() {
	    		isrunning = false;
	    	}
	    	
	    }

	    

}
