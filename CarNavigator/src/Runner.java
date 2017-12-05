import java.util.ArrayList;

public class Runner {
	private static Grid grid;
	
	public static void main (String[] args) {
		grid = new Grid(Integer.parseInt(args[0]));
		startThreads(grid.getCarList(), grid.getLightList());
	}
	
	public static void startThreads(ArrayList<GridCar> listOfCars, ArrayList<Grid.GridEnum> listOfLights) {
		for(GridCar car : listOfCars) {
			//start a thread and move them around and stuff
			//do something like Car.getSpeed() to get the speed and increment the car that many spaces
		}
		
		for(Grid.GridEnum stoplight : listOfLights) {
			//start a thread and change them every few seconds
		}
	}
}
