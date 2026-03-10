public class WorkoutEntry
{
    //creating the classes variables
    private String date;
    private String workout;
    private int sets;
    private int reps;

    //constructor for he class
    public WorkoutEntry(String date, String workout, int sets, int reps)
    {
        this.date = date;
        this.workout = workout;
        this.sets = sets;
        this.reps = reps;
    }

    //get methods for the class
    public String getDate()
    {
        return date;
    }

    public String getWorkout()
    {
        return workout;
    }

    public int getSets()
    {
        return sets;
    }

    public int getReps()
    {
        return reps;
    }

    //display all of these
    public void display()
    {
        System.out.println("Date: " + date + " | Workout: " + workout + " | Sets: " + sets + " | Reps: " + reps);
    }
}
