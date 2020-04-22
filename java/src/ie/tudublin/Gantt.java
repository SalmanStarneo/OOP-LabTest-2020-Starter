package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	ArrayList<Task> tasksArray = new ArrayList<Task>();
	float[] taskHeightArray = new float[9];
	public void settings()
	{
		size(800, 600);
		borderX = width * 0.9f;
		borderY= height *0.06f;
		//These two variables if mouseDragged(), worked it would moved the tasks
		taskSatrtpoint=0;
		taskEndpoint=0;
	}
	//needed global variables
	float borderY;
	float borderX;
	float taskSatrtpoint;
	float taskEndpoint;
	float mouseKeycode1=-1;
	float mouseKeycode2=-1;

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
			float mouseDest1= map(tA.getStarts(), 1, 30, 90, borderX);
			float mouseDest2 = map(tA.getEnds(), 1, 30, 90, borderX);
			//cannot get the mid point between start and end of task for some reason
			float mouseDwidth=(mouseDest2)/30;
			float mouseDestY=map(m, 0,tasksArray.size(), 100, height-borderY);
			taskHeightArray[m]=mouseDestY;
			if (mouseX>=mouseDest1 && mouseX<mouseDwidth&&mouseDestY-30<mouseY&&mouseDestY+30>mouseY)
			{


				if(mouseKeycode1==-1){	
					println("Mouse pressed L"+m+"//"+mouseDwidth);
					// mouseKeycode1=m;	
					// println(distL);
				}
				else
				{
					mouseKeycode1=-1;
				}
				
			}
			else if(mouseX<=mouseDest2&&mouseDwidth<mouseX&&mouseDestY-30<mouseY&&mouseDestY+30>mouseY)
			{
				if(mouseKeycode2==-1)
				{
					
					// mouseKeycode2=m;
					println("Mouse pressed R"+m+"//"+mouseDwidth);
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
				if(mouseX>mouseX+1)
				{
					taskSatrtpoint++;
				}
				else if(mouseX<mouseX+1)
				{
					taskSatrtpoint--;
				}
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
		float y;
        for(Task tA:tasksArray)
        {

			xStart = map(tA.getStarts()+taskSatrtpoint, 1, 30, 90, borderX);
			xEnd = map(tA.getEnds()+taskEndpoint, 1, 30, 90, borderX);
		
			float taskwidth= xEnd-xStart;
			y = map(i, 0,tasksArray.size(), 100, height-borderY);
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
