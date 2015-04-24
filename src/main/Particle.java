package main;

public class Particle {
	
	public Level level;
	public float x, y;
	public int time;
	
	public Particle(float x, float y, Level level) {
		
		this.x = x;
		this.y = y;
		this.level = level;
	}
	
	public void update() {
		
		time--;
		if (time <= 0) {
			level.particles.remove(this);
		}
	}
	
	public void render() {
		
	}
}
