package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	ArrayList<Task> tasksArray = new ArrayList<Task>();
	
	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{
		Table table = loadTable("tasks.csv","header");

        for(TableRow row:table.rows())
        {
            Task tA = new Task(row);
            tasksArray.add(tA);
        }
	}

	public void printTasks()
	{
		for(Task tA:tasksArray)
        {
            System.out.println(tA);
        }
	}
	
	public void mousePressed()
	{
		println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	public void setup() 
	{
		loadTasks();
		printTasks();
		colorMode(HSB);
	}

	public void displayTasks()
	{
		
        for(int i = 0; i < tasksArray.size(); i++)
        {
            Task tA = tasksArray.get(i);

			float x = map(i, 0,tasksArray.size(), 100, width-100);
			float y = map(i, 0,tasksArray.size(), 100, height-100);
			noStroke();
            fill(i*tA.getStarts(),255,255);
			rect(x, y, tA.getStarts()+tA.getEnds(), 50);
			fill(255);
			textAlign(CENTER, CENTER);
			text(tA.getTasks(), 50, y);
			
        }
	}

    public void drawGrid()
    {
        float border = width * 0.06f;
		float x=0;
        stroke(0, 0, 255);
        textAlign(CENTER, CENTER);
		for(int i = 1 ; i <= 30 ; i ++)
        {
			
            x = map(i, 1, 30, 100, width - 50);
            line(x, border, x, height - border);
            // line(border, x, width - border, x);    
            
            fill(255);
			text(i, x, border / 2);
		}
		// for(Task tA:tasksArray)
		// {
			
		// }
    }
	
	public void draw()
	{			
		background(0);
		drawGrid();
		displayTasks();
		
	}
}
