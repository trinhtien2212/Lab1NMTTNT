package chapter2.agent_AB; 

public class TestSimpleReflexAgent {
	public static void main(String[] args) {
		Environment env = new Environment();
		Agent agent = new Agent(new AgentProgram());
		env.addAgent(agent, 0,0);

		env.stepUntilDone();
	}
}
