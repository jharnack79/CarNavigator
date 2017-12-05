
public class GridCar implements GridComponent {	
	int speed = 0;
	
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
	
	public void move() {
		//TODO: implement movement of cars dependent on their speed variable
	}
}
