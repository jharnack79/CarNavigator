
public class GridCar implements GridComponent {
	//put whatever tf in here that a car needs
	
	int speed = 0; //we might want this to be a double idk
	
	public GridCar(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	//lol
	
	@Override
	public String print() {
		return "C";
	}
	
	public void move(int spaces) {
		//is this the approach we want to take?
		//we can either tell the grid the new position and the grid tells that to the car
		//or we can tell the car its new position from the thread and then have it tell the grid
		//we could do this by storing old and new location in car and have it clear out the old location in the grid
	}
}
