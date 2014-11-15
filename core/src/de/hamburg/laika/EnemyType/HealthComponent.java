package de.hamburg.laika.EnemyType;


public class HealthComponent {
	int health;

	public HealthComponent(int health) {
		this.health = health;
	}

	public boolean damage(int damage){
		this.health = Math.max(0, health - damage);
		return  this.health == 0;
	}

	public int getHealth(){
		return this.health;
	}
}
