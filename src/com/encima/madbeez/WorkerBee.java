package com.encima.madbeez;

public class WorkerBee extends Bee {
	private final static int deathThreshold = 70;
	
	WorkerBee() {
		super(deathThreshold);
	}
}
