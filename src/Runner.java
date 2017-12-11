import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class Runner {
	private static Grid grid;
	private static boolean isAbleToMove = true; // flag variable so only one car is manipulating the grid at once
	private static Semaphore sem;
	
	public static void main (String[] args) {
		grid = new Grid(Integer.parseInt(args[0]));
		sem = new Semaphore(1);
		runThreads(grid.getCarList(), grid.getLightList());
	}
	
	public static void runThreads (ArrayList<GridCar> listOfCars, ArrayList<Point> listOfLights) {
		for(GridCar car : listOfCars) {
			new Thread() {
				public void run() {
					while(true) {
						try {
							sleep(1000);
							sem.acquire();
								grid = car.move();
								grid.printGrid();
							sem.release();
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			}.start();
		}
		
		for(Point stoplight : listOfLights) {
			new Thread() {
				public void run() {
					while(true) {
						try {
							Random r = new Random();
							int sleepTime = r.nextInt(4001) + 1000;
							sleep(sleepTime);
							Grid.GridEnum gc = (Grid.GridEnum)grid.getGridComponent(stoplight.x, stoplight.y);
							switch (gc) {
								case GREENLIGHT:
									grid.setGridComponent(stoplight.x, stoplight.y, Grid.GridEnum.REDLIGHT);
									break;
								case REDLIGHT:
									grid.setGridComponent(stoplight.x, stoplight.y, Grid.GridEnum.GREENLIGHT);
									break;
								default:
									break;
							}
						}
						catch(InterruptedException e) {
							e.printStackTrace();
						}
						
					}
				}
			}.start();
		}
	}
}
