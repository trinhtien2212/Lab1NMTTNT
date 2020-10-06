package chapter2.agent_AB; 

import java.util.HashMap;
import java.util.Map;

import chapter2.agent_AB.Environment.LocationState;

public class EnvironmentState {
	private LocationState[][] arr;
	private int agentLocationX = 0;
	private int agentLocationY=0;

	public EnvironmentState(LocationState[][] arr) {
		this.arr=arr;
	}

	public void setAgentLocation(int x,int y) {
		this.agentLocationX=x;
		this.agentLocationY=y;
	}

	public int getAgentLocationX() {
		return this.agentLocationX;
	}
	public int getAgentLocationY() {
		return this.agentLocationY;
	}

	public LocationState getLocationState(int row,int col) {
		return this.arr[row][col];
	}

	public void setLocationState(int row,int col, LocationState locationState) {
		this.arr[row][col]=locationState;
	}

	public void display() {
//		System.out.println("Environment state: \n\t" + this.state);
		System.out.println("Co vo phan display");
		for(int row=0;row<arr.length;row++) {
			for(int col=0;col<arr.length;col++) {
				System.out.print(row+";"+col+": "+arr[row][col]+"\t");
			}
			System.out.println("\n");
		}
	}
}