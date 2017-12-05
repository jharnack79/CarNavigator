import java.util.ArrayList;

public class Runner {
	private static Grid grid;
	private static boolean isMoving;
	private static boolean isAbleToMove; //flag variable so only one car is manipulating the grid at once
	
	public static void main (String[] args) {
		grid = new Grid(Integer.parseInt(args[0]));
		runThreads(grid.getCarList(), grid.getLightList());
	}
	
	public static void runThreads (ArrayList<GridCar> listOfCars, ArrayList<Grid.GridEnum> listOfLights) {
		for(GridCar car : listOfCars) {
			new Thread() {
				public void run() {
					if(isAbleToMove)
						isAbleToMove = false;
						car.move();
						isAbleToMove = true;
				}
			}.start();
		}
		
		for(Grid.GridEnum stoplight : listOfLights) {
			new Thread() {
				public void run() {
					//TODO: implement stoplights, alternating between green and red every few seconds
				}
			}.start();
		}
	}
}
