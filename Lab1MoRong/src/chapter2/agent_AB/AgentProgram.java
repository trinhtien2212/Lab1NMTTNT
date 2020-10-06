package chapter2.agent_AB;

import java.util.Random;

//import com.sun.java.swing.plaf.windows.TMSchema.TypeEnum;

import chapter2.agent_AB.Environment.LocationState;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		int agentLocationX=p.getAgentLocationX();
		int agentLocationY=p.getAgentLocationY();
		System.out.println("Vi tri hien tai"+agentLocationX+";"+agentLocationY);
		if(p.getLocationState()==LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		}
		return Environment.createNextHop(agentLocationX, agentLocationY);		
	}
}