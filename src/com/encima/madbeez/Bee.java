package com.encima.madbeez;

public abstract class Bee {
	//using float instead of double as it is more memory efficient and we do not need to represent decimals
	private float health = 100;
	int deathThreshold;
	
	Bee(int deathThreshold) {
		setDeathThreshold(deathThreshold);
	}
	
	boolean isDead() {
		if(this.health < deathThreshold)
			return true;
		else
			return false;
	}
	
	float damage(int damPercent) {
		//only hurt the bee if it is still alive
		if(!isDead())
			setHealth((health - damPercent)); 
		return health;
	}
	
	//prevent access outside of the class
	private void setHealth(float health) {
		if(health < 0) health = 0;
		this.health = health;
	}
	
	float getHealth() {
		return this.health;
	}

	public int getDeathThreshold() {
		return deathThreshold;
	}

	public void setDeathThreshold(int deathThreshold) {
		this.deathThreshold = deathThreshold;
	}
}
