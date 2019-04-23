import java.awt.*;
import java.util.*;

public class SandLab
{
  //Step 4,6
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  
  //do not add any more fields below
  private int[][] grid;
  private SandDisplay display;
  
  
  /**
   * Constructor for SandLab
   * @param numRows The number of rows to start with
   * @param numCols The number of columns to start with;
   */
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    // Change this value to add more buttons
    //Step 4,6
    names = new String[4];
    // Each value needs a name for the button
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    
    //1. Add code to initialize the data member grid with same dimensions
    
    
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
    grid = new int[numRows][numCols];
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
    //2. Assign the values associated with the parameters to the grid
	  grid[row][col] = tool;
   
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
      //Step 3
   //Hint - use a nested for loop
	  
	  for(int row = 0; row < grid.length; row++)
	  {
		  for(int col = 0; col < grid[0].length; col++)
		  {
			  if(grid[row][col] == EMPTY)
			  {
				  display.setColor(row, col, Color.BLACK);
			  }
			  else if(grid[row][col] == METAL)
			  {
				  display.setColor(row, col, Color.GRAY);
			  }
			  else if(grid[row][col] == SAND)
			  {
				  display.setColor(row,  col,  Color.YELLOW);
			  }
			  else if(grid[row][col] == WATER)
			  {
				  display.setColor(row, col, Color.BLUE);
			  }
				  
		  }
	  }
    
  }

  //Step 5,7
  //called repeatedly.
  //causes one random particle in grid to maybe do something.
  public void step()
  {
    //Remember, you need to access both row and column to specify a spot in the array
    //The scalar refers to how big the value could be
    //int someRandom = (int) (Math.random() * scalar)
    //remember that you need to watch for the edges of the array
    
	  int rowRandom = (int) (Math.random() * grid.length);
	  
	  int colRandom = (int) (Math.random() * grid[0].length);
	  
	  
	  if(grid[rowRandom] [colRandom] == SAND)
	  {
		  if(rowRandom + 1 < grid.length && grid[rowRandom + 1][colRandom] == EMPTY)
		  {
			  grid[rowRandom + 1][colRandom] = SAND;
			  grid[rowRandom - 1][colRandom] = EMPTY;
		  }
	  }
	  
	  if(grid[rowRandom] [colRandom] == WATER)
	  {
		  if(rowRandom + 1 < grid.length && grid[rowRandom + 1][colRandom] == EMPTY)
		  {
			  grid[rowRandom + 1][colRandom] = WATER;
			  grid[rowRandom - 1][colRandom] = EMPTY;
		  }
	  }
    
  }
  
  //do not modify this method!
  public void run()
  {
    while (true) // infinite loop
    {
      for (int i = 0; i < display.getSpeed(); i++)
      {
        step();
      }
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
      {
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
      }
    }
  }
}
