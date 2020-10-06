package chapter2.agent_AB; 

public class Percept {
	private int agentLocationX;
	private int agentLocationY;
	private Environment.LocationState state;

	public Percept(int agentLocationX,int agentLocationY, Environment.LocationState state) {
		this.agentLocationX=agentLocationX;
		this.agentLocationY=agentLocationY;
		this.state = state;
	}

	public Environment.LocationState getLocationState() {
		return this.state;
	}

	public int getAgentLocationX() {
		return this.agentLocationX;
	}
	public int getAgentLocationY() {
		return this.agentLocationY;
	}
}