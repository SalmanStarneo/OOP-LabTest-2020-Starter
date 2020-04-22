package ie.tudublin;

import processing.data.TableRow;

public class Task {

    private String tasks;
    private int starts;
    private int ends;

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public int getStarts() {
        return starts;
    }

    public void setStarts(int starts) {
        this.starts = starts;
    }

    public int getEnds() {
        return ends;
    }

    public void setEnds(int ends) {
        this.ends = ends;
    }

    @Override
    public String toString() {
        return "Task [ends=" + ends + ", starts=" + starts + ", tasks=" + tasks + "]";
    }

    public Task(String tasks, int starts, int ends) {
        this.tasks = tasks;
        this.starts = starts;
        this.ends = ends;
    }

    public Task(TableRow tr)
    {
        this(tr.getString("Task"),tr.getInt("Start"),tr.getInt("End"));
    }


}