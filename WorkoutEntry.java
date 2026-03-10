public class WorkoutEntry
{
    //creating the classes variables
    private String workout;
    private int sets;
    private int reps;

    //constructor for the class
    public WorkoutEntry(String workout, int sets, int reps)
    {
        this.workout = workout;
        this.sets = sets;
        this.reps = reps;
    }

    //get methods for the class
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
        System.out.println("Workout: " + workout + " | Sets: " + sets + " | Reps: " + reps);
    }
}
