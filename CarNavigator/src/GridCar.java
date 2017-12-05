
public class GridCar implements GridComponent {
	//put whatever tf in here that a car needs
	
	int speed = 0; //we might want this to be a double idk
	
	public GridCar(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	@Override
	public String print() {
		return "C";
	}
}
