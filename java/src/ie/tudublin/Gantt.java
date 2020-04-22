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
		mouseKeycode1=-1;
		mouseKeycode2=-2;
		taskSatrtpoint=0;
		taskEndpoint=0;
	}
	//needed global variables
	float borderY;
	float borderX;
	float taskSatrtpoint;
	float taskEndpoint;
	float mouseKeycode1;
	float mouseKeycode2;

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
		for(int m=0 ; m<tasksArray.size() ;m++)
		{
			Task tA =tasksArray.get(m);
			float mouseHeighX1= map(tA.getStarts(), 1, 30, 90, borderX);
			float mouseHeighX2 = map(tA.getEnds(), 1, 30, 90, borderX);
			float mouseHeighY=map(m, 0,tasksArray.size(), 100, height-borderY);
			if (dist(mouseX, mouseY, mouseHeighX1, mouseHeighY)<dist(mouseX, mouseY, mouseHeighX2, mouseHeighY))
			{
				if(mouseKeycode1==-1){	
					println("Mouse pressed L");
					mouseKeycode1=m;	
				}
				else
				{
					mouseKeycode1=-1;
				}
				
			}
			else if(dist(mouseX, mouseY, mouseHeighX2, mouseHeighY)>dist(mouseX, mouseY, mouseHeighX1, mouseHeighY))
			{
				if(mouseKeycode2==-1)
				{
					
					mouseKeycode2=m;
					println("Mouse pressed R");
				}
				else
				{
				
					mouseKeycode2=-1;
				}
			}
		}
		
       
		
	}
	
	public void mouseDragged() 
	{
			
			if (mouseKeycode1!=-1 && mouseKeycode2==-1)
			{
				fill(255);
				ellipse(mouseX, mouseY, 90, 90);
				// taskSatrtpoint=+20;
				println("Mouse Dragged L");	
			}
			else if (mouseKeycode2!=-1 && mouseKeycode1==-1)
			{
				fill(0,255,255);
				ellipse(mouseX, mouseY, 90, 90);
				println("Mouse Dragged R");	
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

			xStart = map(tA.getStarts()-taskSatrtpoint, 1, 30, 90, borderX);
			xEnd = map(tA.getEnds()+taskEndpoint, 1, 30, 90, borderX);
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
