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
		borderX = width * 0.9f;
		borderY= height *0.06f;
		mouseKeycode=0;
	}
	//needed global variables
	float borderY;
	float borderX;
	float taskSatrtpoint;
	float taskEndpoint;
	float mouseKeycode;

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
		
		if ((mouseX > 88 && mouseX < borderX && mouseY>borderY && mouseY<height-borderY))
		{
			println("Mouse pressed");
			mouseKeycode=1;	
			
		}
		else
		{
			mouseKeycode=0;
		}
       
		
	}
	
	public void mouseDragged() 
	{
		
		while (mouseKeycode==1)
		{
			println("Mouse Dragged");	
		}
		
	}

	public void setup() 
	{
		loadTasks();
		printTasks();
		colorMode(HSB);
		textSize(14);
	}

	public void displayTasks()
	{
		int i=0;
		float xStart;
		float xEnd;
        for(Task tA:tasksArray)
        {

			xStart = map(tA.getStarts(), 1, 30, 90, borderX);
			xEnd = map(tA.getEnds(), 1, 30, 90, borderX);
			float taskwidth= xEnd-xStart;
			float y = map(i, 0,tasksArray.size(), 100, height-borderY);
			noStroke();
            fill((i)*(30),255,255);
			rect(xStart, y,taskwidth, 30);
			// rect(xEnd, y,taskwidth, 30);
			fill(255);
			textAlign(CENTER, CENTER);
			text(tA.getTasks(), 50, y+10);
			i++;
			
        }
	}

    public void drawGrid()
    {
        
		float x=0;
        stroke(0, 0, 255);
        textAlign(CENTER, CENTER);
		for(int i = 1 ; i < 31 ; i++)
        {
			
            x = map(i, 1, 30, 90, borderX);
            line(x, borderY, x, height - borderY);
            
            fill(255);
			text(i, x, borderY / 2);
		}
		
    }
	
	public void draw()
	{			
		background(0);
		drawGrid();
		displayTasks();
		
	}
}
