package chapter2.agent_AB;

import java.util.Random;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP=new DynamicAction("UP");
	public static final Action MOVE_DOWN=new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");

	public static final LocationState[][] arr=new LocationState[5][5];


	public enum LocationState {
		CLEAN, DIRTY,Obstacle
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;
	public int cost=0;

	//Khoi tao arr moi truong
	public void initArr() {
		Random r=new Random();
		for(int row=0;row<arr.length;row++) {
			for(int col=0;col<arr[row].length;col++) {
				int num=r.nextInt(100);
				if(num>=0 && num<33) {
					arr[row][col]=LocationState.CLEAN;
				}else if(num>=33 && num<66) {
					arr[row][col]=LocationState.DIRTY;
				}else if(num>=66 && num<100) {
					arr[row][col]=LocationState.Obstacle;
				}
			}
		}
	}

	public Environment() {
		initArr();
		envState = new EnvironmentState(arr);
	}

	//Random o moi cho agent
	public static Action createNextHop(int x, int y) {
		int num;
		Random r=new Random();
		while(true) {
			num=r.nextInt(4);
			LocationState s;
			try {
				switch (num) {
				case 0:
					s=arr[x][y-1];
					return MOVE_LEFT;
				case 1:
					s=arr[x][y+1];
					return MOVE_RIGHT;
				case 2:
					s=arr[x+1][y];
					return MOVE_DOWN;
				case 3:
					s=arr[x-1][y];
					return MOVE_UP;

				default:
					break;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}
			
		}

	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, int x,int y) {
		this.agent=agent;
		envState.setAgentLocation(x, y);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		int currentLocationX=envState.getAgentLocationX();
		int currentLocationY=envState.getAgentLocationY();
		if(action==Environment.SUCK_DIRT) {
			envState.setLocationState(currentLocationX,currentLocationY,LocationState.CLEAN);
		}else if(action==MOVE_RIGHT) {
			envState.setAgentLocation(currentLocationX,currentLocationY+1);
		}else if(action==MOVE_LEFT) {
			envState.setAgentLocation(currentLocationX,currentLocationY-1);
		}else if(action==MOVE_UP) {
			envState.setAgentLocation(currentLocationX-1,currentLocationY);
		}else if(action==MOVE_DOWN) {
			envState.setAgentLocation(currentLocationX+1,currentLocationY);
		}
		if(action !=Environment.SUCK_DIRT) {
			Percept p=getPerceptSeenBy();
			if(p.getLocationState()==LocationState.Obstacle) {
				cost -=100;
			}else cost -=10;
		}
		return envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		int agentLocationX=envState.getAgentLocationX();
		int agentLocationY=envState.getAgentLocationY();
		LocationState locationState=envState.getLocationState(agentLocationX,agentLocationY);
		Percept p=new Percept(agentLocationX,agentLocationY, locationState);
		return p;
		//		return null;
	}

	public void step() {
		envState.display();
		int agentLocationX=envState.getAgentLocationX();
		int agentLocationY=envState.getAgentLocationY();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);
		
		System.out.println("Agent Loc.: " + agentLocationX+";"+agentLocationY + "\tAction: " + anAction);
		System.out.println("Cost: "+cost);
		checkDone();
		es.display();
	}
	public void checkDone() {
		out:for(int row=0;row<arr.length;row++) {
			for(int col=0;col<arr[row].length;col++) {
				if(arr[row][col]==LocationState.DIRTY) {
					return;
				}
			}
		}
	isDone=true;
	System.out.println("Day la isDone: "+isDone);
	}
	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;
		System.out.println("Co vo");
		long t=System.currentTimeMillis();
		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
