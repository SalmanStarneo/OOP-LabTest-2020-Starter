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
		borderX = width * 0.06f;
		borderY= height *0.15f;
	}
	float borderY;
	float borderX;

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
		
		// for(int i =0; i< tasksArray.size(); i++) {
			// float y = map(10, 0, tasksArray.size(), borderX, height - borderX);

			if (mouseX >= 100 && mouseX < (width-50) && mouseY>=borderX && mouseY<height-borderX) {
				println("Mouse pressed");	
               
            }
        // }
		
	}
	int value=0;
	public void mouseDragged() 
	{
		value = value + 5;
		if (value > 255) {
			value = 0;
		}
		if (mouseX > 100 && mouseX < (width-50) && mouseY>borderX && mouseY<height-borderX) {
			println("Mouse Dragged");	
		}
		
}

	public void setup() 
	{
		loadTasks();
		printTasks();
		colorMode(HSB);
		// textSize(14);
	}

	public void displayTasks()
	{
		int i=0;
		float x;
		float x2;
        for(Task tA:tasksArray)
        {

			x = map(tA.getStarts(), 1, 30, 90, width - 60);
			x2 = map(tA.getEnds(), 1, 30, 90, width - 60);
			float taskwidth= x2-x;
			float y = map(i, 0,tasksArray.size(), 100, height-100);
			noStroke();
            fill(i*(tA.getEnds()),255,255);
			rect(x, y,taskwidth, 30);
			// rect(x, y,endwidth, 30);
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
			
            x = map(i, 1, 30, 90, width - 60);
            line(x, borderX, x, height - borderY);
            
            fill(255);
			text(i, x, borderX / 2);
		}
		
    }
	
	public void draw()
	{			
		background(0);
		drawGrid();
		displayTasks();
		
	}
}
