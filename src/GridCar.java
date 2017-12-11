import java.awt.Point;
import java.util.Random;

public class GridCar implements GridComponent {	
	int speed = 0;
	private Direction direction;
	private int column, row;
	private Grid grid;
	private boolean doneMoving = false;
	private String name = "C";
	
	public enum Direction {
		UP {
			@Override
			public void moveOneStep(GridCar car) {
				if (car.row - 1 < 0
						|| car.grid.getGridComponent(car.row - 1, car.column) == Grid.GridEnum.sidewalk) {
					Direction.RIGHT.moveOneStep(car);
					car.direction = Direction.RIGHT;
				} else {
					car.row -= 1;
				}
			}
			@Override
			public void moveBackOneStep(GridCar car) {
				if (car.row + 1 == car.grid.height()
						|| car.grid.getGridComponent(car.row + 1, car.column) == Grid.GridEnum.sidewalk) {
					Direction.LEFT.moveBackOneStep(car);
					car.direction = Direction.LEFT;
				}
				else {
					car.row += 1;
				}
			}
		},
		
		DOWN {
			@Override
			public void moveOneStep(GridCar car) {
				if (car.row + 1 >= car.grid.height()
						|| car.grid.getGridComponent(car.row + 1, car.column) == Grid.GridEnum.sidewalk) {
					Direction.LEFT.moveOneStep(car);
					car.direction = Direction.LEFT;
				} else {
					car.row += 1;
				}
			}
			@Override
			public void moveBackOneStep(GridCar car) {
				if (car.row - 1 == 0
						|| car.grid.getGridComponent(car.row - 1, car.column) == Grid.GridEnum.sidewalk) {
					Direction.RIGHT.moveBackOneStep(car);
					car.direction = Direction.RIGHT;
				}
				else {
					car.row -= 1;
				}
			}
		},
		
		LEFT {
			@Override
			public void moveOneStep(GridCar car) {
				if (car.column - 1 < 0
						|| car.grid.getGridComponent(car.row, car.column - 1) == Grid.GridEnum.sidewalk) {
					Direction.UP.moveOneStep(car);
					car.direction = Direction.UP;
				} else {
					car.column -= 1;
				}
			}
			@Override
			public void moveBackOneStep(GridCar car) {
				if (car.column + 1 == car.grid.width()
						|| car.grid.getGridComponent(car.row, car.column + 1) == Grid.GridEnum.sidewalk) {
					// move position back
					car.direction = Direction.DOWN;
					Direction.DOWN.moveBackOneStep(car);
				}
				else {
					car.column += 1;
				}
			}
		},
		
		RIGHT {
			@Override
			public void moveOneStep(GridCar car) {
				if (car.column + 1 >= car.grid.width()
						|| car.grid.getGridComponent(car.row, car.column + 1) == Grid.GridEnum.sidewalk) {
					Direction.DOWN.moveOneStep(car);
					car.direction = Direction.DOWN;
				} else {
					car.column += 1;
				}
			}
			@Override
			public void moveBackOneStep(GridCar car) {
				if (car.column - 1 == 0 || car.column - 1 == Math.floorDiv(car.grid.width(), 2)
						|| car.grid.getGridComponent(car.row, car.column - 1) == Grid.GridEnum.sidewalk) {
					car.direction = Direction.UP;
					Direction.UP.moveBackOneStep(car);
				}
				else {
					car.column -= 1;
				}
			}
		};
		
		//Gets next step based on boundaries of grid and if next move would be a sidewalk
		public abstract void moveOneStep(GridCar car);
		public abstract void moveBackOneStep(GridCar car);
	}
	
	public GridCar(Direction direction, int speed, int row, int column, Grid grid, String name) {
		this.direction = direction;
		this.speed = speed;
		this.row = row;
		this.column = column;
		this.grid = grid;
		this.name = name;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	
	@Override
	public String print() {
		return name;
	}
	
	public Grid move() {
		//clear current position
		grid.setGridComponent(row, column, Grid.GridEnum.LANE1);
		
		//move as far as possible
		for (int i = 0 ; i < speed ; i++) {
			direction.moveOneStep(this);
			
			GridComponent gc = grid.getGridComponent(row, column);
			if (gc == Grid.GridEnum.REDLIGHT) {
				direction.moveBackOneStep(this);
				break;
			}
			else if (gc == Grid.GridEnum.GREENLIGHT) {
				if (column == Math.floorDiv(grid.width(), 2)) {
					Random r = new Random();
					int randomInt = r.nextInt(2);
					switch (randomInt) {
						case 0: //keep going left
							direction.moveOneStep(this);
							break;
						case 1: //start going up
							direction = Direction.UP;
							direction.moveOneStep(this);
							break;
					}
				}
				else {
					direction.moveOneStep(this);
				}
				
				gc = grid.getGridComponent(row, column);
				if (gc.print().contains("C")) {
					direction.moveBackOneStep(this);
					direction.moveBackOneStep(this);
					break;
				}
			}
			else if(gc == Grid.GridEnum.LANE1) { //change this later when we get rid of lane 2
				continue;
			}
			else { //car
				direction.moveBackOneStep(this);
				gc = grid.getGridComponent(row, column);
				break;
			}
		}
		
		//change grid to reflect new position
		grid.setGridComponent(row, column, this);
		return grid;
	}
}
