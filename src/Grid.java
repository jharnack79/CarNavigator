import java.awt.Point;
import java.util.ArrayList;

public class Grid {
	private GridComponent[][] grid;
	private ArrayList<GridCar> carList;
	private ArrayList<Point> lightList;

	public enum GridEnum implements GridComponent {
		GREENLIGHT, REDLIGHT, sidewalk, LANE;

		public String print() {
			return this.name().substring(0, 1) + " ";
		}
	}

	public Grid(int numberOfCars) {

		if (numberOfCars > 4) {
			System.err.println("Number of cars must be 1 - 4");
			System.exit(0);
		} else {
			grid = new GridComponent[7][7];
			lightList = new ArrayList<Point>();

			for (int row = 0; row < grid.length; row++) {
				for (int column = 0; column < grid[0].length; column++) {
					grid[row][column] = GridEnum.LANE;
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

			// add the lights to a list so they can easily be iterated through when starting
			// threads in the Runner class
			lightList.add(new Point(1, 3));
			lightList.add(new Point(3, 1));
			lightList.add(new Point(3, 3));
			lightList.add(new Point(3, 5));
			lightList.add(new Point(5, 3));

			grid[2][2] = GridEnum.sidewalk;
			grid[2][4] = GridEnum.sidewalk;
			grid[4][2] = GridEnum.sidewalk;
			grid[4][4] = GridEnum.sidewalk;
			grid[3][2] = GridEnum.sidewalk;
			grid[3][4] = GridEnum.sidewalk;

			carList = new ArrayList<GridCar>();

			// randomly place however many cars are passed through the args on the streets
			placeCars(numberOfCars);

			// print out the finalized grid - will likely remove this later
			System.out.println("Original grid:");
			printGrid();
		}
	}

	public void placeCars(int numberOfCars) {
		switch (numberOfCars) {
			case 4:
				GridCar c4 = new GridCar(GridCar.Direction.UP, 1, 4, 3, this, "C4");
	
				grid[4][3] = c4;
	
				carList.add(c4);
			case 3:
				GridCar c3 = new GridCar(GridCar.Direction.RIGHT, 1, 1, 4, this, "C3");
	
				grid[1][4] = c3;
	
				carList.add(c3);
	
			case 2:
				GridCar c2 = new GridCar(GridCar.Direction.UP, 1, 4, 1, this, "C2");
	
				grid[4][1] = c2;
	
				carList.add(c2);
			case 1:
				GridCar c1 = new GridCar(GridCar.Direction.UP, 1, 1, 1, this, "C1");
	
				grid[1][1] = c1;
	
				carList.add(c1);
				break;
			default:
				break;
		}
	}

	public void printGrid() {
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[0].length; column++) {
				System.out.print(grid[row][column].print() + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public ArrayList<GridCar> getCarList() {
		return this.carList;
	}

	public ArrayList<Point> getLightList() {
		return this.lightList;
	}

	public int width() {
		return grid[0].length;
	}

	public int height() {
		return grid.length;
	}

	public GridComponent getGridComponent(int x, int y) {
		return grid[x][y];
	}

	public void setGridComponent(int x, int y, GridComponent gc) {
		grid[x][y] = gc;
	}

}
