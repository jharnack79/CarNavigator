import java.util.ArrayList;
import java.util.Random;

public class Grid {
	
	private GridComponent[][] grid;
	private ArrayList<GridCar> carList;
	private ArrayList<GridEnum> lightList;
	
	public enum GridEnum implements GridComponent{
		CAR, GRASS, GREENLIGHT, REDLIGHT, sidewalk, LANE1, LANE2;
		
		public String print() {
			return this.name().substring(0, 1);
		}
	}
	
	public Grid(int numberOfCars) {
		//instantiate grid how I want it for now obviously we can and probably should change this
		grid = new GridComponent[7][7];
		lightList = new ArrayList<GridEnum>();
		
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[0].length; column++) {
				grid[row][column] = GridEnum.LANE1;
			}
		}
		
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[0].length; column++) {
				if (row == 0 || row == grid.length - 1 || column == 0 || column == grid[0].length - 1)
					grid[row][column] = GridEnum.sidewalk;
			}
		}
		
		grid[1][3] = GridEnum.GREENLIGHT;
		grid[3][1] = GridEnum.GREENLIGHT;
		grid[3][3] = GridEnum.GREENLIGHT;
		grid[3][5] = GridEnum.GREENLIGHT;
		grid[5][3] = GridEnum.GREENLIGHT;
		lightList.add((GridEnum)grid[1][3]);
		lightList.add((GridEnum)grid[3][1]);
		lightList.add((GridEnum)grid[3][3]);
		lightList.add((GridEnum)grid[3][5]);
		lightList.add((GridEnum)grid[5][3]);

		grid[2][2] = GridEnum.sidewalk;
		grid[2][4] = GridEnum.sidewalk;
		grid[4][2] = GridEnum.sidewalk;
		grid[4][4] = GridEnum.sidewalk;
		
		carList = new ArrayList<GridCar>();
		//randomly put on however many cars are passed through the args
		randomizeCars(numberOfCars);
		
		//print out the finalized grid - remove this later probably
		printGrid();
	}
	
	public void randomizeCars(int numberOfCars) {
//		Random r = new Random();    //this takes forever so for now I just hardcoded the cars below
//		boolean done = false;
//		int currentCar = 0;
//		
//		while (!done) {
//			int row = r.nextInt(7);
//			int column = r.nextInt(7);
//			
//			if (grid[row][column] == GridEnum.LANE1) {
//				grid[row][column] = new Car();
//				currentCar++;
//				if(currentCar > numberOfCars)
//					done = true;
//			}
//		}
		
		GridCar c1 = new GridCar();
		GridCar c2 = new GridCar();
		GridCar c3 = new GridCar();
		GridCar c4 = new GridCar();
		
		grid[1][1] = c1;
		grid[4][1] = c2;
		grid[1][5] = c3;
		grid[3][4] = c4;
		
		carList.add(c1);
		carList.add(c2);
		carList.add(c3);
		carList.add(c4);

	}
	
	public void printGrid() {
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[0].length; column++) {
				System.out.print(grid[row][column].print() + " ");
			}
			System.out.println("");
		}
	}
	
	public ArrayList<GridCar> getCarList(){
		return this.carList;
	}
	
	public ArrayList<GridEnum> getLightList(){
		return this.lightList;
	}
	
}
