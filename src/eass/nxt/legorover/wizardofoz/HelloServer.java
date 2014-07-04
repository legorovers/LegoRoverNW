package eass.nxt.legorover.wizardofoz;

import java.lang.reflect.Method;
import java.util.Map;

import javax.xml.ws.Response;

import ail.mas.AIL;
import ail.mas.MAS;
import ail.util.AILConfig;
import ail.syntax.Action;
import ajpf.MCAPLcontroller;

import eass.mas.nxt.EASSNXTEnvironment;
import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.ServerRunner;

public class HelloServer extends NanoHTTPD {
    protected static EASSNXTEnvironment env;
    static MAS mas;

    public HelloServer() {
		super(8080);
	}

	@Override
	public Response serve(IHTTPSession session) {
		Method method = session.getMethod();
		String uri = session.getUri();
		
		
		System.out.println(method + " '" + uri + "' ");

		String msg = "<html><body><h1>Hello server</h1>\n";
		Map<String, String> parms = session.getParms();
		if (parms.get("username") == null)
			msg += "<form action='?' method='get'>\n"
					+ "  <p>Your name: <input type='text' name='username'></p>\n"
					+ "</form>\n";
		else {
			try {
				env.executeAction("noor", new Action("forward"));
				msg += "<p>action sent</p>";
			} catch (Exception e) {
				msg += "<p>action failed</p>";
			}
			
		}
			

		msg += "</body></html>\n";

		return new NanoHTTPD.Response(msg);
	}

	public static void main(String[] args) {
    	AILConfig config;
    	// By default the robot is Noor, but if a different name is supplied then this is used.
     	
    	String path = "/src/eass/nxt/legorover/wizardofoz/";
    	if (args.length > 1) {
    		path = args[1];
    	}
    	
     		config = new AILConfig(path + "dorothy.ail");

		AIL.configureLogging(config);
	
		mas = AIL.AILSetup(config);
		env = (EASSNXTEnvironment) mas.getEnv();
		
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
 
		AgentThread dorothy = new HelloServer.AgentThread();
		dorothy.start();
		ServerRunner.run(HelloServer.class);
		mas.finalize();
	}
	
	static  class AgentThread extends Thread {
		public void run() {
			MCAPLcontroller mccontrol = new MCAPLcontroller(mas, "", 1);
			// Start the system.
			mccontrol.begin(); 
		
		}
	}
}
